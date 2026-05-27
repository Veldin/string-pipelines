package com.veldin.stringpipelines.codepoints.utils.methods;


import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import com.veldin.stringpipelines.codepoints.utils.CodePointUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowerCaseTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("hello world", "hello world"),
                Arguments.of("Hello World", "hello world"),
                Arguments.of("HELLO WORLD", "hello world"),

                Arguments.of("A", "a"),
                Arguments.of("aBc🙂", "abc🙂"),
                Arguments.of("Hello worlD\r\n", "hello world\r\n"),

                // Empty
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void lowerCaseTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.lowerCase(buffer);

        assertEquals(expected, buffer.toString());
    }
}