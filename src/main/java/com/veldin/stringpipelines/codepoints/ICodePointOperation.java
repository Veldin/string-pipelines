package com.veldin.stringpipelines.codepoints;

/**
 * Represents a single codePoint transformation step.
 * Implementations should (ideally) be pure functions.
 */
@FunctionalInterface
public interface ICodePointOperation {

    /**
     * Applies a transformation to the input buffer.
     *
     * @param buffer source buffer
     */
    void apply(CodePointBuffer buffer);
}