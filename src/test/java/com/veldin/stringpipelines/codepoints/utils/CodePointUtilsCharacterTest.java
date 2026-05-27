package com.veldin.stringpipelines.codepoints.utils;

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

}
