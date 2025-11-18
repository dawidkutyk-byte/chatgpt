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

final class TokeniserState.47
extends TokeniserState {
    TokeniserState.47() {
        super(string, n, null);
    }

    void read(Tokeniser t, CharacterReader r) {
        char c = r.current();
        switch (c) {
            case '-': {
                t.advanceTransition(CommentEndDash);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                r.advance();
                t.commentPending.append('\ufffd');
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.emitCommentPending();
                t.transition(Data);
                break;
            }
            default: {
                t.commentPending.append(r.consumeToAny(new char[]{'-', '\u0000'}));
            }
        }
    }
}
