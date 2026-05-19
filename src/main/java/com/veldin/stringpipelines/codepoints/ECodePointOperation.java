package com.veldin.stringpipelines.codepoints;

import com.veldin.stringpipelines.strings.utils.CodePointUtils;

public enum ECodePointOperation implements ICodePointOperation {

    CAPITALIZE(CodePointUtils::capitalize),
    CHOMP(CodePointUtils::chomp),
    CHOP(CodePointUtils::chop),

    DELETE_WHITESPACE(CodePointUtils::deleteWhitespace),
    GET_DIGITS(CodePointUtils::getDigits);

    private final ICodePointOperation operation;

    ECodePointOperation(ICodePointOperation operation) {
        this.operation = operation;
    }

    @Override
    public void apply(CodePointBuffer buffer) {
        operation.apply(buffer);
    }
}