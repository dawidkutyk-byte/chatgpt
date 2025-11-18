/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.5
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isDoctype()) {
            tb.error((HtmlTreeBuilderState)this);
        } else {
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (t.isEndTag() && t.asEndTag().normalName().equals("noscript")) {
                tb.pop();
                tb.transition(InHead);
            } else {
                if (HtmlTreeBuilderState.access$100((Token)t)) return tb.process(t, InHead);
                if (t.isComment()) return tb.process(t, InHead);
                if (t.isStartTag() && StringUtil.inSorted((String)t.asStartTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InHeadNoScriptHead)) {
                    return tb.process(t, InHead);
                }
                if (t.isEndTag() && t.asEndTag().normalName().equals("br")) {
                    return this.anythingElse(t, tb);
                }
                if (!t.isStartTag() || !StringUtil.inSorted((String)t.asStartTag().normalName(), (String[])HtmlTreeBuilderState.Constants.InHeadNoscriptIgnore)) {
                    if (!t.isEndTag()) return this.anythingElse(t, tb);
                }
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
        }
        return true;
    }

    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        tb.error((HtmlTreeBuilderState)this);
        tb.insertCharacterNode(new Token.Character().data(t.toString()));
        return true;
    }

    HtmlTreeBuilderState.5() {
        super(string, n, null);
    }
}
