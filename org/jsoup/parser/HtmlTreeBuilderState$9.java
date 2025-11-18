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

final class HtmlTreeBuilderState.9
extends HtmlTreeBuilderState {
    boolean anythingElse(Token t, HtmlTreeBuilder tb) {
        tb.error((HtmlTreeBuilderState)this);
        tb.setFosterInserts(true);
        tb.process(t, InBody);
        tb.setFosterInserts(false);
        return true;
    }

    HtmlTreeBuilderState.9() {
        super(string, n, null);
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.isCharacter() && StringUtil.inSorted((String)tb.currentElement().normalName(), (String[])HtmlTreeBuilderState.Constants.InTableFoster)) {
            tb.resetPendingTableCharacters();
            tb.markInsertionMode();
            tb.transition(InTableText);
            return tb.process(t);
        }
        if (t.isComment()) {
            tb.insertCommentNode(t.asComment());
            return true;
        }
        if (t.isDoctype()) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        if (t.isStartTag()) {
            Token.StartTag startTag = t.asStartTag();
            String name = startTag.normalName();
            if (name.equals("caption")) {
                tb.clearStackToTableContext();
                tb.insertMarkerToFormattingElements();
                tb.insertElementFor(startTag);
                tb.transition(InCaption);
            } else if (name.equals("colgroup")) {
                tb.clearStackToTableContext();
                tb.insertElementFor(startTag);
                tb.transition(InColumnGroup);
            } else {
                if (name.equals("col")) {
                    tb.clearStackToTableContext();
                    tb.processStartTag("colgroup");
                    return tb.process(t);
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableToBody)) {
                    tb.clearStackToTableContext();
                    tb.insertElementFor(startTag);
                    tb.transition(InTableBody);
                } else {
                    if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableAddBody)) {
                        tb.clearStackToTableContext();
                        tb.processStartTag("tbody");
                        return tb.process(t);
                    }
                    if (name.equals("table")) {
                        tb.error((HtmlTreeBuilderState)this);
                        if (!tb.inTableScope(name)) {
                            return false;
                        }
                        tb.popStackToClose(name);
                        if (tb.resetInsertionMode()) return tb.process(t);
                        tb.insertElementFor(startTag);
                        return true;
                    }
                    if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableToHead)) {
                        return tb.process(t, InHead);
                    }
                    if (name.equals("input")) {
                        if (!startTag.hasAttributes()) return this.anythingElse(t, tb);
                        if (!startTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                            return this.anythingElse(t, tb);
                        }
                        tb.insertEmptyElementFor(startTag);
                    } else {
                        if (!name.equals("form")) return this.anythingElse(t, tb);
                        tb.error((HtmlTreeBuilderState)this);
                        if (tb.getFormElement() != null) return false;
                        if (tb.onStack("template")) {
                            return false;
                        }
                        tb.insertFormElement(startTag, false, false);
                    }
                }
            }
            return true;
        }
        if (!t.isEndTag()) {
            if (!t.isEOF()) return this.anythingElse(t, tb);
            if (!tb.currentElementIs("html")) return true;
            tb.error((HtmlTreeBuilderState)this);
            return true;
        }
        Token.EndTag endTag = t.asEndTag();
        String name = endTag.normalName();
        if (name.equals("table")) {
            if (!tb.inTableScope(name)) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.popStackToClose("table");
            tb.resetInsertionMode();
        } else {
            if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InTableEndErr)) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            if (!name.equals("template")) return this.anythingElse(t, tb);
            tb.process(t, InHead);
        }
        return true;
    }
}
