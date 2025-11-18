/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Iterator;
import java.util.NoSuchElementException;

class FacetAudienceProvider.1
implements Iterator<V> {
    private final Iterator<T> parent;
    private V next;

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    FacetAudienceProvider.1() {
        this.parent = val$input.iterator();
        this.populate();
    }

    private void populate() {
        Object next;
        this.next = null;
        do {
            if (!this.parent.hasNext()) return;
        } while (!val$filter.test(next = this.parent.next()));
        this.next = val$transformer.apply(next);
    }

    @Override
    public V next() {
        if (this.next == null) {
            throw new NoSuchElementException();
        }
        Object next = this.next;
        this.populate();
        return next;
    }
}
