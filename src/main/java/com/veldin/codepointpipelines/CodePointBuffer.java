package com.veldin.codepointpipelines;

public final class CodePointBuffer {

    private final int[] buffer;
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

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return new String(buffer, 0, length);
    }
}