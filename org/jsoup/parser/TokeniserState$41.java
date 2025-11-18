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

final class TokeniserState.41
extends TokeniserState {
    TokeniserState.41() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        char c = r.consume();
        switch (c) {
            case '\t': 
            case '\n': 
            case '\f': 
            case '\r': 
            case ' ': {
                t.transition(BeforeAttributeName);
                break;
            }
            case '/': {
                t.transition(SelfClosingStartTag);
                break;
            }
            case '>': {
                t.emitTagPending();
                t.transition(Data);
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            default: {
                r.unconsume();
                t.error((TokeniserState)this);
                t.transition(BeforeAttributeName);
            }
        }
    }
}
