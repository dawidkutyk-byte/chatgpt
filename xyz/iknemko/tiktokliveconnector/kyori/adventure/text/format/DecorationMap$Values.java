/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterators;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

/*
 * Exception performing whole class analysis ignored.
 */
final class DecorationMap.Values
extends AbstractCollection<TextDecoration.State> {
    @Override
    public <T> T @NotNull [] toArray(T @NotNull [] dest) {
        if (dest.length < DecorationMap.access$100()) {
            return Arrays.copyOf(this.toArray(), DecorationMap.access$100(), dest.getClass());
        }
        System.arraycopy(this.toArray(), 0, dest, 0, DecorationMap.access$100());
        if (dest.length <= DecorationMap.access$100()) return dest;
        dest[DecorationMap.access$100()] = null;
        return dest;
    }

    @Override
    public boolean contains(Object o) {
        return o instanceof TextDecoration.State && super.contains(o);
    }

    @Override
    public Object @NotNull [] toArray() {
        Object[] states = new Object[DecorationMap.access$100()];
        int i = 0;
        while (i < DecorationMap.access$100()) {
            states[i] = DecorationMap.this.get((Object)DecorationMap.DECORATIONS[i]);
            ++i;
        }
        return states;
    }

    @Override
    public int size() {
        return DecorationMap.access$100();
    }

    DecorationMap.Values() {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    @NotNull
    public Iterator<TextDecoration.State> iterator() {
        return Spliterators.iterator(Arrays.spliterator(this.toArray(DecorationMap.access$200())));
    }
}
