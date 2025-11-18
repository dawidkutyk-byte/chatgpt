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

final class TokeniserState.25
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        if (r.matchesAsciiAlpha()) {
            t.createTempBuffer();
            t.dataBuffer.append(r.current());
            t.emit("<");
            t.emit(r.current());
            t.advanceTransition(ScriptDataDoubleEscapeStart);
        } else if (r.matches('/')) {
            t.createTempBuffer();
            t.advanceTransition(ScriptDataEscapedEndTagOpen);
        } else {
            t.emit('<');
            t.transition(ScriptDataEscaped);
        }
    }

    TokeniserState.25() {
        super(string, n, null);
    }
}
