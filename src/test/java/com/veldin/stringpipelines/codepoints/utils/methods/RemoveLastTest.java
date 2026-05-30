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

class RemoveLastTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Basic cases
                Arguments.of("abc", "ab"),
                Arguments.of("cdc", "cd"),

                // Ends with a space
                Arguments.of("cdc ", "cdc"),

                // Two words
                Arguments.of("cdc cdc", "cdc cd"),

                // Starts with number or emoji
                Arguments.of("123abc🙂DEF", "123abc🙂DE"),
                Arguments.of("🙂def", "🙂de"),

                // Single character
                Arguments.of("a", ""),

                // Empty
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void removeLastTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.removeLast(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.REMOVE_LAST)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}