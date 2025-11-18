/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.safety.Safelist$TypedValue
 */
package org.jsoup.safety;

import org.jsoup.internal.Normalizer;
import org.jsoup.safety.Safelist;

static class Safelist.AttributeKey
extends Safelist.TypedValue {
    Safelist.AttributeKey(String value) {
        super(value);
    }

    static Safelist.AttributeKey valueOf(String value) {
        return new Safelist.AttributeKey(Normalizer.lowerCase((String)value));
    }
}
