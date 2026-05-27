package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.ECodePointOperation;
import com.veldin.stringpipelines.codepoints.SimpleCodePointPipeline;
import com.veldin.stringpipelines.strings.EStringOperation;
import com.veldin.stringpipelines.strings.SimpleStringPipeline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsPipelineBuilderTest {

    @Test
    void createPipelineWithOnlyStringOperations() {

        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(EStringOperation.DELETE_WHITESPACE)
                        .pipe(EStringOperation.CAPITALIZE)
                        .pipe(EStringOperation.CHOMP)
                        .build();

        String result = pipeline.apply("this is a Simple pipeline.\r");

        // Contains only 'String' operations, so this is a SimpleStringPipeline.
        assertInstanceOf(SimpleStringPipeline.class, pipeline);
        // 'This' is capitalized and the '\r' is chomped, and whitespace is removed.
        assertEquals("ThisisaSimplepipeline.", result);
    }

    @Test
    void createPipelineWithOnlyCodePointOperations() {

        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.REMOVE_WHITESPACE)
                        .pipe(ECodePointOperation.CAPITALIZE)
                        .pipe(ECodePointOperation.CHOMP)
                        .build();

        String result = pipeline.apply("this is a Simple pipeline.\r");

        // Contains only 'Code Point' operations, so this is a SimpleCodePointPipeline.
        assertInstanceOf(SimpleCodePointPipeline.class, pipeline);
        // 'This' is capitalized and the '\r' is chomped, and whitespace is removed.
        assertEquals("ThisisaSimplepipeline.", result);
    }


    @Test
    void createPipelineWithBothCodePointThenStringOperations() {

        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        // Three CodePoint operations
                        .pipe(ECodePointOperation.REMOVE_WHITESPACE)
                        .pipe(ECodePointOperation.CAPITALIZE)
                        .pipe(ECodePointOperation.CHOMP)
                        // Followed by a String operation.
                        .pipe(EStringOperation.CHOP)
                        .build();

        String result = pipeline.apply("this is a Simple pipeline.\r");

        // Contains 'Code Point' and a 'String' operations, so this is a SimpleStringPipeline.
        assertInstanceOf(SimpleStringPipeline.class, pipeline);

        /* Should look like this:
            pipeline = {SimpleStringPipeline}
                operations = {ImmutableCollections$List12}      //  size = 2
                0 = {SimpleCodePointPipeline}                   // Contains the 3 CodePointOperations
                1 = {SimpleStringPipeline}                      // Contains the single string Operation
         */

        // 'This' is capitalized and the '\r' is chomped, and whitespace is removed.
        // Then the '.' is chopped.
        assertEquals("ThisisaSimplepipeline", result);
    }
}