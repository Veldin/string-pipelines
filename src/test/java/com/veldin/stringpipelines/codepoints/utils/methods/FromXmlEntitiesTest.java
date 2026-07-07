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

class FromXmlEntitiesTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // Empty
                Arguments.of("", ""),

                // No entities
                Arguments.of("hello world", "hello world"),
                Arguments.of("123456", "123456"),

                // Individual entities
                Arguments.of("&amp;", "&"),
                Arguments.of("&lt;", "<"),
                Arguments.of("&gt;", ">"),
                Arguments.of("&quot;", "\""),
                Arguments.of("&apos;", "'"),

                // Mixed entities
                Arguments.of("&lt;tag&gt;", "<tag>"),
                Arguments.of("Jak &amp; Dexter", "Jak & Dexter"),
                Arguments.of("&quot;hello&quot;", "\"hello\""),
                Arguments.of("&apos;hello&apos;", "'hello'"),

                // Consecutive entities
                Arguments.of("&lt;&gt;&amp;&quot;&apos;", "<>&\"'"),

                // Beginning / middle / end
                Arguments.of("&amp;hello", "&hello"),
                Arguments.of("hello&amp;", "hello&"),
                Arguments.of("hello&amp;world", "hello&world"),

                // Unknown entities should remain unchanged
                Arguments.of("&unknown;", "&unknown;"),
                Arguments.of("&aq;", "&aq;"),
                Arguments.of("&am;", "&am;"),
                Arguments.of("&amx;", "&amx;"),

                // Incomplete entities
                Arguments.of("&", "&"),
                Arguments.of("&a", "&a"),
                Arguments.of("&am", "&am"),
                Arguments.of("&amp", "&amp"),
                Arguments.of("&l", "&l"),
                Arguments.of("&g", "&g"),
                Arguments.of("&quo", "&quo"),
                Arguments.of("&apo", "&apo"),

                // Similar but invalid
                Arguments.of("&amps;", "&amps;"),
                Arguments.of("&ltt;", "&ltt;"),
                Arguments.of("&gtt;", "&gtt;"),
                Arguments.of("&quotes;", "&quotes;"),
                Arguments.of("&aposx;", "&aposx;"),

                // Multiple unknowns
                Arguments.of("&foo;&bar;&baz;", "&foo;&bar;&baz;"),

                // Mixed valid and invalid
                Arguments.of("&lt;&foo;&gt;", "<&foo;>"),
                Arguments.of("&amp;&xyz;&amp;", "&&xyz;&"),

                // Real XML snippet
                Arguments.of(
                        "&lt;person name=&quot;John &amp; Jane&quot;&gt;Hello&lt;/person&gt;",
                        "<person name=\"John & Jane\">Hello</person>"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void fromXmlEntitiesTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.fromXmlEntities(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {
        // Create the pipeline
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.FROM_XML_ENTITIES)
                        .build();

        // Apply the strings
        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}