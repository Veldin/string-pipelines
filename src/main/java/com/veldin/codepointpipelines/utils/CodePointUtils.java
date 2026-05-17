package com.veldin.codepointpipelines.utils;

/**
 * Utility methods operating directly on Unicode code point arrays.
 *
 * Methods in this class may modify the provided input array in-place whenever possible, as long as the
 * returned result is behaviorally correct.
 */
public class CodePointUtils {

    private CodePointUtils() {
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' capitalize.
    public static int[] capitalize(int[] codePoints) {
        if (isEmpty(codePoints)) {
            return codePoints;
        }

        int firstCodePoint = codePoints[0];
        int newCodePoint = Character.toTitleCase(firstCodePoint);

        if (firstCodePoint != newCodePoint) {
            codePoints[0] = newCodePoint;
        }

        return codePoints;
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' chomp.
    public static int[] chomp(int[] codePoints) {
        if (isEmpty(codePoints)) {
            return codePoints;
        }

        int length = codePoints.length;

        // There is only one character, check if it's a character we chomp.
        if (length == 1) {
            int codePoint = codePoints[0];
            return (codePoint == '\r' || codePoint == '\n')  ? new int[0] : codePoints;
        }

        int lastIdx = length - 1;
        int last = codePoints[lastIdx];

        if (last == '\n') {
            if (codePoints[lastIdx - 1] == '\r') {
                lastIdx--;
            }
        } else if (last != '\r') {
            return codePoints;
        }

        int[] result = new int[lastIdx];
        System.arraycopy(codePoints, 0, result, 0, lastIdx);

        return result;
    }

    // Implementation tries to mimic 'org.apache.commons.lang3.StringUtils' chop.
    public static int[] chop(int[] codePoints) {
        if (codePoints == null) {
            return null;
        }

        int length = codePoints.length;

        if (length < 2) {
            return new int[0];
        }

        int lastIdx = length - 1;
        int last = codePoints[lastIdx];

        // Handle \r\n first
        if (last == '\n' && codePoints[lastIdx - 1] == '\r') {
            int[] result = new int[lastIdx - 1];
            System.arraycopy(codePoints, 0, result, 0, lastIdx - 1);
            return result;
        }

        // Remove last code point
        int[] result = new int[lastIdx];
        System.arraycopy(codePoints, 0, result, 0, lastIdx);

        return result;
    }

    public static boolean isEmpty(int[] codePoints) {
        return codePoints == null || codePoints.length == 0;
    }
}
