/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup.nodes;

static enum Entities.CoreCharset {
    ascii,
    utf,
    fallback;


    static Entities.CoreCharset byName(String name) {
        if (name.equals("US-ASCII")) {
            return ascii;
        }
        if (!name.startsWith("UTF-")) return fallback;
        return utf;
    }
}
