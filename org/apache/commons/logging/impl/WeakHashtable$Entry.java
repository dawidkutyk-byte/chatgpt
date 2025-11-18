/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.logging.impl;

import java.util.Map;

private static final class WeakHashtable.Entry
implements Map.Entry {
    private final Object key;
    private final Object value;

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o == null) return result;
        if (!(o instanceof Map.Entry)) return result;
        Map.Entry entry = (Map.Entry)o;
        result = (this.getKey() == null ? entry.getKey() == null : this.getKey().equals(entry.getKey())) && (this.getValue() == null ? entry.getValue() == null : this.getValue().equals(entry.getValue()));
        return result;
    }

    public Object getValue() {
        return this.value;
    }

    public Object setValue(Object value) {
        throw new UnsupportedOperationException("Entry.setValue is not supported.");
    }

    public Object getKey() {
        return this.key;
    }

    private WeakHashtable.Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
    }
}
