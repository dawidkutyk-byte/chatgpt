/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTagImpl;

final class ListTagBuilder<T extends BinaryTag>
implements ListBinaryTag.Builder<T> {
    private final boolean permitsHeterogeneity;
    @Nullable
    private List<BinaryTag> tags;
    private BinaryTagType<? extends BinaryTag> elementType;

    ListTagBuilder(boolean permitsHeterogeneity) {
        this(permitsHeterogeneity, (BinaryTagType<? extends BinaryTag>)BinaryTagTypes.END);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ListBinaryTag.Builder<T> add(Iterable<? extends T> tagsToAdd) {
        Iterator<T> iterator = tagsToAdd.iterator();
        while (iterator.hasNext()) {
            BinaryTag tag = (BinaryTag)iterator.next();
            this.add(tag);
        }
        return this;
    }

    ListTagBuilder(boolean permitsHeterogeneity, BinaryTagType<? extends BinaryTag> type) {
        this.permitsHeterogeneity = permitsHeterogeneity;
        this.elementType = type;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ListBinaryTag.Builder<T> add(BinaryTag tag) {
        this.elementType = ListBinaryTagImpl.validateTagType((BinaryTag)tag, this.elementType, (boolean)this.permitsHeterogeneity);
        if (this.tags == null) {
            this.tags = new ArrayList<BinaryTag>();
        }
        this.tags.add(tag);
        return this;
    }

    @NotNull
    public ListBinaryTag build() {
        if (this.tags != null) return new ListBinaryTagImpl(this.elementType, this.permitsHeterogeneity, new ArrayList<BinaryTag>(this.tags));
        return ListBinaryTag.empty();
    }
}
