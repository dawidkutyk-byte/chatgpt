/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection
 *  org.jsoup.Jsoup
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Document$QuirksMode
 *  org.jsoup.nodes.DocumentType
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.FormElement
 *  org.jsoup.nodes.LeafNode
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.XmlDeclaration
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Parser
 *  org.jsoup.parser.Tag
 *  org.jsoup.select.Elements
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.Evaluator$Tag
 */
package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

public class Document
extends Element {
    private @7\u015aCz Connection connection;
    private OutputSettings outputSettings = new OutputSettings();
    private Parser parser;
    private boolean updateMetaCharset = false;
    private QuirksMode quirksMode = QuirksMode.noQuirks;
    private static final Evaluator titleEval = new Evaluator.Tag("title");
    private final String location;

    private void ensureMetaCharsetElement() {
        if (!this.updateMetaCharset) return;
        OutputSettings.Syntax syntax = this.outputSettings().syntax();
        if (syntax == OutputSettings.Syntax.html) {
            Element metaCharset = this.selectFirst("meta[charset]");
            if (metaCharset != null) {
                metaCharset.attr("charset", this.charset().displayName());
            } else {
                this.head().appendElement("meta").attr("charset", this.charset().displayName());
            }
            this.select("meta[name=charset]").remove();
        } else {
            if (syntax != OutputSettings.Syntax.xml) return;
            Node node = (Node)this.ensureChildNodes().get(0);
            if (node instanceof XmlDeclaration) {
                XmlDeclaration decl = (XmlDeclaration)node;
                if (decl.name().equals("xml")) {
                    decl.attr("encoding", this.charset().displayName());
                    if (!decl.hasAttr("version")) return;
                    decl.attr("version", "1.0");
                } else {
                    decl = new XmlDeclaration("xml", false);
                    decl.attr("version", "1.0");
                    decl.attr("encoding", this.charset().displayName());
                    this.prependChild((Node)decl);
                }
            } else {
                XmlDeclaration decl = new XmlDeclaration("xml", false);
                decl.attr("version", "1.0");
                decl.attr("encoding", this.charset().displayName());
                this.prependChild((Node)decl);
            }
        }
    }

    public Connection connection() {
        if (this.connection != null) return this.connection;
        return Jsoup.newSession();
    }

    public void title(String title) {
        Validate.notNull((Object)title);
        Element titleEl = this.head().selectFirst(titleEval);
        if (titleEl == null) {
            titleEl = this.head().appendElement("title");
        }
        titleEl.text(title);
    }

    public Document shallowClone() {
        Document clone = new Document(this.tag().namespace(), this.baseUri());
        if (this.attributes != null) {
            clone.attributes = this.attributes.clone();
        }
        clone.outputSettings = this.outputSettings.clone();
        return clone;
    }

    public @7\u015aCz DocumentType documentType() {
        Node node;
        Iterator iterator = this.childNodes.iterator();
        do {
            if (!iterator.hasNext()) return null;
            node = (Node)iterator.next();
            if (!(node instanceof DocumentType)) continue;
            return (DocumentType)node;
        } while (node instanceof LeafNode);
        return null;
    }

    public String title() {
        Element titleEl = this.head().selectFirst(titleEval);
        return titleEl != null ? StringUtil.normaliseWhitespace((String)titleEl.text()).trim() : "";
    }

    public static Document createShell(String baseUri) {
        Validate.notNull((Object)baseUri);
        Document doc = new Document(baseUri);
        doc.parser = doc.parser();
        Element html = doc.appendElement("html");
        html.appendElement("head");
        html.appendElement("body");
        return doc;
    }

    public Document(String namespace, String baseUri) {
        super(Tag.valueOf((String)"#root", (String)namespace, (ParseSettings)ParseSettings.htmlDefault), baseUri);
        this.location = baseUri;
        this.parser = Parser.htmlParser();
    }

    public String location() {
        return this.location;
    }

    public QuirksMode quirksMode() {
        return this.quirksMode;
    }

    public FormElement expectForm(String cssQuery) {
        Element el;
        Elements els = this.select(cssQuery);
        Iterator iterator = els.iterator();
        do {
            if (iterator.hasNext()) continue;
            Validate.fail((String)"No form elements matched the query '%s' in the document.", (Object[])new Object[]{cssQuery});
            return null;
        } while (!((el = (Element)iterator.next()) instanceof FormElement));
        return (FormElement)el;
    }

    public String nodeName() {
        return "#document";
    }

    public List<FormElement> forms() {
        return this.select("form").forms();
    }

    public Document outputSettings(OutputSettings outputSettings) {
        Validate.notNull((Object)outputSettings);
        this.outputSettings = outputSettings;
        return this;
    }

    public Element createElement(String tagName) {
        return new Element(Tag.valueOf((String)tagName, (String)this.parser.defaultNamespace(), (ParseSettings)ParseSettings.preserveCase), this.baseUri());
    }

    public Charset charset() {
        return this.outputSettings.charset();
    }

    public Element body() {
        Element html = this.htmlEl();
        Element el = html.firstElementChild();
        while (el != null) {
            if (el.nameIs("body")) return el;
            if (el.nameIs("frameset")) {
                return el;
            }
            el = el.nextElementSibling();
        }
        return html.appendElement("body");
    }

    public Document parser(Parser parser) {
        this.parser = parser;
        return this;
    }

    public Document quirksMode(QuirksMode quirksMode) {
        this.quirksMode = quirksMode;
        return this;
    }

    private Element htmlEl() {
        Element el = this.firstElementChild();
        while (el != null) {
            if (el.nameIs("html")) {
                return el;
            }
            el = el.nextElementSibling();
        }
        return this.appendElement("html");
    }

    public Document connection(Connection connection) {
        Validate.notNull((Object)connection);
        this.connection = connection;
        return this;
    }

    public void charset(Charset charset) {
        this.updateMetaCharsetElement(true);
        this.outputSettings.charset(charset);
        this.ensureMetaCharsetElement();
    }

    public void updateMetaCharsetElement(boolean update) {
        this.updateMetaCharset = update;
    }

    public String outerHtml() {
        return super.html();
    }

    public Element head() {
        Element html = this.htmlEl();
        Element el = html.firstElementChild();
        while (el != null) {
            if (el.nameIs("head")) {
                return el;
            }
            el = el.nextElementSibling();
        }
        return html.prependElement("head");
    }

    public Parser parser() {
        return this.parser;
    }

    public boolean updateMetaCharsetElement() {
        return this.updateMetaCharset;
    }

    public Document(String baseUri) {
        this("http://www.w3.org/1999/xhtml", baseUri);
    }

    public Element text(String text) {
        this.body().text(text);
        return this;
    }

    public Document clone() {
        Document clone = (Document)super.clone();
        clone.outputSettings = this.outputSettings.clone();
        return clone;
    }

    public OutputSettings outputSettings() {
        return this.outputSettings;
    }
}
