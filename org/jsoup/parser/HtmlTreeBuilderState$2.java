/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.2
extends HtmlTreeBuilderState {
    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        tb.processStartTag("html");
        tb.transition(BeforeHead);
        return tb.process(t);
    }

    HtmlTreeBuilderState.2() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isDoctype()) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
        } else if (HtmlTreeBuilderState.access$100((Token)t)) {
            tb.insertCharacterNode(t.asCharacter());
        } else if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
            tb.insertElementFor(t.asStartTag());
            tb.transition(BeforeHead);
        } else {
            if (t.isEndTag() && StringUtil.inSorted((String)t.asEndTag().normalName(), (String[])HtmlTreeBuilderState.Constants.BeforeHtmlToHead)) {
                return this.anythingElse(t, tb);
            }
            if (!t.isEndTag()) return this.anythingElse(t, tb);
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        return true;
    }
}
