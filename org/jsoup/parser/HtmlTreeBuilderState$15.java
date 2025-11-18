/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$EndTag
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

final class HtmlTreeBuilderState.15
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isEndTag()) {
            Token.EndTag endTag = t.asEndTag();
            String name = endTag.normalName();
            if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InCellNames)) {
                if (!tb.inTableScope(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.transition(InRow);
                    return false;
                }
                tb.generateImpliedEndTags();
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                tb.clearFormattingElementsToLastMarker();
                tb.transition(InRow);
                return true;
            }
            if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InCellBody)) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InCellTable)) return this.anythingElse(t, tb);
            if (!tb.inTableScope(name)) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            this.closeCell(tb);
            return tb.process(t);
        }
        if (!t.isStartTag()) return this.anythingElse(t, tb);
        if (!StringUtil.inSorted((String)t.asStartTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InCellCol)) return this.anythingElse(t, tb);
        if (!tb.inTableScope("td") && !tb.inTableScope("th")) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        this.closeCell(tb);
        return tb.process(t);
    }

    HtmlTreeBuilderState.15() {
        super(string, n, null);
    }

    private void closeCell(HtmlTreeBuilder tb) {
        if (tb.inTableScope("td")) {
            tb.processEndTag("td");
        } else {
            tb.processEndTag("th");
        }
    }

    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        return tb.process(t, InBody);
    }
}
