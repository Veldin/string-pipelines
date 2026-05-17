package com.veldin.codepointpipelines;

import java.util.List;

public abstract class AbstractCodePointPipeline implements ICodePointOperation {

    protected final List<ICodePointOperation> operations;

    protected AbstractCodePointPipeline(List<ICodePointOperation> operations) {
        this.operations = List.copyOf(operations);
    }

    @Override
    public int[] apply(int[] input) {

        int[] value = input;

        for (ICodePointOperation op : operations) {
            value = op.apply(value);
        }

        return value;
    }

    public String apply(String input) {
        int[] value = input.codePoints().toArray();

        for (ICodePointOperation op : operations) {
            value = op.apply(value);
        }

        return new String(value, 0, value.length);
    }
}