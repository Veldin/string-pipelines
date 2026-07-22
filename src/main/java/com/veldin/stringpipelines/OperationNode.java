package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.CodePointBuffer;

public final class OperationNode {

    private final IStringOperation stringOperation;
    private final ICodePointOperation codePointOperation;

    public OperationNode(IStringOperation stringOperation,
                         ICodePointOperation codePointOperation) {
        this.stringOperation = stringOperation;
        this.codePointOperation = codePointOperation;
    }

    public static OperationNode of(IStringOperation op) {
        return new OperationNode(op, null);
    }

    public static OperationNode of(ICodePointOperation op) {
        return new OperationNode(null, op);
    }

    public boolean hasString() {
        return stringOperation != null;
    }

    public boolean hasCodePoint() {
        return codePointOperation != null;
    }

    public String apply(String input) {
        if (stringOperation == null) return input;
        return stringOperation.apply(input);
    }

    public void apply(CodePointBuffer buffer) {
        if (codePointOperation != null) {
            codePointOperation.apply(buffer);
        }
    }
}