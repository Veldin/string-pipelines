package com.veldin.stringpipelines.codepoints.utils.methods;


import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import com.veldin.stringpipelines.codepoints.utils.CodePointUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalizeTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("abc", "Abc"),
                Arguments.of("cdc", "Cdc"),

                // Starts with a space
                Arguments.of(" cdc", " cdc"),

                // Two words
                Arguments.of("cdc cdc", "Cdc cdc"),

                // Starts with number or emoji
                Arguments.of("123abc🙂DEF", "123abc🙂DEF"),
                Arguments.of("🙂def", "🙂def")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void capitalizeTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.capitalize(buffer);

        assertEquals(expected, buffer.toString());
    }
}