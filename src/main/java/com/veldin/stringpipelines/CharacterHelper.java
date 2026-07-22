package com.veldin.stringpipelines;

public final class CharacterHelper {

    // Private constructor to hide the implicit public one.
    private CharacterHelper() {}

    public static boolean isAsciiLetter(int codePoint) {
        return (codePoint >= 'A' && codePoint <= 'Z')
                || (codePoint >= 'a' && codePoint <= 'z');
    }

    public static boolean isAsciiUpperCaseLetter(int codePoint) {
        return codePoint >= 'A' && codePoint <= 'Z';
    }

    public static boolean isAsciiLowerCaseLetter(int codePoint) {
        return codePoint >= 'a' && codePoint <= 'z';
    }

    public static boolean isAsciiDigit(int codePoint) {
        return codePoint >= '0' && codePoint <= '9';
    }

    public static boolean isAsciiAlphanumeric(int codePoint) {
        return isAsciiLetter(codePoint)
                || isAsciiDigit(codePoint);
    }

    public static boolean isHexDigit(int codePoint) {
        return isAsciiDigit(codePoint)
                || (codePoint >= 'A' && codePoint <= 'F')
                || (codePoint >= 'a' && codePoint <= 'f');
    }

    public static boolean isUuidCharacter(int codePoint) {
        return isHexDigit(codePoint)
                || codePoint == '-';
    }

    public static boolean isBinaryDigit(int codePoint) {
        return codePoint == '0' || codePoint == '1';
    }

    public static boolean isOctalDigit(int codePoint) {
        return codePoint >= '0' && codePoint <= '7';
    }

    public static boolean isBase64Character(int codePoint) {
        return isAsciiAlphanumeric(codePoint)
                || codePoint == '+'
                || codePoint == '/'
                || codePoint == '=';
    }

    public static boolean isBase64UrlCharacter(int codePoint) {
        return isAsciiAlphanumeric(codePoint)
                || codePoint == '-'
                || codePoint == '_'
                || codePoint == '=';
    }

    public static boolean isAsciiWhitespace(int codePoint) {
        return codePoint == ' '
                || codePoint == '\t'
                || codePoint == '\n'
                || codePoint == '\r'
                || codePoint == '\f';
    }

    public static boolean isAsciiPrintable(int codePoint) {
        return codePoint >= 0x20 && codePoint <= 0x7E;
    }

    public static boolean isAsciiControl(int codePoint) {
        return (codePoint >= 0x00 && codePoint <= 0x1F)
                || codePoint == 0x7F;
    }

    public static boolean isTitlecaseLetter(int codePoint) {
        return Character.getType(codePoint) == Character.TITLECASE_LETTER;
    }

    public static boolean isModifierLetter(int codePoint) {
        return Character.getType(codePoint) == Character.MODIFIER_LETTER;
    }

    public static boolean isOtherLetter(int codePoint) {
        return Character.getType(codePoint) == Character.OTHER_LETTER;
    }

    public static boolean isNonSpacingMark(int codePoint) {
        return Character.getType(codePoint) == Character.NON_SPACING_MARK;
    }

    public static boolean isCombiningSpacingMark(int codePoint) {
        return Character.getType(codePoint) == Character.COMBINING_SPACING_MARK;
    }

    public static boolean isEnclosingMark(int codePoint) {
        return Character.getType(codePoint) == Character.ENCLOSING_MARK;
    }

    public static boolean isMark(int codePoint) {
        return switch (Character.getType(codePoint)) {
            case Character.NON_SPACING_MARK,
                 Character.COMBINING_SPACING_MARK,
                 Character.ENCLOSING_MARK -> true;
            default -> false;
        };
    }

    public static boolean isDecimalDigitNumber(int codePoint) {
        return Character.getType(codePoint) == Character.DECIMAL_DIGIT_NUMBER;
    }

    public static boolean isLetterNumber(int codePoint) {
        return Character.getType(codePoint) == Character.LETTER_NUMBER;
    }

    public static boolean isOtherNumber(int codePoint) {
        return Character.getType(codePoint) == Character.OTHER_NUMBER;
    }

