package com.veldin.stringpipelines;

import java.util.List;

public abstract class AbstractStringPipeline implements IStringOperation {

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