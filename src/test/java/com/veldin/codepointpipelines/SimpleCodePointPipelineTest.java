package com.veldin.codepointpipelines;

import org.junit.jupiter.api.Test;

import static com.veldin.codepointpipelines.ECodePointOperation.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCodePointPipelineTest {

    @Test
    void createBasicExample() {

        AbstractCodePointPipeline simpleCodePointPipeline =
                new CodePointPipelineBuilder()
                        .pipe(CAPITALIZE)
                        .pipe(CHOMP)
                        .build();

        String result = simpleCodePointPipeline.apply("this is a Simple pipeline.\r");

        assertInstanceOf(AbstractCodePointPipeline.class, simpleCodePointPipeline);
        assertEquals("This is a Simple pipeline.", result); // 'This' is capitalized.
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