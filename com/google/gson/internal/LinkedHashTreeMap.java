/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.internal.LinkedHashTreeMap$AvlBuilder
 *  com.google.gson.internal.LinkedHashTreeMap$AvlIterator
 *  com.google.gson.internal.LinkedHashTreeMap$EntrySet
 *  com.google.gson.internal.LinkedHashTreeMap$KeySet
 *  com.google.gson.internal.LinkedHashTreeMap$Node
 */
package com.google.gson.internal;

import com.google.gson.internal.LinkedHashTreeMap;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class LinkedHashTreeMap<K, V>
extends AbstractMap<K, V>
implements Serializable {
    Node<K, V>[] table;
    int threshold;
    private static final Comparator<Comparable> NATURAL_ORDER = new /* Unavailable Anonymous Inner Class!! */;
    int size = 0;
    final Node<K, V> header;
    private KeySet keySet;
    private EntrySet entrySet;
    Comparator<? super K> comparator;
    int modCount = 0;

    Node<K, V> findByObject(Object key) {
        try {
            return key != null ? this.find(key, false) : null;
        }
        catch (ClassCastException e) {
            return null;
        }
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator != null ? comparator : NATURAL_ORDER;
        this.header = new Node();
        this.table = new Node[16];
        this.threshold = this.table.length / 2 + this.table.length / 4;
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
        Node parent = node.parent;
        node.parent = null;
        if (replacement != null) {
            replacement.parent = parent;
        }
        if (parent != null) {
            if (parent.left == node) {
                parent.left = replacement;
            } else {
                assert (parent.right == node);
                parent.right = replacement;
            }
        } else {
            int index = node.hash & this.table.length - 1;
            this.table[index] = replacement;
        }
    }

    private void doubleCapacity() {
        this.table = LinkedHashTreeMap.doubleCapacity(this.table);
        this.threshold = this.table.length / 2 + this.table.length / 4;
    }

    static <K, V> Node<K, V>[] doubleCapacity(Node<K, V>[] oldTable) {
        int oldCapacity = oldTable.length;
        Node[] newTable = new Node[oldCapacity * 2];
        AvlIterator iterator = new AvlIterator();
        AvlBuilder leftBuilder = new AvlBuilder();
        AvlBuilder rightBuilder = new AvlBuilder();
        int i = 0;
        while (i < oldCapacity) {
            Node<K, V> root = oldTable[i];
            if (root != null) {
                Node node;
                iterator.reset(root);
                int leftSize = 0;
                int rightSize = 0;
                while ((node = iterator.next()) != null) {
                    if ((node.hash & oldCapacity) == 0) {
                        ++leftSize;
                        continue;
                    }
                    ++rightSize;
                }
                leftBuilder.reset(leftSize);
                rightBuilder.reset(rightSize);
                iterator.reset(root);
                while ((node = iterator.next()) != null) {
                    if ((node.hash & oldCapacity) == 0) {
                        leftBuilder.add(node);
                        continue;
                    }
                    rightBuilder.add(node);
                }
                newTable[i] = leftSize > 0 ? leftBuilder.root() : null;
                newTable[i + oldCapacity] = rightSize > 0 ? rightBuilder.root() : null;
            }
            ++i;
        }
        return newTable;
    }

    @Override
    public void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
        ++this.modCount;
        Node<K, V> header = this.header;
        Node e = header.next;
        while (true) {
            if (e == header) {
                header.next = header.prev = header;
                return;
            }
            Node next = e.next;
            e.prev = null;
            e.next = null;
            e = next;
        }
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySet result = this.entrySet;
        return result != null ? result : (this.entrySet = new EntrySet(this));
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    private void rotateLeft(Node<K, V> root) {
        Node left = root.left;
        Node pivot = root.right;
        Node pivotLeft = pivot.left;
        Node pivotRight = pivot.right;
        root.right = pivotLeft;
        if (pivotLeft != null) {
            pivotLeft.parent = root;
        }
        this.replaceInParent(root, pivot);
        pivot.left = root;
        root.parent = pivot;
        root.height = Math.max(left != null ? left.height : 0, pivotLeft != null ? pivotLeft.height : 0) + 1;
        pivot.height = Math.max(root.height, pivotRight != null ? pivotRight.height : 0) + 1;
    }

    private void rebalance(Node<K, V> unbalanced, boolean insert) {
        Node node = unbalanced;
        while (node != null) {
            Node left = node.left;
            Node right = node.right;
            int leftHeight = left != null ? left.height : 0;
            int rightHeight = right != null ? right.height : 0;
            int delta = leftHeight - rightHeight;
            if (delta == -2) {
                Node rightRight;
                int rightRightHeight;
                Node rightLeft = right.left;
                int rightLeftHeight = rightLeft != null ? rightLeft.height : 0;
                int rightDelta = rightLeftHeight - (rightRightHeight = (rightRight = right.right) != null ? rightRight.height : 0);
                if (rightDelta == -1 || rightDelta == 0 && !insert) {
                    this.rotateLeft(node);
                } else {
                    assert (rightDelta == 1);
                    this.rotateRight(right);
                    this.rotateLeft(node);
                }
                if (insert) {
                    break;
                }
            } else if (delta == 2) {
                Node leftRight;
                int leftRightHeight;
                Node leftLeft = left.left;
                int leftLeftHeight = leftLeft != null ? leftLeft.height : 0;
                int leftDelta = leftLeftHeight - (leftRightHeight = (leftRight = left.right) != null ? leftRight.height : 0);
                if (leftDelta == 1 || leftDelta == 0 && !insert) {
                    this.rotateRight(node);
                } else {
                    assert (leftDelta == -1);
                    this.rotateLeft(left);
                    this.rotateRight(node);
                }
                if (insert) {
                    break;
                }
            } else if (delta == 0) {
                node.height = leftHeight + 1;
                if (insert) {
                    break;
                }
            } else {
                assert (delta == -1 || delta == 1);
                node.height = Math.max(leftHeight, rightHeight) + 1;
                if (!insert) return;
            }
            node = node.parent;
        }
        return;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        Node<K, V> created = this.find(key, true);
        Object result = created.value;
        created.value = value;
        return (V)result;
    }

    Node<K, V> findByEntry(Map.Entry<?, ?> entry) {
        Node<K, V> mine = this.findByObject(entry.getKey());
        boolean valuesEqual = mine != null && this.equal(mine.value, entry.getValue());
        return valuesEqual ? mine : null;
    }

    @Override
    public V get(Object key) {
        Node<K, V> node = this.findByObject(key);
        return (V)(node != null ? node.value : null);
    }

    private void rotateRight(Node<K, V> root) {
        Node pivotRight;
        Node pivot = root.left;
        Node right = root.right;
        Node pivotLeft = pivot.left;
        root.left = pivotRight = pivot.right;
        if (pivotRight != null) {
            pivotRight.parent = root;
        }
        this.replaceInParent(root, pivot);
        pivot.right = root;
        root.parent = pivot;
        root.height = Math.max(right != null ? right.height : 0, pivotRight != null ? pivotRight.height : 0) + 1;
        pivot.height = Math.max(root.height, pivotLeft != null ? pivotLeft.height : 0) + 1;
    }

    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    @Override
    public Set<K> keySet() {
        KeySet result = this.keySet;
        return result != null ? result : (this.keySet = new KeySet(this));
    }

    private static int secondaryHash(int h) {
        h ^= h >>> 20 ^ h >>> 12;
        return h ^ h >>> 7 ^ h >>> 4;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = this.removeInternalByKey(key);
        return (V)(node != null ? node.value : null);
    }

    Node<K, V> removeInternalByKey(Object key) {
        Node<K, V> node = this.findByObject(key);
        if (node == null) return node;
        this.removeInternal(node, true);
        return node;
    }

    void removeInternal(Node<K, V> node, boolean unlink) {
        if (unlink) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
        Node left = node.left;
        Node right = node.right;
        Node originalParent = node.parent;
        if (left != null && right != null) {
            Node adjacent = left.height > right.height ? left.last() : right.first();
            this.removeInternal(adjacent, false);
            int leftHeight = 0;
            left = node.left;
            if (left != null) {
                leftHeight = left.height;
                adjacent.left = left;
                left.parent = adjacent;
                node.left = null;
            }
            int rightHeight = 0;
            right = node.right;
            if (right != null) {
                rightHeight = right.height;
                adjacent.right = right;
                right.parent = adjacent;
                node.right = null;
            }
            adjacent.height = Math.max(leftHeight, rightHeight) + 1;
            this.replaceInParent(node, adjacent);
            return;
        }
        if (left != null) {
            this.replaceInParent(node, left);
            node.left = null;
        } else if (right != null) {
            this.replaceInParent(node, right);
            node.right = null;
        } else {
            this.replaceInParent(node, null);
        }
        this.rebalance(originalParent, false);
        --this.size;
        ++this.modCount;
    }

    Node<K, V> find(K key, boolean create) {
        Node created;
        Comparator<K> comparator = this.comparator;
        Node<K, V>[] table = this.table;
        int hash = LinkedHashTreeMap.secondaryHash(key.hashCode());
        int index = hash & table.length - 1;
        Node nearest = table[index];
        int comparison = 0;
        if (nearest != null) {
            Comparable comparableKey = comparator == NATURAL_ORDER ? (Comparable)key : null;
            while (true) {
                Node child;
                int n = comparison = comparableKey != null ? comparableKey.compareTo(nearest.key) : comparator.compare(key, nearest.key);
                if (comparison == 0) {
                    return nearest;
                }
                Node node = child = comparison < 0 ? nearest.left : nearest.right;
                if (child == null) break;
                nearest = child;
            }
        }
        if (!create) {
            return null;
        }
        Node<K, V> header = this.header;
        if (nearest == null) {
            if (comparator == NATURAL_ORDER && !(key instanceof Comparable)) {
                throw new ClassCastException(key.getClass().getName() + " is not Comparable");
            }
            table[index] = created = new Node(nearest, key, hash, header, header.prev);
        } else {
            created = new Node(nearest, key, hash, header, header.prev);
            if (comparison < 0) {
                nearest.left = created;
            } else {
                nearest.right = created;
            }
            this.rebalance(nearest, true);
        }
        if (this.size++ > this.threshold) {
            this.doubleCapacity();
        }
        ++this.modCount;
        return created;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.findByObject(key) != null;
    }

    private boolean equal(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    @Override
    public int size() {
        return this.size;
    }
}
