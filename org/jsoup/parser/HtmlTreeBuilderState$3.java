/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.3
extends HtmlTreeBuilderState {
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
                return InBody.process(t, tb);
            }
            if (t.isStartTag() && t.asStartTag().normalName().equals("head")) {
                Element head = tb.insertElementFor(t.asStartTag());
                tb.setHeadElement(head);
                tb.transition(InHead);
            } else {
                if (t.isEndTag() && StringUtil.inSorted((String)t.asEndTag().normalName(), (String[])HtmlTreeBuilderState.Constants.BeforeHtmlToHead)) {
                    tb.processStartTag("head");
                    return tb.process(t);
                }
                if (t.isEndTag()) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.processStartTag("head");
                return tb.process(t);
            }
        }
        return true;
    }

    HtmlTreeBuilderState.3() {
        super(string, n, null);
    }
}
