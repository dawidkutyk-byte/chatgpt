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

final class TokeniserState.30
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        char c = r.consume();
        switch (c) {
            case '-': {
                t.emit(c);
                t.transition(ScriptDataDoubleEscapedDashDash);
                break;
            }
            case '<': {
                t.emit(c);
                t.transition(ScriptDataDoubleEscapedLessthanSign);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.emit('\ufffd');
                t.transition(ScriptDataDoubleEscaped);
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            default: {
                t.emit(c);
                t.transition(ScriptDataDoubleEscaped);
            }
        }
    }

    TokeniserState.30() {
        super(string, n, null);
    }
}
