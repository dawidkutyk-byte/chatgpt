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

final class TokeniserState.40
extends TokeniserState {
    TokeniserState.40() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        int pos = r.pos();
        String value = r.consumeToAnySorted(attributeValueUnquoted);
        if (value.length() > 0) {
            t.tagPending.appendAttributeValue(value, pos, r.pos());
        }
        pos = r.pos();
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
            case '&': {
                int[] ref = t.consumeCharacterReference(Character.valueOf('>'), true);
                if (ref != null) {
                    t.tagPending.appendAttributeValue(ref, pos, r.pos());
                    break;
                }
                t.tagPending.appendAttributeValue('&', pos, r.pos());
                break;
            }
            case '>': {
                t.emitTagPending();
                t.transition(Data);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.tagPending.appendAttributeValue('\ufffd', pos, r.pos());
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            case '\"': 
            case '\'': 
            case '<': 
            case '=': 
            case '`': {
                t.error((TokeniserState)this);
                t.tagPending.appendAttributeValue(c, pos, r.pos());
                break;
            }
            default: {
                t.tagPending.appendAttributeValue(c, pos, r.pos());
            }
        }
    }
}
