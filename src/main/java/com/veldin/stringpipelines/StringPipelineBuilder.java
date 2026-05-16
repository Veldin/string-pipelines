package com.veldin.stringpipelines;

import java.util.*;

public class StringPipelineBuilder {

    /**
     * Internal ordered collection of operations
     * to be added to the pipeline.
     */
    private final List<IStringOperation> operations = new ArrayList<>();

    /**
     * Whether the pipeline should use caching.
     */
    private boolean cached = false;

    /**
     * Enables cache for the generated pipeline.
     *
     * @return current builder instance
     */
    public StringPipelineBuilder cached() {
        this.cached = true;
        return this;
    }

    /**
     * Disables cache for the generated pipeline.
     *
     * @return current builder instance
     */
    public StringPipelineBuilder simple() {
        this.cached = false;
        return this;
    }

    /**
     * Adds a single operation.
     *
     * @param operation operation to append
     * @return current builder instance
     */
    public StringPipelineBuilder pipe(IStringOperation operation) {
        operations.add(operation);
        return this;
    }

    /**
     * Adds multiple operations.
     *
     * @param operations operations to append
     * @return current builder instance
     */
    public StringPipelineBuilder pipe(IStringOperation... operations) {
        this.operations.addAll(Arrays.asList(operations));
        return this;
    }

    /**
     * Adds all operations.
     *
     * @param operations operations to append
     * @return current builder instance
     */
    public StringPipelineBuilder pipe(List<? extends IStringOperation> operations) {
        this.operations.addAll(operations);
        return this;
    }

    /**
     * Builds either:
     * - SimpleStringPipeline
     * - StringPipeline (cached)
     */
    public AbstractStringPipeline build() {

        validateNoCycles();

        List<IStringOperation> ops = List.copyOf(operations);

        if (cached) {
            return CachedStringPipeline.Of(ops);
        }

        return SimpleStringPipeline.Of(ops);
    }

    private void validateNoCycles() {

        Set<String> visiting = new HashSet<>();
        Set<String> visited = new HashSet<>();

        for (IStringOperation op : operations) {
            detectCycle(op, visiting, visited);
        }
    }

    private void detectCycle(IStringOperation op,
                             Set<String> visiting,
                             Set<String> visited) {

        String id = identity(op);

        if (visited.contains(id)) {
            return;
        }

        if (visiting.contains(id)) {
            throw new IllegalStateException(
                    "Cycle detected in pipeline: " + id
            );
        }

        visiting.add(id);

        // recurse into nested pipelines
        if (op instanceof AbstractStringPipeline pipeline) {

            for (IStringOperation inner : pipeline.getOperations()) {
                detectCycle(inner, visiting, visited);
            }
        }

        visiting.remove(id);
        visited.add(id);
    }

    private String identity(IStringOperation op) {
        return op.getClass().getName()
                + "@"
                + System.identityHashCode(op);
    }
}