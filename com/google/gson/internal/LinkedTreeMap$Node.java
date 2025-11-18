/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import java.util.Map;

static final class LinkedTreeMap.Node<K, V>
implements Map.Entry<K, V> {
    LinkedTreeMap.Node<K, V> left;
    LinkedTreeMap.Node<K, V> right;
    LinkedTreeMap.Node<K, V> parent;
    int height;
    final K key;
    LinkedTreeMap.Node<K, V> next;
    LinkedTreeMap.Node<K, V> prev;
    V value;

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    LinkedTreeMap.Node() {
        this.key = null;
        this.next = this.prev = this;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    public String toString() {
        return this.key + "=" + this.value;
    }

    @Override
    public int hashCode() {
        return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
    }

    @Override
    public V getValue() {
        return this.value;
    }

    LinkedTreeMap.Node(LinkedTreeMap.Node<K, V> parent, K key, LinkedTreeMap.Node<K, V> next, LinkedTreeMap.Node<K, V> prev) {
        this.parent = parent;
        this.key = key;
        this.height = 1;
        this.next = next;
        this.prev = prev;
        prev.next = this;
        next.prev = this;
    }

    public LinkedTreeMap.Node<K, V> first() {
        LinkedTreeMap.Node<K, V> node = this;
        LinkedTreeMap.Node<K, V> child = node.left;
        while (child != null) {
            node = child;
            child = node.left;
        }
        return node;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Map.Entry)) return false;
        Map.Entry other = (Map.Entry)o;
        return (this.key == null ? other.getKey() == null : this.key.equals(other.getKey())) && (this.value == null ? other.getValue() == null : this.value.equals(other.getValue()));
    }

    public LinkedTreeMap.Node<K, V> last() {
        LinkedTreeMap.Node<K, V> node = this;
        LinkedTreeMap.Node<K, V> child = node.right;
        while (child != null) {
            node = child;
            child = node.right;
        }
        return node;
    }
}
