/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examiner
 *  net.kyori.examination.string.StringExaminer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import net.kyori.examination.Examiner;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;

abstract class AbstractBinaryTag
implements BinaryTag {
    AbstractBinaryTag() {
    }

    public final String toString() {
        return (String)this.examine((Examiner)StringExaminer.simpleEscaping());
    }

    @NotNull
    public final String examinableName() {
        return this.type().toString();
    }
}
