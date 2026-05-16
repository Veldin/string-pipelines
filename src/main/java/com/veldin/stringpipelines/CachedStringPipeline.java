package com.veldin.stringpipelines;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedStringPipeline extends AbstractStringPipeline {

    private final Map<String, String> cache;

    /**
     * Called from the StringPipelineBuilder to create a new StringPipeline
     * Make sure validateNoCycles() is called before creating.
     */
    protected static CachedStringPipeline Of(List<IStringOperation> operations) {
        return new CachedStringPipeline(operations);
    }

    private CachedStringPipeline(List<IStringOperation> operations) {
        super(operations);
        this.cache = new ConcurrentHashMap<>();
    }

    @Override
    public String apply(String input) {
        // check cache first
        String cached = cache.get(input);
        if (cached != null) {
            return cached;
        }

        String value = input;

        for (IStringOperation op : operations) {
            value = op.apply(value);
        }

        cache.put(input, value);

        return value;
    }

    public void clearCache() {
        cache.clear();
    }

    @Override
    protected void clear() {
        clearCache();
    }
}