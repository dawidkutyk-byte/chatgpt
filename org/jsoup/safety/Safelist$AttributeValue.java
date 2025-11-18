/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.safety.Safelist$TypedValue
 */
package org.jsoup.safety;

import org.jsoup.safety.Safelist;

static class Safelist.AttributeValue
extends Safelist.TypedValue {
    static Safelist.AttributeValue valueOf(String value) {
        return new Safelist.AttributeValue(value);
    }

    Safelist.AttributeValue(String value) {
        super(value);
    }
}
