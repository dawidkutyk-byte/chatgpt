/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.Token
 */
package org.jsoup.parser;

import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

final class HtmlTreeBuilderState.8
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.8() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isCharacter()) {
            tb.insertCharacterNode(t.asCharacter());
        } else {
            if (t.isEOF()) {
                tb.error((HtmlTreeBuilderState)this);
                tb.pop();
                tb.transition(tb.originalState());
                return tb.process(t);
            }
            if (!t.isEndTag()) return true;
            tb.pop();
            tb.transition(tb.originalState());
        }
        return true;
    }
}
