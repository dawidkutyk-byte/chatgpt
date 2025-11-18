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

final class TokeniserState.54
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.isEmpty()) {
            t.eofError((TokeniserState)this);
            t.doctypePending.forceQuirks = true;
            t.emitDoctypePending();
            t.transition(Data);
            return;
        }
        if (r.matchesAny(new char[]{'\t', '\n', '\r', '\f', ' '})) {
            r.advance();
        } else if (r.matches('>')) {
            t.emitDoctypePending();
            t.advanceTransition(Data);
        } else if (r.matchConsumeIgnoreCase("PUBLIC")) {
            t.doctypePending.pubSysKey = "PUBLIC";
            t.transition(AfterDoctypePublicKeyword);
        } else if (r.matchConsumeIgnoreCase("SYSTEM")) {
            t.doctypePending.pubSysKey = "SYSTEM";
            t.transition(AfterDoctypeSystemKeyword);
        } else {
            t.error((TokeniserState)this);
            t.doctypePending.forceQuirks = true;
            t.advanceTransition(BogusDoctype);
        }
    }

    TokeniserState.54() {
        super(string, n, null);
    }
}
