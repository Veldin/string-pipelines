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

class ToBase64Test {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("hello world", "aGVsbG8gd29ybGQ="),

                Arguments.of("hello  world ", "aGVsbG8gIHdvcmxkIA=="),
                Arguments.of("hello  world  ", "aGVsbG8gIHdvcmxkICA="),

                Arguments.of(" hello  world", "IGhlbGxvICB3b3JsZA=="),
                Arguments.of("  hello  world", "ICBoZWxsbyAgd29ybGQ="),

                Arguments.of("  hello  world  ", "ICBoZWxsbyAgd29ybGQgIA=="),
                Arguments.of(" \n hello  world   ", "IAogaGVsbG8gIHdvcmxkICAg"),

                Arguments.of(" \n ", "IAog"),

                // Empty
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void toBase64Test(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.toBase64(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.TO_BASE64)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}