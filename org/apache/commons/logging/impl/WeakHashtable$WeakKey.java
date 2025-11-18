/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.impl.WeakHashtable$Referenced
 */
package org.apache.commons.logging.impl;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.apache.commons.logging.impl.WeakHashtable;

private static final class WeakHashtable.WeakKey
extends WeakReference {
    private final WeakHashtable.Referenced referenced;

    static /* synthetic */ WeakHashtable.Referenced access$400(WeakHashtable.WeakKey x0) {
        return x0.getReferenced();
    }

    private WeakHashtable.WeakKey(Object key, ReferenceQueue queue, WeakHashtable.Referenced referenced) {
        super(key, queue);
        this.referenced = referenced;
    }

    private WeakHashtable.Referenced getReferenced() {
        return this.referenced;
    }
}
