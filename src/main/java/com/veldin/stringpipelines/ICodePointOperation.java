package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.CodePointBuffer;

/**
 * Represents a single codePoint transformation step.
 * Implementations should (ideally) be pure functions.
 */
@FunctionalInterface
public non-sealed interface ICodePointOperation extends Operation {

    public int UNIT_SEPARATOR_CODE_POINT = 0x1F;      // 31
    public char UNIT_SEPARATOR_CHAR = '\u001F';

    /**
     * Applies a transformation to the input buffer.
     *
     * @param buffer source buffer
     */
    void apply(CodePointBuffer buffer);
}