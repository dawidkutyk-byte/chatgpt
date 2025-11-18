/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.CDataNode
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.DataNode
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.FormElement
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.ParseError
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Parser
 *  org.jsoup.parser.Tag
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$Comment
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.TokeniserState
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.ParseError;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.parser.Token;
import org.jsoup.parser.TokeniserState;
import org.jsoup.parser.TreeBuilder;

public class HtmlTreeBuilder
extends TreeBuilder {
    static final String[] TagSearchList;
    public static final int MaxScopeSearchDepth = 100;
    private HtmlTreeBuilderState originalState;
    private ArrayList<HtmlTreeBuilderState> tmplInsertMode;
    static final String[] TagMathMlTextIntegration;
    static final String[] TagSearchEndTags;
    private @7\u015aCz FormElement formElement;
    private boolean fragmentParsing;
    static final String[] TagSearchButton;
    private @7\u015aCz Element contextElement;
    static final String[] TagSearchTableScope;
    static final String[] TagSearchSpecial;
    static final String[] TagsSearchInScope;
    private boolean framesetOk;
    private static final int maxUsedFormattingElements = 12;
    static final String[] TagSvgHtmlIntegration;
    private static final int maxQueueDepth = 256;
    static final String[] TagSearchSelectScope;
    private @7\u015aCz Element headElement;
    private HtmlTreeBuilderState state;
    private final String[] specificScopeTarget = new String[]{null};
    private List<Token.Character> pendingTableCharacters;
    private boolean baseUriSetFromDoc;
    private ArrayList<Element> formattingElements;
    private boolean fosterInserts;
    private Token.EndTag emptyEnd;
    static final String[] TagThoroughSearchEndTags;

    void insertInFosterParent(Node in) {
        Element fosterParent;
        Element lastTable = this.getFromStack("table");
        boolean isLastTableParent = false;
        if (lastTable != null) {
            if (lastTable.parent() != null) {
                fosterParent = lastTable.parent();
                isLastTableParent = true;
            } else {
                fosterParent = this.aboveOnStack(lastTable);
            }
        } else {
            fosterParent = (Element)this.stack.get(0);
        }
        if (isLastTableParent) {
            Validate.notNull((Object)lastTable);
            lastTable.before(in);
        } else {
            fosterParent.appendChild(in);
        }
    }

    boolean inButtonScope(String targetName) {
        return this.inScope(targetName, TagSearchButton);
    }

    void insertOnStackAfter(Element after, Element in) {
        int i = this.stack.lastIndexOf(after);
        Validate.isTrue((i != -1 ? 1 : 0) != 0);
        this.stack.add(i + 1, in);
    }

    Element removeLastFormattingElement() {
        int size = this.formattingElements.size();
        if (size <= 0) return null;
        return this.formattingElements.remove(size - 1);
    }

    Element insertElementFor(Token.StartTag startTag) {
        Element el = this.createElementFor(startTag, "http://www.w3.org/1999/xhtml", false);
        this.doInsertElement(el, (Token)startTag);
        if (!startTag.isSelfClosing()) return el;
        Tag tag = el.tag();
        if (tag.isKnownTag()) {
            if (!tag.isEmpty()) {
                this.tokeniser.error("Tag [%s] cannot be self closing; not a void tag", new Object[]{tag.normalName()});
            }
        } else {
            tag.setSelfClosing();
        }
        this.tokeniser.transition(TokeniserState.Data);
        this.tokeniser.emit((Token)this.emptyEnd.reset().name(el.tagName()));
        return el;
    }

    ParseSettings defaultSettings() {
        return ParseSettings.htmlDefault;
    }

    void addPendingTableCharacters(Token.Character c) {
        Token.Character clone = c.clone();
        this.pendingTableCharacters.add(clone);
    }

    boolean framesetOk() {
        return this.framesetOk;
    }

    boolean inTableScope(String targetName) {
        return this.inSpecificScope(targetName, TagSearchTableScope, null);
    }

    private void clearStackToContext(String ... nodeNames) {
        int pos = this.stack.size() - 1;
        while (pos >= 0) {
            Element next = (Element)this.stack.get(pos);
            if ("http://www.w3.org/1999/xhtml".equals(next.tag().namespace())) {
                if (StringUtil.in((String)next.normalName(), (String[])nodeNames)) return;
                if (next.nameIs("html")) {
                    return;
                }
            }
            this.pop();
            --pos;
        }
    }

    private static void replaceInQueue(ArrayList<Element> queue, Element out, Element in) {
        int i = queue.lastIndexOf(out);
        Validate.isTrue((i != -1 ? 1 : 0) != 0);
        queue.set(i, in);
    }

    static {
        TagsSearchInScope = new String[]{"applet", "caption", "html", "marquee", "object", "table", "td", "th"};
        TagSearchList = new String[]{"ol", "ul"};
        TagSearchButton = new String[]{"button"};
        TagSearchTableScope = new String[]{"html", "table"};
        TagSearchSelectScope = new String[]{"optgroup", "option"};
        TagSearchEndTags = new String[]{"dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc"};
        TagThoroughSearchEndTags = new String[]{"caption", "colgroup", "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
        TagSearchSpecial = new String[]{"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", "br", "button", "caption", "center", "col", "colgroup", "command", "dd", "details", "dir", "div", "dl", "dt", "embed", "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", "object", "ol", "p", "param", "plaintext", "pre", "script", "section", "select", "style", "summary", "table", "tbody", "td", "textarea", "tfoot", "th", "thead", "title", "tr", "ul", "wbr", "xmp"};
        TagMathMlTextIntegration = new String[]{"mi", "mn", "mo", "ms", "mtext"};
        TagSvgHtmlIntegration = new String[]{"desc", "foreignObject", "title"};
    }

    @7\u015aCz HtmlTreeBuilderState popTemplateMode() {
        if (this.tmplInsertMode.size() <= 0) return null;
        return this.tmplInsertMode.remove(this.tmplInsertMode.size() - 1);
    }

    void generateImpliedEndTags() {
        this.generateImpliedEndTags(false);
    }

    void clearStackToTableBodyContext() {
        this.clearStackToContext("tbody", "tfoot", "thead", "template");
    }

    void insertMarkerToFormattingElements() {
        this.formattingElements.add(null);
    }

    boolean isFosterInserts() {
        return this.fosterInserts;
    }

    Element getHeadElement() {
        return this.headElement;
    }

    List<Token.Character> getPendingTableCharacters() {
        return this.pendingTableCharacters;
    }

    boolean resetInsertionMode() {
        boolean last = false;
        int bottom = this.stack.size() - 1;
        int upper = bottom >= 256 ? bottom - 256 : 0;
        HtmlTreeBuilderState origState = this.state;
        if (this.stack.size() == 0) {
            this.transition(HtmlTreeBuilderState.InBody);
        }
        block31: for (int pos = bottom; pos >= upper; --pos) {
            String name;
            Element node = (Element)this.stack.get(pos);
            if (pos == upper) {
                last = true;
                if (this.fragmentParsing) {
                    node = this.contextElement;
                }
            }
            String string = name = node != null ? node.normalName() : "";
            if (!"http://www.w3.org/1999/xhtml".equals(node.tag().namespace())) continue;
            switch (name) {
                case "select": {
                    this.transition(HtmlTreeBuilderState.InSelect);
                    break block31;
                }
                case "td": 
                case "th": {
                    if (last) break;
                    this.transition(HtmlTreeBuilderState.InCell);
                    break block31;
                }
                case "tr": {
                    this.transition(HtmlTreeBuilderState.InRow);
                    break block31;
                }
                case "tbody": 
                case "thead": 
                case "tfoot": {
                    this.transition(HtmlTreeBuilderState.InTableBody);
                    break block31;
                }
                case "caption": {
                    this.transition(HtmlTreeBuilderState.InCaption);
                    break block31;
                }
                case "colgroup": {
                    this.transition(HtmlTreeBuilderState.InColumnGroup);
                    break block31;
                }
                case "table": {
                    this.transition(HtmlTreeBuilderState.InTable);
                    break block31;
                }
                case "template": {
                    HtmlTreeBuilderState tmplState = this.currentTemplateMode();
                    Validate.notNull((Object)tmplState, (String)"Bug: no template insertion mode on stack!");
                    this.transition(tmplState);
                    break block31;
                }
                case "head": {
                    if (last) break;
                    this.transition(HtmlTreeBuilderState.InHead);
                    break block31;
                }
                case "body": {
                    this.transition(HtmlTreeBuilderState.InBody);
                    break block31;
                }
                case "frameset": {
                    this.transition(HtmlTreeBuilderState.InFrameset);
                    break block31;
                }
                case "html": {
                    this.transition(this.headElement == null ? HtmlTreeBuilderState.BeforeHead : HtmlTreeBuilderState.AfterHead);
                    break block31;
                }
            }
            if (!last) continue;
            this.transition(HtmlTreeBuilderState.InBody);
            break;
        }
        return this.state != origState;
    }

    boolean isFragmentParsing() {
        return this.fragmentParsing;
    }

    void popStackToClose(String ... elNames) {
        int pos = this.stack.size() - 1;
        while (pos >= 0) {
            Element el = this.pop();
            if (StringUtil.inSorted((String)el.normalName(), (String[])elNames) && "http://www.w3.org/1999/xhtml".equals(el.tag().namespace())) {
                return;
            }
            --pos;
        }
    }

    void clearStackToTableRowContext() {
        this.clearStackToContext("tr", "template");
    }

    @7\u015aCz HtmlTreeBuilderState currentTemplateMode() {
        return this.tmplInsertMode.size() > 0 ? this.tmplInsertMode.get(this.tmplInsertMode.size() - 1) : null;
    }

    void error(HtmlTreeBuilderState state) {
        if (!this.parser.getErrors().canAddError()) return;
        this.parser.getErrors().add((Object)new ParseError(this.reader, "Unexpected %s token [%s] when in state [%s]", new Object[]{this.currentToken.tokenType(), this.currentToken, state}));
    }

    HtmlTreeBuilderState originalState() {
        return this.originalState;
    }

    @7\u015aCz Element aboveOnStack(Element el) {
        assert (this.onStack(el));
        int pos = this.stack.size() - 1;
        while (pos >= 0) {
            Element next = (Element)this.stack.get(pos);
            if (next == el) {
                return (Element)this.stack.get(pos - 1);
            }
            --pos;
        }
        return null;
    }

    void pushWithBookmark(Element in, int bookmark) {
        this.checkActiveFormattingElements(in);
        try {
            this.formattingElements.add(bookmark, in);
        }
        catch (IndexOutOfBoundsException e) {
            this.formattingElements.add(in);
        }
    }

    void replaceActiveFormattingElement(Element out, Element in) {
        HtmlTreeBuilder.replaceInQueue(this.formattingElements, out, in);
    }

    Document getDocument() {
        return this.doc;
    }

    boolean onStackNot(String[] allowedTags) {
        int bottom = this.stack.size() - 1;
        int top = bottom > 100 ? bottom - 100 : 0;
        int pos = bottom;
        while (pos >= top) {
            String elName = ((Element)this.stack.get(pos)).normalName();
            if (!StringUtil.inSorted((String)elName, (String[])allowedTags)) {
                return true;
            }
            --pos;
        }
        return false;
    }

    int templateModeSize() {
        return this.tmplInsertMode.size();
    }

    void reconstructFormattingElements() {
        if (this.stack.size() > 256) {
            return;
        }
        Element last = this.lastFormattingElement();
        if (last == null) return;
        if (this.onStack(last)) {
            return;
        }
        Element entry = last;
        int size = this.formattingElements.size();
        int ceil = size - 12;
        if (ceil < 0) {
            ceil = 0;
        }
        int pos = size - 1;
        boolean skip = false;
        do {
            if (pos != ceil) continue;
            skip = true;
            break;
        } while ((entry = this.formattingElements.get(--pos)) != null && !this.onStack(entry));
        do {
            if (!skip) {
                entry = this.formattingElements.get(++pos);
            }
            Validate.notNull((Object)entry);
            skip = false;
            Element newEl = new Element(this.tagFor(entry.normalName(), this.settings), null, entry.attributes().clone());
            this.doInsertElement(newEl, null);
            this.formattingElements.set(pos, newEl);
        } while (pos != size - 1);
    }

    @7\u015aCz Element popStackToClose(String elName) {
        int pos = this.stack.size() - 1;
        while (pos >= 0) {
            Element el = this.pop();
            if (el.elementIs(elName, "http://www.w3.org/1999/xhtml")) {
                return el;
            }
            --pos;
        }
        return null;
    }

    boolean useCurrentOrForeignInsert(Token token) {
        if (this.stack.isEmpty()) {
            return true;
        }
        Element el = this.currentElement();
        String ns = el.tag().namespace();
        if ("http://www.w3.org/1999/xhtml".equals(ns)) {
            return true;
        }
        if (HtmlTreeBuilder.isMathmlTextIntegration(el)) {
            if (token.isStartTag() && !"mglyph".equals(token.asStartTag().normalName) && !"malignmark".equals(token.asStartTag().normalName)) {
                return true;
            }
            if (token.isCharacter()) {
                return true;
            }
        }
        if ("http://www.w3.org/1998/Math/MathML".equals(ns) && el.nameIs("annotation-xml") && token.isStartTag() && "svg".equals(token.asStartTag().normalName)) {
            return true;
        }
        if (!HtmlTreeBuilder.isHtmlIntegration(el)) return token.isEOF();
        if (token.isStartTag()) return true;
        if (!token.isCharacter()) return token.isEOF();
        return true;
    }

    void insertCommentNode(Token.Comment token) {
        Comment node = new Comment(token.getData());
        this.currentElement().appendChild((Node)node);
        this.onNodeInserted((Node)node);
    }

    Element lastFormattingElement() {
        return this.formattingElements.size() > 0 ? this.formattingElements.get(this.formattingElements.size() - 1) : null;
    }

    void setHeadElement(Element headElement) {
        this.headElement = headElement;
    }

    protected boolean process(Token token) {
        HtmlTreeBuilderState dispatch = this.useCurrentOrForeignInsert(token) ? this.state : HtmlTreeBuilderState.ForeignContent;
        return dispatch.process(token, this);
    }

    private boolean inSpecificScope(String[] targetNames, String[] baseTypes, @7\u015aCz String[] extraTypes) {
        int bottom = this.stack.size() - 1;
        int top = bottom > 100 ? bottom - 100 : 0;
        int pos = bottom;
        while (pos >= top) {
            Element el = (Element)this.stack.get(pos);
            if (el.tag().namespace().equals("http://www.w3.org/1999/xhtml")) {
                String elName = el.normalName();
                if (StringUtil.inSorted((String)elName, (String[])targetNames)) {
                    return true;
                }
                if (StringUtil.inSorted((String)elName, (String[])baseTypes)) {
                    return false;
                }
                if (extraTypes != null && StringUtil.inSorted((String)elName, (String[])extraTypes)) {
                    return false;
                }
            }
            --pos;
        }
        return false;
    }

    boolean onStack(String elName) {
        return this.getFromStack(elName) != null;
    }

    @7\u015aCz FormElement getFormElement() {
        return this.formElement;
    }

    void closeElement(String name) {
        this.generateImpliedEndTags(name);
        if (!name.equals(this.currentElement().normalName())) {
            this.error(this.state());
        }
        this.popStackToClose(name);
    }

    protected boolean isContentForTagData(String normalName) {
        return normalName.equals("script") || normalName.equals("style");
    }

    void removeFromActiveFormattingElements(Element el) {
        int pos = this.formattingElements.size() - 1;
        while (pos >= 0) {
            Element next = this.formattingElements.get(pos);
            if (next == el) {
                this.formattingElements.remove(pos);
                return;
            }
            --pos;
        }
    }

    @7\u015aCz Element getActiveFormattingElement(String nodeName) {
        int pos = this.formattingElements.size() - 1;
        while (pos >= 0) {
            Element next = this.formattingElements.get(pos);
            if (next == null) {
                return null;
            }
            if (next.nameIs(nodeName)) {
                return next;
            }
            --pos;
        }
        return null;
    }

    Element createElementFor(Token.StartTag startTag, String namespace, boolean forcePreserveCase) {
        int dupes;
        Attributes attributes = startTag.attributes;
        if (!forcePreserveCase) {
            attributes = this.settings.normalizeAttributes(attributes);
        }
        if (attributes != null && !attributes.isEmpty() && (dupes = attributes.deduplicate(this.settings)) > 0) {
            this.error("Dropped duplicate attribute(s) in tag [%s]", new Object[]{startTag.normalName});
        }
        Tag tag = this.tagFor(startTag.tagName, namespace, forcePreserveCase ? ParseSettings.preserveCase : this.settings);
        return tag.normalName().equals("form") ? new FormElement(tag, null, attributes) : new Element(tag, null, attributes);
    }

    void pushActiveFormattingElements(Element in) {
        this.checkActiveFormattingElements(in);
        this.formattingElements.add(in);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.currentToken + ", state=" + this.state + ", currentElement=" + this.currentElement() + '}';
    }

    void insertCharacterNode(Token.Character characterToken) {
        Element el = this.currentElement();
        this.insertCharacterToElement(characterToken, el);
    }

    boolean inScope(String targetName, String[] extras) {
        return this.inSpecificScope(targetName, TagsSearchInScope, extras);
    }

    HtmlTreeBuilder newInstance() {
        return new HtmlTreeBuilder();
    }

    static boolean isHtmlIntegration(Element el) {
        if ("http://www.w3.org/1998/Math/MathML".equals(el.tag().namespace()) && el.nameIs("annotation-xml")) {
            String encoding = Normalizer.normalize((String)el.attr("encoding"));
            if (encoding.equals("text/html")) return true;
            if (encoding.equals("application/xhtml+xml")) {
                return true;
            }
        }
        if (!"http://www.w3.org/2000/svg".equals(el.tag().namespace())) return false;
        if (!StringUtil.in((String)el.tagName(), (String[])TagSvgHtmlIntegration)) return false;
        return true;
    }

    void insertCharacterToElement(Token.Character characterToken, Element el) {
        String tagName = el.normalName();
        String data = characterToken.getData();
        Object node = characterToken.isCData() ? new CDataNode(data) : (this.isContentForTagData(tagName) ? new DataNode(data) : new TextNode(data));
        el.appendChild((Node)node);
        this.onNodeInserted((Node)node);
    }

    void transition(HtmlTreeBuilderState state) {
        this.state = state;
    }

    void generateImpliedEndTags(String excludeTag) {
        while (StringUtil.inSorted((String)this.currentElement().normalName(), (String[])TagSearchEndTags)) {
            if (excludeTag != null && this.currentElementIs(excludeTag)) {
                return;
            }
            this.pop();
        }
    }

    Element insertForeignElementFor(Token.StartTag startTag, String namespace) {
        Element el = this.createElementFor(startTag, namespace, true);
        this.doInsertElement(el, (Token)startTag);
        if (!startTag.isSelfClosing()) return el;
        el.tag().setSelfClosing();
        this.pop();
        return el;
    }

    @7\u015aCz Element getFromStack(String elName) {
        int bottom = this.stack.size() - 1;
        int upper = bottom >= 256 ? bottom - 256 : 0;
        int pos = bottom;
        while (pos >= upper) {
            Element next = (Element)this.stack.get(pos);
            if (next.elementIs(elName, "http://www.w3.org/1999/xhtml")) {
                return next;
            }
            --pos;
        }
        return null;
    }

    boolean process(Token token, HtmlTreeBuilderState state) {
        return state.process(token, this);
    }

    void maybeSetBaseUri(Element base) {
        if (this.baseUriSetFromDoc) {
            return;
        }
        String href = base.absUrl("href");
        if (href.length() == 0) return;
        this.baseUri = href;
        this.baseUriSetFromDoc = true;
        this.doc.setBaseUri(href);
    }

    @7\u015aCz Element popStackToCloseAnyNamespace(String elName) {
        int pos = this.stack.size() - 1;
        while (pos >= 0) {
            Element el = this.pop();
            if (el.nameIs(elName)) {
                return el;
            }
            --pos;
        }
        return null;
    }

    private static boolean isSameFormattingElement(Element a, Element b) {
        return a.normalName().equals(b.normalName()) && a.attributes().equals((Object)b.attributes());
    }

    boolean inListItemScope(String targetName) {
        return this.inScope(targetName, TagSearchList);
    }

    boolean removeFromStack(Element el) {
        int pos = this.stack.size() - 1;
        while (pos >= 0) {
            Element next = (Element)this.stack.get(pos);
            if (next == el) {
                this.stack.remove(pos);
                this.onNodeClosed((Node)el);
                return true;
            }
            --pos;
        }
        return false;
    }

    boolean inScope(String targetName) {
        return this.inScope(targetName, null);
    }

    void resetBody() {
        if (!this.onStack("body")) {
            this.stack.add(this.doc.body());
        }
        this.transition(HtmlTreeBuilderState.InBody);
    }

    void resetPendingTableCharacters() {
        this.pendingTableCharacters.clear();
    }

    private boolean inSpecificScope(String targetName, String[] baseTypes, String[] extraTypes) {
        this.specificScopeTarget[0] = targetName;
        return this.inSpecificScope(this.specificScopeTarget, baseTypes, extraTypes);
    }

    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        super.initialiseParse(input, baseUri, parser);
        this.state = HtmlTreeBuilderState.Initial;
        this.originalState = null;
        this.baseUriSetFromDoc = false;
        this.headElement = null;
        this.formElement = null;
        this.contextElement = null;
        this.formattingElements = new ArrayList();
        this.tmplInsertMode = new ArrayList();
        this.pendingTableCharacters = new ArrayList<Token.Character>();
        this.emptyEnd = new Token.EndTag((TreeBuilder)this);
        this.framesetOk = true;
        this.fosterInserts = false;
        this.fragmentParsing = false;
    }

    void replaceOnStack(Element out, Element in) {
        HtmlTreeBuilder.replaceInQueue(this.stack, out, in);
    }

    boolean isInActiveFormattingElements(Element el) {
        return HtmlTreeBuilder.onStack(this.formattingElements, el);
    }

    String getBaseUri() {
        return this.baseUri;
    }

    int positionOfElement(Element el) {
        int i = 0;
        while (i < this.formattingElements.size()) {
            if (el == this.formattingElements.get(i)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    HtmlTreeBuilderState state() {
        return this.state;
    }

    ArrayList<Element> getStack() {
        return this.stack;
    }

    void clearStackToTableContext() {
        this.clearStackToContext("table", "template");
    }

    boolean inSelectScope(String targetName) {
        int pos = this.stack.size() - 1;
        while (true) {
            if (pos < 0) {
                Validate.fail((String)"Should not be reachable");
                return false;
            }
            Element el = (Element)this.stack.get(pos);
            String elName = el.normalName();
            if (elName.equals(targetName)) {
                return true;
            }
            if (!StringUtil.inSorted((String)elName, (String[])TagSearchSelectScope)) {
                return false;
            }
            --pos;
        }
    }

    private static boolean onStack(ArrayList<Element> queue, Element element) {
        int bottom = queue.size() - 1;
        int upper = bottom >= 256 ? bottom - 256 : 0;
        int pos = bottom;
        while (pos >= upper) {
            Element next = queue.get(pos);
            if (next == element) {
                return true;
            }
            --pos;
        }
        return false;
    }

    void markInsertionMode() {
        this.originalState = this.state;
    }

    void pushTemplateMode(HtmlTreeBuilderState state) {
        this.tmplInsertMode.add(state);
    }

    boolean onStack(Element el) {
        return HtmlTreeBuilder.onStack(this.stack, el);
    }

    void clearFormattingElementsToLastMarker() {
        Element el;
        do {
            if (this.formattingElements.isEmpty()) return;
        } while ((el = this.removeLastFormattingElement()) != null);
    }

    Element insertEmptyElementFor(Token.StartTag startTag) {
        Element el = this.createElementFor(startTag, "http://www.w3.org/1999/xhtml", false);
        this.doInsertElement(el, (Token)startTag);
        this.pop();
        return el;
    }

    private void doInsertElement(Element el, @7\u015aCz Token token) {
        if (el.tag().isFormListed() && this.formElement != null) {
            this.formElement.addElement(el);
        }
        if (el.hasAttr("xmlns") && !el.attr("xmlns").equals(el.tag().namespace())) {
            this.error("Invalid xmlns attribute [%s] on tag [%s]", new Object[]{el.attr("xmlns"), el.tagName()});
        }
        if (this.isFosterInserts() && StringUtil.inSorted((String)this.currentElement().normalName(), (String[])HtmlTreeBuilderState.Constants.InTableFoster)) {
            this.insertInFosterParent((Node)el);
        } else {
            this.currentElement().appendChild((Node)el);
        }
        this.push(el);
    }

    List<Node> parseFragment(String inputFragment, @7\u015aCz Element context, String baseUri, Parser parser) {
        this.state = HtmlTreeBuilderState.Initial;
        this.initialiseParse(new StringReader(inputFragment), baseUri, parser);
        this.contextElement = context;
        this.fragmentParsing = true;
        Element root = null;
        if (context != null) {
            String contextTag;
            if (context.ownerDocument() != null) {
                this.doc.quirksMode(context.ownerDocument().quirksMode());
            }
            switch (contextTag = context.normalName()) {
                case "title": 
                case "textarea": {
                    this.tokeniser.transition(TokeniserState.Rcdata);
                    break;
                }
                case "iframe": 
                case "noembed": 
                case "noframes": 
                case "style": 
                case "xmp": {
                    this.tokeniser.transition(TokeniserState.Rawtext);
                    break;
                }
                case "script": {
                    this.tokeniser.transition(TokeniserState.ScriptData);
                    break;
                }
                case "plaintext": {
                    this.tokeniser.transition(TokeniserState.PLAINTEXT);
                    break;
                }
                case "template": {
                    this.tokeniser.transition(TokeniserState.Data);
                    this.pushTemplateMode(HtmlTreeBuilderState.InTemplate);
                    break;
                }
                default: {
                    this.tokeniser.transition(TokeniserState.Data);
                }
            }
            root = new Element(this.tagFor(contextTag, this.settings), baseUri);
            this.doc.appendChild((Node)root);
            this.push(root);
            this.resetInsertionMode();
            for (Element formSearch = context; formSearch != null; formSearch = formSearch.parent()) {
                if (!(formSearch instanceof FormElement)) continue;
                this.formElement = (FormElement)formSearch;
                break;
            }
        }
        this.runParser();
        if (context == null) return this.doc.childNodes();
        List nodes = root.siblingNodes();
        if (nodes.isEmpty()) return root.childNodes();
        root.insertChildren(-1, (Collection)nodes);
        return root.childNodes();
    }

    void framesetOk(boolean framesetOk) {
        this.framesetOk = framesetOk;
    }

    void setFormElement(FormElement formElement) {
        this.formElement = formElement;
    }

    void generateImpliedEndTags(boolean thorough) {
        String[] search = thorough ? TagThoroughSearchEndTags : TagSearchEndTags;
        while ("http://www.w3.org/1999/xhtml".equals(this.currentElement().tag().namespace())) {
            if (!StringUtil.inSorted((String)this.currentElement().normalName(), (String[])search)) return;
            this.pop();
        }
    }

    void setFosterInserts(boolean fosterInserts) {
        this.fosterInserts = fosterInserts;
    }

    static boolean isSpecial(Element el) {
        String name = el.normalName();
        return StringUtil.inSorted((String)name, (String[])TagSearchSpecial);
    }

    void checkActiveFormattingElements(Element in) {
        int numSeen = 0;
        int size = this.formattingElements.size() - 1;
        int ceil = size - 12;
        if (ceil < 0) {
            ceil = 0;
        }
        int pos = size;
        while (pos >= ceil) {
            Element el = this.formattingElements.get(pos);
            if (el == null) return;
            if (HtmlTreeBuilder.isSameFormattingElement(in, el)) {
                ++numSeen;
            }
            if (numSeen == 3) {
                this.formattingElements.remove(pos);
                break;
            }
            --pos;
        }
        return;
    }

    boolean inScope(String[] targetNames) {
        return this.inSpecificScope(targetNames, TagsSearchInScope, null);
    }

    static boolean isMathmlTextIntegration(Element el) {
        return "http://www.w3.org/1998/Math/MathML".equals(el.tag().namespace()) && StringUtil.inSorted((String)el.normalName(), (String[])TagMathMlTextIntegration);
    }

    FormElement insertFormElement(Token.StartTag startTag, boolean onStack, boolean checkTemplateStack) {
        FormElement el = (FormElement)this.createElementFor(startTag, "http://www.w3.org/1999/xhtml", false);
        if (checkTemplateStack) {
            if (!this.onStack("template")) {
                this.setFormElement(el);
            }
        } else {
            this.setFormElement(el);
        }
        this.doInsertElement((Element)el, (Token)startTag);
        if (onStack) return el;
        this.pop();
        return el;
    }
}
