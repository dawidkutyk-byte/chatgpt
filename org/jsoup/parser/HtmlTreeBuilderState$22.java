/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.Token
 */
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.22
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.22() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
        } else {
            if (t.isDoctype()) return tb.process(t, InBody);
            if (t.isStartTag() && t.asStartTag().normalName().equals("html")) {
                return tb.process(t, InBody);
            }
            if (HtmlTreeBuilderState.access$100((Token)t)) {
                Document doc = tb.getDocument();
                tb.insertCharacterToElement(t.asCharacter(), (Element)doc);
            } else {
                if (t.isEOF()) return true;
                tb.error((HtmlTreeBuilderState)this);
                tb.resetBody();
                return tb.process(t);
            }
        }
        return true;
    }
}
