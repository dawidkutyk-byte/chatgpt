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

final class HtmlTreeBuilderState.11
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isEndTag() && t.asEndTag().normalName().equals("caption")) {
            if (!tb.inTableScope("caption")) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.generateImpliedEndTags();
            if (!tb.currentElementIs("caption")) {
                tb.error((HtmlTreeBuilderState)this);
            }
            tb.popStackToClose("caption");
            tb.clearFormattingElementsToLastMarker();
            tb.transition(InTable);
        } else if (t.isStartTag() && StringUtil.inSorted((String)t.asStartTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InCellCol) || t.isEndTag() && t.asEndTag().normalName().equals("table")) {
            if (!tb.inTableScope("caption")) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.generateImpliedEndTags(false);
            if (!tb.currentElementIs("caption")) {
                tb.error((HtmlTreeBuilderState)this);
            }
            tb.popStackToClose("caption");
            tb.clearFormattingElementsToLastMarker();
            tb.transition(InTable);
            InTable.process(t, tb);
        } else {
            if (!t.isEndTag()) return tb.process(t, InBody);
            if (!StringUtil.inSorted((String)t.asEndTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InCaptionIgnore)) return tb.process(t, InBody);
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        return true;
    }

    HtmlTreeBuilderState.11() {
        super(string, n, null);
    }
}
