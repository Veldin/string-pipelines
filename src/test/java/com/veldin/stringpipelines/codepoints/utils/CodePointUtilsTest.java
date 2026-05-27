package com.veldin.stringpipelines.codepoints.utils;

import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodePointUtilsTest {

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void capitalizeTest() {
        String input = "hello world";
        String capitalize = StringUtils.capitalize(input);

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.capitalize(buffer);

        assertEquals(
                capitalize,
                buffer.toString()
        );
    }

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void chompTest() {
        String input = "hello world\r\n";
        String chomp =  StringUtils.chomp(input);

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.chomp(buffer);

        assertEquals(
                chomp,
                buffer.toString()
        );
    }

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void chopTest() {
        String input = "hello world";
        String chop =  StringUtils.chop(input);

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.chop(buffer);

        assertEquals(
                chop,
                buffer.toString()
        );
    }

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void deleteWhitespaceTest() {
        String input = " hello wor ld ";
        String deleteWhitespace =  StringUtils.deleteWhitespace(input);

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.deleteWhitespace(buffer);

        assertEquals(
                deleteWhitespace,
                buffer.toString()
        );
    }

    @Test
    void removeWhitespaceTest() {
        String input = "123abc123";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDigits(buffer);

        String output = buffer.toString();

        // Ensure no digit remains
        for (int i = 0; i < output.length(); i++) {
            char ch = output.charAt(i);
            assertFalse(Character.isDigit(ch));
        }
    }

    @Test
    void keepWhitespaceTest() {
        String input = "123abc123";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDigits(buffer);

        String output = buffer.toString();

        // Ensure no digit remains
        for (int i = 0; i < output.length(); i++) {
            char ch = output.charAt(i);
            assertTrue(Character.isDigit(ch));
        }
    }
}