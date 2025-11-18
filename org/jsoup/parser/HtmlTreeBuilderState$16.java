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
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.16
extends HtmlTreeBuilderState {
    HtmlTreeBuilderState.16() {
        super(string, n, null);
    }

    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        tb.error((HtmlTreeBuilderState)this);
        return false;
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        block0 : switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 5: {
                Token.Character c = t.asCharacter();
                if (c.getData().equals(HtmlTreeBuilderState.access$400())) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.insertCharacterNode(c);
                break;
            }
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
                    return tb.process((Token)start, InBody);
                }
                if (name.equals("option")) {
                    if (tb.currentElementIs("option")) {
                        tb.processEndTag("option");
                    }
                    tb.insertElementFor(start);
                    break;
                }
                if (name.equals("optgroup")) {
                    if (tb.currentElementIs("option")) {
                        tb.processEndTag("option");
                    }
                    if (tb.currentElementIs("optgroup")) {
                        tb.processEndTag("optgroup");
                    }
                    tb.insertElementFor(start);
                    break;
                }
                if (name.equals("select")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return tb.processEndTag("select");
                }
                if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InSelectEnd)) {
                    if (name.equals("script")) return tb.process(t, InHead);
                    if (!name.equals("template")) return this.anythingElse(t, tb);
                    return tb.process(t, InHead);
                }
                tb.error((HtmlTreeBuilderState)this);
                if (!tb.inSelectScope("select")) {
                    return false;
                }
                tb.processEndTag("select");
                return tb.process((Token)start);
            }
            case 4: {
                String name;
                Token.EndTag end = t.asEndTag();
                switch (name = end.normalName()) {
                    case "optgroup": {
                        if (tb.currentElementIs("option") && tb.aboveOnStack(tb.currentElement()) != null && tb.aboveOnStack(tb.currentElement()).nameIs("optgroup")) {
                            tb.processEndTag("option");
                        }
                        if (tb.currentElementIs("optgroup")) {
                            tb.pop();
                            break block0;
                        }
                        tb.error((HtmlTreeBuilderState)this);
                        break block0;
                    }
                    case "option": {
                        if (tb.currentElementIs("option")) {
                            tb.pop();
                            break block0;
                        }
                        tb.error((HtmlTreeBuilderState)this);
                        break block0;
                    }
                    case "select": {
                        if (!tb.inSelectScope(name)) {
                            tb.error((HtmlTreeBuilderState)this);
                            return false;
                        }
                        tb.popStackToClose(name);
                        tb.resetInsertionMode();
                        break block0;
                    }
                    case "template": {
                        return tb.process(t, InHead);
                    }
                }
                return this.anythingElse(t, tb);
            }
            case 6: {
                if (tb.currentElementIs("html")) return true;
                tb.error((HtmlTreeBuilderState)this);
                break;
            }
            default: {
                return this.anythingElse(t, tb);
            }
        }
        return true;
    }
}
