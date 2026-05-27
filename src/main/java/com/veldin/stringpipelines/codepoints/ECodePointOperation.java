package com.veldin.stringpipelines.codepoints;

import com.veldin.stringpipelines.ICodePointOperation;
import com.veldin.stringpipelines.codepoints.utils.CodePointUtils;

public enum ECodePointOperation implements ICodePointOperation {

    CAPITALIZE(CodePointUtils::capitalize),
    CHOMP(CodePointUtils::chomp),
    CHOP(CodePointUtils::chop),
    LOWER_CASE(CodePointUtils::lowerCase),
    NORMALIZE_SPACE(CodePointUtils::normalizeSpace),
    REVERSE(CodePointUtils::reverse),
    STRIP(CodePointUtils::strip),

    // ToggleCase

    // RotateLeft
    // RotateRight
    // Increment
    // Decrement

    //isBase64Characters

    //Keep/Remove htmlEntities / xmlEntities

    // Character based filters

    REMOVE_VALID_CODE_POINTS(CodePointUtils::removeValidCodePoints),
    KEEP_VALID_CODE_POINTS(CodePointUtils::keepValidCodePoints),
    REMOVE_BMP_CODE_POINTS(CodePointUtils::removeBmpCodePoints),
    KEEP_BMP_CODE_POINTS(CodePointUtils::keepBmpCodePoints),
    REMOVE_SUPPLEMENTARY_CODE_POINTS(CodePointUtils::removeSupplementaryCodePoints),
    KEEP_SUPPLEMENTARY_CODE_POINTS(CodePointUtils::keepSupplementaryCodePoints),
    REMOVE_LOWER_CASE(CodePointUtils::removeLowerCase),
    KEEP_LOWER_CASE(CodePointUtils::keepLowerCase),
    REMOVE_UPPER_CASE(CodePointUtils::removeUpperCase),
    KEEP_UPPER_CASE(CodePointUtils::keepUpperCase),
    REMOVE_TITLE_CASE(CodePointUtils::removeTitleCase),
    KEEP_TITLE_CASE(CodePointUtils::keepTitleCase),
    REMOVE_DIGITS(CodePointUtils::removeDigits),
    KEEP_DIGITS(CodePointUtils::keepDigits),
    REMOVE_DEFINED(CodePointUtils::removeDefined),
    KEEP_DEFINED(CodePointUtils::keepDefined),
    REMOVE_LETTERS(CodePointUtils::removeLetters),
    KEEP_LETTERS(CodePointUtils::keepLetters),
    REMOVE_LETTER_OR_DIGITS(CodePointUtils::removeLetterOrDigits),
    KEEP_LETTER_OR_DIGITS(CodePointUtils::keepLetterOrDigits),
    REMOVE_ALPHABETIC(CodePointUtils::removeAlphabetic),
    KEEP_ALPHABETIC(CodePointUtils::keepAlphabetic),
    REMOVE_IDEOGRAPHICS(CodePointUtils::removeIdeographics),
    KEEP_IDEOGRAPHICS(CodePointUtils::keepIdeographics),
    REMOVE_JAVA_IDENTIFIER_START(CodePointUtils::removeJavaIdentifierStart),
    KEEP_JAVA_IDENTIFIER_START(CodePointUtils::keepJavaIdentifierStart),
    REMOVE_UNICODE_IDENTIFIER_START(CodePointUtils::removeUnicodeIdentifierStart),
    KEEP_UNICODE_IDENTIFIER_START(CodePointUtils::keepUnicodeIdentifierStart),
    REMOVE_IDENTIFIER_IGNORABLE(CodePointUtils::removeIdentifierIgnorable),
    KEEP_IDENTIFIER_IGNORABLE(CodePointUtils::keepIdentifierIgnorable),
    REMOVE_EMOJIS(CodePointUtils::removeEmojis),
    KEEP_EMOJIS(CodePointUtils::keepEmojis),
    REMOVE_EMOJI_PRESENTATION(CodePointUtils::removeEmojiPresentation),
    KEEP_EMOJI_PRESENTATION(CodePointUtils::keepEmojiPresentation),
    REMOVE_EMOJI_MODIFIERS(CodePointUtils::removeEmojiModifiers),
    KEEP_EMOJI_MODIFIERS(CodePointUtils::keepEmojiModifiers),
    REMOVE_EMOJI_MODIFIER_BASE(CodePointUtils::removeEmojiModifierBase),
    KEEP_EMOJI_MODIFIER_BASE(CodePointUtils::keepEmojiModifierBase),
    REMOVE_EMOJI_COMPONENTS(CodePointUtils::removeEmojiComponents),
    KEEP_EMOJI_COMPONENTS(CodePointUtils::keepEmojiComponents),
    REMOVE_EXTENDED_PICTOGRAPHIC(CodePointUtils::removeExtendedPictographic),
    KEEP_EXTENDED_PICTOGRAPHIC(CodePointUtils::keepExtendedPictographic),
    REMOVE_WHITESPACE(CodePointUtils::removeWhitespace),
    KEEP_WHITESPACE(CodePointUtils::keepWhitespace),
    REMOVE_SPACE_CHARS(CodePointUtils::removeSpaceChars),
    KEEP_SPACE_CHARS(CodePointUtils::keepSpaceChars),
    REMOVE_ISO_CONTROLS(CodePointUtils::removeISOControls),
    KEEP_ISO_CONTROLS(CodePointUtils::keepISOControls),
    REMOVE_MIRRORED(CodePointUtils::removeMirrored),
    KEEP_MIRRORED(CodePointUtils::keepMirrored);

    private final ICodePointOperation operation;

    ECodePointOperation(ICodePointOperation operation) {
        this.operation = operation;
    }

    @Override
    public void apply(CodePointBuffer buffer) {
        operation.apply(buffer);
    }
}