/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$TokenType
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;

static final class Token.EOF
extends Token {
    Token reset() {
        super.reset();
        return this;
    }

    public String toString() {
        return "";
    }

    Token.EOF() {
        super(Token.TokenType.EOF, null);
    }
}
