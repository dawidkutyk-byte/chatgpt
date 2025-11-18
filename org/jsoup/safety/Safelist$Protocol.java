/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.safety.Safelist$TypedValue
 */
package org.jsoup.safety;

import org.jsoup.safety.Safelist;

static class Safelist.Protocol
extends Safelist.TypedValue {
    Safelist.Protocol(String value) {
        super(value);
    }

    static Safelist.Protocol valueOf(String value) {
        return new Safelist.Protocol(value);
    }
}
