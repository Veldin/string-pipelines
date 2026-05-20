package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import com.veldin.stringpipelines.codepoints.ECodePointOperation;
import com.veldin.stringpipelines.strings.EStringOperation;

public enum EOperation implements ICodePointOperation, IStringOperation, Operation {

    CAPITALIZE(
            ECodePointOperation.CAPITALIZE,
            EStringOperation.CAPITALIZE
    ),

    CHOMP(
            ECodePointOperation.CHOMP,
            EStringOperation.CHOMP
    ),

    CHOP(
            ECodePointOperation.CHOP,
            EStringOperation.CHOP
    ),

    DELETE_WHITESPACE(
            ECodePointOperation.DELETE_WHITESPACE,
            EStringOperation.DELETE_WHITESPACE
    ),

    GET_DIGITS(
            ECodePointOperation.GET_DIGITS,
            EStringOperation.GET_DIGITS
    ),

    LOWER_CASE(
            ECodePointOperation.LOWER_CASE,
            EStringOperation.LOWER_CASE
    ),

    NORMALIZE_SPACE(
            ECodePointOperation.NORMALIZE_SPACE,
            EStringOperation.NORMALIZE_SPACE
    ),

    REVERSE(
            ECodePointOperation.REVERSE,
            EStringOperation.REVERSE
    ),

    STRIP(
            ECodePointOperation.STRIP,
            EStringOperation.STRIP
    );

    private final ICodePointOperation codePointOperation;
    private final IStringOperation stringOperation;

    EOperation(
            ICodePointOperation codePointOperation,
            IStringOperation stringOperation
    ) {
        this.codePointOperation = codePointOperation;
        this.stringOperation = stringOperation;
    }

    public ICodePointOperation getCodePointOperation() {
        return codePointOperation;
    }

    public IStringOperation getStringOperation() {
        return stringOperation;
    }

    @Override
    public void apply(CodePointBuffer buffer) {
        this.codePointOperation.apply(buffer);
    }

    @Override
    public String apply(String input) {
        return this.stringOperation.apply(input);
    }
}