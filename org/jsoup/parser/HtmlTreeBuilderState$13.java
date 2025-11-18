/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$25
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

final class HtmlTreeBuilderState.13
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 3: {
                Token.StartTag startTag = t.asStartTag();
                String name = startTag.normalName();
                if (name.equals("tr")) {
                    tb.clearStackToTableBodyContext();
                    tb.insertElementFor(startTag);
                    tb.transition(InRow);
                    break;
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InCellNames)) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.processStartTag("tr");
                    return tb.process((Token)startTag);
                }
                if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableBodyExit)) return this.anythingElse(t, tb);
                return this.exitTableBody(t, tb);
            }
            case 4: {
                Token.EndTag endTag = t.asEndTag();
                String name = endTag.normalName();
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableEndIgnore)) {
                    if (!tb.inTableScope(name)) {
                        tb.error((HtmlTreeBuilderState)this);
                        return false;
                    }
                    tb.clearStackToTableBodyContext();
                    tb.pop();
                    tb.transition(InTable);
                    break;
                }
                if (name.equals("table")) {
                    return this.exitTableBody(t, tb);
                }
                if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableBodyEndIgnore)) return this.anythingElse(t, tb);
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            default: {
                return this.anythingElse(t, tb);
            }
        }
        return true;
    }

    HtmlTreeBuilderState.13() {
        super(string, n, null);
    }

    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        return tb.process(t, InTable);
    }

    private boolean exitTableBody(Token t, HtmlTreeBuilder tb) {
        if (!(tb.inTableScope("tbody") || tb.inTableScope("thead") || tb.inScope("tfoot"))) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        tb.clearStackToTableBodyContext();
        tb.processEndTag(tb.currentElement().normalName());
        return tb.process(t);
    }
}
