/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.impl.WeakHashtable$Entry
 *  org.apache.commons.logging.impl.WeakHashtable$Referenced
 *  org.apache.commons.logging.impl.WeakHashtable$WeakKey
 */
package org.apache.commons.logging.impl;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.impl.WeakHashtable;

/*
 * Exception performing whole class analysis ignored.
 */
public final class WeakHashtable
extends Hashtable {
    private static final int PARTIAL_PURGE_COUNT = 10;
    private int changeCount = 0;
    private final ReferenceQueue queue = new ReferenceQueue();
    private static final int MAX_CHANGES_BEFORE_PURGE = 100;
    private static final long serialVersionUID = -1546036869799732453L;

    @Override
    public Set keySet() {
        this.purge();
        Set referencedKeys = super.keySet();
        HashSet<Object> unreferencedKeys = new HashSet<Object>();
        Iterator it = referencedKeys.iterator();
        while (it.hasNext()) {
            Referenced referenceKey = (Referenced)it.next();
            Object keyValue = Referenced.access$100((Referenced)referenceKey);
            if (keyValue == null) continue;
            unreferencedKeys.add(keyValue);
        }
        return unreferencedKeys;
    }

    @Override
    public synchronized Object remove(Object key) {
        if (this.changeCount++ > 100) {
            this.purge();
            this.changeCount = 0;
        } else {
            if (this.changeCount % 10 != 0) return super.remove(new Referenced(key, null));
            this.purgeOne();
        }
        return super.remove(new Referenced(key, null));
    }

    @Override
    public Set entrySet() {
        this.purge();
        Set referencedEntries = super.entrySet();
        HashSet<Entry> unreferencedEntries = new HashSet<Entry>();
        Iterator it = referencedEntries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            Referenced referencedKey = (Referenced)entry.getKey();
            Object key = Referenced.access$100((Referenced)referencedKey);
            Object value = entry.getValue();
            if (key == null) continue;
            Entry dereferencedEntry = new Entry(key, value, null);
            unreferencedEntries.add(dereferencedEntry);
        }
        return unreferencedEntries;
    }

    @Override
    public boolean isEmpty() {
        this.purge();
        return super.isEmpty();
    }

    @Override
    public int size() {
        this.purge();
        return super.size();
    }

    @Override
    public Object get(Object key) {
        Referenced referenceKey = new Referenced(key, null);
        return super.get(referenceKey);
    }

    @Override
    public boolean containsKey(Object key) {
        Referenced referenced = new Referenced(key, null);
        return super.containsKey(referenced);
    }

    @Override
    protected void rehash() {
        this.purge();
        super.rehash();
    }

    @Override
    public synchronized Object put(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException("Null keys are not allowed");
        }
        if (value == null) {
            throw new NullPointerException("Null values are not allowed");
        }
        if (this.changeCount++ > 100) {
            this.purge();
            this.changeCount = 0;
        } else if (this.changeCount % 10 == 0) {
            this.purgeOne();
        }
        Referenced keyRef = new Referenced(key, this.queue, null);
        return super.put(keyRef, value);
    }

    @Override
    public String toString() {
        this.purge();
        return super.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void purge() {
        ArrayList<Referenced> toRemove = new ArrayList<Referenced>();
        ReferenceQueue referenceQueue = this.queue;
        synchronized (referenceQueue) {
            WeakKey key;
            while ((key = (WeakKey)this.queue.poll()) != null) {
                toRemove.add(WeakKey.access$400((WeakKey)key));
            }
        }
        int size = toRemove.size();
        int i = 0;
        while (i < size) {
            super.remove(toRemove.get(i));
            ++i;
        }
    }

    @Override
    public void putAll(Map t) {
        if (t == null) return;
        Set entrySet = t.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Collection values() {
        this.purge();
        return super.values();
    }

    @Override
    public Enumeration elements() {
        this.purge();
        return super.elements();
    }

    @Override
    public Enumeration keys() {
        this.purge();
        Enumeration enumer = super.keys();
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void purgeOne() {
        ReferenceQueue referenceQueue = this.queue;
        synchronized (referenceQueue) {
            WeakKey key = (WeakKey)this.queue.poll();
            if (key == null) return;
            super.remove(WeakKey.access$400((WeakKey)key));
        }
    }
}
