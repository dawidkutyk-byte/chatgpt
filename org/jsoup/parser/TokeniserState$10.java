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

/*
 * Exception performing whole class analysis ignored.
 */
final class TokeniserState.10
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        String tagName = r.consumeTagName();
        t.tagPending.appendTagName(tagName);
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
            case '<': {
                r.unconsume();
                t.error((TokeniserState)this);
            }
            case '>': {
                t.emitTagPending();
                t.transition(Data);
                break;
            }
            case '\u0000': {
                t.tagPending.appendTagName(TokeniserState.access$300());
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            default: {
                t.tagPending.appendTagName(c);
            }
        }
    }

    TokeniserState.10() {
        super(string, n, null);
    }
}
