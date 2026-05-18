package com.veldin.stringpipelines.codepoints;

class ECodePointOperationTest {

    /**
     *

    @Test
    @DisplayName("CAPITALIZE should behave the same as CodePointUtils.capitalize")
    void capitalizeShouldMatchCodePointsUtils() {

        int[] input = "hello world".codePoints().toArray();

        assertEquals(
                codePointsToString(CodePointUtils.capitalize(input)),
                codePointsToString(ECodePointOperation.CAPITALIZE.apply(input))
        );
    }

    @Test
    @DisplayName("CHOMP should behave the same as CodePointUtils.capitalize")
    void chompShouldMatchCodePointsUtils() {

        int[] input = "hello world\n".codePoints().toArray();

        assertEquals(
                codePointsToString(CodePointUtils.chomp(input)),
                codePointsToString(ECodePointOperation.CHOMP.apply(input))
        );
    }

    @Test
    @DisplayName("CHOP should behave the same as CodePointUtils.capitalize")
    void chopShouldMatchCodePointsUtils() {

        int[] input = "hello world".codePoints().toArray();

        assertEquals(
                codePointsToString(CodePointUtils.chop(input)),
                codePointsToString(ECodePointOperation.CHOP.apply(input))
        );
    }

    private String codePointsToString(int[] input) {
        return new String(input, 0, input.length);
    }
     */
}