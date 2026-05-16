package com.veldin.stringpipelines;

import java.util.List;

public class SimpleStringPipeline extends AbstractStringPipeline {

    protected static SimpleStringPipeline Of(List<IStringOperation> operations) {
        return new SimpleStringPipeline(operations);
    }

    private SimpleStringPipeline(List<IStringOperation> operations) {
        super(operations);
    }
}