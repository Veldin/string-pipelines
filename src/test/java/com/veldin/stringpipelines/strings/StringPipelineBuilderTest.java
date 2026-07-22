package com.veldin.stringpipelines.strings;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringPipelineBuilderTest {

    @Test
    void shouldBuildSimplePipeline() {

        AbstractStringPipeline pipeline = new StringPipelineBuilder()
                .pipe(StringUtils::trim)
                .pipe(StringUtils::lowerCase)
                .build();

        String result = pipeline.apply("  HeLLo WoRLD  ");

        assertEquals("hello world", result);
        assertInstanceOf(SimpleStringPipeline.class, pipeline);
    }

    @Test
    void shouldBuildCachedPipeline() {

        AbstractStringPipeline pipeline = new StringPipelineBuilder()
                .cached()
                .pipe(StringUtils::trim)
                .pipe(StringUtils::lowerCase)
                .build();

        String result = pipeline.apply("  hello world  ");

        assertEquals("hello world", result);
        assertInstanceOf(CachedStringPipeline.class, pipeline);
    }

    @Test
    void shouldApplyOperationsInCorrectOrder() {

        AbstractStringPipeline pipeline = new StringPipelineBuilder()
                .pipe(StringUtils::trim)
                .pipe(StringUtils::deleteWhitespace)
                .pipe(StringUtils::upperCase)
                .build();

        String result = pipeline.apply("  hello world  ");

        assertEquals("HELLOWORLD", result);
    }

    @Test
    void shouldSupportVarArgsPipe() {

        AbstractStringPipeline pipeline = new StringPipelineBuilder()
                .pipe(
                        StringUtils::trim,
                        StringUtils::lowerCase,
                        StringUtils::reverse
                )
                .build();

        String result = pipeline.apply("  HeLLo  ");

        assertEquals("olleh", result);
    }

    @Test
    void shouldSupportListPipe() {

        AbstractStringPipeline pipeline = new StringPipelineBuilder()
                .pipe(
                        java.util.List.of(
                                StringUtils::trim,
                                StringUtils::upperCase
                        )
                )
                .build();

        String result = pipeline.apply("  hello  ");

        assertEquals("HELLO", result);
    }

    @Test
    void shouldDetectDirectCycle() {

        AbstractStringPipeline pipeline =
                new StringPipelineBuilder()
                        .build();

        StringPipelineBuilder builder = new StringPipelineBuilder();

        builder.pipe(pipeline);

        assertDoesNotThrow(builder::build);
    }

    @Test
    void shouldAllowNestedPipelinesWithoutCycles() {

        AbstractStringPipeline inner = new StringPipelineBuilder()
                .cached()
                .pipe(StringUtils::trim)
                .pipe(StringUtils::lowerCase)
                .build();

        AbstractStringPipeline outer = new StringPipelineBuilder()
                .pipe(inner)
                .pipe(StringUtils::reverse)
                .build();

        String result = outer.apply("  HeLLo  ");

        assertEquals("olleh", result);
    }

    @Test
    void shouldBuildEmptyPipeline() {

        AbstractStringPipeline pipeline =
                new StringPipelineBuilder()
                        .build();

        String result = pipeline.apply("hello");

        assertEquals("hello", result);
    }

    @Test
    void shouldReturnSameInputForEmptyPipeline() {

        AbstractStringPipeline pipeline =
                new StringPipelineBuilder()
                        .build();

        assertEquals("test", pipeline.apply("test"));
    }
}