package com.veldin.stringpipelines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Enables caching for the generated pipeline.
     *
     * @return current builder instance
     */
    public StringPipelineBuilder cached() {
        this.cached = true;
        return this;
    }

    /**
     * Disables caching for the generated pipeline.
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
     * - CachedStringPipeline
     */
    public AbstractStringPipeline build() {

        List<IStringOperation> ops = List.copyOf(operations);

        if (cached) {
            return CachedStringPipeline.Of(ops);
        }

        return SimpleStringPipeline.Of(ops);
    }
}