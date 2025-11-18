/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;

public interface BinaryTag
extends BinaryTagLike,
Examinable {
    @NotNull
    default public BinaryTag asBinaryTag() {
        return this;
    }

    @NotNull
    public BinaryTagType<? extends BinaryTag> type();
}
