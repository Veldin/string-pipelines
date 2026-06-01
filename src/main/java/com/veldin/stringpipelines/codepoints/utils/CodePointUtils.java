package com.veldin.stringpipelines.codepoints.utils;

import com.veldin.stringpipelines.codepoints.CodePointBuffer;

import java.util.Base64;

/**
 * Utility methods operating directly on Unicode code points.
 *
 * <p>
 * Methods in this class mutate the provided {@link CodePointBuffer}
 * in-place whenever possible to minimize allocations and copying.
 * </p>
 */
public class CodePointUtils {

    private CodePointUtils() {
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' capitalize.
    public static void capitalize(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int firstCodePoint = buffer.get(0);
        int newCodePoint = Character.toTitleCase(firstCodePoint);

        if (firstCodePoint != newCodePoint) {
            buffer.set(0, newCodePoint);
        }
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' chomp.
    public static void chomp(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        // Single character edge case
        if (length == 1) {

            int codePoint = buffer.get(0);

            if (codePoint == '\r' || codePoint == '\n') {
                buffer.setLength(0);
            }

            return;
        }

        int lastIdx = length - 1;
        int last = buffer.get(lastIdx);

        if (last == '\n') {

            if (buffer.get(lastIdx - 1) == '\r') {
                buffer.setLength(length - 2);
            } else {
                buffer.setLength(length - 1);
            }

        } else if (last == '\r') {
            buffer.setLength(length - 1);
        }
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' chop.
    public static void chop(CodePointBuffer buffer) {

        if (buffer == null) {
            return;
        }

        int length = buffer.length();

        if (length < 2) {
            buffer.setLength(0);
            return;
        }

        int lastIdx = length - 1;
        int last = buffer.get(lastIdx);

        // Handle \r\n first
        if (last == '\n' && buffer.get(lastIdx - 1) == '\r') {
            buffer.setLength(length - 2);
            return;
        }

        // Remove last code point
        buffer.setLength(length - 1);
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' deleteWhitespace.
    public static void deleteWhitespace(CodePointBuffer buffer) {
        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int cp = buffer.get(read);

            if (!Character.isWhitespace(cp)) {
                buffer.set(write++, cp);
            }
        }

        buffer.setLength(write);
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' lowerCase.
    // TODO: locale-sensitive and multi-character transformations are not handled, handle them.
    public static void lowerCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        for (int i = 0; i < length; i++) {

            int codePoint = buffer.get(i);
            int lowerCase = Character.toLowerCase(codePoint);

            if (codePoint != lowerCase) {
                buffer.set(i, lowerCase);
            }
        }
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' normalizeSpace.
    public static void normalizeSpace(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();
        int write = 0;

        boolean previousWasWhitespace = true;

        for (int read = 0; read < length; read++) {
            int codePoint = buffer.get(read);
            boolean isWhitespace = Character.isWhitespace(codePoint);

            if (isWhitespace) {
                // Only have a single space between content
                if (!previousWasWhitespace) {
                    buffer.set(write++, ' ');
                    previousWasWhitespace = true;
                }

            } else {
                previousWasWhitespace = false;

                // Normalize non-breaking space -> normal space
                buffer.set(write++, codePoint == 160 ? ' ' : codePoint);
            }
        }

        // Remove trailing emitted space
        if (write > 0 && buffer.get(write - 1) == ' ') {
            write--;
        }

        buffer.setLength(write);
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' reverse.
    public static void reverse(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int left = 0;
        int right = buffer.length() - 1;

        while (left < right) {

            int tmp = buffer.get(left);

            buffer.set(left, buffer.get(right));
            buffer.set(right, tmp);

            left++;
            right--;
        }
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' strip.
    public static void strip(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        int start = 0;
        int end = length - 1;

        // Find first non-whitespace
        while (start < length && Character.isWhitespace(buffer.get(start))) {
            start++;
        }

        // Entire buffer is whitespace
        if (start == length) {
            buffer.setLength(0);
            return;
        }

        // Find last non-whitespace
        while (end >= start && Character.isWhitespace(buffer.get(end))) {
            end--;
        }

        int newLength = end - start + 1;

        // Shift contents left if needed (our buffer does not have offset.)
        if (start > 0) {
            for (int i = 0; i < newLength; i++) {
                buffer.set(i, buffer.get(start + i));
            }
        }

        buffer.setLength(newLength);
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' uncapitalize.
    public static void uncapitalize(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int firstCodePoint = buffer.get(0);
        int newCodePoint = Character.toLowerCase(firstCodePoint);

        if (firstCodePoint != newCodePoint) {
            buffer.set(0, newCodePoint);
        }
    }

    public static void toggleCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        for (int i = 0; i < length; i++) {

            int codePoint = buffer.get(i);

            if (Character.isUpperCase(codePoint)) {
                buffer.set(i, Character.toLowerCase(codePoint));
            } else if (Character.isLowerCase(codePoint)) {
                buffer.set(i, Character.toUpperCase(codePoint));
            }
        }
    }

    // a left rotation of the buffer contents.
    public static void rotateLeft(CodePointBuffer buffer) {

        if (isEmpty(buffer) || buffer.length() < 2) {
            return;
        }

        int length = buffer.length();
        int first = buffer.get(0);

        for (int i = 1; i < length; i++) {
            buffer.set(i - 1, buffer.get(i));
        }

        buffer.set(length - 1, first);
    }

    // a left rotation of the buffer contents.
    public static void rotateRight(CodePointBuffer buffer) {

        if (isEmpty(buffer) || buffer.length() < 2) {
            return;
        }

        int length = buffer.length();
        int last = buffer.get(length - 1);

        for (int i = length - 1; i > 0; i--) {
            buffer.set(i, buffer.get(i - 1));
        }

        buffer.set(0, last);
    }

    // Increment every code point by 1
    public static void increment(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        for (int i = 0; i < buffer.length(); i++) {
            buffer.set(i, buffer.get(i) + 1);
        }
    }

    // Decrement every code point by 1
    public static void decrement(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        for (int i = 0; i < buffer.length(); i++) {
            buffer.set(i, buffer.get(i) - 1);
        }
    }

    public static void removeFirst(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        for (int i = 1; i < length; i++) {
            buffer.set(i - 1, buffer.get(i));
        }

        buffer.setLength(length - 1);
    }

    public static void removeLast(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        buffer.setLength(buffer.length() - 1);
    }

    public static void toBase64(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        // Worst-case UTF-8 size is 4 bytes per code point.
        byte[] utf8 = new byte[length * 4];
        int bytePos = 0;

        for (int i = 0; i < length; i++) {

            int cp = buffer.get(i);

            if (cp <= 0x7F) {
                utf8[bytePos++] = (byte) cp;

            } else if (cp <= 0x7FF) {
                utf8[bytePos++] = (byte) (0xC0 | (cp >>> 6));
                utf8[bytePos++] = (byte) (0x80 | (cp & 0x3F));

            } else if (cp <= 0xFFFF) {
                utf8[bytePos++] = (byte) (0xE0 | (cp >>> 12));
                utf8[bytePos++] = (byte) (0x80 | ((cp >>> 6) & 0x3F));
                utf8[bytePos++] = (byte) (0x80 | (cp & 0x3F));

            } else {
                utf8[bytePos++] = (byte) (0xF0 | (cp >>> 18));
                utf8[bytePos++] = (byte) (0x80 | ((cp >>> 12) & 0x3F));
                utf8[bytePos++] = (byte) (0x80 | ((cp >>> 6) & 0x3F));
                utf8[bytePos++] = (byte) (0x80 | (cp & 0x3F));
            }
        }

        byte[] encoded = Base64.getEncoder().encode(
                java.util.Arrays.copyOf(utf8, bytePos));

        buffer.setLength(encoded.length);

        for (int i = 0; i < encoded.length; i++) {
            buffer.set(i, encoded[i] & 0xFF);
        }
    }

    public static void fromBase64(CodePointBuffer buffer) {
        if (isEmpty(buffer)) {
            return;
        }

        int length = buffer.length();

        // Base64 text is ASCII, one code point -> one byte.
        byte[] base64 = new byte[length];

        for (int i = 0; i < length; i++) {
            base64[i] = (byte) buffer.get(i);
        }

        byte[] utf8 = Base64.getDecoder().decode(base64);

        int write = 0;
        int i = 0;

        while (i < utf8.length) {

            int b0 = utf8[i] & 0xFF;

            if (b0 < 0x80) {

                buffer.set(write++, b0);
                i++;

            } else if ((b0 & 0xE0) == 0xC0) {

                int b1 = utf8[i + 1] & 0x3F;

                int cp =
                        ((b0 & 0x1F) << 6)
                                | b1;

                buffer.set(write++, cp);
                i += 2;

            } else if ((b0 & 0xF0) == 0xE0) {

                int b1 = utf8[i + 1] & 0x3F;
                int b2 = utf8[i + 2] & 0x3F;

                int cp =
                        ((b0 & 0x0F) << 12)
                                | (b1 << 6)
                                | b2;

                buffer.set(write++, cp);
                i += 3;

            } else {

                int b1 = utf8[i + 1] & 0x3F;
                int b2 = utf8[i + 2] & 0x3F;
                int b3 = utf8[i + 3] & 0x3F;

                int cp =
                        ((b0 & 0x07) << 18)
                                | (b1 << 12)
                                | (b2 << 6)
                                | b3;

                buffer.set(write++, cp);
                i += 4;
            }
        }

        buffer.setLength(write);
    }

    public static void removeValidCodePoints(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isValidCodePoint(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepValidCodePoints(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isValidCodePoint(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeBmpCodePoints(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isBmpCodePoint(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepBmpCodePoints(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isBmpCodePoint(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeSupplementaryCodePoints(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isSupplementaryCodePoint(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepSupplementaryCodePoints(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isSupplementaryCodePoint(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeLowerCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isLowerCase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepLowerCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isLowerCase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeUpperCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isUpperCase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepUpperCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isUpperCase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeTitleCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isTitleCase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepTitleCase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isTitleCase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeDigits(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepDigits(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeDefined(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isDefined(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepDefined(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isDefined(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeLetters(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepLetters(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeLetterOrDigits(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isLetterOrDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepLetterOrDigits(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isLetterOrDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAlphabetic(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isAlphabetic(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAlphabetic(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isAlphabetic(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeIdeographics(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isIdeographic(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepIdeographics(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isIdeographic(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeJavaIdentifierStart(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isJavaIdentifierStart(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepJavaIdentifierStart(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isJavaIdentifierStart(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeUnicodeIdentifierStart(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isUnicodeIdentifierStart(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepUnicodeIdentifierStart(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isUnicodeIdentifierStart(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeIdentifierIgnorable(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isIdentifierIgnorable(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepIdentifierIgnorable(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isIdentifierIgnorable(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEmojis(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isEmoji(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEmojis(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isEmoji(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEmojiPresentation(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isEmojiPresentation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEmojiPresentation(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isEmojiPresentation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEmojiModifiers(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isEmojiModifier(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEmojiModifiers(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isEmojiModifier(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEmojiModifierBase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isEmojiModifierBase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEmojiModifierBase(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isEmojiModifierBase(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEmojiComponents(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isEmojiComponent(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEmojiComponents(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isEmojiComponent(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeExtendedPictographic(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isExtendedPictographic(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepExtendedPictographic(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isExtendedPictographic(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeWhitespace(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isWhitespace(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepWhitespace(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isWhitespace(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeSpaceChars(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isSpaceChar(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepSpaceChars(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isSpaceChar(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeISOControls(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isISOControl(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepISOControls(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isISOControl(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeMirrored(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!Character.isMirrored(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepMirrored(CodePointBuffer buffer) {

        if (isEmpty(buffer)) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (Character.isMirrored(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static boolean isEmpty(CodePointBuffer buffer) {
        return buffer == null || buffer.length() == 0;
    }
}