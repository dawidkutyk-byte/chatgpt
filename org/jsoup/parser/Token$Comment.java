/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$TokenType
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;

static final class Token.Comment
extends Token {
    boolean bogus = false;
    private String dataS;
    private final StringBuilder data = new StringBuilder();

    Token reset() {
        super.reset();
        Token.Comment.reset((StringBuilder)this.data);
        this.dataS = null;
        this.bogus = false;
        return this;
    }

    Token.Comment append(char append) {
        this.ensureData();
        this.data.append(append);
        return this;
    }

    Token.Comment() {
        super(Token.TokenType.Comment, null);
    }

    public String toString() {
        return "<!--" + this.getData() + "-->";
    }

    private void ensureData() {
        if (this.dataS == null) return;
        this.data.append(this.dataS);
        this.dataS = null;
    }

    Token.Comment append(String append) {
        this.ensureData();
        if (this.data.length() == 0) {
            this.dataS = append;
        } else {
            this.data.append(append);
        }
        return this;
    }

    String getData() {
        return this.dataS != null ? this.dataS : this.data.toString();
    }
}
