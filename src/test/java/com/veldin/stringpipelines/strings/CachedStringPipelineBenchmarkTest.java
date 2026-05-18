package com.veldin.stringpipelines.strings;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.veldin.stringpipelines.strings.EStringOperation.*;
import static com.veldin.stringpipelines.strings.EStringOperation.CAPITALIZE;

class CachedStringPipelineBenchmarkTest {

    private static final int WARMUP = 10_000;
    private static final int ITERATIONS = 200_000;

    private static final List<String> INPUTS = List.of(
            "Title	Released",
            "Grand Theft Auto: San Andreas	2004",
            "Grand Theft Auto: Vice City	2002",
            "Tony Hawk's Pro Skater 3	2001",
            "Final Fantasy XII	2006",
            "Jak and Daxter: The Precursor Legacy	2001",
            "Ratchet & Clank: Up Your Arsenal	2004",
            "Kingdom Hearts II	2005",
            "Bully	2006",
            "TimeSplitters: Future Perfect	2005",
            "Jak 3	2004",
            "Jak II	2003"
    );

    @Test
    void benchmarkCachedVsSimplePipeline() {

        AbstractStringPipeline simple = new StringPipelineBuilder()
                .simple()
                .pipe(STRIP)
                .pipe(NORMALIZE_SPACE)
                .pipe(LOWER_CASE)
                .pipe(CAPITALIZE)
                // Only attempt to be faster when using 'heavy' operations, else the overhead won't be worth it.
                .pipe(s -> s.replaceAll("\\s+", "_"))
                .build();

        AbstractStringPipeline cached = new StringPipelineBuilder()
                .cached()
                .pipe(STRIP)
                .pipe(NORMALIZE_SPACE)
                .pipe(LOWER_CASE)
                .pipe(CAPITALIZE)
                // Only attempt to be faster when using 'heavy' operations, else the overhead won't be worth it.
                .pipe(s -> s.replaceAll("\\s+", "_"))
                .build();

        // warmup run (JIT and stuff.)
        run(simple, WARMUP);
        run(cached, WARMUP);

        long simpleTime = run(simple, ITERATIONS);
        long cachedTime = run(cached, ITERATIONS);

        System.out.println("=== BENCHMARK RESULTS ===");
        System.out.println("Simple pipeline: " + simpleTime + " ms");
        System.out.println("Cached pipeline: " + cachedTime + " ms");
        System.out.println("Speedup: " + (simpleTime / (double) cachedTime) + " x");
    }

    private long run(AbstractStringPipeline pipeline, int iterations) {

        long start = System.nanoTime();

        for (int i = 0; i < iterations; i++) {
            String input = INPUTS.get(i % INPUTS.size());
            pipeline.apply(input);
        }

        long end = System.nanoTime();

        return (end - start) / 1_000_000;
    }
}