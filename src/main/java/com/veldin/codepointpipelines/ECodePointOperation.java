package com.veldin.codepointpipelines;

import com.veldin.codepointpipelines.utils.CodePointUtils;

public enum ECodePointOperation implements ICodePointOperation {

    CAPITALIZE(CodePointUtils::capitalize),
    CHOMP(CodePointUtils::chomp),
    CHOP(CodePointUtils::chop);

    private final ICodePointOperation operation;

    ECodePointOperation(ICodePointOperation operation) {
        this.operation = operation;
    }

    @Override
    public int[] apply(int[] input) {
        return operation.apply(input);
    }
}