package com.veldin.stringpipelines.codepoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodePointPipelineBuilder {

    /**
     * Internal ordered collection of operations
     * to be added to the pipeline.
     */
    private final List<ICodePointOperation> operations = new ArrayList<>();

    /**
     * Adds a single operation.
     *
     * @param operation operation to append
     * @return current builder instance
     */
    public CodePointPipelineBuilder pipe(ICodePointOperation operation) {
        operations.add(operation);
        return this;
    }

    /**
     * Adds multiple operations.
     *
     * @param operations operations to append
     * @return current builder instance
     */
    public CodePointPipelineBuilder pipe(ICodePointOperation... operations) {
        this.operations.addAll(Arrays.asList(operations));
        return this;
    }

    /**
     * Adds all operations.
     *
     * @param operations operations to append
     * @return current builder instance
     */
    public CodePointPipelineBuilder pipe(List<? extends ICodePointOperation> operations) {
        this.operations.addAll(operations);
        return this;
    }

    /**
     * Builds SimpleCodePointPipeline
     */
    public AbstractCodePointPipeline build() {

        List<ICodePointOperation> ops = List.copyOf(operations);

        return SimpleCodePointPipeline.Of(ops);
    }
}