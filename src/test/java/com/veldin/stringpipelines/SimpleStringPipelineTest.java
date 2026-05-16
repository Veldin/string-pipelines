package com.veldin.stringpipelines;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.veldin.stringpipelines.EStringOperation.*;
import static org.junit.jupiter.api.Assertions.*;

class SimpleStringPipelineTest {

    @Test
    void createBasicExample() {

        AbstractStringPipeline simpleStringPipeline =
                new StringPipelineBuilder()
                        .pipe(STRIP)
                        .pipe(NORMALIZE_SPACE)
                        .pipe(LOWER_CASE)
                        .pipe(CAPITALIZE)
                        .build();

        String result = simpleStringPipeline.apply(" this is a Simple  pipeline. ");

        assertInstanceOf(SimpleStringPipeline.class, simpleStringPipeline);
        assertEquals("This is a simple pipeline.", result);
    }

    @Test
    void createSlugExample() {

        AbstractStringPipeline slugPipeline =
                new StringPipelineBuilder()
                        .pipe(TRIM)
                        .pipe(LOWER_CASE)
                        .pipe(s -> s.replaceAll("[^a-z0-9\\s-]", ""))
                        .pipe(s -> s.replaceAll("\\s+", "-"))
                        .build();

        String result =
                slugPipeline.apply(" imagine this being something you want slugged! ");

        assertInstanceOf(SimpleStringPipeline.class, slugPipeline);
        assertEquals("imagine-this-being-something-you-want-slugged", result);
    }

    @Test
    void createSlugExampleList() {

        List<String> games = List.of(
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

        AbstractStringPipeline slugPipeline =
                new StringPipelineBuilder()
                        .pipe(TRIM)
                        .pipe(LOWER_CASE)
                        .pipe(s -> s.replaceAll("\\s+", "-"))
                        .build();

        List<String> out = new ArrayList<>();
        for (String game : games){
            out.add(slugPipeline.apply(game));
        }

        /*
            0 = "title-released"
            1 = "grand-theft-auto:-san-andreas-2004"
            2 = "grand-theft-auto:-vice-city-2002"
            3 = "tony-hawk's-pro-skater-3-2001"
            4 = "final-fantasy-xii-2006"
            5 = "jak-and-daxter:-the-precursor-legacy-2001"
            6 = "ratchet-&-clank:-up-your-arsenal-2004"
            7 = "kingdom-hearts-ii-2005"
            8 = "bully-2006"
            9 = "timesplitters:-future-perfect-2005"
            10 = "jak-3-2004"
            11 = "jak-ii-2003"
         */

        assertEquals(games.size(), out.size());
    }

    @Test
    void sanitizeUsernameExample() {

        AbstractStringPipeline usernamePipeline =
                new StringPipelineBuilder()
                        .pipe(TRIM)
                        .pipe(LOWER_CASE)
                        .pipe(s -> s.replaceAll("[^a-z0-9_]", ""))
                        .build();

        String result = usernamePipeline.apply("  John_Doe!!!  ");
        assertEquals( "john_doe", result);
    }

    @Test
    void normalizeSearchQueryExample() {

        AbstractStringPipeline searchPipeline =
                new StringPipelineBuilder()
                        .pipe(TRIM)
                        .pipe(LOWER_CASE)
                        .pipe(s -> s.replaceAll("\\s+", " "))
                        .build();

        String result = searchPipeline.apply("   Hello     WORLD ");
        assertEquals("hello world", result);
    }

    @Test
    void sanitizeFileNameExample() {

        AbstractStringPipeline filenamePipeline =
                new StringPipelineBuilder()
                        .pipe(TRIM)
                        .pipe(LOWER_CASE)
                        .pipe(s -> s.replaceAll("[\\\\/:*?\"<>|]", ""))
                        .pipe(s -> s.replaceAll("\\s+", "-"))
                        .build();

        String result = filenamePipeline.apply("My File: Version 1?.txt");
        assertEquals("my-file-version-1.txt", result);
    }

    @Test
    void normalizeNumberExample() {

        AbstractStringPipeline phonePipeline =
                new StringPipelineBuilder()
                        .pipe(s -> s.replaceAll("[^0-9]", ""))
                        .build();

        String result = phonePipeline.apply("+31 (0)6 12 34 56 78");
        assertEquals("310612345678", result);
    }

    @Test
    void shouldSupportNestedPipelines() {

        StringPipelineBuilder exampleBuilder =
                new StringPipelineBuilder()
                        .pipe(TRIM)
                        .pipe(LOWER_CASE);

        AbstractStringPipeline slugify =
                new StringPipelineBuilder()
                        .pipe(exampleBuilder.build())   // Build example pipeline 'inside' other pipeline
                        .pipe(s -> s.replaceAll("\\s+", "-"))
                        .build();

        String result = slugify.apply(" Hello World ");
        assertEquals( "hello-world", result);
    }
}