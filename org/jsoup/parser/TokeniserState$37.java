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

final class TokeniserState.37
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
            case '\"': {
                t.transition(AttributeValue_doubleQuoted);
                break;
            }
            case '&': {
                r.unconsume();
                t.transition(AttributeValue_unquoted);
                break;
            }
            case '\'': {
                t.transition(AttributeValue_singleQuoted);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.tagPending.appendAttributeValue('\ufffd', r.pos() - 1, r.pos());
                t.transition(AttributeValue_unquoted);
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.emitTagPending();
                t.transition(Data);
                break;
            }
            case '>': {
                t.error((TokeniserState)this);
                t.emitTagPending();
                t.transition(Data);
                break;
            }
            case '<': 
            case '=': 
            case '`': {
                t.error((TokeniserState)this);
                t.tagPending.appendAttributeValue(c, r.pos() - 1, r.pos());
                t.transition(AttributeValue_unquoted);
                break;
            }
            default: {
                r.unconsume();
                t.transition(AttributeValue_unquoted);
            }
        }
    }

    TokeniserState.37() {
        super(string, n, null);
    }
}
