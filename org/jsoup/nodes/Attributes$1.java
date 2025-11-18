/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes
 */
package org.jsoup.nodes;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

/*
 * Exception performing whole class analysis ignored.
 */
class Attributes.1
implements Iterator<Attribute> {
    int expectedSize;
    int i;

    private void checkModified() {
        if (Attributes.access$000((Attributes)Attributes.this) == this.expectedSize) return;
        throw new ConcurrentModificationException("Use Iterator#remove() instead to remove attributes while iterating.");
    }

    @Override
    public boolean hasNext() {
        this.checkModified();
        while (this.i < Attributes.access$000((Attributes)Attributes.this) && Attributes.isInternalKey((String)Attributes.this.keys[this.i])) {
            ++this.i;
        }
        return this.i < Attributes.access$000((Attributes)Attributes.this);
    }

    @Override
    public void remove() {
        Attributes.access$100((Attributes)Attributes.this, (int)(--this.i));
        --this.expectedSize;
    }

    Attributes.1() {
        this.expectedSize = Attributes.access$000((Attributes)Attributes.this);
        this.i = 0;
    }

    @Override
    public Attribute next() {
        this.checkModified();
        if (this.i >= Attributes.access$000((Attributes)Attributes.this)) {
            throw new NoSuchElementException();
        }
        Attribute attr = new Attribute(Attributes.this.keys[this.i], (String)Attributes.this.vals[this.i], Attributes.this);
        ++this.i;
        return attr;
    }
}
