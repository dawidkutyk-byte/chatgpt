/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$25
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.TokeniserState
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;
import org.jsoup.parser.TokeniserState;
import org.jsoup.parser.TreeBuilder;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.4
extends HtmlTreeBuilderState {
    private boolean anythingElse(Token t, TreeBuilder tb) {
        tb.processEndTag("head");
        return tb.process(t);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (HtmlTreeBuilderState.access$100((Token)t)) {
            tb.insertCharacterNode(t.asCharacter());
            return true;
        }
        switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 1: {
                tb.insertCommentNode(t.asComment());
                break;
            }
            case 2: {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            case 3: {
                Token.StartTag start = t.asStartTag();
                String name = start.normalName();
                if (name.equals("html")) {
                    return InBody.process(t, tb);
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InHeadEmpty)) {
                    Element el = tb.insertEmptyElementFor(start);
                    if (!name.equals("base")) return true;
                    if (!el.hasAttr("href")) return true;
                    tb.maybeSetBaseUri(el);
                    break;
                }
                if (name.equals("meta")) {
                    tb.insertEmptyElementFor(start);
                    break;
                }
                if (name.equals("title")) {
                    HtmlTreeBuilderState.access$200((Token.StartTag)start, (HtmlTreeBuilder)tb);
                    break;
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InHeadRaw)) {
                    HtmlTreeBuilderState.access$300((Token.StartTag)start, (HtmlTreeBuilder)tb);
                    break;
                }
                if (name.equals("noscript")) {
                    tb.insertElementFor(start);
                    tb.transition(InHeadNoscript);
                    break;
                }
                if (name.equals("script")) {
                    tb.tokeniser.transition(TokeniserState.ScriptData);
                    tb.markInsertionMode();
                    tb.transition(Text);
                    tb.insertElementFor(start);
                    break;
                }
                if (name.equals("head")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (!name.equals("template")) return this.anythingElse(t, (TreeBuilder)tb);
                tb.insertElementFor(start);
                tb.insertMarkerToFormattingElements();
                tb.framesetOk(false);
                tb.transition(InTemplate);
                tb.pushTemplateMode(InTemplate);
                break;
            }
            case 4: {
                Token.EndTag end = t.asEndTag();
                String name = end.normalName();
                if (name.equals("head")) {
                    tb.pop();
                    tb.transition(AfterHead);
                    break;
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InHeadEnd)) {
                    return this.anythingElse(t, (TreeBuilder)tb);
                }
                if (!name.equals("template")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (!tb.onStack(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    break;
                }
                tb.generateImpliedEndTags(true);
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                tb.clearFormattingElementsToLastMarker();
                tb.popTemplateMode();
                tb.resetInsertionMode();
                break;
            }
            default: {
                return this.anythingElse(t, (TreeBuilder)tb);
            }
        }
        return true;
    }

    HtmlTreeBuilderState.4() {
        super(string, n, null);
    }
}
