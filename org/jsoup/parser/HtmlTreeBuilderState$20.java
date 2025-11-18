/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$StartTag
 */
package org.jsoup.parser;

import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.20
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.20() {
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
            if (t.isStartTag()) {
                Token.StartTag start = t.asStartTag();
                switch (start.normalName()) {
                    case "html": {
                        return tb.process((Token)start, InBody);
                    }
                    case "frameset": {
                        tb.insertElementFor(start);
                        break;
                    }
                    case "frame": {
                        tb.insertEmptyElementFor(start);
                        break;
                    }
                    case "noframes": {
                        return tb.process((Token)start, InHead);
                    }
                    default: {
                        tb.error((HtmlTreeBuilderState)this);
                        return false;
                    }
                }
            } else if (t.isEndTag() && t.asEndTag().normalName().equals("frameset")) {
                if (tb.currentElementIs("html")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.pop();
                if (tb.isFragmentParsing()) return true;
                if (tb.currentElementIs("frameset")) return true;
                tb.transition(AfterFrameset);
            } else {
                if (t.isEOF()) {
                    if (tb.currentElementIs("html")) return true;
                    tb.error((HtmlTreeBuilderState)this);
                    return true;
                }
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
        }
        return true;
    }
}
