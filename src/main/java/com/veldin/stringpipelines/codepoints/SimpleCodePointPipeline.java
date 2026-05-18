package com.veldin.stringpipelines.codepoints;

import java.util.List;

/**
 * Basic pipeline implementation that executes operations sequentially.
 */
public class SimpleCodePointPipeline extends AbstractCodePointPipeline {

    protected static SimpleCodePointPipeline Of(List<ICodePointOperation> operations) {
        return new SimpleCodePointPipeline(operations);
    }

    SimpleCodePointPipeline(List<ICodePointOperation> operations) {
        super(operations);
    }
}