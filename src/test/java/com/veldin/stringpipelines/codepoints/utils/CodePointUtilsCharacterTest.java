package com.veldin.stringpipelines.codepoints.utils;

import com.veldin.stringpipelines.CharacterHelper;
import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class CodePointUtilsCharacterTest {

    @Test
    void removeValidCodePointsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeValidCodePoints(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isValidCodePoint(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepValidCodePointsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepValidCodePoints(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isValidCodePoint(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeValidCodePointsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeValidCodePointsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isValidCodePoint(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepValidCodePointsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepValidCodePointsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isValidCodePoint(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeBmpCodePointsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBmpCodePoints(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isBmpCodePoint(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepBmpCodePointsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBmpCodePoints(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isBmpCodePoint(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeBmpCodePointsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBmpCodePointsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isBmpCodePoint(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepBmpCodePointsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBmpCodePointsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isBmpCodePoint(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeSupplementaryCodePointsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSupplementaryCodePoints(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isSupplementaryCodePoint(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepSupplementaryCodePointsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSupplementaryCodePoints(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isSupplementaryCodePoint(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeSupplementaryCodePointsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSupplementaryCodePointsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isSupplementaryCodePoint(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepSupplementaryCodePointsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSupplementaryCodePointsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isSupplementaryCodePoint(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeLowerCaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLowerCase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isLowerCase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepLowerCaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLowerCase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isLowerCase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeLowerCasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLowerCasePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isLowerCase(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepLowerCasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLowerCasePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isLowerCase(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeUpperCaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUpperCase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isUpperCase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepUpperCaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUpperCase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isUpperCase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeUpperCasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUpperCasePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isUpperCase(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepUpperCasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUpperCasePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isUpperCase(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeTitleCaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeTitleCase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isTitleCase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepTitleCaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepTitleCase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isTitleCase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeTitleCasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeTitleCasePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isTitleCase(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepTitleCasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepTitleCasePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isTitleCase(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDigitsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isDigit(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDigitsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isDigit(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeDefinedTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDefined(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isDefined(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepDefinedTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDefined(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isDefined(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeDefinedPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDefinedPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isDefined(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepDefinedPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDefinedPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isDefined(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeLetterOrDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLetterOrDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isLetterOrDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepLetterOrDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLetterOrDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isLetterOrDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeLetterOrDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLetterOrDigitsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isLetterOrDigit(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepLetterOrDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLetterOrDigitsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isLetterOrDigit(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAlphabeticTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAlphabetic(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isAlphabetic(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAlphabeticTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAlphabetic(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isAlphabetic(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAlphabeticPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAlphabeticPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isAlphabetic(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAlphabeticPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAlphabeticPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isAlphabetic(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeIdeographicsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeIdeographics(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isIdeographic(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepIdeographicsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepIdeographics(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isIdeographic(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeIdeographicsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeIdeographicsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isIdeographic(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepIdeographicsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepIdeographicsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isIdeographic(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeJavaIdentifierStartTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeJavaIdentifierStart(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isJavaIdentifierStart(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepJavaIdentifierStartTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepJavaIdentifierStart(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isJavaIdentifierStart(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeJavaIdentifierStartPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeJavaIdentifierStartPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isJavaIdentifierStart(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepJavaIdentifierStartPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepJavaIdentifierStartPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isJavaIdentifierStart(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeUnicodeIdentifierStartTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUnicodeIdentifierStart(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isUnicodeIdentifierStart(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepUnicodeIdentifierStartTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUnicodeIdentifierStart(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isUnicodeIdentifierStart(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeUnicodeIdentifierStartPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUnicodeIdentifierStartPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isUnicodeIdentifierStart(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepUnicodeIdentifierStartPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUnicodeIdentifierStartPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isUnicodeIdentifierStart(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeIdentifierIgnorableTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeIdentifierIgnorable(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isIdentifierIgnorable(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepIdentifierIgnorableTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepIdentifierIgnorable(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isIdentifierIgnorable(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeIdentifierIgnorablePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeIdentifierIgnorablePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isIdentifierIgnorable(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepIdentifierIgnorablePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepIdentifierIgnorablePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isIdentifierIgnorable(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEmojisTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojis(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isEmoji(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEmojisTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojis(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmoji(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEmojisPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojisPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isEmoji(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEmojisPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojisPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmoji(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEmojiPresentationTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiPresentation(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isEmojiPresentation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEmojiPresentationTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiPresentation(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiPresentation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEmojiPresentationPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiPresentationPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isEmojiPresentation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEmojiPresentationPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiPresentationPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiPresentation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEmojiModifiersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiModifiers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isEmojiModifier(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEmojiModifiersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiModifiers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiModifier(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEmojiModifiersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiModifiersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isEmojiModifier(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEmojiModifiersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiModifiersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiModifier(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEmojiModifierBaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiModifierBase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isEmojiModifierBase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEmojiModifierBaseTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiModifierBase(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiModifierBase(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEmojiModifierBasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiModifierBasePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isEmojiModifierBase(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEmojiModifierBasePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiModifierBasePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiModifierBase(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEmojiComponentsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiComponents(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isEmojiComponent(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEmojiComponentsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiComponents(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiComponent(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEmojiComponentsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEmojiComponentsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isEmojiComponent(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEmojiComponentsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEmojiComponentsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isEmojiComponent(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeExtendedPictographicTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeExtendedPictographic(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isExtendedPictographic(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepExtendedPictographicTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepExtendedPictographic(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isExtendedPictographic(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeExtendedPictographicPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeExtendedPictographicPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isExtendedPictographic(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepExtendedPictographicPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepExtendedPictographicPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isExtendedPictographic(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeWhitespaceTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeWhitespace(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isWhitespace(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepWhitespaceTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepWhitespace(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isWhitespace(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeWhitespacePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeWhitespacePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isWhitespace(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepWhitespacePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepWhitespacePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isWhitespace(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeSpaceCharsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSpaceChars(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isSpaceChar(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepSpaceCharsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSpaceChars(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isSpaceChar(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeSpaceCharsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSpaceCharsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isSpaceChar(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepSpaceCharsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSpaceCharsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isSpaceChar(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeISOControlsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeISOControls(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isISOControl(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepISOControlsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepISOControls(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isISOControl(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeISOControlsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeISOControlsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isISOControl(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepISOControlsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepISOControlsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isISOControl(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeMirroredTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeMirrored(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(Character.isMirrored(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepMirroredTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepMirrored(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(Character.isMirrored(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeMirroredPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeMirroredPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!Character.isMirrored(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepMirroredPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepMirroredPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(Character.isMirrored(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }


    @Test
    void removeAsciiLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiUpperCaseLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiUpperCaseLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiUpperCaseLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiUpperCaseLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiUpperCaseLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiUpperCaseLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiUpperCaseLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiUpperCaseLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiUpperCaseLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiUpperCaseLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiUpperCaseLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiUpperCaseLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiLowerCaseLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiLowerCaseLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiLowerCaseLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiLowerCaseLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiLowerCaseLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiLowerCaseLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiLowerCaseLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiLowerCaseLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiLowerCaseLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiLowerCaseLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiLowerCaseLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiLowerCaseLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiDigitsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiDigit(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiDigitsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiDigit(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiAlphanumericTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiAlphanumeric(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiAlphanumeric(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiAlphanumericTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiAlphanumeric(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiAlphanumeric(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiAlphanumericPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiAlphanumericPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiAlphanumeric(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiAlphanumericPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiAlphanumericPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiAlphanumeric(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeHexDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeHexDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isHexDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepHexDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepHexDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isHexDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeHexDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeHexDigitsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isHexDigit(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepHexDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepHexDigitsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isHexDigit(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeUuidCharactersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUuidCharacters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isUuidCharacter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepUuidCharactersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUuidCharacters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isUuidCharacter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeUuidCharactersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUuidCharactersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isUuidCharacter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepUuidCharactersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUuidCharactersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isUuidCharacter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeBinaryDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBinaryDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isBinaryDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepBinaryDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBinaryDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isBinaryDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeBinaryDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBinaryDigitsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isBinaryDigit(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepBinaryDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBinaryDigitsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isBinaryDigit(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeOctalDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOctalDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isOctalDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepOctalDigitsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOctalDigits(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOctalDigit(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeOctalDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOctalDigitsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isOctalDigit(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepOctalDigitsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOctalDigitsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOctalDigit(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeBase64CharactersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBase64Characters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isBase64Character(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepBase64CharactersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBase64Characters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isBase64Character(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeBase64CharactersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBase64CharactersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isBase64Character(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepBase64CharactersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBase64CharactersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isBase64Character(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeBase64UrlCharactersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBase64UrlCharacters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isBase64UrlCharacter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepBase64UrlCharactersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBase64UrlCharacters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isBase64UrlCharacter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeBase64UrlCharactersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeBase64UrlCharactersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isBase64UrlCharacter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepBase64UrlCharactersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepBase64UrlCharactersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isBase64UrlCharacter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiWhitespacesTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiWhitespaces(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiWhitespace(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiWhitespacesTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiWhitespaces(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiWhitespace(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiWhitespacesPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiWhitespacesPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiWhitespace(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiWhitespacesPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiWhitespacesPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiWhitespace(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiPrintableTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiPrintable(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiPrintable(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiPrintableTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiPrintable(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiPrintable(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiPrintablePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiPrintablePrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiPrintable(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiPrintablePrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiPrintablePrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiPrintable(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeAsciiControlTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiControl(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isAsciiControl(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepAsciiControlTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiControl(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiControl(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeAsciiControlPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeAsciiControlPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isAsciiControl(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepAsciiControlPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepAsciiControlPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isAsciiControl(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeTitlecaseLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeTitlecaseLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isTitlecaseLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepTitlecaseLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepTitlecaseLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isTitlecaseLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeTitlecaseLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeTitlecaseLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isTitlecaseLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepTitlecaseLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepTitlecaseLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isTitlecaseLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeModifierLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeModifierLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isModifierLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepModifierLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepModifierLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isModifierLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeModifierLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeModifierLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isModifierLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepModifierLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepModifierLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isModifierLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeOtherLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isOtherLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepOtherLettersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherLetters(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherLetter(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeOtherLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherLettersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isOtherLetter(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepOtherLettersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherLettersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherLetter(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeNonSpacingMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeNonSpacingMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isNonSpacingMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepNonSpacingMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepNonSpacingMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isNonSpacingMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeNonSpacingMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeNonSpacingMarksPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isNonSpacingMark(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepNonSpacingMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepNonSpacingMarksPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isNonSpacingMark(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeCombiningSpacingMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeCombiningSpacingMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isCombiningSpacingMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepCombiningSpacingMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepCombiningSpacingMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isCombiningSpacingMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeCombiningSpacingMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeCombiningSpacingMarksPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isCombiningSpacingMark(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepCombiningSpacingMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepCombiningSpacingMarksPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isCombiningSpacingMark(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEnclosingMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEnclosingMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isEnclosingMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEnclosingMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEnclosingMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isEnclosingMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEnclosingMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEnclosingMarksPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isEnclosingMark(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEnclosingMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEnclosingMarksPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isEnclosingMark(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepMarksTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepMarks(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isMark(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeMarksPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isMark(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepMarksPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepMarksPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isMark(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeDecimalDigitNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDecimalDigitNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isDecimalDigitNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepDecimalDigitNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDecimalDigitNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isDecimalDigitNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeDecimalDigitNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDecimalDigitNumbersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isDecimalDigitNumber(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepDecimalDigitNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDecimalDigitNumbersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isDecimalDigitNumber(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeLetterNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLetterNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isLetterNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepLetterNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLetterNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isLetterNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeLetterNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLetterNumbersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isLetterNumber(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepLetterNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLetterNumbersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isLetterNumber(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeOtherNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isOtherNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepOtherNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeOtherNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherNumbersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isOtherNumber(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepOtherNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherNumbersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherNumber(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepNumbersTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepNumbers(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isNumber(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeNumbersPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isNumber(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepNumbersPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepNumbersPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isNumber(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeSpaceSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSpaceSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isSpaceSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepSpaceSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSpaceSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSpaceSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeSpaceSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSpaceSeparatorsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isSpaceSeparator(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepSpaceSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSpaceSeparatorsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSpaceSeparator(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeLineSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLineSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isLineSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepLineSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLineSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isLineSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeLineSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeLineSeparatorsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isLineSeparator(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepLineSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepLineSeparatorsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isLineSeparator(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeParagraphSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeParagraphSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isParagraphSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepParagraphSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepParagraphSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isParagraphSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeParagraphSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeParagraphSeparatorsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isParagraphSeparator(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepParagraphSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepParagraphSeparatorsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isParagraphSeparator(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepSeparatorsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSeparators(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSeparator(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSeparatorsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isSeparator(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepSeparatorsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSeparatorsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSeparator(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeControlCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeControlCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isControlCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepControlCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepControlCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isControlCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeControlCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeControlCategoryPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isControlCategory(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepControlCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepControlCategoryPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isControlCategory(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeFormatCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeFormatCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isFormatCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepFormatCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepFormatCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isFormatCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeFormatCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeFormatCategoryPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isFormatCategory(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepFormatCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepFormatCategoryPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isFormatCategory(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removePrivateUseCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removePrivateUseCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isPrivateUseCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepPrivateUseCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepPrivateUseCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isPrivateUseCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removePrivateUseCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removePrivateUseCategoryPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isPrivateUseCategory(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepPrivateUseCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepPrivateUseCategoryPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isPrivateUseCategory(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeSurrogateCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSurrogateCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isSurrogateCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepSurrogateCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSurrogateCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSurrogateCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeSurrogateCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSurrogateCategoryPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isSurrogateCategory(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepSurrogateCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSurrogateCategoryPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSurrogateCategory(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeUnassignedCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUnassignedCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isUnassignedCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepUnassignedCategoryTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUnassignedCategory(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isUnassignedCategory(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeUnassignedCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeUnassignedCategoryPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isUnassignedCategory(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepUnassignedCategoryPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepUnassignedCategoryPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isUnassignedCategory(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeDashPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDashPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isDashPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepDashPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDashPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isDashPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeDashPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeDashPunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isDashPunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepDashPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepDashPunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isDashPunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeStartPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeStartPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isStartPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepStartPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepStartPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isStartPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeStartPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeStartPunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isStartPunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepStartPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepStartPunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isStartPunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeEndPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEndPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isEndPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepEndPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEndPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isEndPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeEndPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeEndPunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isEndPunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepEndPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepEndPunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isEndPunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeConnectorPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeConnectorPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isConnectorPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepConnectorPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepConnectorPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isConnectorPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeConnectorPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeConnectorPunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isConnectorPunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepConnectorPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepConnectorPunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isConnectorPunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeOtherPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isOtherPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepOtherPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeOtherPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherPunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isOtherPunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepOtherPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherPunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherPunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeInitialQuotePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeInitialQuotePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isInitialQuotePunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepInitialQuotePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepInitialQuotePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isInitialQuotePunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeInitialQuotePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeInitialQuotePunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isInitialQuotePunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepInitialQuotePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepInitialQuotePunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isInitialQuotePunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeFinalQuotePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeFinalQuotePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isFinalQuotePunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepFinalQuotePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepFinalQuotePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isFinalQuotePunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeFinalQuotePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeFinalQuotePunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isFinalQuotePunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepFinalQuotePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepFinalQuotePunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isFinalQuotePunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeQuotePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeQuotePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isQuotePunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepQuotePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepQuotePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isQuotePunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeQuotePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeQuotePunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isQuotePunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepQuotePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepQuotePunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isQuotePunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removePunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removePunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepPunctuationsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepPunctuations(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isPunctuation(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removePunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removePunctuationsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isPunctuation(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepPunctuationsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepPunctuationsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isPunctuation(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeMathSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeMathSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isMathSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepMathSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepMathSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isMathSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeMathSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeMathSymbolsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isMathSymbol(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepMathSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepMathSymbolsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isMathSymbol(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeCurrencySymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeCurrencySymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isCurrencySymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepCurrencySymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepCurrencySymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isCurrencySymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeCurrencySymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeCurrencySymbolsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isCurrencySymbol(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepCurrencySymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepCurrencySymbolsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isCurrencySymbol(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeModifierSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeModifierSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isModifierSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepModifierSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepModifierSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isModifierSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeModifierSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeModifierSymbolsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isModifierSymbol(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepModifierSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepModifierSymbolsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isModifierSymbol(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeOtherSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isOtherSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepOtherSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeOtherSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeOtherSymbolsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isOtherSymbol(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepOtherSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepOtherSymbolsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isOtherSymbol(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void removeSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertFalse(CharacterHelper.isSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void keepSymbolsTest() {
        String input = "123abc🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSymbols(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {
            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSymbol(codePoint), "Value was " + output);
            i += Character.charCount(codePoint);
        }
    }

    @Test
    void removeSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.removeSymbolsPrefix(buffer);

        String output = buffer.toString();

        int prefixLength = 0;
        while (prefixLength < input.length()) {

            int codePoint = input.codePointAt(prefixLength);

            if (!CharacterHelper.isSymbol(codePoint)) {
                break;
            }

            prefixLength += Character.charCount(codePoint);
        }

        String expected = input.substring(prefixLength);

        assertEquals(expected, output);

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }

    @Test
    void keepSymbolsPrefixTest() {
        String input = "abc123🙂DEF";

        CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
        CodePointUtils.keepSymbolsPrefix(buffer);

        String output = buffer.toString();

        for (int i = 0; i < output.length(); ) {

            int codePoint = output.codePointAt(i);

            assertTrue(CharacterHelper.isSymbol(codePoint), "Value was " + output);

            i += Character.charCount(codePoint);
        }

        assertEquals(output.codePointCount(0, output.length()), buffer.length());
    }
}
