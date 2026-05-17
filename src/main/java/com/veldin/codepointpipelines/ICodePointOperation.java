package com.veldin.codepointpipelines;

/**
 * Represents a single codePoint transformation step.
 * Implementations should (ideally) be pure functions.
 */
@FunctionalInterface
public interface ICodePointOperation {

    /**
     * Applies a transformation to the input array.
     * (The input array might change in the operation.)
     *
     * @param input source array
     * @return the transformed array
     */
    int[] apply(int[] input);
}