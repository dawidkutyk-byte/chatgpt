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
 *  org.jsoup.parser.Token$StartTag
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
final class HtmlTreeBuilderState.6
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.6() {
        super(string, n, null);
    }

    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        tb.processStartTag("body");
        tb.framesetOk(true);
        return tb.process(t);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (HtmlTreeBuilderState.access$100((Token)t)) {
            tb.insertCharacterNode(t.asCharacter());
        } else if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
        } else if (t.isDoctype()) {
            tb.error((HtmlTreeBuilderState)this);
        } else if (t.isStartTag()) {
            Token.StartTag startTag = t.asStartTag();
            String name = startTag.normalName();
            if (name.equals("html")) {
                return tb.process(t, InBody);
            }
            if (name.equals("body")) {
                tb.insertElementFor(startTag);
                tb.framesetOk(false);
                tb.transition(InBody);
            } else if (name.equals("frameset")) {
                tb.insertElementFor(startTag);
                tb.transition(InFrameset);
            } else if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartToHead)) {
                tb.error((HtmlTreeBuilderState)this);
                Element head = tb.getHeadElement();
                tb.push(head);
                tb.process(t, InHead);
                tb.removeFromStack(head);
            } else {
                if (name.equals("head")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                this.anythingElse(t, tb);
            }
        } else if (t.isEndTag()) {
            String name = t.asEndTag().normalName();
            if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.AfterHeadBody)) {
                this.anythingElse(t, tb);
            } else {
                if (!name.equals("template")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.process(t, InHead);
            }
        } else {
            this.anythingElse(t, tb);
        }
        return true;
    }
}
