/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

static class Streams.AppendableWriter.CurrentWrite
implements CharSequence {
    char[] chars;

    @Override
    public int length() {
        return this.chars.length;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new String(this.chars, start, end - start);
    }

    Streams.AppendableWriter.CurrentWrite() {
    }

    @Override
    public char charAt(int i) {
        return this.chars[i];
    }
}
