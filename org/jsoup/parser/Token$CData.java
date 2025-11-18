/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token$Character
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;

static final class Token.CData
extends Token.Character {
    Token.CData(String data) {
        this.data(data);
    }

    public String toString() {
        return "<![CDATA[" + this.getData() + "]]>";
    }
}
