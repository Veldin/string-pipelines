package com.veldin.stringpipelines.codepoints;

import java.util.Arrays;

public final class CodePointBuffer {

    private int[] buffer;
    private int length;

    public CodePointBuffer(int[] buffer) {
        this.buffer = buffer;
        this.length = buffer.length;
    }

    public int get(int index) {
        return buffer[index];
    }

    public void set(int index, int value) {
        buffer[index] = value;
    }

    public int length() {
        return length;
    }

    public void setLength(int newLength) {

        if (newLength < 0) {
            throw new IllegalArgumentException("length < 0");
        }

        ensureCapacity(newLength);
        length = newLength;
    }

    public void ensureCapacity(int capacity) {

        if (capacity <= buffer.length) {
            return;
        }

        int newCapacity = Math.max(capacity, buffer.length * 2 + 1);
        buffer = Arrays.copyOf(buffer, newCapacity);
    }

    @Override
    public String toString() {
        return new String(buffer, 0, length);
    }
}