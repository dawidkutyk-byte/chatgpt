/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 */
package org.jsoup.safety;

import org.jsoup.nodes.Element;

private static class Cleaner.ElementMeta {
    int numAttribsDiscarded;
    Element el;

    Cleaner.ElementMeta(Element el, int numAttribsDiscarded) {
        this.el = el;
        this.numAttribsDiscarded = numAttribsDiscarded;
    }
}
