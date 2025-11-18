/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Element
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$25
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 */
package org.jsoup.parser;

import java.util.ArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.24
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 5: {
                Token.Character c = t.asCharacter();
                if (c.getData().equals(HtmlTreeBuilderState.access$400())) {
                    tb.error((HtmlTreeBuilderState)this);
                    break;
                }
                if (HtmlTreeBuilderState.access$100((Token)c)) {
                    tb.insertCharacterNode(c);
                    break;
                }
                tb.insertCharacterNode(c);
                tb.framesetOk(false);
                break;
            }
            case 1: {
                tb.insertCommentNode(t.asComment());
                break;
            }
            case 2: {
                tb.error((HtmlTreeBuilderState)this);
                break;
            }
            case 3: {
                Token.StartTag start = t.asStartTag();
                if (StringUtil.in((String)start.normalName, (String[])HtmlTreeBuilderState.Constants.InForeignToHtml)) {
                    return this.processAsHtml(t, tb);
                }
                if (start.normalName.equals("font")) {
                    if (start.hasAttributeIgnoreCase("color")) return this.processAsHtml(t, tb);
                    if (start.hasAttributeIgnoreCase("face")) return this.processAsHtml(t, tb);
                    if (start.hasAttributeIgnoreCase("size")) {
                        return this.processAsHtml(t, tb);
                    }
                }
                tb.insertForeignElementFor(start, tb.currentElement().tag().namespace());
                break;
            }
            case 4: {
                int i;
                Element el;
                Token.EndTag end = t.asEndTag();
                if (end.normalName.equals("br")) return this.processAsHtml(t, tb);
                if (end.normalName.equals("p")) {
                    return this.processAsHtml(t, tb);
                }
                if (end.normalName.equals("script") && tb.currentElementIs("script", "http://www.w3.org/2000/svg")) {
                    tb.pop();
                    return true;
                }
                ArrayList stack = tb.getStack();
                if (stack.isEmpty()) {
                    Validate.wtf((String)"Stack unexpectedly empty");
                }
                if (!(el = (Element)stack.get(i = stack.size() - 1)).nameIs(end.normalName)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                do {
                    if (i == 0) return true;
                    if (!el.nameIs(end.normalName)) continue;
                    tb.popStackToCloseAnyNamespace(el.normalName());
                    return true;
                } while (!(el = (Element)stack.get(--i)).tag().namespace().equals("http://www.w3.org/1999/xhtml"));
                return this.processAsHtml(t, tb);
            }
        }
        return true;
    }

    boolean processAsHtml(Token t, HtmlTreeBuilder tb) {
        return tb.state().process(t, tb);
    }

    HtmlTreeBuilderState.24() {
        super(string, n, null);
    }
}
