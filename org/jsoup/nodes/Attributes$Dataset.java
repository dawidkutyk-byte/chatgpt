/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Attributes$Dataset$EntrySet
 */
package org.jsoup.nodes;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import org.jsoup.nodes.Attributes;

/*
 * Exception performing whole class analysis ignored.
 */
private static class Attributes.Dataset
extends AbstractMap<String, String> {
    private final Attributes attributes;

    @Override
    public String put(String key, String value) {
        String dataKey = Attributes.access$400((String)key);
        String oldValue = this.attributes.hasKey(dataKey) ? this.attributes.get(dataKey) : null;
        this.attributes.put(dataKey, value);
        return oldValue;
    }

    static /* synthetic */ Attributes access$600(Attributes.Dataset x0) {
        return x0.attributes;
    }

    private Attributes.Dataset(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return new EntrySet(this, null);
    }
}
