/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.LeafNode
 */
package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.LeafNode;

public class DataNode
extends LeafNode {
    public String toString() {
        return this.outerHtml();
    }

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        String data = this.getWholeData();
        if (out.syntax() == Document.OutputSettings.Syntax.xml && !data.contains("<![CDATA[")) {
            if (this.parentNameIs("script")) {
                accum.append("//<![CDATA[\n").append(data).append("\n//]]>");
            } else if (this.parentNameIs("style")) {
                accum.append("/*<![CDATA[*/\n").append(data).append("\n/*]]>*/");
            } else {
                accum.append("<![CDATA[").append(data).append("]]>");
            }
        } else {
            accum.append(this.getWholeData());
        }
    }

    public String getWholeData() {
        return this.coreValue();
    }

    public DataNode setWholeData(String data) {
        this.coreValue(data);
        return this;
    }

    public DataNode(String data) {
        this.value = data;
    }

    public String nodeName() {
        return "#data";
    }

    public DataNode clone() {
        return (DataNode)super.clone();
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) {
    }
}
