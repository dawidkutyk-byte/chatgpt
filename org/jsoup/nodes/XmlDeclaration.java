/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.SerializationException
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Entities
 *  org.jsoup.nodes.LeafNode
 */
package org.jsoup.nodes;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.LeafNode;

public class XmlDeclaration
extends LeafNode {
    private final boolean isProcessingInstruction;

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        accum.append("<").append(this.isProcessingInstruction ? "!" : "?").append(this.coreValue());
        this.getWholeDeclaration(accum, out);
        accum.append(this.isProcessingInstruction ? "!" : "?").append(">");
    }

    public XmlDeclaration clone() {
        return (XmlDeclaration)super.clone();
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) {
    }

    public String nodeName() {
        return "#declaration";
    }

    public String name() {
        return this.coreValue();
    }

    public XmlDeclaration(String name, boolean isProcessingInstruction) {
        Validate.notNull((Object)name);
        this.value = name;
        this.isProcessingInstruction = isProcessingInstruction;
    }

    public String toString() {
        return this.outerHtml();
    }

    private void getWholeDeclaration(Appendable accum, Document.OutputSettings out) throws IOException {
        Iterator iterator = this.attributes().iterator();
        while (iterator.hasNext()) {
            Attribute attribute = (Attribute)iterator.next();
            String key = attribute.getKey();
            String val = attribute.getValue();
            if (key.equals(this.nodeName())) continue;
            accum.append(' ');
            accum.append(key);
            if (val.isEmpty()) continue;
            accum.append("=\"");
            Entities.escape((Appendable)accum, (String)val, (Document.OutputSettings)out, (boolean)true, (boolean)false, (boolean)false, (boolean)false);
            accum.append('\"');
        }
    }

    public String getWholeDeclaration() {
        StringBuilder sb = StringUtil.borrowBuilder();
        try {
            this.getWholeDeclaration(sb, new Document.OutputSettings());
        }
        catch (IOException e) {
            throw new SerializationException((Throwable)e);
        }
        return StringUtil.releaseBuilder((StringBuilder)sb).trim();
    }
}
