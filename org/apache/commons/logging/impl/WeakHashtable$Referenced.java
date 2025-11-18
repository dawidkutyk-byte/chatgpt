/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.impl.WeakHashtable$WeakKey
 */
package org.apache.commons.logging.impl;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.apache.commons.logging.impl.WeakHashtable;

private static final class WeakHashtable.Referenced {
    private final int hashCode;
    private final WeakReference reference;

    private WeakHashtable.Referenced(Object key, ReferenceQueue queue) {
        this.reference = new WeakHashtable.WeakKey(key, queue, this, null);
        this.hashCode = key.hashCode();
    }

    public boolean equals(Object o) {
        boolean result = false;
        if (!(o instanceof WeakHashtable.Referenced)) return result;
        WeakHashtable.Referenced otherKey = (WeakHashtable.Referenced)o;
        Object thisKeyValue = this.getValue();
        Object otherKeyValue = otherKey.getValue();
        if (thisKeyValue == null) {
            result = otherKeyValue == null;
            result = result && this.hashCode() == otherKey.hashCode();
        } else {
            result = thisKeyValue.equals(otherKeyValue);
        }
        return result;
    }

    private Object getValue() {
        return this.reference.get();
    }

    private WeakHashtable.Referenced(Object referant) {
        this.reference = new WeakReference<Object>(referant);
        this.hashCode = referant.hashCode();
    }

    static /* synthetic */ Object access$100(WeakHashtable.Referenced x0) {
        return x0.getValue();
    }

    public int hashCode() {
        return this.hashCode;
    }
}
