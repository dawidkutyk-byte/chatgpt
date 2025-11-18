/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 */
package org.jsoup.safety;

import org.jsoup.helper.Validate;

static abstract class Safelist.TypedValue {
    private final String value;

    public String toString() {
        return this.value;
    }

    Safelist.TypedValue(String value) {
        Validate.notNull((Object)value);
        this.value = value;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.value == null ? 0 : this.value.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Safelist.TypedValue other = (Safelist.TypedValue)obj;
        if (this.value != null) return this.value.equals(other.value);
        return other.value == null;
    }
}
