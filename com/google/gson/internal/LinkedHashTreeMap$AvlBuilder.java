/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$Node
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;

static final class LinkedHashTreeMap.AvlBuilder<K, V> {
    private int size;
    private int leavesSkipped;
    private LinkedHashTreeMap.Node<K, V> stack;
    private int leavesToSkip;

    LinkedHashTreeMap.AvlBuilder() {
    }

    void reset(int targetSize) {
        int treeCapacity = Integer.highestOneBit(targetSize) * 2 - 1;
        this.leavesToSkip = treeCapacity - targetSize;
        this.size = 0;
        this.leavesSkipped = 0;
        this.stack = null;
    }

    void add(LinkedHashTreeMap.Node<K, V> node) {
        node.right = null;
        node.parent = null;
        node.left = null;
        node.height = 1;
        if (this.leavesToSkip > 0 && (this.size & 1) == 0) {
            ++this.size;
            --this.leavesToSkip;
            ++this.leavesSkipped;
        }
        node.parent = this.stack;
        this.stack = node;
        ++this.size;
        if (this.leavesToSkip > 0 && (this.size & 1) == 0) {
            ++this.size;
            --this.leavesToSkip;
            ++this.leavesSkipped;
        }
        int scale = 4;
        while ((this.size & scale - 1) == scale - 1) {
            LinkedHashTreeMap.Node center;
            LinkedHashTreeMap.Node<K, V> right;
            if (this.leavesSkipped == 0) {
                right = this.stack;
                center = right.parent;
                LinkedHashTreeMap.Node left = center.parent;
                center.parent = left.parent;
                this.stack = center;
                center.left = left;
                center.right = right;
                center.height = right.height + 1;
                left.parent = center;
                right.parent = center;
            } else if (this.leavesSkipped == 1) {
                right = this.stack;
                this.stack = center = right.parent;
                center.right = right;
                center.height = right.height + 1;
                right.parent = center;
                this.leavesSkipped = 0;
            } else if (this.leavesSkipped == 2) {
                this.leavesSkipped = 0;
            }
            scale *= 2;
        }
    }

    LinkedHashTreeMap.Node<K, V> root() {
        LinkedHashTreeMap.Node<K, V> stackTop = this.stack;
        if (stackTop.parent == null) return stackTop;
        throw new IllegalStateException();
    }
}
