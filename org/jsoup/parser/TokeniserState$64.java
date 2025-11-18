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

final class TokeniserState.64
extends TokeniserState {
    TokeniserState.64() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        char c = r.consume();
        switch (c) {
            case '\'': {
                t.transition(AfterDoctypeSystemIdentifier);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.doctypePending.systemIdentifier.append('\ufffd');
                break;
            }
            case '>': {
                t.error((TokeniserState)this);
                t.doctypePending.forceQuirks = true;
                t.emitDoctypePending();
                t.transition(Data);
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
                t.doctypePending.systemIdentifier.append(c);
            }
        }
    }
}
