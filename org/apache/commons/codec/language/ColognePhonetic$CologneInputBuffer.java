/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import org.apache.commons.codec.language.ColognePhonetic;

private class ColognePhonetic.CologneInputBuffer
extends ColognePhonetic.CologneBuffer {
    public char getNextChar() {
        return this.data[this.getNextPos()];
    }

    public char removeNext() {
        char ch = this.getNextChar();
        --this.length;
        return ch;
    }

    public ColognePhonetic.CologneInputBuffer(char[] data) {
        super(ColognePhonetic.this, data);
    }

    public void addLeft(char ch) {
        ++this.length;
        this.data[this.getNextPos()] = ch;
    }

    protected int getNextPos() {
        return this.data.length - this.length;
    }

    @Override
    protected char[] copyData(int start, int length) {
        char[] newData = new char[length];
        System.arraycopy(this.data, this.data.length - this.length + start, newData, 0, length);
        return newData;
    }
}
