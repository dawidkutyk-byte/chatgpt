/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentIteratorFlag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentIteratorType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentIteratorFlag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentIteratorType;

final class ComponentIterator
implements Iterator<Component> {
    private final Set<ComponentIteratorFlag> flags;
    private final Deque<Component> deque;
    private Component component;
    private final ComponentIteratorType type;

    @Override
    public boolean hasNext() {
        return this.component != null || !this.deque.isEmpty();
    }

    ComponentIterator(@NotNull Component component, @NotNull ComponentIteratorType type, @NotNull Set<ComponentIteratorFlag> flags) {
        this.component = component;
        this.type = type;
        this.flags = flags;
        this.deque = new ArrayDeque<Component>();
    }

    @Override
    public Component next() {
        if (this.component != null) {
            Component next = this.component;
            this.component = null;
            this.type.populate(next, this.deque, this.flags);
            return next;
        }
        if (this.deque.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.component = this.deque.poll();
        return this.next();
    }
}
