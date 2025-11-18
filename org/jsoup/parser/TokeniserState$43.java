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

final class TokeniserState.43
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        t.commentPending.append(r.consumeTo('>'));
        char next = r.current();
        if (next != '>') {
            if (next != '\uffff') return;
        }
        r.consume();
        t.emitCommentPending();
        t.transition(Data);
    }

    TokeniserState.43() {
        super(string, n, null);
    }
}
