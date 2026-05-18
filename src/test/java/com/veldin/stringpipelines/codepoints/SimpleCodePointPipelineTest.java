package com.veldin.stringpipelines.codepoints;

import org.junit.jupiter.api.Test;

import static com.veldin.stringpipelines.codepoints.ECodePointOperation.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCodePointPipelineTest {

    @Test
    void createBasicExample() {

        AbstractCodePointPipeline simpleCodePointPipeline =
                new CodePointPipelineBuilder()
                        .pipe(DELETE_WHITESPACE)
                        .pipe(CAPITALIZE)
                        .pipe(CHOMP)
                        .build();

        String result = simpleCodePointPipeline.apply("this is a Simple pipeline.\r");

        assertInstanceOf(AbstractCodePointPipeline.class, simpleCodePointPipeline);
        // 'This' is capitalized and the '\r' is chomped, and whitespace is removed.
        assertEquals("ThisisaSimplepipeline.", result);
    }

    @Test
    void createBasicExampleChainChop() {

        AbstractCodePointPipeline simpleCodePointPipeline =
                new CodePointPipelineBuilder()
                        .pipe(CHOP)
                        .pipe(CHOP)
                        .pipe(CHOP)
                        .build();

        String result = simpleCodePointPipeline.apply("1234567");

        assertInstanceOf(AbstractCodePointPipeline.class, simpleCodePointPipeline);
        assertEquals("1234", result);
    }

}