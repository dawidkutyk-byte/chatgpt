/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token$CData
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$Comment
 *  org.jsoup.parser.Token$Doctype
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.Token$TokenType
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;

abstract class Token {
    static final int Unset = -1;
    private int endPos = -1;
    private int startPos;
    final TokenType type;

    final boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    final boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    void startPos(int pos) {
        this.startPos = pos;
    }

    final Comment asComment() {
        return (Comment)this;
    }

    void endPos(int pos) {
        this.endPos = pos;
    }

    String tokenType() {
        return this.getClass().getSimpleName();
    }

    final boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    int endPos() {
        return this.endPos;
    }

    final EndTag asEndTag() {
        return (EndTag)this;
    }

    final Doctype asDoctype() {
        return (Doctype)this;
    }

    final boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    final boolean isCData() {
        return this instanceof CData;
    }

    final boolean isComment() {
        return this.type == TokenType.Comment;
    }

    final Character asCharacter() {
        return (Character)this;
    }

    Token reset() {
        this.startPos = -1;
        this.endPos = -1;
        return this;
    }

    static void reset(StringBuilder sb) {
        if (sb == null) return;
        sb.delete(0, sb.length());
    }

    private Token(TokenType type) {
        this.type = type;
    }

    final StartTag asStartTag() {
        return (StartTag)this;
    }

    final boolean isEOF() {
        return this.type == TokenType.EOF;
    }

    int startPos() {
        return this.startPos;
    }
}
