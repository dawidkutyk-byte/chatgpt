/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Node
 */
package org.jsoup.select;

import org.jsoup.nodes.Node;

@FunctionalInterface
public interface NodeVisitor {
    public void head(Node var1, int var2);

    default public void tail(Node node, int depth) {
    }
}
