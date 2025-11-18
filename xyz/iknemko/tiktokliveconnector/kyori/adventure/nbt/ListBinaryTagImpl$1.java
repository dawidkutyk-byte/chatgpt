/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Iterator;
import java.util.function.Consumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;

class ListBinaryTagImpl.1
implements Iterator<BinaryTag> {
    final /* synthetic */ Iterator val$iterator;

    @Override
    public void forEachRemaining(Consumer<? super BinaryTag> action) {
        this.val$iterator.forEachRemaining(action);
    }

    ListBinaryTagImpl.1() {
        this.val$iterator = iterator;
    }

    @Override
    public BinaryTag next() {
        return (BinaryTag)this.val$iterator.next();
    }

    @Override
    public boolean hasNext() {
        return this.val$iterator.hasNext();
    }
}
