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

final class TokeniserState.29
extends TokeniserState {
    TokeniserState.29() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        char c = r.current();
        switch (c) {
            case '-': {
                t.emit(c);
                t.advanceTransition(ScriptDataDoubleEscapedDash);
                break;
            }
            case '<': {
                t.emit(c);
                t.advanceTransition(ScriptDataDoubleEscapedLessthanSign);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                r.advance();
                t.emit('\ufffd');
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            default: {
                String data = r.consumeToAny(new char[]{'-', '<', '\u0000'});
                t.emit(data);
            }
        }
    }
}
