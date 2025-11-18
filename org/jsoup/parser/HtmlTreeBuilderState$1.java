/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document$QuirksMode
 *  org.jsoup.nodes.DocumentType
 *  org.jsoup.nodes.Node
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Doctype
 */
package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.1
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        if (HtmlTreeBuilderState.access$100((Token)t)) {
            return true;
        }
        if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
        } else {
            if (!t.isDoctype()) {
                tb.transition(BeforeHtml);
                return tb.process(t);
            }
            Token.Doctype d = t.asDoctype();
            DocumentType doctype = new DocumentType(tb.settings.normalizeTag(d.getName()), d.getPublicIdentifier(), d.getSystemIdentifier());
            doctype.setPubSysKey(d.getPubSysKey());
            tb.getDocument().appendChild((Node)doctype);
            tb.onNodeInserted((Node)doctype);
            if (d.isForceQuirks()) {
                tb.getDocument().quirksMode(Document.QuirksMode.quirks);
            }
            tb.transition(BeforeHtml);
        }
        return true;
    }

    HtmlTreeBuilderState.1() {
        super(string, n, null);
    }
}
