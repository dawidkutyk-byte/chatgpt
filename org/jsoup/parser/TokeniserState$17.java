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

final class TokeniserState.17
extends TokeniserState {
    void read(Tokeniser t, CharacterReader r) {
        switch (r.consume()) {
            case '/': {
                t.createTempBuffer();
                t.transition(ScriptDataEndTagOpen);
                break;
            }
            case '!': {
                t.emit("<!");
                t.transition(ScriptDataEscapeStart);
                break;
            }
            case '\uffff': {
                t.emit("<");
                t.eofError((TokeniserState)this);
                t.transition(Data);
                break;
            }
            default: {
                t.emit("<");
                r.unconsume();
                t.transition(ScriptData);
            }
        }
    }

    TokeniserState.17() {
        super(string, n, null);
    }
}
