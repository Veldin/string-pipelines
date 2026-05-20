package com.veldin.stringpipelines.strings;
import com.veldin.stringpipelines.IStringOperation;
import org.apache.commons.lang3.StringUtils;

/**
 * Common reusable string operations backed by
 * Apache Commons Lang StringUtils.
 */
public enum EStringOperation implements IStringOperation {

    CAPITALIZE(StringUtils::capitalize),
    CHOMP(StringUtils::chomp),
    CHOP(StringUtils::chop),
    DEFAULT_STRING(StringUtils::defaultString),
    DELETE_WHITESPACE(StringUtils::deleteWhitespace),
    GET_DIGITS(StringUtils::getDigits),
    LOWER_CASE(StringUtils::lowerCase),
    NORMALIZE_SPACE(StringUtils::normalizeSpace),
    REVERSE(StringUtils::reverse),
    STRIP(StringUtils::strip),
    STRIP_TO_EMPTY(StringUtils::stripToEmpty),
    STRIP_TO_NULL(StringUtils::stripToNull),
    SWAP_CASE(StringUtils::swapCase),
    TRIM(StringUtils::trim),
    TRIM_TO_EMPTY(StringUtils::trimToEmpty),
    TRIM_TO_NULL(StringUtils::trimToNull),
    UNCAPITALIZE(StringUtils::uncapitalize),
    UPPER_CASE(StringUtils::upperCase);


    private final IStringOperation operation;

    EStringOperation(IStringOperation operation) {
        this.operation = operation;
    }

    @Override
    public String apply(String input) {
        return operation.apply(input);
    }
}