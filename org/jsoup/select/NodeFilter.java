/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Node
 *  org.jsoup.select.NodeFilter$FilterResult
 */
package org.jsoup.select;

import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

public interface NodeFilter {
    public FilterResult head(Node var1, int var2);

    default public FilterResult tail(Node node, int depth) {
        return FilterResult.CONTINUE;
    }
}
