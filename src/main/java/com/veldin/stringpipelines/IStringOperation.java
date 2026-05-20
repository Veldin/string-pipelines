package com.veldin.stringpipelines;

/**
 * Represents a single string transformation step.
 * Implementations should (ideally) be pure functions.
 */
@FunctionalInterface
public non-sealed interface IStringOperation extends Operation {

    /**
     * Applies a transformation to the input string.
     *
     * @param input source string
     * @return the transformed string
     */
    String apply(String input);
}