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
 *  org.jsoup.parser.Token$StartTag
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

final class HtmlTreeBuilderState.14
extends HtmlTreeBuilderState {
    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        return tb.process(t, InTable);
    }

    /*
     * Enabled force condition propagation
     */
    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isStartTag()) {
            Token.StartTag startTag = t.asStartTag();
            String name = startTag.normalName();
            if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InCellNames)) {
                tb.clearStackToTableRowContext();
                tb.insertElementFor(startTag);
                tb.transition(InCell);
                tb.insertMarkerToFormattingElements();
                return true;
            }
            if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InRowMissing)) return this.anythingElse(t, tb);
            if (!tb.inTableScope("tr")) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.clearStackToTableRowContext();
            tb.pop();
            tb.transition(InTableBody);
            return tb.process(t);
        }
        if (!t.isEndTag()) return this.anythingElse(t, tb);
        Token.EndTag endTag = t.asEndTag();
        String name = endTag.normalName();
        if (name.equals("tr")) {
            if (!tb.inTableScope(name)) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.clearStackToTableRowContext();
            tb.pop();
            tb.transition(InTableBody);
            return true;
        }
        if (name.equals("table")) {
            if (!tb.inTableScope("tr")) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.clearStackToTableRowContext();
            tb.pop();
            tb.transition(InTableBody);
            return tb.process(t);
        }
        if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableToBody)) {
            if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InRowIgnore)) return this.anythingElse(t, tb);
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        if (!tb.inTableScope(name)) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        if (!tb.inTableScope("tr")) {
            return false;
        }
        tb.clearStackToTableRowContext();
        tb.pop();
        tb.transition(InTableBody);
        return tb.process(t);
    }

    HtmlTreeBuilderState.14() {
        super(string, n, null);
    }
}
