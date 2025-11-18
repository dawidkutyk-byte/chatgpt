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

final class HtmlTreeBuilderState.17
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.17() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isStartTag() && StringUtil.inSorted((String)t.asStartTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InSelectTableEnd)) {
            tb.error((HtmlTreeBuilderState)this);
            tb.popStackToClose("select");
            tb.resetInsertionMode();
            return tb.process(t);
        }
        if (!t.isEndTag()) return tb.process(t, InSelect);
        if (!StringUtil.inSorted((String)t.asEndTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InSelectTableEnd)) return tb.process(t, InSelect);
        tb.error((HtmlTreeBuilderState)this);
        if (!tb.inTableScope(t.asEndTag().normalName())) return false;
        tb.popStackToClose("select");
        tb.resetInsertionMode();
        return tb.process(t);
    }
}
