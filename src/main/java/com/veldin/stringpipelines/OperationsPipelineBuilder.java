package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.CodePointPipelineBuilder;
import com.veldin.stringpipelines.strings.StringPipelineBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationsPipelineBuilder {

    /**
     * Internal ordered collection of operations
     * to be added to the pipeline.
     */
    private final List<Operation> operations = new ArrayList<>();

    public OperationsPipelineBuilder pipe(Operation operation) {
        operations.add(operation);
        return this;
    }

    public OperationsPipelineBuilder pipe(Operation... operations) {
        this.operations.addAll(Arrays.asList(operations));
        return this;
    }

    public OperationsPipelineBuilder pipe(IStringOperation operation){
        operations.add(operation);
        return this;
    }

    public OperationsPipelineBuilder pipeCodePointOperation(ICodePointOperation operation){
        operations.add(operation);
        return this;
    }

    public AbstractPipeline build() {
        List<AbstractPipeline> optimized = compress(operations);
        if(optimized.size() == 1){
            return optimized.getFirst();
        }

        return new StringPipelineBuilder()
                .pipe(optimized)
                .build();
    }

    private List<AbstractPipeline> compress(List<Operation> input) {
        List<AbstractPipeline> result = new ArrayList<>();

        List<IStringOperation> stringBuffer = new ArrayList<>();
        List<ICodePointOperation> codeBuffer = new ArrayList<>();

        for (Operation op : input) {
            // Check for codepoints first
            if (op instanceof ICodePointOperation c) {

                // We encountered a codePoint, so flush string pipeline first
                if (!stringBuffer.isEmpty()) {
                    result.add(new StringPipelineBuilder()
                            .pipe(stringBuffer)
                            .build());
                    stringBuffer.clear();
                }

                codeBuffer.add(c);
            }
            // Check for strings
            else if (op instanceof IStringOperation s) {

                // We encountered a string, so flush codepoint pipeline first
                if (!codeBuffer.isEmpty()) {
                    result.add(new CodePointPipelineBuilder()
                            .pipe(codeBuffer)
                            .build());
                    codeBuffer.clear();
                }

                stringBuffer.add(s);
            }else{
                throw new IllegalStateException("Input should be either ICodePointOperation or IStringOperation.");
            }

        }

        // final flush both
        if (!codeBuffer.isEmpty()) {
            result.add(new CodePointPipelineBuilder()
                    .pipe(codeBuffer)
                    .build());
            codeBuffer.clear();
        }

        if (!stringBuffer.isEmpty()) {
            result.add(new StringPipelineBuilder()
                    .pipe(stringBuffer)
                    .build());
            stringBuffer.clear();
        }

        return result;
    }
}