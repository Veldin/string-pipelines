package com.veldin.stringpipelines;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EStringOperationTest {

    @Test
    @DisplayName("CAPITALIZE should behave the same as StringUtils.capitalize")
    void capitalizeShouldMatchStringUtils() {

        String input = "hello world";

        assertEquals(
                StringUtils.capitalize(input),
                EStringOperation.CAPITALIZE.apply(input)
        );
    }

    @Test
    @DisplayName("CHOMP should behave the same as StringUtils.chomp")
    void chompShouldMatchStringUtils() {

        String input = "hello world\n";

        assertEquals(
                StringUtils.chomp(input),
                EStringOperation.CHOMP.apply(input)
        );
    }

    @Test
    @DisplayName("CHOP should behave the same as StringUtils.chop")
    void chopShouldMatchStringUtils() {

        String input = "hello world";

        assertEquals(
                StringUtils.chop(input),
                EStringOperation.CHOP.apply(input)
        );
    }

    @Test
    @DisplayName("DEFAULT_STRING should behave the same as StringUtils.defaultString")
    void defaultStringShouldMatchStringUtils() {

        String input = null;

        assertEquals(
                StringUtils.defaultString(input),
                EStringOperation.DEFAULT_STRING.apply(input)
        );
    }

    @Test
    @DisplayName("DELETE_WHITESPACE should behave the same as StringUtils.deleteWhitespace")
    void deleteWhitespaceShouldMatchStringUtils() {

        String input = " a b  c   d ";

        assertEquals(
                StringUtils.deleteWhitespace(input),
                EStringOperation.DELETE_WHITESPACE.apply(input)
        );
    }

    @Test
    @DisplayName("GET_DIGITS should behave the same as StringUtils.getDigits")
    void getDigitsShouldMatchStringUtils() {

        String input = "abc123def456";

        assertEquals(
                StringUtils.getDigits(input),
                EStringOperation.GET_DIGITS.apply(input)
        );
    }

    @Test
    @DisplayName("LOWER_CASE should behave the same as StringUtils.lowerCase")
    void lowerCaseShouldMatchStringUtils() {

        String input = "HeLLo WoRLD";

        assertEquals(
                StringUtils.lowerCase(input),
                EStringOperation.LOWER_CASE.apply(input)
        );
    }

    @Test
    @DisplayName("NORMALIZE_SPACE should behave the same as StringUtils.normalizeSpace")
    void normalizeSpaceShouldMatchStringUtils() {

        String input = "   hello     world   ";

        assertEquals(
                StringUtils.normalizeSpace(input),
                EStringOperation.NORMALIZE_SPACE.apply(input)
        );
    }

    @Test
    @DisplayName("REVERSE should behave the same as StringUtils.reverse")
    void reverseShouldMatchStringUtils() {

        String input = "abcdef";

        assertEquals(
                StringUtils.reverse(input),
                EStringOperation.REVERSE.apply(input)
        );
    }

    @Test
    @DisplayName("STRIP should behave the same as StringUtils.strip")
    void stripShouldMatchStringUtils() {

        String input = "   hello world   ";

        assertEquals(
                StringUtils.strip(input),
                EStringOperation.STRIP.apply(input)
        );
    }

    @Test
    @DisplayName("STRIP_TO_EMPTY should behave the same as StringUtils.stripToEmpty")
    void stripToEmptyShouldMatchStringUtils() {

        String input = null;

        assertEquals(
                StringUtils.stripToEmpty(input),
                EStringOperation.STRIP_TO_EMPTY.apply(input)
        );
    }

    @Test
    @DisplayName("STRIP_TO_NULL should behave the same as StringUtils.stripToNull")
    void stripToNullShouldMatchStringUtils() {

        String input = "    ";

        assertEquals(
                StringUtils.stripToNull(input),
                EStringOperation.STRIP_TO_NULL.apply(input)
        );
    }

    @Test
    @DisplayName("SWAP_CASE should behave the same as StringUtils.swapCase")
    void swapCaseShouldMatchStringUtils() {

        String input = "Hello WORLD";

        assertEquals(
                StringUtils.swapCase(input),
                EStringOperation.SWAP_CASE.apply(input)
        );
    }

    @Test
    @DisplayName("TRIM should behave the same as StringUtils.trim")
    void trimShouldMatchStringUtils() {

        String input = "   hello world   ";

        assertEquals(
                StringUtils.trim(input),
                EStringOperation.TRIM.apply(input)
        );
    }

    @Test
    @DisplayName("TRIM_TO_EMPTY should behave the same as StringUtils.trimToEmpty")
    void trimToEmptyShouldMatchStringUtils() {

        String input = null;

        assertEquals(
                StringUtils.trimToEmpty(input),
                EStringOperation.TRIM_TO_EMPTY.apply(input)
        );
    }

    @Test
    @DisplayName("TRIM_TO_NULL should behave the same as StringUtils.trimToNull")
    void trimToNullShouldMatchStringUtils() {

        String input = "   ";

        assertEquals(
                StringUtils.trimToNull(input),
                EStringOperation.TRIM_TO_NULL.apply(input)
        );
    }

    @Test
    @DisplayName("UNCAPITALIZE should behave the same as StringUtils.uncapitalize")
    void uncapitalizeShouldMatchStringUtils() {

        String input = "Hello World";

        assertEquals(
                StringUtils.uncapitalize(input),
                EStringOperation.UNCAPITALIZE.apply(input)
        );
    }

    @Test
    @DisplayName("UPPER_CASE should behave the same as StringUtils.upperCase")
    void upperCaseShouldMatchStringUtils() {

        String input = "HeLLo WoRLD";

        assertEquals(
                StringUtils.upperCase(input),
                EStringOperation.UPPER_CASE.apply(input)
        );
    }
}