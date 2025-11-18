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

final class TokeniserState.36
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        char c = r.consume();
        switch (c) {
            case '\t': 
            case '\n': 
            case '\f': 
            case '\r': 
            case ' ': {
                break;
            }
            case '/': {
                t.transition(SelfClosingStartTag);
                break;
            }
            case '=': {
                t.transition(BeforeAttributeValue);
                break;
            }
            case '>': {
                t.emitTagPending();
                t.transition(Data);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.tagPending.appendAttributeName('\ufffd', r.pos() - 1, r.pos());
                t.transition(AttributeName);
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            case '\"': 
            case '\'': 
            case '<': {
                t.error((TokeniserState)this);
                t.tagPending.newAttribute();
                t.tagPending.appendAttributeName(c, r.pos() - 1, r.pos());
                t.transition(AttributeName);
                break;
            }
            default: {
                t.tagPending.newAttribute();
                r.unconsume();
                t.transition(AttributeName);
            }
        }
    }

    TokeniserState.36() {
        super(string, n, null);
    }
}
