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

final class TokeniserState.66
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        char c = r.consume();
        switch (c) {
            case '>': {
                t.emitDoctypePending();
                t.transition(Data);
                break;
            }
            case '\uffff': {
                t.emitDoctypePending();
                t.transition(Data);
                break;
            }
        }
    }

    TokeniserState.66() {
        super(string, n, null);
    }
}
