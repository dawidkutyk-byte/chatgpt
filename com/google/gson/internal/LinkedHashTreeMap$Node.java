/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import java.util.Map;

static final class LinkedHashTreeMap.Node<K, V>
implements Map.Entry<K, V> {
    V value;
    final int hash;
    LinkedHashTreeMap.Node<K, V> next;
    LinkedHashTreeMap.Node<K, V> right;
    LinkedHashTreeMap.Node<K, V> parent;
    LinkedHashTreeMap.Node<K, V> left;
    final K key;
    LinkedHashTreeMap.Node<K, V> prev;
    int height;

    public LinkedHashTreeMap.Node<K, V> first() {
        LinkedHashTreeMap.Node<K, V> node = this;
        LinkedHashTreeMap.Node<K, V> child = node.left;
        while (child != null) {
            node = child;
            child = node.left;
        }
        return node;
    }

    @Override
    public int hashCode() {
        return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }

    LinkedHashTreeMap.Node() {
        this.key = null;
        this.hash = -1;
        this.next = this.prev = this;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Map.Entry)) return false;
        Map.Entry other = (Map.Entry)o;
        return (this.key == null ? other.getKey() == null : this.key.equals(other.getKey())) && (this.value == null ? other.getValue() == null : this.value.equals(other.getValue()));
    }

    LinkedHashTreeMap.Node(LinkedHashTreeMap.Node<K, V> parent, K key, int hash, LinkedHashTreeMap.Node<K, V> next, LinkedHashTreeMap.Node<K, V> prev) {
        this.parent = parent;
        this.key = key;
        this.hash = hash;
        this.height = 1;
        this.next = next;
        this.prev = prev;
        prev.next = this;
        next.prev = this;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    public LinkedHashTreeMap.Node<K, V> last() {
        LinkedHashTreeMap.Node<K, V> node = this;
        LinkedHashTreeMap.Node<K, V> child = node.right;
        while (child != null) {
            node = child;
            child = node.right;
        }
        return node;
    }

    public String toString() {
        return this.key + "=" + this.value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
