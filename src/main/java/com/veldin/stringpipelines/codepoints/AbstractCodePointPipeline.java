package com.veldin.stringpipelines.codepoints;

import java.util.List;

public abstract class AbstractCodePointPipeline implements ICodePointOperation {

    protected final List<ICodePointOperation> operations;

    protected AbstractCodePointPipeline(List<ICodePointOperation> operations) {
        this.operations = List.copyOf(operations);
    }

    @Override
    public void apply(CodePointBuffer buffer) {
        for (ICodePointOperation op : operations) {
            op.apply(buffer);
        }
    }

    public String apply(String input) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        for (ICodePointOperation op : operations) {
            op.apply(buffer);
        }

        return buffer.toString();
    }
}