/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl;

final class CompoundTagBuilder
implements CompoundBinaryTag.Builder {
    @Nullable
    private Map<String, BinaryTag> tags;

    private Map<String, BinaryTag> tags() {
        if (this.tags != null) return this.tags;
        this.tags = new HashMap<String, BinaryTag>();
        return this.tags;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull CompoundBinaryTag.Builder put(@NotNull CompoundBinaryTag tag) {
        Map<String, BinaryTag> tags = this.tags();
        Iterator iterator = tag.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            tags.put(key, tag.get(key));
        }
        return this;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull CompoundBinaryTag.Builder remove(@NotNull String key, @Nullable Consumer<? super BinaryTag> removed) {
        if (this.tags == null) return this;
        BinaryTag tag = this.tags.remove(key);
        if (removed == null) return this;
        removed.accept((BinaryTag)tag);
        return this;
    }

    @NotNull
    public CompoundBinaryTag build() {
        if (this.tags != null) return new CompoundBinaryTagImpl(new HashMap<String, BinaryTag>(this.tags));
        return CompoundBinaryTag.empty();
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull CompoundBinaryTag.Builder put(@NotNull String key, @NotNull BinaryTag tag) {
        this.tags().put(key, tag);
        return this;
    }

    CompoundTagBuilder() {
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull CompoundBinaryTag.Builder put(@NotNull Map<String, ? extends BinaryTag> tags) {
        this.tags().putAll(tags);
        return this;
    }
}
