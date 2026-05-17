package com.veldin.codepointpipelines;

import org.junit.jupiter.api.Test;

import static com.veldin.codepointpipelines.ECodePointOperation.CAPITALIZE;
import static com.veldin.codepointpipelines.ECodePointOperation.CHOMP;
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

}