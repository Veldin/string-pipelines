package com.veldin.stringpipelines;

import com.veldin.stringpipelines.codepoints.AbstractCodePointPipeline;
import com.veldin.stringpipelines.codepoints.CodePointPipelineBuilder;
import com.veldin.stringpipelines.codepoints.ECodePointOperation;
import com.veldin.stringpipelines.strings.AbstractStringPipeline;
import com.veldin.stringpipelines.strings.EStringOperation;
import com.veldin.stringpipelines.strings.StringPipelineBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PipelinePerformanceComparisonTest {

    @Test
    void compareStringPipelineVsCodePointPipeline() {

        // String pipeline
        AbstractStringPipeline stringPipeline =
                new StringPipelineBuilder()
                        .pipe(EStringOperation.CAPITALIZE)
                        .pipe(EStringOperation.CHOMP)
                        .pipe(EStringOperation.CHOP)
                        .pipe(EStringOperation.DELETE_WHITESPACE)
                        .build();

        // Code point pipeline
        AbstractCodePointPipeline codePointPipeline =
                new CodePointPipelineBuilder()
                        .pipe(ECodePointOperation.CAPITALIZE)
                        .pipe(ECodePointOperation.CHOMP)
                        .pipe(ECodePointOperation.CHOP)
                        .pipe(ECodePointOperation.DELETE_WHITESPACE)
                        .build();

        // Create large test dataset
        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < 1_000_000; i++) {
            inputs.add("   This Is Example Input  Number  " + i + " !!!\\r\\n");
        }

        // Warmup JVM
        for (String input : inputs) {
            stringPipeline.apply(input);
            codePointPipeline.apply(input);
        }

        // Measure String pipeline
        long stringStart = System.nanoTime();

        for (String input : inputs) {
            stringPipeline.apply(input);
        }

        long stringEnd = System.nanoTime();

        // Measure CodePoint pipeline
        long codePointStart = System.nanoTime();

        for (String input : inputs) {
            codePointPipeline.apply(input);
        }

        long codePointEnd = System.nanoTime();

        long stringDurationMs = (stringEnd - stringStart) / 1_000_000;
        long codePointDurationMs = (codePointEnd - codePointStart) / 1_000_000;

        System.out.println("String pipeline duration     : " + stringDurationMs + " ms");
        System.out.println("Code point pipeline duration : " + codePointDurationMs + " ms");

        double ratio = (double) codePointDurationMs / stringDurationMs;

        System.out.println("CodePoint/String ratio       : " + ratio);
    }
}
