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

final class TokeniserState.52
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.matchesAsciiAlpha()) {
            t.createDoctypePending();
            t.transition(DoctypeName);
            return;
        }
        char c = r.consume();
        switch (c) {
            case '\t': 
            case '\n': 
            case '\f': 
            case '\r': 
            case ' ': {
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.createDoctypePending();
                t.doctypePending.name.append('\ufffd');
                t.transition(DoctypeName);
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.createDoctypePending();
                t.doctypePending.forceQuirks = true;
                t.emitDoctypePending();
                t.transition(Data);
                break;
            }
            default: {
                t.createDoctypePending();
                t.doctypePending.name.append(c);
                t.transition(DoctypeName);
            }
        }
    }

    TokeniserState.52() {
        super(string, n, null);
    }
}
