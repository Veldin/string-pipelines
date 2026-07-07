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

class ToXmlEntitiesTest {

    static Stream<Arguments> cases() {
        return Stream.of(

                // Empty
                Arguments.of("", ""),

                // No escaping needed
                Arguments.of("hello world", "hello world"),
                Arguments.of("123456789", "123456789"),

                // Individual entities
                Arguments.of("&", "&amp;"),
                Arguments.of("<", "&lt;"),
                Arguments.of(">", "&gt;"),
                Arguments.of("\"", "&quot;"),
                Arguments.of("'", "&apos;"),

                // Typical XML content
                Arguments.of(
                        "<tag>value</tag>",
                        "&lt;tag&gt;value&lt;/tag&gt;"
                ),

                Arguments.of(
                        "<tag attr=\"value\">",
                        "&lt;tag attr=&quot;value&quot;&gt;"
                ),

                Arguments.of(
                        "Tom & Jerry",
                        "Tom &amp; Jerry"
                ),

                // Mixed entities
                Arguments.of(
                        "<root attr=\"Tom & Jerry\">'test'</root>",
                        "&lt;root attr=&quot;Tom &amp; Jerry&quot;&gt;&apos;test&apos;&lt;/root&gt;"
                ),

                // Consecutive entities
                Arguments.of(
                        "<<&&>>",
                        "&lt;&lt;&amp;&amp;&gt;&gt;"
                ),

                // Whitespace preserved
                Arguments.of(
                        " \n <tag> \t ",
                        " \n &lt;tag&gt; \t "
                ),

                // Unicode preserved
                Arguments.of(
                        "😀 < 世界 & café",
                        "😀 &lt; 世界 &amp; café"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void toXmlEntitiesTest(String input, String expected) {
        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());

        CodePointUtils.toXmlEntities(buffer);

        assertEquals(expected, buffer.toString());
    }

    @Test
    void pipelineStepTest() {

        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.TO_XML_ENTITIES)
                        .build();

        cases().forEach(args -> {
            String input = (String) args.get()[0];
            String expected = (String) args.get()[1];

            assertEquals(expected, pipeline.apply(input));
        });
    }
}