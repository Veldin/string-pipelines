package com.veldin.stringpipelines;

@FunctionalInterface
public interface IStringOperation {
    String apply(String input);
}