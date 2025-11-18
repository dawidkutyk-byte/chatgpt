/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$EOF
 *  org.jsoup.parser.Tokeniser
 *  org.jsoup.parser.TokeniserState
 */
package org.jsoup.parser;

import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Token;
import org.jsoup.parser.Tokeniser;
import org.jsoup.parser.TokeniserState;

final class TokeniserState.1
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        switch (r.current()) {
            case '&': {
                t.advanceTransition(CharacterReferenceInData);
                break;
            }
            case '<': {
                t.advanceTransition(TagOpen);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.emit(r.consume());
                break;
            }
            case '\uffff': {
                t.emit((Token)new Token.EOF());
                break;
            }
            default: {
                String data = r.consumeData();
                t.emit(data);
            }
        }
    }

    TokeniserState.1() {
        super(string, n, null);
    }
}
