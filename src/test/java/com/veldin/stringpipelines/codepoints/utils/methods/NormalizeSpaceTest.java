package com.veldin.stringpipelines.codepoints.utils.methods;


import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import com.veldin.stringpipelines.codepoints.utils.CodePointUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalizeSpaceTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("hello  world", "hello world"), // Double spaces in centre become one space
                Arguments.of("hello  world ", "hello world"), // Space at the end
                Arguments.of("hello  world  ", "hello world"), // Spaces at the end
                Arguments.of("  hello  world  ", "hello world"), // Spaces in begin and end get removed

                Arguments.of(" \n ", ""), // Gets emptied

                // Empty
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void normalizeSpaceTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.normalizeSpace(buffer);

        assertEquals(expected, buffer.toString());
    }
}