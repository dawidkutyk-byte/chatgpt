/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

final class ShadyPines {
    static int floor(double dv) {
        int iv = (int)dv;
        return dv < (double)iv ? iv - 1 : iv;
    }

    private ShadyPines() {
    }

    static int floor(float fv) {
        int iv = (int)fv;
        return fv < (float)iv ? iv - 1 : iv;
    }
}
