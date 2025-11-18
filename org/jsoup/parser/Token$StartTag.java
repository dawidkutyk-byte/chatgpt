/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Token$Tag
 *  org.jsoup.parser.Token$TokenType
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.parser;

import org.jsoup.nodes.Attributes;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Token;
import org.jsoup.parser.TreeBuilder;

static final class Token.StartTag
extends Token.Tag {
    Token.StartTag nameAttr(String name, Attributes attributes) {
        this.tagName = name;
        this.attributes = attributes;
        this.normalName = ParseSettings.normalName((String)this.tagName);
        return this;
    }

    Token.Tag reset() {
        super.reset();
        this.attributes = null;
        return this;
    }

    public String toString() {
        String closer = this.isSelfClosing() ? "/>" : ">";
        if (!this.hasAttributes()) return "<" + this.toStringName() + closer;
        if (this.attributes.size() <= 0) return "<" + this.toStringName() + closer;
        return "<" + this.toStringName() + " " + this.attributes.toString() + closer;
    }

    Token.StartTag(TreeBuilder treeBuilder) {
        super(Token.TokenType.StartTag, treeBuilder);
    }
}
