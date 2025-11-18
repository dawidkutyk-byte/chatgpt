/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$Node
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;

static class LinkedHashTreeMap.AvlIterator<K, V> {
    private LinkedHashTreeMap.Node<K, V> stackTop;

    LinkedHashTreeMap.AvlIterator() {
    }

    void reset(LinkedHashTreeMap.Node<K, V> root) {
        LinkedHashTreeMap.Node stackTop = null;
        LinkedHashTreeMap.Node n = root;
        while (true) {
            if (n == null) {
                this.stackTop = stackTop;
                return;
            }
            n.parent = stackTop;
            stackTop = n;
            n = n.left;
        }
    }

    public LinkedHashTreeMap.Node<K, V> next() {
        LinkedHashTreeMap.Node stackTop = this.stackTop;
        if (stackTop == null) {
            return null;
        }
        LinkedHashTreeMap.Node result = stackTop;
        stackTop = result.parent;
        result.parent = null;
        LinkedHashTreeMap.Node n = result.right;
        while (true) {
            if (n == null) {
                this.stackTop = stackTop;
                return result;
            }
            n.parent = stackTop;
            stackTop = n;
            n = n.left;
        }
    }
}
