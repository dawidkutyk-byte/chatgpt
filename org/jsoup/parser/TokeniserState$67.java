/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$CData
 *  org.jsoup.parser.Tokeniser
 *  org.jsoup.parser.TokeniserState
 */
package org.jsoup.parser;

import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Token;
import org.jsoup.parser.Tokeniser;
import org.jsoup.parser.TokeniserState;

final class TokeniserState.67
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        String data = r.consumeTo("]]>");
        t.dataBuffer.append(data);
        if (!r.matchConsume("]]>")) {
            if (!r.isEmpty()) return;
        }
        t.emit((Token)new Token.CData(t.dataBuffer.toString()));
        t.transition(Data);
    }

    TokeniserState.67() {
        super(string, n, null);
    }
}
