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

    UNCAPITALIZE(CodePointUtils::uncapitalize),
    TOGGLE_CASE(CodePointUtils::toggleCase),

    ROTATE_LEFT(CodePointUtils::rotateLeft),
    ROTATE_RIGHT(CodePointUtils::rotateRight),
    INCREMENT(CodePointUtils::increment),
    DECREMENT(CodePointUtils::decrement),

    REMOVE_LAST(CodePointUtils::removeLast),
    REMOVE_FIRST(CodePointUtils::removeFirst),

    TO_BASE64(CodePointUtils::toBase64),
    FROM_BASE64(CodePointUtils::fromBase64),

    TO_XML_ENTITIES(CodePointUtils::toXmlEntities),
    FROM_XML_ENTITIES(CodePointUtils::fromXmlEntities),

    // SortCharacters

    // Resize (Set buffer to a specific size, fill it. (Whitespace? Underscore?)

    // emojis to text (aka) 🔥 to :fire: (and back)

    //Keep/Remove htmlEntities / xmlEntities

    // SPLIT PREPENT or /we on https://www.compart.com/en/unicode/U+001F
    // Or should we instead of a unicode character, use _ as a 'replacement' character?

    // Character based filters

    // KEEP [XXX] PREFIX // REMOVE [XXX] PREFIX
    // KEEP ALPHANUMERIC PREFIX
    // KEEP DIGIT PREFIX

    // Character.getType(cp) types

    REMOVE_VALID_CODE_POINTS(CodePointUtils::removeValidCodePoints),
    KEEP_VALID_CODE_POINTS(CodePointUtils::keepValidCodePoints),
    KEEP_VALID_CODE_POINTS_PREFIX(CodePointUtils::keepValidCodePointsPrefix),
    REMOVE_BMP_CODE_POINTS(CodePointUtils::removeBmpCodePoints),
    KEEP_BMP_CODE_POINTS(CodePointUtils::keepBmpCodePoints),
    KEEP_BMP_CODE_POINTS_PREFIX(CodePointUtils::keepBmpCodePointsPrefix),
    REMOVE_SUPPLEMENTARY_CODE_POINTS(CodePointUtils::removeSupplementaryCodePoints),
    KEEP_SUPPLEMENTARY_CODE_POINTS(CodePointUtils::keepSupplementaryCodePoints),
    KEEP_SUPPLEMENTARY_CODE_POINTS_PREFIX(CodePointUtils::keepSupplementaryCodePointsPrefix),
    REMOVE_LOWER_CASE(CodePointUtils::removeLowerCase),
    KEEP_LOWER_CASE(CodePointUtils::keepLowerCase),
    KEEP_LOWER_CASE_PREFIX(CodePointUtils::keepLowerCasePrefix),
    REMOVE_UPPER_CASE(CodePointUtils::removeUpperCase),
    KEEP_UPPER_CASE(CodePointUtils::keepUpperCase),
    KEEP_UPPER_CASE_PREFIX(CodePointUtils::keepUpperCasePrefix),
    REMOVE_TITLE_CASE(CodePointUtils::removeTitleCase),
    KEEP_TITLE_CASE(CodePointUtils::keepTitleCase),
    KEEP_TITLE_CASE_PREFIX(CodePointUtils::keepTitleCasePrefix),
    REMOVE_DIGITS(CodePointUtils::removeDigits),
    KEEP_DIGITS(CodePointUtils::keepDigits),
    KEEP_DIGITS_PREFIX(CodePointUtils::keepDigitsPrefix),
    REMOVE_DEFINED(CodePointUtils::removeDefined),
    KEEP_DEFINED(CodePointUtils::keepDefined),
    KEEP_DEFINED_PREFIX(CodePointUtils::keepDefinedPrefix),
    REMOVE_LETTERS(CodePointUtils::removeLetters),
    KEEP_LETTERS(CodePointUtils::keepLetters),
    KEEP_LETTERS_PREFIX(CodePointUtils::keepLettersPrefix),
    REMOVE_LETTER_OR_DIGITS(CodePointUtils::removeLetterOrDigits),
    KEEP_LETTER_OR_DIGITS(CodePointUtils::keepLetterOrDigits),
    KEEP_LETTER_OR_DIGITS_PREFIX(CodePointUtils::keepLetterOrDigitsPrefix),
    REMOVE_ALPHABETIC(CodePointUtils::removeAlphabetic),
    KEEP_ALPHABETIC(CodePointUtils::keepAlphabetic),
    KEEP_ALPHABETIC_PREFIX(CodePointUtils::keepAlphabeticPrefix),
    REMOVE_IDEOGRAPHICS(CodePointUtils::removeIdeographics),
    KEEP_IDEOGRAPHICS(CodePointUtils::keepIdeographics),
    KEEP_IDEOGRAPHICS_PREFIX(CodePointUtils::keepIdeographicsPrefix),
    REMOVE_JAVA_IDENTIFIER_START(CodePointUtils::removeJavaIdentifierStart),
    KEEP_JAVA_IDENTIFIER_START(CodePointUtils::keepJavaIdentifierStart),
    KEEP_JAVA_IDENTIFIER_START_PREFIX(CodePointUtils::keepJavaIdentifierStartPrefix),
    REMOVE_UNICODE_IDENTIFIER_START(CodePointUtils::removeUnicodeIdentifierStart),
    KEEP_UNICODE_IDENTIFIER_START(CodePointUtils::keepUnicodeIdentifierStart),
    KEEP_UNICODE_IDENTIFIER_START_PREFIX(CodePointUtils::keepUnicodeIdentifierStartPrefix),
    REMOVE_IDENTIFIER_IGNORABLE(CodePointUtils::removeIdentifierIgnorable),
    KEEP_IDENTIFIER_IGNORABLE(CodePointUtils::keepIdentifierIgnorable),
    KEEP_IDENTIFIER_IGNORABLE_PREFIX(CodePointUtils::keepIdentifierIgnorablePrefix),
    REMOVE_EMOJIS(CodePointUtils::removeEmojis),
    KEEP_EMOJIS(CodePointUtils::keepEmojis),
    KEEP_EMOJIS_PREFIX(CodePointUtils::keepEmojisPrefix),
    REMOVE_EMOJI_PRESENTATION(CodePointUtils::removeEmojiPresentation),
    KEEP_EMOJI_PRESENTATION(CodePointUtils::keepEmojiPresentation),
    KEEP_EMOJI_PRESENTATION_PREFIX(CodePointUtils::keepEmojiPresentationPrefix),
    REMOVE_EMOJI_MODIFIERS(CodePointUtils::removeEmojiModifiers),
    KEEP_EMOJI_MODIFIERS(CodePointUtils::keepEmojiModifiers),
    KEEP_EMOJI_MODIFIERS_PREFIX(CodePointUtils::keepEmojiModifiersPrefix),
    REMOVE_EMOJI_MODIFIER_BASE(CodePointUtils::removeEmojiModifierBase),
    KEEP_EMOJI_MODIFIER_BASE(CodePointUtils::keepEmojiModifierBase),
    KEEP_EMOJI_MODIFIER_BASE_PREFIX(CodePointUtils::keepEmojiModifierBasePrefix),
    REMOVE_EMOJI_COMPONENTS(CodePointUtils::removeEmojiComponents),
    KEEP_EMOJI_COMPONENTS(CodePointUtils::keepEmojiComponents),
    KEEP_EMOJI_COMPONENTS_PREFIX(CodePointUtils::keepEmojiComponentsPrefix),
    REMOVE_EXTENDED_PICTOGRAPHIC(CodePointUtils::removeExtendedPictographic),
    KEEP_EXTENDED_PICTOGRAPHIC(CodePointUtils::keepExtendedPictographic),
    KEEP_EXTENDED_PICTOGRAPHIC_PREFIX(CodePointUtils::keepExtendedPictographicPrefix),
    REMOVE_WHITESPACE(CodePointUtils::removeWhitespace),
    KEEP_WHITESPACE(CodePointUtils::keepWhitespace),
    KEEP_WHITESPACE_PREFIX(CodePointUtils::keepWhitespacePrefix),
    REMOVE_SPACE_CHARS(CodePointUtils::removeSpaceChars),
    KEEP_SPACE_CHARS(CodePointUtils::keepSpaceChars),
    KEEP_SPACE_CHARS_PREFIX(CodePointUtils::keepSpaceCharsPrefix),
    REMOVE_ISO_CONTROLS(CodePointUtils::removeISOControls),
    KEEP_ISO_CONTROLS(CodePointUtils::keepISOControls),
    KEEP_ISO_CONTROLS_PREFIX(CodePointUtils::keepISOControlsPrefix),
    REMOVE_MIRRORED(CodePointUtils::removeMirrored),
    KEEP_MIRRORED(CodePointUtils::keepMirrored),
    KEEP_MIRRORED_PREFIX(CodePointUtils::keepMirroredPrefix);

    private final ICodePointOperation operation;

    ECodePointOperation(ICodePointOperation operation) {
        this.operation = operation;
    }

    @Override
    public void apply(CodePointBuffer buffer) {
        operation.apply(buffer);
    }
}