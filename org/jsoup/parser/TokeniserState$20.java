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

final class TokeniserState.20
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.matches('-')) {
            t.emit('-');
            t.advanceTransition(ScriptDataEscapeStartDash);
        } else {
            t.transition(ScriptData);
        }
    }

    TokeniserState.20() {
        super(string, n, null);
    }
}
