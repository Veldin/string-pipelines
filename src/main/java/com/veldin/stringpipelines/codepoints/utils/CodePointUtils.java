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

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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
        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int firstCodePoint = buffer.get(0);
        int newCodePoint = Character.toLowerCase(firstCodePoint);

        if (firstCodePoint != newCodePoint) {
            buffer.set(0, newCodePoint);
        }
    }

    public static void toggleCase(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.length() < 2) {
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

        if (buffer == null || buffer.length() < 2) {
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

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        for (int i = 0; i < buffer.length(); i++) {
            buffer.set(i, buffer.get(i) + 1);
        }
    }

    // Decrement every code point by 1
    public static void decrement(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        for (int i = 0; i < buffer.length(); i++) {
            buffer.set(i, buffer.get(i) - 1);
        }
    }

    public static void removeFirst(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();

        for (int i = 1; i < length; i++) {
            buffer.set(i - 1, buffer.get(i));
        }

        buffer.setLength(length - 1);
    }

    public static void removeLast(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        buffer.setLength(buffer.length() - 1);
    }

    public static void toBase64(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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
        if (buffer == null || buffer.isEmpty()) {
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

    public static void toXmlEntities(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int originalLength = buffer.length();

        int extra = 0;

        for (int i = 0; i < originalLength; i++) {

            switch (buffer.get(i)) {
                case '&':
                    extra += 4; // & -> &amp;
                    break;

                case '<':
                case '>':
                    extra += 3; // &lt; &gt;
                    break;

                case '"':
                case '\'':
                    extra += 5; // &quot; &apos;
                    break;
            }
        }

        if (extra == 0) {
            return;
        }

        int newLength = originalLength + extra;

        buffer.setLength(newLength);

        int write = newLength - 1;

        for (int read = originalLength - 1; read >= 0; read--) {

            int cp = buffer.get(read);

            switch (cp) {

                case '&':
                    buffer.set(write--, ';');
                    buffer.set(write--, 'p');
                    buffer.set(write--, 'm');
                    buffer.set(write--, 'a');
                    buffer.set(write--, '&');
                    break;

                case '<':
                    buffer.set(write--, ';');
                    buffer.set(write--, 't');
                    buffer.set(write--, 'l');
                    buffer.set(write--, '&');
                    break;

                case '>':
                    buffer.set(write--, ';');
                    buffer.set(write--, 't');
                    buffer.set(write--, 'g');
                    buffer.set(write--, '&');
                    break;

                case '"':
                    buffer.set(write--, ';');
                    buffer.set(write--, 't');
                    buffer.set(write--, 'o');
                    buffer.set(write--, 'u');
                    buffer.set(write--, 'q');
                    buffer.set(write--, '&');
                    break;

                case '\'':
                    buffer.set(write--, ';');
                    buffer.set(write--, 's');
                    buffer.set(write--, 'o');
                    buffer.set(write--, 'p');
                    buffer.set(write--, 'a');
                    buffer.set(write--, '&');
                    break;

                default:
                    buffer.set(write--, cp);
            }
        }
    }

    public static void fromXmlEntities(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();

        int read = 0;
        int write = 0;

        while (read < length) {

            if (buffer.get(read) != '&') {
                buffer.set(write++, buffer.get(read++));
                continue;
            }

            // if is AMP
            if (read + 5 <= length
                    && buffer.get(read) == '&'
                    && buffer.get(read + 1) == 'a'
                    && buffer.get(read + 2) == 'm'
                    && buffer.get(read + 3) == 'p'
                    && buffer.get(read + 4) == ';'
            ) {
                buffer.set(write++, '&');
                read += 5;

            // if is Lt
            } else if (read + 4 <= length
                    && buffer.get(read) == '&'
                    && buffer.get(read + 1) == 'l'
                    && buffer.get(read + 2) == 't'
                    && buffer.get(read + 3) == ';'
            ) {
                buffer.set(write++, '<');
                read += 4;

            // if is Gt
            } else if (read + 4 <= length
                    && buffer.get(read) == '&'
                    && buffer.get(read + 1) == 'g'
                    && buffer.get(read + 2) == 't'
                    && buffer.get(read + 3) == ';'
            ) {
                buffer.set(write++, '>');
                read += 4;

            // if is Quot
            } else if ( read + 6 <= length
                    && buffer.get(read) == '&'
                    && buffer.get(read + 1) == 'q'
                    && buffer.get(read + 2) == 'u'
                    && buffer.get(read + 3) == 'o'
                    && buffer.get(read + 4) == 't'
                    && buffer.get(read + 5) == ';'
            ) {
                buffer.set(write++, '"');
                read += 6;

            // if is Aphos
            } else if (read + 6 <= length
                    && buffer.get(read) == '&'
                    && buffer.get(read + 1) == 'a'
                    && buffer.get(read + 2) == 'p'
                    && buffer.get(read + 3) == 'o'
                    && buffer.get(read + 4) == 's'
                    && buffer.get(read + 5) == ';'
            ) {
                buffer.set(write++, '\'');
                read += 6;

            } else {
                // Unknown entity: keep the '&' and continue.
                buffer.set(write++, '&');
                read++;
            }
        }

        buffer.setLength(write);
    }

    public static void removeValidCodePoints(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepValidCodePointsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isValidCodePoint(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeBmpCodePoints(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepBmpCodePointsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isBmpCodePoint(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeSupplementaryCodePoints(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepSupplementaryCodePointsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isSupplementaryCodePoint(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeLowerCase(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepLowerCasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isLowerCase(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeUpperCase(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepUpperCasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isUpperCase(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeTitleCase(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepTitleCasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isTitleCase(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isDigit(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeDefined(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepDefinedPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isDefined(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeLetterOrDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepLetterOrDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isLetterOrDigit(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAlphabetic(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepAlphabeticPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isAlphabetic(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeIdeographics(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepIdeographicsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isIdeographic(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeJavaIdentifierStart(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepJavaIdentifierStartPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isJavaIdentifierStart(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeUnicodeIdentifierStart(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepUnicodeIdentifierStartPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isUnicodeIdentifierStart(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeIdentifierIgnorable(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepIdentifierIgnorablePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isIdentifierIgnorable(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEmojis(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepEmojisPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isEmoji(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEmojiPresentation(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepEmojiPresentationPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isEmojiPresentation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEmojiModifiers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepEmojiModifiersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isEmojiModifier(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEmojiModifierBase(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepEmojiModifierBasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isEmojiModifierBase(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEmojiComponents(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepEmojiComponentsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isEmojiComponent(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeExtendedPictographic(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepExtendedPictographicPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isExtendedPictographic(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeWhitespace(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepWhitespacePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isWhitespace(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeSpaceChars(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepSpaceCharsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isSpaceChar(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeISOControls(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepISOControlsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isISOControl(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeMirrored(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
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

        if (buffer == null || buffer.isEmpty()) {
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

    public static void keepMirroredPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!Character.isMirrored(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }
    
}