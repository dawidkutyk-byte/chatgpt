/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.CharacterReader
 */
package org.jsoup.parser;

import org.jsoup.parser.CharacterReader;

public class ParseError {
    private final String errorMsg;
    private final String cursorPos;
    private final int pos;

    ParseError(CharacterReader reader, String errorFormat, Object ... args) {
        this.pos = reader.pos();
        this.cursorPos = reader.posLineCol();
        this.errorMsg = String.format(errorFormat, args);
    }

    public String toString() {
        return "<" + this.cursorPos + ">: " + this.errorMsg;
    }

    public int getPosition() {
        return this.pos;
    }

    public String getErrorMessage() {
        return this.errorMsg;
    }

    ParseError(int pos, String errorMsg) {
        this.pos = pos;
        this.cursorPos = String.valueOf(pos);
        this.errorMsg = errorMsg;
    }

    ParseError(CharacterReader reader, String errorMsg) {
        this.pos = reader.pos();
        this.cursorPos = reader.posLineCol();
        this.errorMsg = errorMsg;
    }

    public String getCursorPos() {
        return this.cursorPos;
    }

    ParseError(int pos, String errorFormat, Object ... args) {
        this.pos = pos;
        this.cursorPos = String.valueOf(pos);
        this.errorMsg = String.format(errorFormat, args);
    }
}
