# Welcome to [StringPipelines](https://github.com/Veldin/ResultTryEx)!

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

You can easily create reusable pipelines for little tasks like generating slugs:

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

I have defined the following function interface to let anyone define any pipeline step aslong as it takes a string, and return a string.

```java
    @FunctionalInterface
    public interface IStringOperation {
        String apply(String input);
    }
```

Using org.apache.commons::commons-lang3 I've created the following enum with some basic string operations.

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

But you can use anything aslong as it takes a string and returns a string, for example, we can use both "capitalize" from this library, from StringUtils, or make something yourself.

```java
    .pipe(CAPITALIZE)                           // From this library
    .pipe(StringUtils::capitalize)              // From StringUtils
    .pipe(string -> myOwnFunction(string))      // From yourself
```

And ofcource you can also do some reggex. I think this library makes it nice to do multiple reggex acctions in a row on a string.

```java
    .pipe(s -> s.replaceAll("\d+", ""))        // remove all digits.
    .pipe(s -> s.replaceAll("\\s+", "_"))      // then replaces whitespace (groups) with underscore.
```

## Nested pipelines

Because pipelines take strings, and return strings, you can use pipelines inside pipelines. In this example the first pipeline runs TRIM and LOWER_CASE, and then we have a second pipeline that also calls REVERSE on the string.

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

## implementations

The implementations of the pipeline classes are responsible for actually processing strings.

There are two implementations:


| Pipeline  | Description |
| ------------- | ------------- |
| SimpleStringPipeline  | Executes operations directly.  |
| CachedStringPipeline  | Optimizes repeating executions using Map<String, String> cache.  |

Both extend AbstractStringPipeline.

Default a SimpleStringPipeline is created using the builder.

```java
AbstractStringPipeline inner = new StringPipelineBuilder()
        .pipe(EStringOperation.TRIM)
        .pipe(EStringOperation.LOWER_CASE)
        .build();
```

## CachedStringPipeline

Cashed() can be called to make clear you want the CashedStringPipeline instead.

```java
AbstractStringPipeline inner = new StringPipelineBuilder()
        .cached() // <--
        .pipe(EStringOperation.TRIM)
        .pipe(EStringOperation.LOWER_CASE)
        .build();
```

When using the CachedStringPipeline, the given input is checked to exist inside the its map. If it doesn't it runs all operations and stores the value inside the map and returns the value. Then next time the same input is seen, the value from the map is returned instead.

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