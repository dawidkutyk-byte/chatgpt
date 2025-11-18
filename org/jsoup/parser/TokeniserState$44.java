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

final class TokeniserState.44
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.matchConsume("--")) {
            t.createCommentPending();
            t.transition(CommentStart);
        } else if (r.matchConsumeIgnoreCase("DOCTYPE")) {
            t.transition(Doctype);
        } else if (r.matchConsume("[CDATA[")) {
            t.createTempBuffer();
            t.transition(CdataSection);
        } else {
            t.error((TokeniserState)this);
            t.createBogusCommentPending();
            t.transition(BogusComment);
        }
    }

    TokeniserState.44() {
        super(string, n, null);
    }
}