    public static boolean isNumber(int codePoint) {
        return switch (Character.getType(codePoint)) {
            case Character.DECIMAL_DIGIT_NUMBER,
                 Character.LETTER_NUMBER,
                 Character.OTHER_NUMBER -> true;
            default -> false;
        };
    }

    public static boolean isSpaceSeparator(int codePoint) {
        return Character.getType(codePoint) == Character.SPACE_SEPARATOR;
    }

    public static boolean isLineSeparator(int codePoint) {
        return Character.getType(codePoint) == Character.LINE_SEPARATOR;
    }

    public static boolean isParagraphSeparator(int codePoint) {
        return Character.getType(codePoint) == Character.PARAGRAPH_SEPARATOR;
    }

    public static boolean isSeparator(int codePoint) {
        return switch (Character.getType(codePoint)) {
            case Character.SPACE_SEPARATOR,
                 Character.LINE_SEPARATOR,
                 Character.PARAGRAPH_SEPARATOR -> true;
            default -> false;
        };
    }

    public static boolean isControlCategory(int codePoint) {
        return Character.getType(codePoint) == Character.CONTROL;
    }

    public static boolean isFormatCategory(int codePoint) {
        return Character.getType(codePoint) == Character.FORMAT;
    }

    public static boolean isPrivateUseCategory(int codePoint) {
        return Character.getType(codePoint) == Character.PRIVATE_USE;
    }

    public static boolean isSurrogateCategory(int codePoint) {
        return Character.getType(codePoint) == Character.SURROGATE;
    }

    public static boolean isUnassignedCategory(int codePoint) {
        return Character.getType(codePoint) == Character.UNASSIGNED;
    }

    public static boolean isDashPunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.DASH_PUNCTUATION;
    }

    public static boolean isStartPunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.START_PUNCTUATION;
    }

    public static boolean isEndPunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.END_PUNCTUATION;
    }

    public static boolean isConnectorPunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.CONNECTOR_PUNCTUATION;
    }

    public static boolean isOtherPunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.OTHER_PUNCTUATION;
    }

    public static boolean isInitialQuotePunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.INITIAL_QUOTE_PUNCTUATION;
    }

    public static boolean isFinalQuotePunctuation(int codePoint) {
        return Character.getType(codePoint) == Character.FINAL_QUOTE_PUNCTUATION;
    }

    public static boolean isQuotePunctuation(int codePoint) {
        return switch (Character.getType(codePoint)) {
            case Character.INITIAL_QUOTE_PUNCTUATION,
                 Character.FINAL_QUOTE_PUNCTUATION -> true;
            default -> false;
        };
    }

    public static boolean isPunctuation(int codePoint) {
        return switch (Character.getType(codePoint)) {
            case Character.DASH_PUNCTUATION,
                 Character.START_PUNCTUATION,
                 Character.END_PUNCTUATION,
                 Character.CONNECTOR_PUNCTUATION,
                 Character.OTHER_PUNCTUATION,
                 Character.INITIAL_QUOTE_PUNCTUATION,
                 Character.FINAL_QUOTE_PUNCTUATION -> true;
            default -> false;
        };
    }

    public static boolean isMathSymbol(int codePoint) {
        return Character.getType(codePoint) == Character.MATH_SYMBOL;
    }

    public static boolean isCurrencySymbol(int codePoint) {
        return Character.getType(codePoint) == Character.CURRENCY_SYMBOL;
    }

    public static boolean isModifierSymbol(int codePoint) {
        return Character.getType(codePoint) == Character.MODIFIER_SYMBOL;
    }

    public static boolean isOtherSymbol(int codePoint) {
        return Character.getType(codePoint) == Character.OTHER_SYMBOL;
    }

    public static boolean isSymbol(int codePoint) {
        return switch (Character.getType(codePoint)) {
            case Character.MATH_SYMBOL,
                 Character.CURRENCY_SYMBOL,
                 Character.MODIFIER_SYMBOL,
                 Character.OTHER_SYMBOL ->
                    true;
            default -> false;
        };
    }
}