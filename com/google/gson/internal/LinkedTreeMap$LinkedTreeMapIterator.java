/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

private abstract class LinkedTreeMap.LinkedTreeMapIterator<T>
implements Iterator<T> {
    LinkedTreeMap.Node<K, V> lastReturned;
    LinkedTreeMap.Node<K, V> next;
    int expectedModCount;

    LinkedTreeMap.LinkedTreeMapIterator() {
        this.next = LinkedTreeMap.this.header.next;
        this.lastReturned = null;
        this.expectedModCount = LinkedTreeMap.this.modCount;
    }

    final LinkedTreeMap.Node<K, V> nextNode() {
        LinkedTreeMap.Node e = this.next;
        if (e == LinkedTreeMap.this.header) {
            throw new NoSuchElementException();
        }
        if (LinkedTreeMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        this.next = e.next;
        this.lastReturned = e;
        return this.lastReturned;
    }

    @Override
    public final boolean hasNext() {
        return this.next != LinkedTreeMap.this.header;
    }

    @Override
    public final void remove() {
        if (this.lastReturned == null) {
            throw new IllegalStateException();
        }
        LinkedTreeMap.this.removeInternal(this.lastReturned, true);
        this.lastReturned = null;
        this.expectedModCount = LinkedTreeMap.this.modCount;
    }
}
