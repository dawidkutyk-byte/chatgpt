/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$Node
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

private abstract class LinkedHashTreeMap.LinkedTreeMapIterator<T>
implements Iterator<T> {
    LinkedHashTreeMap.Node<K, V> lastReturned;
    int expectedModCount;
    LinkedHashTreeMap.Node<K, V> next;

    final LinkedHashTreeMap.Node<K, V> nextNode() {
        LinkedHashTreeMap.Node e = this.next;
        if (e == LinkedHashTreeMap.this.header) {
            throw new NoSuchElementException();
        }
        if (LinkedHashTreeMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        this.next = e.next;
        this.lastReturned = e;
        return this.lastReturned;
    }

    @Override
    public final void remove() {
        if (this.lastReturned == null) {
            throw new IllegalStateException();
        }
        LinkedHashTreeMap.this.removeInternal(this.lastReturned, true);
        this.lastReturned = null;
        this.expectedModCount = LinkedHashTreeMap.this.modCount;
    }

    LinkedHashTreeMap.LinkedTreeMapIterator() {
        this.next = LinkedHashTreeMap.this.header.next;
        this.lastReturned = null;
        this.expectedModCount = LinkedHashTreeMap.this.modCount;
    }

    @Override
    public final boolean hasNext() {
        return this.next != LinkedHashTreeMap.this.header;
    }
}
