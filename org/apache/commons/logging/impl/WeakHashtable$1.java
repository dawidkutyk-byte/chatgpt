/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.impl.WeakHashtable$Referenced
 */
package org.apache.commons.logging.impl;

import java.util.Enumeration;
import org.apache.commons.logging.impl.WeakHashtable;

/*
 * Exception performing whole class analysis ignored.
 */
class WeakHashtable.1
implements Enumeration {
    private final /* synthetic */ Enumeration val$enumer;

    public Object nextElement() {
        WeakHashtable.Referenced nextReference = (WeakHashtable.Referenced)this.val$enumer.nextElement();
        return WeakHashtable.Referenced.access$100((WeakHashtable.Referenced)nextReference);
    }

    WeakHashtable.1(Enumeration enumeration) {
        this.val$enumer = enumeration;
    }

    @Override
    public boolean hasMoreElements() {
        return this.val$enumer.hasMoreElements();
    }
}
