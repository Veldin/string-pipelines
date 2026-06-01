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

class FromBase64Test {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("aGVsbG8gd29ybGQ=", "hello world"),

                Arguments.of("aGVsbG8gIHdvcmxkIA==", "hello  world " ),
                Arguments.of("aGVsbG8gIHdvcmxkICA=", "hello  world  " ),

                Arguments.of("IGhlbGxvICB3b3JsZA==", " hello  world" ),
                Arguments.of("ICBoZWxsbyAgd29ybGQ=", "  hello  world" ),

                Arguments.of("ICBoZWxsbyAgd29ybGQgIA==", "  hello  world  " ),
                Arguments.of("IAogaGVsbG8gIHdvcmxkICAg", " \n hello  world   " ),

                Arguments.of("IAog", " \n " ),

                // Empty
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void fromBase64Test(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.fromBase64(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.FROM_BASE64)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}