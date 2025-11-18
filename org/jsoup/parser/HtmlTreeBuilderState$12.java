/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$25
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 */
package org.jsoup.parser;

import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.12
extends HtmlTreeBuilderState {
    private boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        if (!tb.currentElementIs("colgroup")) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        tb.pop();
        tb.transition(InTable);
        tb.process(t);
        return true;
    }

    HtmlTreeBuilderState.12() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (HtmlTreeBuilderState.access$100((Token)t)) {
            tb.insertCharacterNode(t.asCharacter());
            return true;
        }
        block0 : switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 1: {
                tb.insertCommentNode(t.asComment());
                break;
            }
            case 2: {
                tb.error((HtmlTreeBuilderState)this);
                break;
            }
            case 3: {
                Token.StartTag startTag = t.asStartTag();
                switch (startTag.normalName()) {
                    case "html": {
                        return tb.process(t, InBody);
                    }
                    case "col": {
                        tb.insertEmptyElementFor(startTag);
                        break block0;
                    }
                    case "template": {
                        tb.process(t, InHead);
                        break block0;
                    }
                }
                return this.anythingElse(t, tb);
            }
            case 4: {
                String name;
                Token.EndTag endTag = t.asEndTag();
                switch (name = endTag.normalName()) {
                    case "colgroup": {
                        if (!tb.currentElementIs(name)) {
                            tb.error((HtmlTreeBuilderState)this);
                            return false;
                        }
                        tb.pop();
                        tb.transition(InTable);
                        break block0;
                    }
                    case "template": {
                        tb.process(t, InHead);
                        break block0;
                    }
                }
                return this.anythingElse(t, tb);
            }
            case 6: {
                if (!tb.currentElementIs("html")) return this.anythingElse(t, tb);
                return true;
            }
            default: {
                return this.anythingElse(t, tb);
            }
        }
        return true;
    }
}
