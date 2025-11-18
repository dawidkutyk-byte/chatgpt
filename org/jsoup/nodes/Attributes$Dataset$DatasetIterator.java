/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes$Dataset
 */
package org.jsoup.nodes;

import java.util.Iterator;
import java.util.Map;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

/*
 * Exception performing whole class analysis ignored.
 */
private class Attributes.Dataset.DatasetIterator
implements Iterator<Map.Entry<String, String>> {
    private Iterator<Attribute> attrIter;
    private Attribute attr;

    @Override
    public Map.Entry<String, String> next() {
        return new Attribute(this.attr.getKey().substring("data-".length()), this.attr.getValue());
    }

    @Override
    public boolean hasNext() {
        do {
            if (!this.attrIter.hasNext()) return false;
            this.attr = this.attrIter.next();
        } while (!this.attr.isDataAttribute());
        return true;
    }

    private Attributes.Dataset.DatasetIterator() {
        this.attrIter = Attributes.Dataset.access$600((Attributes.Dataset)Dataset.this).iterator();
    }

    @Override
    public void remove() {
        Attributes.Dataset.access$600((Attributes.Dataset)Dataset.this).remove(this.attr.getKey());
    }
}
