/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

private abstract class ColognePhonetic.CologneBuffer {
    protected final char[] data;
    protected int length = 0;

    protected abstract char[] copyData(int var1, int var2);

    public ColognePhonetic.CologneBuffer(int buffSize) {
        this.data = new char[buffSize];
        this.length = 0;
    }

    public String toString() {
        return new String(this.copyData(0, this.length));
    }

    public int length() {
        return this.length;
    }

    public ColognePhonetic.CologneBuffer(char[] data) {
        this.data = data;
        this.length = data.length;
    }
}
