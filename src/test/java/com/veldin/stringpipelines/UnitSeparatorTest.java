package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.ECodePointOperation;
import com.veldin.stringpipelines.codepoints.SimpleCodePointPipeline;
import com.veldin.stringpipelines.strings.SimpleStringPipeline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitSeparatorTest {

    public char UNIT_SEPARATOR_CHAR = '\u001F';

    @Test
    void removeWhiteSpaceRemovesSeparator() {

        /*
            U+001C FILE SEPARATOR
            U+001D GROUP SEPARATOR
            U+001E RECORD SEPARATOR
            U+001F UNIT SEPARATOR
         */

        // Separator is Whitespace
        assertTrue(Character.isWhitespace(UNIT_SEPARATOR_CHAR));

        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.REMOVE_WHITESPACE)
                        .build();

        String input = "" + UNIT_SEPARATOR_CHAR;
        String result = pipeline.apply(input);

        // Contains only 'Code Point' operations, so this is a SimpleCodePointPipeline.
        assertInstanceOf(SimpleCodePointPipeline.class, pipeline);

        // UNIT_SEPARATOR_CHAR removed because it is considered whitespace.
        assertEquals("", result);
    }

    @Test
    void endsUpInFinalString() {
        AbstractPipeline pipeline =
                new OperationsPipelineBuilder().build();

        String input = "" + UNIT_SEPARATOR_CHAR;
        String result = pipeline.apply(input);

        // Is empty, so it's a SimpleStringPipeline.
        assertInstanceOf(SimpleStringPipeline.class, pipeline);
        assertEquals(input, result);
    }

    @Test
    void removeRemoveISOControlsRemovesSeperator() {
        assertTrue(Character.isWhitespace(UNIT_SEPARATOR_CHAR));

        AbstractPipeline pipeline =
                new OperationsPipelineBuilder()
                        .pipe(ECodePointOperation.REMOVE_ISO_CONTROLS)
                        .build();

        String input = "" + UNIT_SEPARATOR_CHAR;
        String result = pipeline.apply(input);

        // Contains only 'Code Point' operations, so this is a SimpleCodePointPipeline.
        assertInstanceOf(SimpleCodePointPipeline.class, pipeline);

        // UNIT_SEPARATOR_CHAR removed because it is considered an ISO CONTROL.
        assertEquals("", result);
    }
}
