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

class ChopTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("hello world", "hello worl"),
                Arguments.of("a", ""),
                Arguments.of("abc🙂", "abc"),
                Arguments.of("abc🙂abc", "abc🙂ab"),

                // Empty
                Arguments.of("", ""),

                // \r\n
                Arguments.of("hello world\r\n", "hello world"), // Removes both \r\n in one call
                Arguments.of("hello world\r", "hello world"),
                Arguments.of("hello world\n", "hello world"),

                // Removes last \n
                Arguments.of("hello world\r\n\n", "hello world\r\n"),

                // Removes last \r\n
                Arguments.of("hello world\r\n\r\n", "hello world\r\n"),

                Arguments.of("hello world\na", "hello world\n")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void chopTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.chop(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.CHOP)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}