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
final class HtmlTreeBuilderState.23
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.23() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
        } else {
            if (t.isDoctype()) return tb.process(t, InBody);
            if (HtmlTreeBuilderState.access$100((Token)t)) return tb.process(t, InBody);
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (t.isEOF()) return true;
            if (t.isStartTag() && t.asStartTag().normalName().equals("noframes")) {
                return tb.process(t, InHead);
            }
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        return true;
    }
}
