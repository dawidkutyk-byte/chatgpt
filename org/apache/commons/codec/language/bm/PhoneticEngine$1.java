/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.NameType;

static class PhoneticEngine.1 {
    static final /* synthetic */ int[] $SwitchMap$org$apache$commons$codec$language$bm$NameType;

    static {
        $SwitchMap$org$apache$commons$codec$language$bm$NameType = new int[NameType.values().length];
        try {
            PhoneticEngine.1.$SwitchMap$org$apache$commons$codec$language$bm$NameType[NameType.SEPHARDIC.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PhoneticEngine.1.$SwitchMap$org$apache$commons$codec$language$bm$NameType[NameType.ASHKENAZI.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PhoneticEngine.1.$SwitchMap$org$apache$commons$codec$language$bm$NameType[NameType.GENERIC.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
