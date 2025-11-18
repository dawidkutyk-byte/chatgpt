/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag0
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag0;

@Debug.Renderer(text="\"ListBinaryTag[type=\" + this.type.toString() + \"]\"", childrenArray="this.tags.toArray()", hasChildren="!this.tags.isEmpty()")
final class ListBinaryTagImpl
extends AbstractBinaryTag
implements ListBinaryTag {
    static final ListBinaryTag EMPTY = new ListBinaryTagImpl((BinaryTagType<? extends BinaryTag>)BinaryTagTypes.END, false, Collections.<BinaryTag>emptyList());
    private final BinaryTagType<? extends BinaryTag> elementType;
    private final boolean permitsHeterogeneity;
    private final List<BinaryTag> tags;
    private final int hashCode;

    @NotNull
    public ListBinaryTag add(Iterable<? extends BinaryTag> tagsToAdd) {
        if (tagsToAdd instanceof Collection && ((Collection)tagsToAdd).isEmpty()) {
            return this;
        }
        BinaryTagType<?> type = ListBinaryTagImpl.validateTagType(tagsToAdd, this.permitsHeterogeneity);
        return this.edit(tags -> {
            Iterator iterator = tagsToAdd.iterator();
            while (iterator.hasNext()) {
                BinaryTag tag = (BinaryTag)iterator.next();
                tags.add(tag);
            }
        }, type);
    }

    static BinaryTagType<?> validateTagType(Iterable<? extends BinaryTag> tags, boolean permitHeterogeneity) {
        BinaryTagType type = null;
        Iterator<? extends BinaryTag> iterator = tags.iterator();
        while (iterator.hasNext()) {
            BinaryTag tag = iterator.next();
            if (type == null) {
                ListBinaryTagImpl.noAddEnd(tag);
                type = tag.type();
                continue;
            }
            ListBinaryTagImpl.validateTagType(tag, type, permitHeterogeneity);
            if (type == tag.type()) continue;
            type = BinaryTagTypes.LIST_WILDCARD;
        }
        return type;
    }

    @NotNull
    public ListBinaryTag add(BinaryTag tag) {
        BinaryTagType<?> targetType = ListBinaryTagImpl.validateTagType(tag, this.elementType, this.permitsHeterogeneity);
        return this.edit(tags -> tags.add(tag), targetType);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"tags", this.tags), ExaminableProperty.of((String)"type", this.elementType));
    }

    ListBinaryTagImpl(BinaryTagType<? extends BinaryTag> elementType, boolean permitsHeterogeneity, List<BinaryTag> tags) {
        this.tags = Collections.unmodifiableList(tags);
        this.permitsHeterogeneity = permitsHeterogeneity;
        this.elementType = elementType;
        this.hashCode = tags.hashCode();
    }

    public int hashCode() {
        return this.hashCode;
    }

    @NotNull
    public ListBinaryTag remove(int index, @Nullable Consumer<? super BinaryTag> removed) {
        return this.edit(tags -> {
            BinaryTag oldTag = (BinaryTag)tags.remove(index);
            if (removed == null) return;
            removed.accept(oldTag);
        }, null);
    }

    @NotNull
    public BinaryTag get(@Range(from=0L, to=0x7FFFFFFFL) int index) {
        return this.tags.get(index);
    }

    static void noAddEnd(BinaryTag tag) {
        if (tag.type() != BinaryTagTypes.END) return;
        throw new IllegalArgumentException(String.format("Cannot add a %s to a %s", BinaryTagTypes.END, BinaryTagTypes.LIST));
    }

    @NotNull
    public Iterator<BinaryTag> iterator() {
        Iterator<BinaryTag> iterator = this.tags.iterator();
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    static BinaryTagType<?> validateTagType(BinaryTag tag, BinaryTagType<? extends BinaryTag> type, boolean permitHeterogenity) {
        ListBinaryTagImpl.noAddEnd(tag);
        if (type == BinaryTagTypes.END) {
            return tag.type();
        }
        if (tag.type() == type || permitHeterogenity) return tag.type() != type ? BinaryTagTypes.LIST_WILDCARD : type;
        throw new IllegalArgumentException(String.format("Trying to add tag of type %s to list of %s", tag.type(), type));
    }

    private ListBinaryTag edit(Consumer<List<BinaryTag>> consumer, @Nullable BinaryTagType<? extends BinaryTag> maybeElementType) {
        ArrayList<BinaryTag> tags = new ArrayList<BinaryTag>(this.tags);
        consumer.accept(tags);
        BinaryTagType<? extends BinaryTag> elementType = this.elementType;
        if (maybeElementType == null) return new ListBinaryTagImpl(elementType, this.permitsHeterogeneity, new ArrayList<BinaryTag>(tags));
        elementType = maybeElementType;
        return new ListBinaryTagImpl(elementType, this.permitsHeterogeneity, new ArrayList<BinaryTag>(tags));
    }

    public boolean equals(Object that) {
        return this == that || that instanceof ListBinaryTagImpl && this.tags.equals(((ListBinaryTagImpl)((Object)that)).tags);
    }

    @NotNull
    public BinaryTagType<? extends BinaryTag> elementType() {
        return this.elementType;
    }

    @NotNull
    public Stream<BinaryTag> stream() {
        return this.tags.stream();
    }

    @NotNull
    public ListBinaryTag wrapHeterogeneity() {
        if (this.elementType != BinaryTagTypes.LIST_WILDCARD) {
            return this;
        }
        ArrayList<BinaryTag> newTags = new ArrayList<BinaryTag>(this.tags.size());
        Iterator<BinaryTag> iterator = this.tags.iterator();
        while (iterator.hasNext()) {
            BinaryTag tag = iterator.next();
            newTags.add((BinaryTag)ListBinaryTag0.box((BinaryTag)tag));
        }
        return new ListBinaryTagImpl((BinaryTagType<? extends BinaryTag>)BinaryTagTypes.COMPOUND, false, newTags);
    }

    public int size() {
        return this.tags.size();
    }

    public boolean isEmpty() {
        return this.tags.isEmpty();
    }

    @NotNull
    public ListBinaryTag set(int index, @NotNull BinaryTag newTag, @Nullable Consumer<? super BinaryTag> removed) {
        BinaryTagType<?> targetType = ListBinaryTagImpl.validateTagType(newTag, this.elementType, this.permitsHeterogeneity);
        return this.edit(tags -> {
            BinaryTag oldTag = tags.set(index, newTag);
            if (removed == null) return;
            removed.accept(oldTag);
        }, targetType);
    }

    @NotNull
    public ListBinaryTag unwrapHeterogeneity() {
        if (this.permitsHeterogeneity) return this;
        if (this.elementType != BinaryTagTypes.COMPOUND) {
            return new ListBinaryTagImpl(this.elementType, true, this.tags);
        }
        List<BinaryTag> newTags = null;
        ListIterator<BinaryTag> it = this.tags.listIterator();
        while (it.hasNext()) {
            BinaryTag current = it.next();
            BinaryTag unboxed = ListBinaryTag0.unbox((CompoundBinaryTag)((CompoundBinaryTag)current));
            if (unboxed != current && newTags == null) {
                newTags = new ArrayList<BinaryTag>(this.tags.size());
                int idx = it.nextIndex() - 1;
                for (int ptr = 0; ptr < idx; ++ptr) {
                    newTags.add(this.tags.get(ptr));
                }
            }
            if (newTags == null) continue;
            newTags.add(unboxed);
        }
        return new ListBinaryTagImpl((BinaryTagType<? extends BinaryTag>)(newTags == null ? BinaryTagTypes.COMPOUND : BinaryTagTypes.LIST_WILDCARD), true, newTags == null ? this.tags : newTags);
    }

    public void forEach(Consumer<? super BinaryTag> action) {
        this.tags.forEach(action);
    }

    public Spliterator<BinaryTag> spliterator() {
        return Spliterators.spliterator(this.tags, 1040);
    }
}
