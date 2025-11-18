/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fr.mrmicky.fastboard.FastBoardBase
 */
package fr.mrmicky.fastboard;

import fr.mrmicky.fastboard.FastBoardBase;

/*
 * Exception performing whole class analysis ignored.
 */
static enum FastBoardBase.VersionType {
    V1_7,
    V1_8,
    V1_13,
    V1_17;


    public boolean isHigherOrEqual() {
        return FastBoardBase.access$000().ordinal() >= this.ordinal();
    }
}
