/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Node
 */
package org.jsoup.nodes;

import java.util.List;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Node;

public abstract class LeafNode
extends Node {
    Object value;

    String coreValue() {
        return this.attr(this.nodeName());
    }

    public String baseUri() {
        return this.hasParent() ? this.parent().baseUri() : "";
    }

    void coreValue(String value) {
        this.attr(this.nodeName(), value);
    }

    public Node empty() {
        return this;
    }

    protected List<Node> ensureChildNodes() {
        return EmptyNodes;
    }

    public String absUrl(String key) {
        this.ensureAttributes();
        return super.absUrl(key);
    }

    public String attr(String key) {
        if (this.hasAttributes()) return super.attr(key);
        return this.nodeName().equals(key) ? (String)this.value : "";
    }

    protected void doSetBaseUri(String baseUri) {
    }

    public int childNodeSize() {
        return 0;
    }

    public boolean hasAttr(String key) {
        this.ensureAttributes();
        return super.hasAttr(key);
    }

    protected LeafNode doClone(Node parent) {
        LeafNode clone = (LeafNode)super.doClone(parent);
        if (!this.hasAttributes()) return clone;
        clone.value = ((Attributes)this.value).clone();
        return clone;
    }

    public final Attributes attributes() {
        this.ensureAttributes();
        return (Attributes)this.value;
    }

    private void ensureAttributes() {
        if (this.hasAttributes()) return;
        Object coreValue = this.value;
        Attributes attributes = new Attributes();
        this.value = attributes;
        if (coreValue == null) return;
        attributes.put(this.nodeName(), (String)coreValue);
    }

    public Node attr(String key, String value) {
        if (!this.hasAttributes() && key.equals(this.nodeName())) {
            this.value = value;
        } else {
            this.ensureAttributes();
            super.attr(key, value);
        }
        return this;
    }

    public Node removeAttr(String key) {
        this.ensureAttributes();
        return super.removeAttr(key);
    }

    protected final boolean hasAttributes() {
        return this.value instanceof Attributes;
    }
}
