package com.veldin.stringpipelines.strings.utils;

import com.veldin.stringpipelines.codepoints.CodePointBuffer;

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

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' getDigits.
    public static void getDigits(CodePointBuffer buffer) {

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

    public static boolean isEmpty(CodePointBuffer buffer) {
        return buffer == null || buffer.length() == 0;
    }
}