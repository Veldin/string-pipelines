package com.veldin.stringpipelines.codepoints.utils.methods;


import com.veldin.stringpipelines.AbstractPipeline;
import com.veldin.stringpipelines.OperationsPipelineBuilder;
import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import com.veldin.stringpipelines.codepoints.ECodePointOperation;
import com.veldin.stringpipelines.codepoints.utils.CodePointUtils;
import org.junit.jupiter.api.Test;
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

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.LOWER_CASE)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}