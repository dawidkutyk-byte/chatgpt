/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.SerializationException
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Node
 *  org.jsoup.select.NodeVisitor
 */
package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.SerializationException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

private static class Node.OuterHtmlVisitor
implements NodeVisitor {
    private final Appendable accum;
    private final Document.OutputSettings out;

    public void tail(Node node, int depth) {
        if (node.nodeName().equals("#text")) return;
        try {
            node.outerHtmlTail(this.accum, depth, this.out);
        }
        catch (IOException exception) {
            throw new SerializationException((Throwable)exception);
        }
    }

    Node.OuterHtmlVisitor(Appendable accum, Document.OutputSettings out) {
        this.accum = accum;
        this.out = out;
        out.prepareEncoder();
    }

    public void head(Node node, int depth) {
        try {
            node.outerHtmlHead(this.accum, depth, this.out);
        }
        catch (IOException exception) {
            throw new SerializationException((Throwable)exception);
        }
    }
}
