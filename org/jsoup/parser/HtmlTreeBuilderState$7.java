/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Document$QuirksMode
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.FormElement
 *  org.jsoup.nodes.Node
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$25
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Tag
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.TokeniserState
 */
package org.jsoup.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tag;
import org.jsoup.parser.Token;
import org.jsoup.parser.TokeniserState;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.7
extends HtmlTreeBuilderState {
    private static final int MaxStackScan = 24;

    /*
     * Enabled force condition propagation
     */
    private boolean inBodyStartTag(Token t, HtmlTreeBuilder tb) {
        String name;
        Token.StartTag startTag = t.asStartTag();
        switch (name = startTag.normalName()) {
            case "a": {
                if (tb.getActiveFormattingElement("a") != null) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.processEndTag("a");
                    Element remainingA = tb.getFromStack("a");
                    if (remainingA != null) {
                        tb.removeFromActiveFormattingElements(remainingA);
                        tb.removeFromStack(remainingA);
                    }
                }
                tb.reconstructFormattingElements();
                Element el = tb.insertElementFor(startTag);
                tb.pushActiveFormattingElements(el);
                return true;
            }
            case "span": {
                tb.reconstructFormattingElements();
                tb.insertElementFor(startTag);
                return true;
            }
            case "li": {
                tb.framesetOk(false);
                ArrayList stack = tb.getStack();
                for (int i = stack.size() - 1; i > 0; --i) {
                    Element el = (Element)stack.get(i);
                    if (el.nameIs("li")) {
                        tb.processEndTag("li");
                        break;
                    }
                    if (HtmlTreeBuilder.isSpecial((Element)el) && !StringUtil.inSorted((String)el.normalName(), (String[])HtmlTreeBuilderState.Constants.InBodyStartLiBreakers)) break;
                }
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.insertElementFor(startTag);
                return true;
            }
            case "html": {
                tb.error((HtmlTreeBuilderState)this);
                if (tb.onStack("template")) {
                    return false;
                }
                ArrayList stack = tb.getStack();
                if (stack.size() <= 0) return true;
                Element html = (Element)tb.getStack().get(0);
                if (!startTag.hasAttributes()) return true;
                Iterator iterator = startTag.attributes.iterator();
                while (iterator.hasNext()) {
                    Attribute attribute = (Attribute)iterator.next();
                    if (html.hasAttr(attribute.getKey())) continue;
                    html.attributes().put(attribute);
                }
                return true;
            }
            case "body": {
                tb.error((HtmlTreeBuilderState)this);
                ArrayList stack = tb.getStack();
                if (stack.size() == 1) return false;
                if (stack.size() > 2) {
                    if (!((Element)stack.get(1)).nameIs("body")) return false;
                }
                if (tb.onStack("template")) {
                    return false;
                }
                tb.framesetOk(false);
                if (!startTag.hasAttributes()) return true;
                Element body = tb.getFromStack("body");
                if (body == null) return true;
                Iterator iterator = startTag.attributes.iterator();
                while (iterator.hasNext()) {
                    Attribute attribute = (Attribute)iterator.next();
                    if (body.hasAttr(attribute.getKey())) continue;
                    body.attributes().put(attribute);
                }
                return true;
            }
            case "frameset": {
                tb.error((HtmlTreeBuilderState)this);
                ArrayList stack = tb.getStack();
                if (stack.size() == 1) return false;
                if (stack.size() > 2 && !((Element)stack.get(1)).nameIs("body")) {
                    return false;
                }
                if (!tb.framesetOk()) {
                    return false;
                }
                Element second = (Element)stack.get(1);
                if (second.parent() != null) {
                    second.remove();
                }
                while (true) {
                    if (stack.size() <= 1) {
                        tb.insertElementFor(startTag);
                        tb.transition(InFrameset);
                        return true;
                    }
                    stack.remove(stack.size() - 1);
                }
            }
            case "form": {
                if (tb.getFormElement() != null && !tb.onStack("template")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (tb.inButtonScope("p")) {
                    tb.closeElement("p");
                }
                tb.insertFormElement(startTag, true, true);
                return true;
            }
            case "plaintext": {
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.insertElementFor(startTag);
                tb.tokeniser.transition(TokeniserState.PLAINTEXT);
                return true;
            }
            case "button": {
                if (tb.inButtonScope("button")) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.processEndTag("button");
                    tb.process((Token)startTag);
                    return true;
                }
                tb.reconstructFormattingElements();
                tb.insertElementFor(startTag);
                tb.framesetOk(false);
                return true;
            }
            case "nobr": {
                tb.reconstructFormattingElements();
                if (tb.inScope("nobr")) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.processEndTag("nobr");
                    tb.reconstructFormattingElements();
                }
                Element el = tb.insertElementFor(startTag);
                tb.pushActiveFormattingElements(el);
                return true;
            }
            case "table": {
                if (tb.getDocument().quirksMode() != Document.QuirksMode.quirks && tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.insertElementFor(startTag);
                tb.framesetOk(false);
                tb.transition(InTable);
                return true;
            }
            case "input": {
                tb.reconstructFormattingElements();
                Element el = tb.insertEmptyElementFor(startTag);
                if (el.attr("type").equalsIgnoreCase("hidden")) return true;
                tb.framesetOk(false);
                return true;
            }
            case "hr": {
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.insertEmptyElementFor(startTag);
                tb.framesetOk(false);
                return true;
            }
            case "image": {
                if (tb.getFromStack("svg") == null) {
                    return tb.process((Token)startTag.name("img"));
                }
                tb.insertElementFor(startTag);
                return true;
            }
            case "isindex": {
                FormElement form;
                tb.error((HtmlTreeBuilderState)this);
                if (tb.getFormElement() != null) {
                    return false;
                }
                tb.processStartTag("form");
                if (startTag.hasAttribute("action") && (form = tb.getFormElement()) != null && startTag.hasAttribute("action")) {
                    String action = startTag.attributes.get("action");
                    form.attributes().put("action", action);
                }
                tb.processStartTag("hr");
                tb.processStartTag("label");
                String prompt = startTag.hasAttribute("prompt") ? startTag.attributes.get("prompt") : "This is a searchable index. Enter search keywords: ";
                tb.process((Token)new Token.Character().data(prompt));
                Attributes inputAttribs = new Attributes();
                if (startTag.hasAttributes()) {
                    for (Attribute attr : startTag.attributes) {
                        if (StringUtil.inSorted((String)attr.getKey(), (String[])HtmlTreeBuilderState.Constants.InBodyStartInputAttribs)) continue;
                        inputAttribs.put(attr);
                    }
                }
                inputAttribs.put("name", "isindex");
                tb.processStartTag("input", inputAttribs);
                tb.processEndTag("label");
                tb.processStartTag("hr");
                tb.processEndTag("form");
                return true;
            }
            case "textarea": {
                tb.insertElementFor(startTag);
                if (startTag.isSelfClosing()) return true;
                tb.tokeniser.transition(TokeniserState.Rcdata);
                tb.markInsertionMode();
                tb.framesetOk(false);
                tb.transition(Text);
                return true;
            }
            case "xmp": {
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.reconstructFormattingElements();
                tb.framesetOk(false);
                HtmlTreeBuilderState.access$300((Token.StartTag)startTag, (HtmlTreeBuilder)tb);
                return true;
            }
            case "iframe": {
                tb.framesetOk(false);
                HtmlTreeBuilderState.access$300((Token.StartTag)startTag, (HtmlTreeBuilder)tb);
                return true;
            }
            case "noembed": {
                HtmlTreeBuilderState.access$300((Token.StartTag)startTag, (HtmlTreeBuilder)tb);
                return true;
            }
            case "select": {
                tb.reconstructFormattingElements();
                tb.insertElementFor(startTag);
                tb.framesetOk(false);
                if (startTag.selfClosing) {
                    return true;
                }
                HtmlTreeBuilderState state = tb.state();
                if (!(state.equals((Object)InTable) || state.equals((Object)InCaption) || state.equals((Object)InTableBody) || state.equals((Object)InRow) || state.equals((Object)InCell))) {
                    tb.transition(InSelect);
                    return true;
                }
                tb.transition(InSelectInTable);
                return true;
            }
            case "math": {
                tb.reconstructFormattingElements();
                tb.insertForeignElementFor(startTag, "http://www.w3.org/1998/Math/MathML");
                return true;
            }
            case "svg": {
                tb.reconstructFormattingElements();
                tb.insertForeignElementFor(startTag, "http://www.w3.org/2000/svg");
                return true;
            }
            case "h1": 
            case "h2": 
            case "h3": 
            case "h4": 
            case "h5": 
            case "h6": {
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                if (StringUtil.inSorted((String)tb.currentElement().normalName(), (String[])HtmlTreeBuilderState.Constants.Headings)) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.pop();
                }
                tb.insertElementFor(startTag);
                return true;
            }
            case "pre": 
            case "listing": {
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.insertElementFor(startTag);
                tb.reader.matchConsume("\n");
                tb.framesetOk(false);
                return true;
            }
            case "dd": 
            case "dt": {
                tb.framesetOk(false);
                ArrayList stack = tb.getStack();
                int bottom = stack.size() - 1;
                int upper = bottom >= 24 ? bottom - 24 : 0;
                for (int i = bottom; i >= upper; --i) {
                    Element el = (Element)stack.get(i);
                    if (StringUtil.inSorted((String)el.normalName(), (String[])HtmlTreeBuilderState.Constants.DdDt)) {
                        tb.processEndTag(el.normalName());
                        break;
                    }
                    if (HtmlTreeBuilder.isSpecial((Element)el) && !StringUtil.inSorted((String)el.normalName(), (String[])HtmlTreeBuilderState.Constants.InBodyStartLiBreakers)) break;
                }
                if (tb.inButtonScope("p")) {
                    tb.processEndTag("p");
                }
                tb.insertElementFor(startTag);
                return true;
            }
            case "optgroup": 
            case "option": {
                if (tb.currentElementIs("option")) {
                    tb.processEndTag("option");
                }
                tb.reconstructFormattingElements();
                tb.insertElementFor(startTag);
                return true;
            }
            case "rb": 
            case "rtc": {
                if (tb.inScope("ruby")) {
                    tb.generateImpliedEndTags();
                    if (!tb.currentElementIs("ruby")) {
                        tb.error((HtmlTreeBuilderState)this);
                    }
                }
                tb.insertElementFor(startTag);
                return true;
            }
            case "rp": 
            case "rt": {
                if (tb.inScope("ruby")) {
                    tb.generateImpliedEndTags("rtc");
                    if (!tb.currentElementIs("rtc") && !tb.currentElementIs("ruby")) {
                        tb.error((HtmlTreeBuilderState)this);
                    }
                }
                tb.insertElementFor(startTag);
                return true;
            }
            case "area": 
            case "br": 
            case "embed": 
            case "img": 
            case "keygen": 
            case "wbr": {
                tb.reconstructFormattingElements();
                tb.insertEmptyElementFor(startTag);
                tb.framesetOk(false);
                return true;
            }
            case "b": 
            case "big": 
            case "code": 
            case "em": 
            case "font": 
            case "i": 
            case "s": 
            case "small": 
            case "strike": 
            case "strong": 
            case "tt": 
            case "u": {
                tb.reconstructFormattingElements();
                Element el = tb.insertElementFor(startTag);
                tb.pushActiveFormattingElements(el);
                return true;
            }
        }
        if (!Tag.isKnownTag((String)name)) {
            tb.insertElementFor(startTag);
            return true;
        }
        if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartPClosers)) {
            if (tb.inButtonScope("p")) {
                tb.processEndTag("p");
            }
            tb.insertElementFor(startTag);
            return true;
        }
        if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartToHead)) {
            return tb.process(t, InHead);
        }
        if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartApplets)) {
            tb.reconstructFormattingElements();
            tb.insertElementFor(startTag);
            tb.insertMarkerToFormattingElements();
            tb.framesetOk(false);
            return true;
        }
        if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartMedia)) {
            tb.insertEmptyElementFor(startTag);
            return true;
        }
        if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartDrop)) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        tb.reconstructFormattingElements();
        tb.insertElementFor(startTag);
        return true;
    }

    private boolean inBodyEndTagAdoption(Token t, HtmlTreeBuilder tb) {
        Token.EndTag endTag = t.asEndTag();
        String name = endTag.normalName();
        ArrayList stack = tb.getStack();
        int i = 0;
        while (i < 8) {
            Element formatEl = tb.getActiveFormattingElement(name);
            if (formatEl == null) {
                return this.anyOtherEndTag(t, tb);
            }
            if (!tb.onStack(formatEl)) {
                tb.error((HtmlTreeBuilderState)this);
                tb.removeFromActiveFormattingElements(formatEl);
                return true;
            }
            if (!tb.inScope(formatEl.normalName())) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            if (tb.currentElement() != formatEl) {
                tb.error((HtmlTreeBuilderState)this);
            }
            Element furthestBlock = null;
            Element commonAncestor = null;
            boolean seenFormattingElement = false;
            int stackSize = stack.size();
            int bookmark = -1;
            for (int si = 1; si < stackSize && si < 64; ++si) {
                Element el = (Element)stack.get(si);
                if (el == formatEl) {
                    commonAncestor = (Element)stack.get(si - 1);
                    seenFormattingElement = true;
                    bookmark = tb.positionOfElement(el);
                    continue;
                }
                if (!seenFormattingElement || !HtmlTreeBuilder.isSpecial((Element)el)) continue;
                furthestBlock = el;
                break;
            }
            if (furthestBlock == null) {
                tb.popStackToClose(formatEl.normalName());
                tb.removeFromActiveFormattingElements(formatEl);
                return true;
            }
            Element node = furthestBlock;
            Element lastNode = furthestBlock;
            for (int j = 0; j < 3; ++j) {
                if (tb.onStack(node)) {
                    node = tb.aboveOnStack(node);
                }
                if (!tb.isInActiveFormattingElements(node)) {
                    tb.removeFromStack(node);
                    continue;
                }
                if (node == formatEl) break;
                Element replacement = new Element(tb.tagFor(node.nodeName(), ParseSettings.preserveCase), tb.getBaseUri());
                tb.replaceActiveFormattingElement(node, replacement);
                tb.replaceOnStack(node, replacement);
                node = replacement;
                if (lastNode == furthestBlock) {
                    bookmark = tb.positionOfElement(node) + 1;
                }
                if (lastNode.parent() != null) {
                    lastNode.remove();
                }
                node.appendChild((Node)lastNode);
                lastNode = node;
            }
            if (commonAncestor != null) {
                if (StringUtil.inSorted((String)commonAncestor.normalName(), (String[])HtmlTreeBuilderState.Constants.InBodyEndTableFosters)) {
                    if (lastNode.parent() != null) {
                        lastNode.remove();
                    }
                    tb.insertInFosterParent((Node)lastNode);
                } else {
                    if (lastNode.parent() != null) {
                        lastNode.remove();
                    }
                    commonAncestor.appendChild((Node)lastNode);
                }
            }
            Element adopter = new Element(formatEl.tag(), tb.getBaseUri());
            adopter.attributes().addAll(formatEl.attributes());
            adopter.appendChildren((Collection)furthestBlock.childNodes());
            furthestBlock.appendChild((Node)adopter);
            tb.removeFromActiveFormattingElements(formatEl);
            tb.pushWithBookmark(adopter, bookmark);
            tb.removeFromStack(formatEl);
            tb.insertOnStackAfter(furthestBlock, adopter);
            ++i;
        }
        return true;
    }

    boolean process(Token t, HtmlTreeBuilder tb) {
        switch (HtmlTreeBuilderState.25.$SwitchMap$org$jsoup$parser$Token$TokenType[t.type.ordinal()]) {
            case 5: {
                Token.Character c = t.asCharacter();
                if (c.getData().equals(HtmlTreeBuilderState.access$400())) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (tb.framesetOk() && HtmlTreeBuilderState.access$100((Token)c)) {
                    tb.reconstructFormattingElements();
                    tb.insertCharacterNode(c);
                    break;
                }
                tb.reconstructFormattingElements();
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
                return false;
            }
            case 3: {
                return this.inBodyStartTag(t, tb);
            }
            case 4: {
                return this.inBodyEndTag(t, tb);
            }
            case 6: {
                if (tb.templateModeSize() > 0) {
                    return tb.process(t, InTemplate);
                }
                if (!tb.onStackNot(HtmlTreeBuilderState.Constants.InBodyEndOtherErrors)) return true;
                tb.error((HtmlTreeBuilderState)this);
                break;
            }
        }
        return true;
    }

    boolean anyOtherEndTag(Token t, HtmlTreeBuilder tb) {
        String name = t.asEndTag().normalName;
        ArrayList stack = tb.getStack();
        Element elFromStack = tb.getFromStack(name);
        if (elFromStack == null) {
            tb.error((HtmlTreeBuilderState)this);
            return false;
        }
        int pos = stack.size() - 1;
        while (pos >= 0) {
            Element node = (Element)stack.get(pos);
            if (node.nameIs(name)) {
                tb.generateImpliedEndTags(name);
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                return true;
            }
            if (HtmlTreeBuilder.isSpecial((Element)node)) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            --pos;
        }
        return true;
    }

    private boolean inBodyEndTag(Token t, HtmlTreeBuilder tb) {
        String name;
        Token.EndTag endTag = t.asEndTag();
        switch (name = endTag.normalName()) {
            case "template": {
                tb.process(t, InHead);
                break;
            }
            case "sarcasm": 
            case "span": {
                return this.anyOtherEndTag(t, tb);
            }
            case "li": {
                if (!tb.inListItemScope(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.generateImpliedEndTags(name);
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                break;
            }
            case "body": {
                if (!tb.inScope("body")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (tb.onStackNot(HtmlTreeBuilderState.Constants.InBodyEndOtherErrors)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.onNodeClosed((Node)tb.getFromStack("body"));
                tb.transition(AfterBody);
                break;
            }
            case "html": {
                if (!tb.onStack("body")) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                if (tb.onStackNot(HtmlTreeBuilderState.Constants.InBodyEndOtherErrors)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.transition(AfterBody);
                return tb.process(t);
            }
            case "form": {
                if (!tb.onStack("template")) {
                    FormElement currentForm = tb.getFormElement();
                    tb.setFormElement(null);
                    if (currentForm == null || !tb.inScope(name)) {
                        tb.error((HtmlTreeBuilderState)this);
                        return false;
                    }
                    tb.generateImpliedEndTags();
                    if (!tb.currentElementIs(name)) {
                        tb.error((HtmlTreeBuilderState)this);
                    }
                    tb.removeFromStack((Element)currentForm);
                    break;
                }
                if (!tb.inScope(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.generateImpliedEndTags();
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                break;
            }
            case "p": {
                if (!tb.inButtonScope(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    tb.processStartTag(name);
                    return tb.process((Token)endTag);
                }
                tb.generateImpliedEndTags(name);
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                break;
            }
            case "dd": 
            case "dt": {
                if (!tb.inScope(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.generateImpliedEndTags(name);
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                break;
            }
            case "h1": 
            case "h2": 
            case "h3": 
            case "h4": 
            case "h5": 
            case "h6": {
                if (!tb.inScope(HtmlTreeBuilderState.Constants.Headings)) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.generateImpliedEndTags(name);
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(HtmlTreeBuilderState.Constants.Headings);
                break;
            }
            case "br": {
                tb.error((HtmlTreeBuilderState)this);
                tb.processStartTag("br");
                return false;
            }
            default: {
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyEndAdoptionFormatters)) {
                    return this.inBodyEndTagAdoption(t, tb);
                }
                if (StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyEndClosers)) {
                    if (!tb.inScope(name)) {
                        tb.error((HtmlTreeBuilderState)this);
                        return false;
                    }
                    tb.generateImpliedEndTags();
                    if (!tb.currentElementIs(name)) {
                        tb.error((HtmlTreeBuilderState)this);
                    }
                    tb.popStackToClose(name);
                    break;
                }
                if (!StringUtil.inSorted((String)name, (String[])HtmlTreeBuilderState.Constants.InBodyStartApplets)) return this.anyOtherEndTag(t, tb);
                if (tb.inScope("name")) return true;
                if (!tb.inScope(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                    return false;
                }
                tb.generateImpliedEndTags();
                if (!tb.currentElementIs(name)) {
                    tb.error((HtmlTreeBuilderState)this);
                }
                tb.popStackToClose(name);
                tb.clearFormattingElementsToLastMarker();
                break;
            }
        }
        return true;
    }

    HtmlTreeBuilderState.7() {
        super(string, n, null);
    }
}
