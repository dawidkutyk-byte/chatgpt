/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.LeafNode
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.NodeUtils
 *  org.jsoup.nodes.XmlDeclaration
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Parser
 */
package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.NodeUtils;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;

public class Comment
extends LeafNode {
    public String toString() {
        return this.outerHtml();
    }

    public String getData() {
        return this.coreValue();
    }

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (out.prettyPrint() && (this.isEffectivelyFirst() && this.parentNode instanceof Element && ((Element)this.parentNode).tag().formatAsBlock() || out.outline())) {
            this.indent(accum, depth, out);
        }
        accum.append("<!--").append(this.getData()).append("-->");
    }

    private static boolean isXmlDeclarationData(String data) {
        return data.length() > 1 && (data.startsWith("!") || data.startsWith("?"));
    }

    public String nodeName() {
        return "#comment";
    }

    public Comment(String data) {
        this.value = data;
    }

    public @7\u015aCz XmlDeclaration asXmlDeclaration() {
        String data = this.getData();
        XmlDeclaration decl = null;
        String declContent = data.substring(1, data.length() - 1);
        if (Comment.isXmlDeclarationData(declContent)) {
            return null;
        }
        String fragment = "<" + declContent + ">";
        Document doc = Parser.htmlParser().settings(ParseSettings.preserveCase).parseInput(fragment, this.baseUri());
        if (doc.body().childrenSize() <= 0) return decl;
        Element el = doc.body().child(0);
        decl = new XmlDeclaration(NodeUtils.parser((Node)doc).settings().normalizeTag(el.tagName()), data.startsWith("!"));
        decl.attributes().addAll(el.attributes());
        return decl;
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) {
    }

    public boolean isXmlDeclaration() {
        String data = this.getData();
        return Comment.isXmlDeclarationData(data);
    }

    public Comment clone() {
        return (Comment)super.clone();
    }

    public Comment setData(String data) {
        this.coreValue(data);
        return this;
    }
}
