package com.veldin.codepointpipelines.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodePointUtilsTest {

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void capitalizeTest() {
        String input = "hello world";
        String capitalize = StringUtils.capitalize(input);

        assertEquals(
                capitalize,
                codePointsToString(CodePointUtils.capitalize(input.codePoints().toArray()))
        );
    }

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void chompTest() {
        String input = "hello world\r\n";
        String chomp =  StringUtils.chomp(input);

        assertEquals(
                chomp,
                codePointsToString(CodePointUtils.chomp(input.codePoints().toArray()))
        );
    }

    @Test
    // More tests todo to make sure input/output is properly mimic.
    void chopTest() {
        String input = "hello world";
        String chomp =  StringUtils.chop(input);

        assertEquals(
                chomp,
                codePointsToString(CodePointUtils.chop(input.codePoints().toArray()))
        );
    }

    private String codePointsToString(int[] input) {
        return new String(input, 0, input.length);
    }
}