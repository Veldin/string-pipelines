package com.veldin.stringpipelines.codepoints.utils;

import com.veldin.stringpipelines.CharacterHelper;
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

    public static void removeValidCodePointsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isValidCodePoint(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeBmpCodePointsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isBmpCodePoint(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeSupplementaryCodePointsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isSupplementaryCodePoint(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeLowerCasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isLowerCase(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeUpperCasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isUpperCase(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeTitleCasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isTitleCase(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isDigit(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeDefinedPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isDefined(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeLettersOrDigits(CodePointBuffer buffer) {

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

    public static void keepLettersOrDigits(CodePointBuffer buffer) {

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

    public static void removeLettersOrDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isLetterOrDigit(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepLettersOrDigitsPrefix(CodePointBuffer buffer) {

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

    public static void removeAlphabeticPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isAlphabetic(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeIdeographic(CodePointBuffer buffer) {

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

    public static void keepIdeographic(CodePointBuffer buffer) {

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

    public static void removeIdeographicPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isIdeographic(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepIdeographicPrefix(CodePointBuffer buffer) {

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

    public static void removeJavaIdentifierStartPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isJavaIdentifierStart(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeUnicodeIdentifierStartPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isUnicodeIdentifierStart(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeIdentifierIgnorablePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isIdentifierIgnorable(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeEmojisPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isEmoji(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeEmojiPresentations(CodePointBuffer buffer) {

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

    public static void keepEmojiPresentations(CodePointBuffer buffer) {

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

    public static void removeEmojiPresentationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isEmojiPresentation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepEmojiPresentationsPrefix(CodePointBuffer buffer) {

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

    public static void removeEmojiModifiersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isEmojiModifier(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeEmojiModifierBasePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isEmojiModifierBase(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeEmojiComponentsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isEmojiComponent(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeExtendedPictographicPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isExtendedPictographic(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeWhitespacePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isWhitespace(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeSpaceChar(CodePointBuffer buffer) {

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

    public static void removeSpaceCharsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isSpaceChar(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeISOControl(CodePointBuffer buffer) {

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

    public static void keepISOControl(CodePointBuffer buffer) {

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

    public static void removeISOControlPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isISOControl(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepISOControlPrefix(CodePointBuffer buffer) {

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

    public static void removeMirroredPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!Character.isMirrored(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

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

    public static void removeAsciiLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiUpperCaseLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiUpperCaseLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiUpperCaseLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiUpperCaseLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiUpperCaseLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiUpperCaseLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiUpperCaseLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiUpperCaseLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiLowerCaseLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiLowerCaseLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiLowerCaseLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiLowerCaseLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiLowerCaseLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiLowerCaseLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiLowerCaseLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiLowerCaseLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiDigit(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiDigit(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiAlphanumeric(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiAlphanumeric(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiAlphanumeric(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiAlphanumeric(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiAlphanumericPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiAlphanumeric(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiAlphanumericPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiAlphanumeric(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeHexDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isHexDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepHexDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isHexDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeHexDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isHexDigit(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepHexDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isHexDigit(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeUuidCharacters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isUuidCharacter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepUuidCharacters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isUuidCharacter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeUuidCharactersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isUuidCharacter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepUuidCharactersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isUuidCharacter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeBinaryDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isBinaryDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepBinaryDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isBinaryDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeBinaryDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isBinaryDigit(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepBinaryDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isBinaryDigit(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeOctalDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isOctalDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepOctalDigits(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isOctalDigit(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeOctalDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isOctalDigit(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepOctalDigitsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isOctalDigit(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeBase64Characters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isBase64Character(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepBase64Characters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isBase64Character(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeBase64CharactersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isBase64Character(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepBase64CharactersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isBase64Character(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeBase64UrlCharacters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isBase64UrlCharacter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepBase64UrlCharacters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isBase64UrlCharacter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeBase64UrlCharactersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isBase64UrlCharacter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepBase64UrlCharactersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isBase64UrlCharacter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiWhitespaces(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiWhitespace(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiWhitespaces(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiWhitespace(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiWhitespacesPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiWhitespace(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiWhitespacesPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiWhitespace(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiPrintable(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiPrintable(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiPrintable(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiPrintable(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiPrintablePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiPrintable(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiPrintablePrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiPrintable(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeAsciiControl(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isAsciiControl(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepAsciiControl(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isAsciiControl(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeAsciiControlPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isAsciiControl(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepAsciiControlPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isAsciiControl(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeTitlecaseLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isTitlecaseLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepTitlecaseLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isTitlecaseLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeTitlecaseLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isTitlecaseLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepTitlecaseLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isTitlecaseLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeModifierLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isModifierLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepModifierLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isModifierLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeModifierLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isModifierLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepModifierLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isModifierLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeOtherLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isOtherLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepOtherLetters(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isOtherLetter(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeOtherLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isOtherLetter(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepOtherLettersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isOtherLetter(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeNonSpacingMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isNonSpacingMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepNonSpacingMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isNonSpacingMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeNonSpacingMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isNonSpacingMark(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepNonSpacingMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isNonSpacingMark(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeCombiningSpacingMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isCombiningSpacingMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepCombiningSpacingMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isCombiningSpacingMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeCombiningSpacingMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isCombiningSpacingMark(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepCombiningSpacingMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isCombiningSpacingMark(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEnclosingMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isEnclosingMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEnclosingMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isEnclosingMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEnclosingMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isEnclosingMark(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepEnclosingMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isEnclosingMark(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepMarks(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isMark(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isMark(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepMarksPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isMark(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeDecimalDigitNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isDecimalDigitNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepDecimalDigitNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isDecimalDigitNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeDecimalDigitNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isDecimalDigitNumber(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepDecimalDigitNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isDecimalDigitNumber(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeLetterNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isLetterNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepLetterNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isLetterNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeLetterNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isLetterNumber(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepLetterNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isLetterNumber(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeOtherNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isOtherNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepOtherNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isOtherNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeOtherNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isOtherNumber(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepOtherNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isOtherNumber(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepNumbers(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isNumber(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isNumber(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepNumbersPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isNumber(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeSpaceSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isSpaceSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepSpaceSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isSpaceSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeSpaceSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isSpaceSeparator(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepSpaceSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isSpaceSeparator(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeLineSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isLineSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepLineSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isLineSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeLineSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isLineSeparator(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepLineSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isLineSeparator(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeParagraphSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isParagraphSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepParagraphSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isParagraphSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeParagraphSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isParagraphSeparator(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepParagraphSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isParagraphSeparator(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepSeparators(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isSeparator(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isSeparator(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepSeparatorsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isSeparator(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeControlCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isControlCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepControlCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isControlCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeControlCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isControlCategory(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepControlCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isControlCategory(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeFormatCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isFormatCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepFormatCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isFormatCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeFormatCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isFormatCategory(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepFormatCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isFormatCategory(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removePrivateUseCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isPrivateUseCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepPrivateUseCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isPrivateUseCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removePrivateUseCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isPrivateUseCategory(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepPrivateUseCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isPrivateUseCategory(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeSurrogateCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isSurrogateCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepSurrogateCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isSurrogateCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeSurrogateCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isSurrogateCategory(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepSurrogateCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isSurrogateCategory(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeUnassignedCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isUnassignedCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepUnassignedCategory(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isUnassignedCategory(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeUnassignedCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isUnassignedCategory(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepUnassignedCategoryPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isUnassignedCategory(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeDashPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isDashPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepDashPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isDashPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeDashPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isDashPunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepDashPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isDashPunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeStartPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isStartPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepStartPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isStartPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeStartPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isStartPunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepStartPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isStartPunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeEndPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isEndPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepEndPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isEndPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeEndPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isEndPunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepEndPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isEndPunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeConnectorPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isConnectorPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepConnectorPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isConnectorPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeConnectorPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isConnectorPunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepConnectorPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isConnectorPunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeOtherPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isOtherPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepOtherPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isOtherPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeOtherPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isOtherPunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepOtherPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isOtherPunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeInitialQuotePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isInitialQuotePunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepInitialQuotePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isInitialQuotePunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeInitialQuotePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isInitialQuotePunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepInitialQuotePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isInitialQuotePunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeFinalQuotePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isFinalQuotePunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepFinalQuotePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isFinalQuotePunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeFinalQuotePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isFinalQuotePunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepFinalQuotePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isFinalQuotePunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeQuotePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isQuotePunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepQuotePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isQuotePunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeQuotePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isQuotePunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepQuotePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isQuotePunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removePunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepPunctuations(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isPunctuation(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removePunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isPunctuation(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepPunctuationsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isPunctuation(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeMathSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isMathSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepMathSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isMathSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeMathSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isMathSymbol(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepMathSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isMathSymbol(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeCurrencySymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isCurrencySymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepCurrencySymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isCurrencySymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeCurrencySymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isCurrencySymbol(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepCurrencySymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isCurrencySymbol(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeModifierSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isModifierSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepModifierSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isModifierSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeModifierSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isModifierSymbol(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepModifierSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isModifierSymbol(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeOtherSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isOtherSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepOtherSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isOtherSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeOtherSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isOtherSymbol(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepOtherSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isOtherSymbol(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }

    public static void removeSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (!CharacterHelper.isSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void keepSymbols(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int write = 0;
        int length = buffer.length();

        for (int read = 0; read < length; read++) {

            int codePoint = buffer.get(read);

            if (CharacterHelper.isSymbol(codePoint)) {
                buffer.set(write++, codePoint);
            }
        }

        buffer.setLength(write);
    }

    public static void removeSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int start = 0;

        while (start < length) {

            int codePoint = buffer.get(start);

            if (!CharacterHelper.isSymbol(codePoint)) {
                break;
            }

            start++;
        }

        if (start == 0) {
            return;
        }

        int remaining = length - start;

        for (int i = 0; i < remaining; i++) {
            buffer.set(i, buffer.get(start + i));
        }

        buffer.setLength(remaining);

    }

    public static void keepSymbolsPrefix(CodePointBuffer buffer) {

        if (buffer == null || buffer.isEmpty()) {
            return;
        }

        int length = buffer.length();
        int end = 0;

        while (end < length) {

            int codePoint = buffer.get(end);

            if (!CharacterHelper.isSymbol(codePoint)) {
                break;
            }

            end++;
        }

        buffer.setLength(end);
    }
}