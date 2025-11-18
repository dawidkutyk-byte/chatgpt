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

final class TokeniserState.24
extends TokeniserState {
    TokeniserState.24() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        if (r.isEmpty()) {
            t.eofError((TokeniserState)this);
            t.transition(Data);
            return;
        }
        char c = r.consume();
        switch (c) {
            case '-': {
                t.emit(c);
                break;
            }
            case '<': {
                t.transition(ScriptDataEscapedLessthanSign);
                break;
            }
            case '>': {
                t.emit(c);
                t.transition(ScriptData);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.emit('\ufffd');
                t.transition(ScriptDataEscaped);
                break;
            }
            default: {
                t.emit(c);
                t.transition(ScriptDataEscaped);
            }
        }
    }
}
