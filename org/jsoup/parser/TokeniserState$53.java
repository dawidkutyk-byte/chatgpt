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

final class TokeniserState.53
extends TokeniserState {
    TokeniserState.53() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        if (r.matchesLetter()) {
            String name = r.consumeLetterSequence();
            t.doctypePending.name.append(name);
            return;
        }
        char c = r.consume();
        switch (c) {
            case '>': {
                t.emitDoctypePending();
                t.transition(Data);
                break;
            }
            case '\t': 
            case '\n': 
            case '\f': 
            case '\r': 
            case ' ': {
                t.transition(AfterDoctypeName);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.doctypePending.name.append('\ufffd');
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.doctypePending.forceQuirks = true;
                t.emitDoctypePending();
                t.transition(Data);
                break;
            }
            default: {
                t.doctypePending.name.append(c);
            }
        }
    }
}
