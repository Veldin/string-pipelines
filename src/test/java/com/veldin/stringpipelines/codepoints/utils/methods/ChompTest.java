package com.veldin.stringpipelines.codepoints.utils.methods;


import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import com.veldin.stringpipelines.codepoints.utils.CodePointUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChompTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("hello world\r\n", "hello world"),
                Arguments.of("hello world\r", "hello world"),
                Arguments.of("hello world\n", "hello world"),

                // Removes last \n
                Arguments.of("hello world\r\n\n", "hello world\r\n"),

                // Removes last \r\n
                Arguments.of("hello world\r\n\r\n", "hello world\r\n"),

                // Does nothing (ends with a)
                Arguments.of("hello world\na", "hello world\na")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void chompTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.chomp(buffer);

        assertEquals(expected, buffer.toString());
    }
}