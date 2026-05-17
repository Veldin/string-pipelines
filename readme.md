# Welcome to [StringPipelines](https://github.com/Veldin/string-pipelines)!

A small string utils library for Java that allows you to build reusable and testable string processing flows.

Usefull for **Sanitization, Normalization, Slug Generation, Filtering**, or any repeated operaions on strings.

## What is this?

The StringPipelineBuilder lets you define and compose string-processing flows in a clear, ordered way. The order in which you define operations is the order in which they are executed.

Example:
```java
AbstractStringPipeline pipeline = new StringPipelineBuilder()
        .pipe(STRIP)
        .pipe(NORMALIZE_SPACE)
        .pipe(LOWER_CASE)
        .pipe(CAPITALIZE)
        .build();
```

The pipeline classes are responsible for actually processing strings.

```java
    String result = simpleStringPipeline.apply(" this is a Simple  pipeline. ");
assertEquals("This is a simple pipeline.", result);
```

## More examples

You can easily create reusable pipelines for small tasks like generating slugs:

```java
    @Test
void createSlugExampleList() {

    AbstractStringPipeline slugPipeline =
            new StringPipelineBuilder()
                    .pipe(TRIM)
                    .pipe(LOWER_CASE)
                    .pipe(s -> s.replaceAll("\\s+", "-"))
                    .build();

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

    List<String> out = new ArrayList<>();
    for (String game : games){
        out.add(slugPipeline.apply(game));
    }

}
```
This gives the following output:
```java
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
```

## Functional Interface

I have defined the following functional interface to let anyone define a pipeline step, as long as it takes a string and returns a string.

```java
    @FunctionalInterface
public interface IStringOperation {
    String apply(String input);
}
```

Using org.apache.commons:commons-lang3, I created the following enum with some basic string operations.

```java
CAPITALIZE
        CHOMP
CHOP
        DEFAULT_STRING
DELETE_WHITESPACE
        GET_DIGITS
LOWER_CASE
        NORMALIZE_SPACE
REVERSE
        STRIP
STRIP_TO_EMPTY
        STRIP_TO_NULL
SWAP_CASE
        TRIM
TRIM_TO_EMPTY
        TRIM_TO_NULL
UNCAPITALIZE
        UPPER_CASE
```

But you can use anything as long as it takes a string and returns a string, for example, we can use both "capitalize" from this library, from StringUtils, or make something yourself.

```java
    .pipe(CAPITALIZE)                           // From this library
    .pipe(StringUtils::capitalize)              // From StringUtils
    .pipe(string -> myOwnFunction(string))      // From yourself
```

And of course, you can also use some regex. I think this library makes it nice to perform multiple regex actions in a row on a string.

```java
    .pipe(s -> s.replaceAll("\d+", ""))        // remove all digits.
        .pipe(s -> s.replaceAll("\\s+", "_"))      // then replaces whitespace (groups) with underscore.
```

## Nested pipelines

Because pipelines take strings and return strings, you can use pipelines inside other pipelines.

```java
    @Test
void shouldAllowNestedPipelinesWithoutCycles() {

    AbstractStringPipeline inner = new StringPipelineBuilder()
            .pipe(EStringOperation.TRIM)
            .pipe(EStringOperation.LOWER_CASE)
            .build();

    AbstractStringPipeline outer = new StringPipelineBuilder()
            .pipe(inner)
            .pipe(EStringOperation.REVERSE)
            .build();

    String result = outer.apply("  HeLLo  ");

    assertEquals("olleh", result);
}
```

## Implementations

The implementations of the pipeline classes are responsible for actually processing strings.

There are two implementations:


| Pipeline  | Description |
| ------------- | ------------- |
| SimpleStringPipeline  | Executes operations directly.  |
| CachedStringPipeline  | Optimizes repeating executions using Map<String, String> cache.  |

Both extend AbstractStringPipeline.

By default, a SimpleStringPipeline is created using the builder.

```java
AbstractStringPipeline inner = new StringPipelineBuilder()
        .pipe(EStringOperation.TRIM)
        .pipe(EStringOperation.LOWER_CASE)
        .build();
```

## CachedStringPipeline

Cashed() can be called to make the builder create a CashedStringPipeline instead.

```java
AbstractStringPipeline inner = new StringPipelineBuilder()
        .cached() // <--
        .pipe(EStringOperation.TRIM)
        .pipe(EStringOperation.LOWER_CASE)
        .build();
```

When using the CachedStringPipeline, the given input is checked to exist inside the its map. If it does not exist, the pipeline runs all operations, stores the result in the map, and returns it. Then next time the same input is seen, the value from the map is returned instead.

Note that Caching assumes pure functions. This makes it ideal for deterministic transformations like regex-heavy pipelines.

(Also Note that most 'simple' string operations are so fast, that the overhead is not worth it. But I use it for some reggex operations for creating proxy search values in a big CSV file, so then its worth it pretty quickly.)

When you want to get spicy, you can use a CashedStringPipeline as a step inside a (non cashed) simpleStringPipeline to cashe certain parts of the chain.

## CachedStringPipelineBenchmarkTest

I've added a non-real-world scenario test where you can run some arbitrair pipeline on a repeating set and it loggs times. I reccommend playing around with the pipeline steps and the input to see if a cashed pipeline is worth for your spesific use-case.

```java
=== BENCHMARK RESULTS ===
Simple pipeline: 94 ms
Cached pipeline: 3 ms
Speedup: 31.333333333333332 x
```

Have fun <3

-------
EDIT:

## CodePointsPipeline

As a Proof-Of-Concept I have added a version of the pipeline that takes and returs a string, but uses the codePoints array internaly.

The functional interface now looks like:

```java
/**
 * Represents a single codePoint transformation step.
 * Implementations should (ideally) be pure functions.
 */
@FunctionalInterface
public interface ICodePointOperation {

    /**
     * Applies a transformation to the input array.
     * (The input array might change in the operation.)
     *
     * @param input source array
     * @return the transformed array
     */
    int[] apply(int[] input);
}
```

A pipeline now looks like:

```java
AbstractCodePointPipeline simpleCodePointPipeline =
        new CodePointPipelineBuilder()
                .pipe(CAPITALIZE)
                .pipe(CHOMP)
                .build();

String result = simpleCodePointPipeline.apply("this is a Simple pipeline.\r");

assertInstanceOf(AbstractCodePointPipeline.class, simpleCodePointPipeline);
assertEquals("This is a Simple pipeline.", result); // 'This' is capitalized and the '\r' is chomped.
```

I'm still working on this :)