/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Entities
 *  org.jsoup.nodes.LeafNode
 *  org.jsoup.nodes.Node
 */
package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;

public class TextNode
extends LeafNode {
    public TextNode(String text) {
        this.value = text;
    }

    public String toString() {
        return this.outerHtml();
    }

    static String stripLeadingWhitespace(String text) {
        return text.replaceFirst("^\\s+", "");
    }

    public String getWholeText() {
        return this.coreValue();
    }

    public static TextNode createFromEncoded(String encodedText) {
        String text = Entities.unescape((String)encodedText);
        return new TextNode(text);
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
    }

    public TextNode splitText(int offset) {
        String text = this.coreValue();
        Validate.isTrue((offset >= 0 ? 1 : 0) != 0, (String)"Split offset must be not be negative");
        Validate.isTrue((offset < text.length() ? 1 : 0) != 0, (String)"Split offset must not be greater than current text length");
        String head = text.substring(0, offset);
        String tail = text.substring(offset);
        this.text(head);
        TextNode tailNode = new TextNode(tail);
        if (this.parentNode == null) return tailNode;
        this.parentNode.addChildren(this.siblingIndex() + 1, new Node[]{tailNode});
        return tailNode;
    }

    static String normaliseWhitespace(String text) {
        text = StringUtil.normaliseWhitespace((String)text);
        return text;
    }

    public TextNode text(String text) {
        this.coreValue(text);
        return this;
    }

    public boolean isBlank() {
        return StringUtil.isBlank((String)this.coreValue());
    }

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        boolean prettyPrint = out.prettyPrint();
        Element parent = this.parentNode instanceof Element ? (Element)this.parentNode : null;
        boolean normaliseWhite = prettyPrint && !Element.preserveWhitespace((Node)this.parentNode);
        boolean trimLikeBlock = parent != null && (parent.tag().isBlock() || parent.tag().formatAsBlock());
        boolean trimLeading = false;
        boolean trimTrailing = false;
        if (normaliseWhite) {
            boolean couldSkip;
            trimLeading = trimLikeBlock && this.siblingIndex == 0 || this.parentNode instanceof Document;
            trimTrailing = trimLikeBlock && this.nextSibling() == null;
            Node next = this.nextSibling();
            Node prev = this.previousSibling();
            boolean isBlank = this.isBlank();
            boolean bl = couldSkip = next instanceof Element && ((Element)next).shouldIndent(out) || next instanceof TextNode && ((TextNode)next).isBlank() || prev instanceof Element && (((Element)prev).isBlock() || prev.nameIs("br"));
            if (couldSkip && isBlank) {
                return;
            }
            if (prev == null && parent != null && parent.tag().formatAsBlock() && !isBlank || out.outline() && this.siblingNodes().size() > 0 && !isBlank || prev != null && prev.nameIs("br")) {
                this.indent(accum, depth, out);
            }
        }
        Entities.escape((Appendable)accum, (String)this.coreValue(), (Document.OutputSettings)out, (boolean)false, (boolean)normaliseWhite, (boolean)trimLeading, (boolean)trimTrailing);
    }

    static boolean lastCharIsWhitespace(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ';
    }

    public TextNode clone() {
        return (TextNode)super.clone();
    }

    public String text() {
        return StringUtil.normaliseWhitespace((String)this.getWholeText());
    }

    public String nodeName() {
        return "#text";
    }
}
