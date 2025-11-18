/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.TextNode
 */
package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;

public class CDataNode
extends TextNode {
    public String text() {
        return this.getWholeText();
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        accum.append("]]>");
    }

    public String nodeName() {
        return "#cdata";
    }

    public CDataNode clone() {
        return (CDataNode)super.clone();
    }

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        accum.append("<![CDATA[").append(this.getWholeText());
    }

    public CDataNode(String text) {
        super(text);
    }
}
