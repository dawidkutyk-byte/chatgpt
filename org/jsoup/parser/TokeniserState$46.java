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

final class TokeniserState.46
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        char c = r.consume();
        switch (c) {
            case '-': {
                t.transition(CommentEnd);
                break;
            }
            case '\u0000': {
                t.error((TokeniserState)this);
                t.commentPending.append('\ufffd');
                t.transition(Comment);
                break;
            }
            case '>': {
                t.error((TokeniserState)this);
                t.emitCommentPending();
                t.transition(Data);
                break;
            }
            case '\uffff': {
                t.eofError((TokeniserState)this);
                t.emitCommentPending();
                t.transition(Data);
                break;
            }
            default: {
                t.commentPending.append(c);
                t.transition(Comment);
            }
        }
    }

    TokeniserState.46() {
        super(string, n, null);
    }
}
