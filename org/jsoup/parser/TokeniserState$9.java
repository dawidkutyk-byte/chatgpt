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

final class TokeniserState.9
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.isEmpty()) {
            t.eofError((TokeniserState)this);
            t.emit("</");
            t.transition(Data);
        } else if (r.matchesAsciiAlpha()) {
            t.createTagPending(false);
            t.transition(TagName);
        } else if (r.matches('>')) {
            t.error((TokeniserState)this);
            t.advanceTransition(Data);
        } else {
            t.error((TokeniserState)this);
            t.createBogusCommentPending();
            t.commentPending.append('/');
            t.transition(BogusComment);
        }
    }

    TokeniserState.9() {
        super(string, n, null);
    }
}
