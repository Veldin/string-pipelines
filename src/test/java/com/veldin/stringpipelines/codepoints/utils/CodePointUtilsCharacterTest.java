package com.veldin.stringpipelines.codepoints.utils;

import com.veldin.stringpipelines.CharacterHelper;
import com.veldin.stringpipelines.codepoints.CodePointBuffer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class CodePointUtilsCharacterTest {

    private static final String[] TEST_STRINGS = {
            "",
            "123abc🙂DEF",
            "Hello, World!",
            "こんにちは",
            "😀😃😄😁😆",
            "1234567890",
            "ABCdefGHI",
            "€£¥₿",
            "\u0000\u0001\u0002",
            "𐍈𐐷𐤀", // supplementary code points
            "a🙂b😂c👍",
            " \t\r\n",
    };

    @Test
    void removeValidCodePointsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeValidCodePoints(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isValidCodePoint(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepValidCodePointsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepValidCodePoints(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isValidCodePoint(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeValidCodePointsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepValidCodePointsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeBmpCodePointsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeBmpCodePoints(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isBmpCodePoint(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepBmpCodePointsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepBmpCodePoints(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isBmpCodePoint(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeBmpCodePointsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepBmpCodePointsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeSupplementaryCodePointsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeSupplementaryCodePoints(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isSupplementaryCodePoint(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepSupplementaryCodePointsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepSupplementaryCodePoints(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isSupplementaryCodePoint(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeSupplementaryCodePointsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepSupplementaryCodePointsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeLowerCaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeLowerCase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isLowerCase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepLowerCaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepLowerCase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isLowerCase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeLowerCasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepLowerCasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeUpperCaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeUpperCase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isUpperCase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepUpperCaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepUpperCase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isUpperCase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeUpperCasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepUpperCasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeTitleCaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeTitleCase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isTitleCase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepTitleCaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepTitleCase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isTitleCase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeTitleCasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepTitleCasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeDefinedTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeDefined(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isDefined(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepDefinedTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepDefined(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isDefined(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeDefinedPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepDefinedPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeLettersOrDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeLettersOrDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isLetterOrDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepLettersOrDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepLettersOrDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isLetterOrDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeLettersOrDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeLettersOrDigitsPrefix(buffer);

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
    }

    @Test
    void keepLettersOrDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepLettersOrDigitsPrefix(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {

                int codePoint = output.codePointAt(i);

                assertTrue(Character.isLetterOrDigit(codePoint), "Value was " + output);

                i += Character.charCount(codePoint);
            }

            assertEquals(output.codePointCount(0, output.length()), buffer.length());
        }
    }

    @Test
    void removeAlphabeticTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAlphabetic(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isAlphabetic(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAlphabeticTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAlphabetic(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isAlphabetic(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAlphabeticPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAlphabeticPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeIdeographicTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeIdeographic(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isIdeographic(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepIdeographicTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepIdeographic(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isIdeographic(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeIdeographicPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeIdeographicPrefix(buffer);

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
    }

    @Test
    void keepIdeographicPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepIdeographicPrefix(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {

                int codePoint = output.codePointAt(i);

                assertTrue(Character.isIdeographic(codePoint), "Value was " + output);

                i += Character.charCount(codePoint);
            }

            assertEquals(output.codePointCount(0, output.length()), buffer.length());
        }
    }

    @Test
    void removeJavaIdentifierStartTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeJavaIdentifierStart(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isJavaIdentifierStart(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepJavaIdentifierStartTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepJavaIdentifierStart(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isJavaIdentifierStart(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeJavaIdentifierStartPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepJavaIdentifierStartPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeUnicodeIdentifierStartTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeUnicodeIdentifierStart(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isUnicodeIdentifierStart(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepUnicodeIdentifierStartTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepUnicodeIdentifierStart(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isUnicodeIdentifierStart(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeUnicodeIdentifierStartPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepUnicodeIdentifierStartPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeIdentifierIgnorableTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeIdentifierIgnorable(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isIdentifierIgnorable(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepIdentifierIgnorableTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepIdentifierIgnorable(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isIdentifierIgnorable(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeIdentifierIgnorablePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepIdentifierIgnorablePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeEmojisTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEmojis(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isEmoji(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEmojisTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEmojis(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isEmoji(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEmojisPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepEmojisPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeEmojiPresentationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEmojiPresentations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isEmojiPresentation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEmojiPresentationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEmojiPresentations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isEmojiPresentation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEmojiPresentationsPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEmojiPresentationsPrefix(buffer);

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
    }

    @Test
    void keepEmojiPresentationsPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEmojiPresentationsPrefix(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {

                int codePoint = output.codePointAt(i);

                assertTrue(Character.isEmojiPresentation(codePoint), "Value was " + output);

                i += Character.charCount(codePoint);
            }

            assertEquals(output.codePointCount(0, output.length()), buffer.length());
        }
    }

    @Test
    void removeEmojiModifiersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEmojiModifiers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isEmojiModifier(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEmojiModifiersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEmojiModifiers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isEmojiModifier(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEmojiModifiersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepEmojiModifiersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeEmojiModifierBaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEmojiModifierBase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isEmojiModifierBase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEmojiModifierBaseTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEmojiModifierBase(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isEmojiModifierBase(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEmojiModifierBasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepEmojiModifierBasePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeEmojiComponentsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEmojiComponents(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isEmojiComponent(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEmojiComponentsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEmojiComponents(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isEmojiComponent(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEmojiComponentsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepEmojiComponentsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeExtendedPictographicTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeExtendedPictographic(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isExtendedPictographic(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepExtendedPictographicTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepExtendedPictographic(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isExtendedPictographic(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeExtendedPictographicPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepExtendedPictographicPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeWhitespaceTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeWhitespace(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isWhitespace(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepWhitespaceTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepWhitespace(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isWhitespace(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeWhitespacePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepWhitespacePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeSpaceCharTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeSpaceChar(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isSpaceChar(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepSpaceCharTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepSpaceChars(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isSpaceChar(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeSpaceCharPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepSpaceCharPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeISOControlTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeISOControl(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isISOControl(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepISOControlTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepISOControl(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isISOControl(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeISOControlPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeISOControlPrefix(buffer);

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
    }

    @Test
    void keepISOControlPrefixTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepISOControlPrefix(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {

                int codePoint = output.codePointAt(i);

                assertTrue(Character.isISOControl(codePoint), "Value was " + output);

                i += Character.charCount(codePoint);
            }

            assertEquals(output.codePointCount(0, output.length()), buffer.length());
        }
    }

    @Test
    void removeMirroredTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeMirrored(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(Character.isMirrored(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepMirroredTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepMirrored(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(Character.isMirrored(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeMirroredPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepMirroredPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiUpperCaseLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiUpperCaseLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiUpperCaseLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiUpperCaseLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiUpperCaseLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiUpperCaseLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiUpperCaseLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiUpperCaseLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiLowerCaseLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiLowerCaseLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiLowerCaseLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiLowerCaseLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiLowerCaseLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiLowerCaseLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiLowerCaseLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiLowerCaseLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiAlphanumericTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiAlphanumeric(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiAlphanumeric(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiAlphanumericTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiAlphanumeric(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiAlphanumeric(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiAlphanumericPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiAlphanumericPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeHexDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeHexDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isHexDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepHexDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepHexDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isHexDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeHexDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepHexDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeUuidCharactersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeUuidCharacters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isUuidCharacter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepUuidCharactersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepUuidCharacters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isUuidCharacter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeUuidCharactersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepUuidCharactersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeBinaryDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeBinaryDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isBinaryDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepBinaryDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepBinaryDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isBinaryDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeBinaryDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepBinaryDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeOctalDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeOctalDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isOctalDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepOctalDigitsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepOctalDigits(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isOctalDigit(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeOctalDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepOctalDigitsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeBase64CharactersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeBase64Characters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isBase64Character(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepBase64CharactersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepBase64Characters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isBase64Character(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeBase64CharactersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepBase64CharactersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeBase64UrlCharactersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeBase64UrlCharacters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isBase64UrlCharacter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepBase64UrlCharactersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepBase64UrlCharacters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isBase64UrlCharacter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeBase64UrlCharactersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepBase64UrlCharactersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiWhitespacesTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiWhitespaces(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiWhitespace(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiWhitespacesTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiWhitespaces(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiWhitespace(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiWhitespacesPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiWhitespacesPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiPrintableTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiPrintable(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiPrintable(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiPrintableTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiPrintable(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiPrintable(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiPrintablePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiPrintablePrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeAsciiControlTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeAsciiControl(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isAsciiControl(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepAsciiControlTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepAsciiControl(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isAsciiControl(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeAsciiControlPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepAsciiControlPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeTitlecaseLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeTitlecaseLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isTitlecaseLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepTitlecaseLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepTitlecaseLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isTitlecaseLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeTitlecaseLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepTitlecaseLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeModifierLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeModifierLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isModifierLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepModifierLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepModifierLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isModifierLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeModifierLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepModifierLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeOtherLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeOtherLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isOtherLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepOtherLettersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepOtherLetters(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isOtherLetter(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeOtherLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepOtherLettersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeNonSpacingMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeNonSpacingMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isNonSpacingMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepNonSpacingMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepNonSpacingMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isNonSpacingMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeNonSpacingMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepNonSpacingMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeCombiningSpacingMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeCombiningSpacingMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isCombiningSpacingMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepCombiningSpacingMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepCombiningSpacingMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isCombiningSpacingMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeCombiningSpacingMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepCombiningSpacingMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeEnclosingMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEnclosingMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isEnclosingMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEnclosingMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEnclosingMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isEnclosingMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEnclosingMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepEnclosingMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepMarksTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepMarks(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isMark(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepMarksPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeDecimalDigitNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeDecimalDigitNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isDecimalDigitNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepDecimalDigitNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepDecimalDigitNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isDecimalDigitNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeDecimalDigitNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepDecimalDigitNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeLetterNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeLetterNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isLetterNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepLetterNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepLetterNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isLetterNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeLetterNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepLetterNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeOtherNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeOtherNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isOtherNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepOtherNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepOtherNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isOtherNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeOtherNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepOtherNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepNumbersTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepNumbers(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isNumber(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepNumbersPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeSpaceSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeSpaceSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isSpaceSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepSpaceSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepSpaceSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isSpaceSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeSpaceSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepSpaceSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeLineSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeLineSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isLineSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepLineSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepLineSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isLineSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeLineSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepLineSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeParagraphSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeParagraphSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isParagraphSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepParagraphSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepParagraphSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isParagraphSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeParagraphSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepParagraphSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepSeparatorsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepSeparators(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isSeparator(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepSeparatorsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeControlCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeControlCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isControlCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepControlCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepControlCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isControlCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeControlCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepControlCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeFormatCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeFormatCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isFormatCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepFormatCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepFormatCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isFormatCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeFormatCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepFormatCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removePrivateUseCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removePrivateUseCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isPrivateUseCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepPrivateUseCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepPrivateUseCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isPrivateUseCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removePrivateUseCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepPrivateUseCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeSurrogateCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeSurrogateCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isSurrogateCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepSurrogateCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepSurrogateCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isSurrogateCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeSurrogateCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepSurrogateCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeUnassignedCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeUnassignedCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isUnassignedCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepUnassignedCategoryTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepUnassignedCategory(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isUnassignedCategory(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeUnassignedCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepUnassignedCategoryPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeDashPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeDashPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isDashPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepDashPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepDashPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isDashPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeDashPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepDashPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeStartPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeStartPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isStartPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepStartPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepStartPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isStartPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeStartPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepStartPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeEndPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeEndPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isEndPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepEndPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepEndPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isEndPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeEndPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepEndPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeConnectorPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeConnectorPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isConnectorPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepConnectorPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepConnectorPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isConnectorPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeConnectorPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepConnectorPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeOtherPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeOtherPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isOtherPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepOtherPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepOtherPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isOtherPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeOtherPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepOtherPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeInitialQuotePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeInitialQuotePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isInitialQuotePunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepInitialQuotePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepInitialQuotePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isInitialQuotePunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeInitialQuotePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepInitialQuotePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeFinalQuotePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeFinalQuotePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isFinalQuotePunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepFinalQuotePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepFinalQuotePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isFinalQuotePunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeFinalQuotePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepFinalQuotePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeQuotePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeQuotePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isQuotePunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepQuotePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepQuotePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isQuotePunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeQuotePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepQuotePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removePunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removePunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepPunctuationsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepPunctuations(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isPunctuation(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removePunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepPunctuationsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeMathSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeMathSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isMathSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepMathSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepMathSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isMathSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeMathSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepMathSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeCurrencySymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeCurrencySymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isCurrencySymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepCurrencySymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepCurrencySymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isCurrencySymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeCurrencySymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepCurrencySymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeModifierSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeModifierSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isModifierSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepModifierSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepModifierSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isModifierSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeModifierSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepModifierSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeOtherSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeOtherSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isOtherSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepOtherSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepOtherSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isOtherSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeOtherSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepOtherSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void removeSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.removeSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertFalse(CharacterHelper.isSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void keepSymbolsTest() {
        for (String input : TEST_STRINGS) {

            CodePointBuffer buffer = new CodePointBuffer(input.codePoints().toArray());
            CodePointUtils.keepSymbols(buffer);

            String output = buffer.toString();

            for (int i = 0; i < output.length(); ) {
                int codePoint = output.codePointAt(i);

                assertTrue(CharacterHelper.isSymbol(codePoint), "Value was " + output);
                i += Character.charCount(codePoint);
            }
        }
    }

    @Test
    void removeSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
    }

    @Test
    void keepSymbolsPrefixTest() {
        for (String input : TEST_STRINGS) {

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
}
