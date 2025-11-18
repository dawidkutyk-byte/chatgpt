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

static class Safelist.TagName
extends Safelist.TypedValue {
    Safelist.TagName(String value) {
        super(value);
    }

    static Safelist.TagName valueOf(String value) {
        return new Safelist.TagName(Normalizer.lowerCase((String)value));
    }
}
