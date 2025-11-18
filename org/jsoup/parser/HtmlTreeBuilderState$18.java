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
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

final class HtmlTreeBuilderState.18
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 1: 
            case 2: 
            case 5: {
                tb.process(t, InBody);
                break;
            }
            case 3: {
                String name = t.asStartTag().normalName();
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTemplateToHead)) {
                    tb.process(t, InHead);
                    break;
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTemplateToTable)) {
                    tb.popTemplateMode();
                    tb.pushTemplateMode(InTable);
                    tb.transition(InTable);
                    return tb.process(t);
                }
                if (name.equals("col")) {
                    tb.popTemplateMode();
                    tb.pushTemplateMode(InColumnGroup);
                    tb.transition(InColumnGroup);
                    return tb.process(t);
                }
                if (name.equals("tr")) {
                    tb.popTemplateMode();
                    tb.pushTemplateMode(InTableBody);
                    tb.transition(InTableBody);
                    return tb.process(t);
                }
                if (!name.equals("td") && !name.equals("th")) {
                    tb.popTemplateMode();
                    tb.pushTemplateMode(InBody);
                    tb.transition(InBody);
                    return tb.process(t);
                }
                tb.popTemplateMode();
                tb.pushTemplateMode(InRow);
                tb.transition(InRow);
                return tb.process(t);
            }
            case 4: {
                String name = t.asEndTag().normalName();
                if (!name.equals("template")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.process(t, InHead);
                break;
            }
            case 6: {
                if (!tb.onStack("template")) {
                    return true;
                }
                tb.error((HtmlTreeBuilderState)this);
                tb.popStackToClose("template");
                tb.clearFormattingElementsToLastMarker();
                tb.popTemplateMode();
                tb.resetInsertionMode();
                if (tb.state() == InTemplate) return true;
                if (tb.templateModeSize() >= 12) return true;
                return tb.process(t);
            }
        }
        return true;
    }

    HtmlTreeBuilderState.18() {
        super(string, n, null);
    }
}
