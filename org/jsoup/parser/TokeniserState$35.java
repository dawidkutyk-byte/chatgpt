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

final class TokeniserState.35
extends TokeniserState {
    TokeniserState.35() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        int pos = r.pos();
        String name = r.consumeToAnySorted(attributeNameCharsSorted);
        t.tagPending.appendAttributeName(name, pos, r.pos());
        pos = r.pos();
        char c = r.consume();
        switch (c) {
            case '\t': 
            case '\n': 
            case '\f': 
            case '\r': 
            case ' ': {
                t.transition(AfterAttributeName);
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
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            case '\"': 
            case '\'': 
            case '<': {
                t.error((TokeniserState)this);
                t.tagPending.appendAttributeName(c, pos, r.pos());
                break;
            }
            default: {
                t.tagPending.appendAttributeName(c, pos, r.pos());
            }
        }
    }
}
