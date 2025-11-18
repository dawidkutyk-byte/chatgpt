/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterators;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

/*
 * Exception performing whole class analysis ignored.
 */
static final class DecorationMap.KeySet
extends AbstractSet<TextDecoration> {
    @Override
    @NotNull
    public Iterator<TextDecoration> iterator() {
        return Spliterators.iterator(Arrays.spliterator(DecorationMap.DECORATIONS));
    }

    DecorationMap.KeySet() {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return o instanceof TextDecoration;
    }

    @Override
    public <T> T @NotNull [] toArray(T @NotNull [] dest) {
        if (dest.length < DecorationMap.access$100()) {
            return Arrays.copyOf(DecorationMap.DECORATIONS, DecorationMap.access$100(), dest.getClass());
        }
        System.arraycopy(DecorationMap.DECORATIONS, 0, dest, 0, DecorationMap.access$100());
        if (dest.length <= DecorationMap.access$100()) return dest;
        dest[DecorationMap.access$100()] = null;
        return dest;
    }

    @Override
    public int size() {
        return DecorationMap.access$100();
    }

    @Override
    public Object @NotNull [] toArray() {
        return Arrays.copyOf(DecorationMap.DECORATIONS, DecorationMap.access$100(), Object[].class);
    }
}
