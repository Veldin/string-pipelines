# Welcome to [StringPipelines](https://github.com/Veldin/string-pipelines)!

A small string utils library for Java that allows you to build reusable and testable string processing flows.

Usefull for **Sanitization, Normalization, Slug Generation, Filtering**, or any repeated operaions on strings.

## Motivation

I originally started building this because I noticed I kept rewriting the same kinds of string transformations over and over again (across projects). Things like sanitization, slug generation, normalization, CSV cleanup, regex replacements, etc. usually start simple, but quickly become messy and hard to reuse.

The idea behind this library is to make those transformations composable, testable, and reusable, while also experimenting with ways to make them faster internally.

## Maven Central Repository

Get this as a dependency via the Maven Central Repository.

```java
<dependency>
    <groupId>com.veldin</groupId>
    <artifactId>string-pipelines</artifactId>
    <version>0.0.1</version>
</dependency>
```

## What is this?

The OperationsPipelineBuilder lets you define and compose string-processing flows in a clear, ordered way.

One thing that was very important to me while designing this was that the pipeline should feel predictable. The order in which you define operations is always the order in which they are executed.

Example:
```java
AbstractPipeline pipeline =
        new OperationsPipelineBuilder()
                .pipe(EStringOperation.DELETE_WHITESPACE)
                .pipe(EStringOperation.CAPITALIZE)
                .pipe(EStringOperation.CHOMP)
                .build();
```

The build pipeline classes are responsible for actually processing strings.

```java
    String result = pipeline.apply("this is a Simple pipeline.\r");

    // 'This' is capitalized and the '\r' is chomped, and whitespace is removed.
    assertEquals("ThisisaSimplepipeline.", result);
```

## More examples

You can easily create reusable pipelines for small tasks like generating slugs.
This was one of the first practical use-cases I built it for.

```java
    @Test
void createSlugExampleList() {

    AbstractPipeline pipeline =
            new StringPipelineBuilder()
                    .pipe(EStringOperation.TRIM)
                    .pipe(EStringOperation.LOWER_CASE)
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

## Functional Interfaces

I defined two functional interfaces to make it easy to plug in your own pipeline steps.

### IStringOperation

The first takes in any function that takes a String and returns a (modified) String.

```java
@FunctionalInterface
public interface IStringOperation {
    String apply(String input);
}
```
For example:

```java
    .pipe(CAPITALIZE)                           // From this library
    .pipe(StringUtils::capitalize)              // From StringUtils
    .pipe(string -> myOwnFunction(string))      // From yourself
```

### ICodePointOperation

The second applies a transformation to a mutable code point buffer directly.

(This part started mostly as an experiment and learning project around reducing allocations and avoiding intermediate strings, thanks reddit!)

```java
@FunctionalInterface
public non-sealed interface ICodePointOperation extends Operation {

    /**
     * Applies a transformation to the input buffer.
     *
     * @param buffer source buffer
     */
    void apply(CodePointBuffer buffer);
}
```

The buffer being an int[] containing codepoints representing the String.

```java
public final class CodePointBuffer {

    private final int[] buffer;
    private int length;

    ...
}

```
So unlike string operations, code point operations modify a shared internal buffer instead of constantly creating new strings.

# Why Code Point Pipelines?

One of the biggest things I learned while building this library was just how expensive intermediate string creation can become.

For example in our slug pipeline, every step allocates a brand new String.

```java
AbstractPipeline pipeline =
        new StringPipelineBuilder()
                .pipe(EStringOperation.TRIM)
                .pipe(EStringOperation.LOWER_CASE)
                .pipe(s -> s.replaceAll("\\s+", "-"))
                .build();
```
For small transformations this is usually fine. But this library is meant to take some of the cost up-front, to create a faster pipeline. And that is where Code Points come into play.

CodePointPipeline avoids most intermediate string allocations by operating on a single mutable buffer internally. Multiple code point operations can execute in sequence using the same buffer.

I have included operations in this library where we have a string based, and a code-point based set of 'the same' operations.

With that I set up a (verry syntetic) comparison.

```java
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
```
When repeating the same transformations many thousands of times, the code point version ended up being significantly faster in this benchmark.

Though this is a very (VERY) synthetic test, it was exciting to see the idea actually work.

```java
String pipeline duration     : 296 ms
Code point pipeline duration : 156 ms
CodePoint/String ratio       : 0.527027027027027
```

# Combining String and CodePoint Operations

One of the main goals that I want this library to deliver is the ability to make decisions about how transformations are executed internally.

```java
AbstractPipeline pipeline =
        new OperationsPipelineBuilder()
                // Three CodePoint operations
                .pipe(ECodePointOperation.DELETE_WHITESPACE)
                .pipe(ECodePointOperation.CAPITALIZE)
                .pipe(ECodePointOperation.CHOMP)
                // Followed by a String operation.
                .pipe(EStringOperation.CHOP)
                .build();
```

At first glance, this may look like a pipeline containing four independent operations, three codePoint operations, followed by a String operation.

```java
pipeline = {SimpleStringPipeline}
    operations = {ImmutableCollections$List12}      //  size = 2
    0 = {SimpleCodePointPipeline}                   // Contains the 3 CodePointOperations
    1 = {SimpleStringPipeline}                      // Contains the single string Operation
```
Internalty we group the same type of operations together.

Making this a two step pipeline, where the CodePointOperations are run on the same buffer and transformed back to a string string, then the last operation is done on the string and returned.

```java

    String result = pipeline.apply("this is a Simple pipeline.\r");

    // Contains 'Code Point' and a 'String' operations, so this is a SimpleStringPipeline.
    assertInstanceOf(SimpleStringPipeline.class, pipeline);

    // 'This' is capitalized and the '\r' is chomped, and whitespace is removed.
    // Then the '.' is chopped.
    assertEquals("ThisisaSimplepipeline", result);
```

Even though the builder may internally collapse compatible operations into grouped pipelines, it will never reorder operations.

Pipelines are always executed strictly from top to bottom, meaning every operation receives the output of the previous step.

So for best performance, I would generally recommend grouping similar operation types together when defining pipelines.

I'm still working on this lib :)