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

final class TokeniserState.3
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        switch (r.current()) {
            case '&': {
                t.advanceTransition(CharacterReferenceInRcdata);
                break;
            }
            case '<': {
                t.advanceTransition(RcdataLessthanSign);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                r.advance();
                t.emit('\ufffd');
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

    TokeniserState.3() {
        super(string, n, null);
    }
}
