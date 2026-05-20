package com.veldin.stringpipelines.strings;

import com.veldin.stringpipelines.AbstractPipeline;
import com.veldin.stringpipelines.IStringOperation;

import java.util.List;

public abstract class AbstractStringPipeline extends AbstractPipeline implements IStringOperation {

    protected final List<IStringOperation> operations;

    protected AbstractStringPipeline(List<IStringOperation> operations) {
        this.operations = List.copyOf(operations);
    }

    @Override
    public String apply(String input) {

        String value = input;

        for (IStringOperation op : operations) {
            value = op.apply(value);
        }

        return value;
    }
}