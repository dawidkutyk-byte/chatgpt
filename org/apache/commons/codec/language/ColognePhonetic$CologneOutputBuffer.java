/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import org.apache.commons.codec.language.ColognePhonetic;

private class ColognePhonetic.CologneOutputBuffer
extends ColognePhonetic.CologneBuffer {
    public void addRight(char chr) {
        this.data[this.length] = chr;
        ++this.length;
    }

    @Override
    protected char[] copyData(int start, int length) {
        char[] newData = new char[length];
        System.arraycopy(this.data, start, newData, 0, length);
        return newData;
    }

    public ColognePhonetic.CologneOutputBuffer(int buffSize) {
        super(ColognePhonetic.this, buffSize);
    }
}
