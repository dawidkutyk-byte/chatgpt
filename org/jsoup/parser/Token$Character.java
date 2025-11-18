/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$TokenType
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;

static class Token.Character
extends Token
implements Cloneable {
    private String data;

    public String toString() {
        return this.getData();
    }

    Token reset() {
        super.reset();
        this.data = null;
        return this;
    }

    protected Token.Character clone() {
        try {
            return (Token.Character)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    Token.Character() {
        super(Token.TokenType.Character, null);
    }

    String getData() {
        return this.data;
    }

    Token.Character data(String data) {
        this.data = data;
        return this;
    }
}
