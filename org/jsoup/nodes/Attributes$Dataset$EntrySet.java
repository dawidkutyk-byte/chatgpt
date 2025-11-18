/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attributes$Dataset$DatasetIterator
 */
package org.jsoup.nodes;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.nodes.Attributes;

private class Attributes.Dataset.EntrySet
extends AbstractSet<Map.Entry<String, String>> {
    @Override
    public int size() {
        int count = 0;
        Attributes.Dataset.DatasetIterator iter = new Attributes.Dataset.DatasetIterator(Dataset.this, null);
        while (iter.hasNext()) {
            ++count;
        }
        return count;
    }

    private Attributes.Dataset.EntrySet() {
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return new Attributes.Dataset.DatasetIterator(Dataset.this, null);
    }
}
