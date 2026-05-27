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

class StripTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("hello  world", "hello  world"), // Double spaces in centre stay

                Arguments.of("hello  world ", "hello  world"), // Space at the end removed
                Arguments.of("hello  world  ", "hello  world"), // Spaces at the end removed

                Arguments.of(" hello  world", "hello  world"), // Single space at the begin removed
                Arguments.of("  hello  world", "hello  world"), // Double Spaces at the begin removed

                Arguments.of("  hello  world  ", "hello  world"), // Double Spaces in begin and end get removed
                Arguments.of(" \n hello  world   ", "hello  world"), // \n at start and spaces end removed

                Arguments.of(" \n ", ""), // Gets emptied

                // Empty
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void stripTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.strip(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.STRIP)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}