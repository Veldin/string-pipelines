package com.veldin.stringpipelines;

import java.util.List;

public abstract class AbstractStringPipeline implements IStringOperation {

    protected final List<IStringOperation> operations;

    protected AbstractStringPipeline(List<IStringOperation> operations) {
        this.operations = List.copyOf(operations);
    }

    @Override
    public String apply(String input) {

        String value = input;

        for (IStringOperation op : operations) {
            value = op.apply(value);
        }

        return value;
    }

    public void clearDeep() {

        for (IStringOperation op : operations) {

            // recurse into nested pipelines
            if (op instanceof AbstractStringPipeline pipeline) {
                pipeline.clearDeep();
            }
        }

        clear();
    }

    /**
     * Override in subclasses that have state/cache.
     */
    protected void clear() {
        // nothing by default
    }
}