/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.Token
 */
package org.jsoup.parser;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.19
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        Element html = tb.getFromStack("html");
        if (HtmlTreeBuilderState.access$100((Token)t)) {
            if (html != null) {
                tb.insertCharacterToElement(t.asCharacter(), html);
            } else {
                tb.process(t, InBody);
            }
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
                if (tb.isFragmentParsing()) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (html != null) {
                    tb.onNodeClosed((Node)html);
                }
                tb.transition(AfterAfterBody);
            } else {
                if (t.isEOF()) return true;
                tb.error((HtmlTreeBuilderState)this);
                tb.resetBody();
                return tb.process(t);
            }
        }
        return true;
    }

    HtmlTreeBuilderState.19() {
        super(string, n, null);
    }
}
