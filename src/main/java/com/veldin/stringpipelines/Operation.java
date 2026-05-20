package com.veldin.stringpipelines;

public sealed interface Operation
        permits IStringOperation, ICodePointOperation, EOperation {
}