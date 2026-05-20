package com.veldin.stringpipelines.strings;

import com.veldin.stringpipelines.IStringOperation;

import java.util.List;

/**
 * Basic pipeline implementation that executes
 * operations sequentially without caching.
 */
public class SimpleStringPipeline extends AbstractStringPipeline {

    protected static SimpleStringPipeline Of(List<IStringOperation> operations) {
        return new SimpleStringPipeline(operations);
    }

    private SimpleStringPipeline(List<IStringOperation> operations) {
        super(operations);
    }
}