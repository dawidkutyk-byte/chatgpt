/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token$Tag
 *  org.jsoup.parser.Token$TokenType
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;
import org.jsoup.parser.TreeBuilder;

static final class Token.EndTag
extends Token.Tag {
    public String toString() {
        return "</" + this.toStringName() + ">";
    }

    Token.EndTag(TreeBuilder treeBuilder) {
        super(Token.TokenType.EndTag, treeBuilder);
    }
}
