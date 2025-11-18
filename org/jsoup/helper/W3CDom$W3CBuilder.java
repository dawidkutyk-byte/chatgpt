/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.DataNode
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.select.NodeVisitor
 */
package org.jsoup.helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.select.NodeVisitor;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

protected static class W3CDom.W3CBuilder
implements NodeVisitor {
    private final Stack<HashMap<String, String>> namespacesStack = new Stack();
    private final org.w3c.dom.Document doc;
    private Document.OutputSettings.Syntax syntax = Document.OutputSettings.Syntax.xml;
    private static final String xmlnsPrefix = "xmlns:";
    private boolean namespaceAware = true;
    private final org.jsoup.nodes.Element contextElement;
    private static final String xmlnsKey = "xmlns";
    private org.w3c.dom.Node dest;

    public void head(Node source, int depth) {
        this.namespacesStack.push(new HashMap(this.namespacesStack.peek()));
        if (source instanceof org.jsoup.nodes.Element) {
            org.jsoup.nodes.Element sourceEl = (org.jsoup.nodes.Element)source;
            String prefix = this.updateNamespaces(sourceEl);
            String namespace = this.namespaceAware ? this.namespacesStack.peek().get(prefix) : null;
            String tagName = sourceEl.tagName();
            try {
                String imputedNamespace = namespace == null && tagName.contains(":") ? "" : namespace;
                Element el = this.doc.createElementNS(imputedNamespace, tagName);
                this.copyAttributes((Node)sourceEl, el);
                this.append(el, (Node)sourceEl);
                if (sourceEl == this.contextElement) {
                    this.doc.setUserData("jsoupContextNode", el, null);
                }
                this.dest = el;
            }
            catch (DOMException e) {
                this.append(this.doc.createTextNode("<" + tagName + ">"), (Node)sourceEl);
            }
        } else if (source instanceof TextNode) {
            TextNode sourceText = (TextNode)source;
            Text text = this.doc.createTextNode(sourceText.getWholeText());
            this.append(text, (Node)sourceText);
        } else if (source instanceof Comment) {
            Comment sourceComment = (Comment)source;
            org.w3c.dom.Comment comment = this.doc.createComment(sourceComment.getData());
            this.append(comment, (Node)sourceComment);
        } else {
            if (!(source instanceof DataNode)) return;
            DataNode sourceData = (DataNode)source;
            Text node = this.doc.createTextNode(sourceData.getWholeData());
            this.append(node, (Node)sourceData);
        }
    }

    public W3CDom.W3CBuilder(org.w3c.dom.Document doc) {
        this.doc = doc;
        this.namespacesStack.push(new HashMap());
        this.dest = doc;
        this.contextElement = (org.jsoup.nodes.Element)doc.getUserData("jsoupContextSource");
        Document inDoc = this.contextElement.ownerDocument();
        if (!this.namespaceAware) return;
        if (inDoc == null) return;
        if (!(inDoc.parser().getTreeBuilder() instanceof HtmlTreeBuilder)) return;
        this.namespacesStack.peek().put("", "http://www.w3.org/1999/xhtml");
    }

    private String updateNamespaces(org.jsoup.nodes.Element el) {
        Attributes attributes = el.attributes();
        for (Attribute attr : attributes) {
            String prefix;
            String key = attr.getKey();
            if (key.equals(xmlnsKey)) {
                prefix = "";
            } else {
                if (!key.startsWith(xmlnsPrefix)) continue;
                prefix = key.substring(xmlnsPrefix.length());
            }
            this.namespacesStack.peek().put(prefix, attr.getValue());
        }
        int pos = el.tagName().indexOf(58);
        return pos > 0 ? el.tagName().substring(0, pos) : "";
    }

    static /* synthetic */ Document.OutputSettings.Syntax access$102(W3CDom.W3CBuilder x0, Document.OutputSettings.Syntax x1) {
        x0.syntax = x1;
        return x0.syntax;
    }

    public void tail(Node source, int depth) {
        if (source instanceof org.jsoup.nodes.Element && this.dest.getParentNode() instanceof Element) {
            this.dest = this.dest.getParentNode();
        }
        this.namespacesStack.pop();
    }

    private void append(org.w3c.dom.Node append, Node source) {
        append.setUserData("jsoupSource", source, null);
        this.dest.appendChild(append);
    }

    private void copyAttributes(Node source, Element el) {
        Iterator iterator = source.attributes().iterator();
        while (iterator.hasNext()) {
            Attribute attribute = (Attribute)iterator.next();
            String key = Attribute.getValidKey((String)attribute.getKey(), (Document.OutputSettings.Syntax)this.syntax);
            if (key == null) continue;
            el.setAttribute(key, attribute.getValue());
        }
    }

    static /* synthetic */ boolean access$002(W3CDom.W3CBuilder x0, boolean x1) {
        x0.namespaceAware = x1;
        return x0.namespaceAware;
    }
}
