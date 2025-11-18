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

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.21
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.21() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (HtmlTreeBuilderState.access$100((Token)t)) {
            tb.insertCharacterNode(t.asCharacter());
        } else if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
        } else {
            if (t.isDoctype()) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (t.isEndTag() && t.asEndTag().normalName().equals("html")) {
                tb.transition(AfterAfterFrameset);
            } else {
                if (t.isStartTag() && t.asStartTag().normalName().equals("noframes")) {
                    return tb.process(t, InHead);
                }
                if (t.isEOF()) return true;
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
        }
        return true;
    }
}
