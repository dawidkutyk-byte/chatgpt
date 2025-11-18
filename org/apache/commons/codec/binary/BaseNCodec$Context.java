/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.binary;

import java.util.Arrays;

static class BaseNCodec.Context {
    int pos;
    byte[] buffer;
    int modulus;
    int readPos;
    int ibitWorkArea;
    int currentLinePos;
    boolean eof;
    long lbitWorkArea;

    public String toString() {
        return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", this.getClass().getSimpleName(), Arrays.toString(this.buffer), this.currentLinePos, this.eof, this.ibitWorkArea, this.lbitWorkArea, this.modulus, this.pos, this.readPos);
    }

    BaseNCodec.Context() {
    }
}
