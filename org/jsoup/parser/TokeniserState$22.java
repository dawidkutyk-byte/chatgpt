/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.Tokeniser
 *  org.jsoup.parser.TokeniserState
 */
package org.jsoup.parser;

import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Tokeniser;
import org.jsoup.parser.TokeniserState;

final class TokeniserState.22
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.isEmpty()) {
            t.eofError((TokeniserState)this);
            t.transition(Data);
            return;
        }
        switch (r.current()) {
            case '-': {
                t.emit('-');
                t.advanceTransition(ScriptDataEscapedDash);
                break;
            }
            case '<': {
                t.advanceTransition(ScriptDataEscapedLessthanSign);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                r.advance();
                t.emit('\ufffd');
                break;
            }
            default: {
                String data = r.consumeToAny(new char[]{'-', '<', '\u0000'});
                t.emit(data);
            }
        }
    }

    TokeniserState.22() {
        super(string, n, null);
    }
}
