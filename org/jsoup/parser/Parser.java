/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.ParseErrorList
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Tokeniser
 *  org.jsoup.parser.TreeBuilder
 *  org.jsoup.parser.XmlTreeBuilder
 */
package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tokeniser;
import org.jsoup.parser.TreeBuilder;
import org.jsoup.parser.XmlTreeBuilder;

public class Parser {
    public static final String NamespaceXml = "http://www.w3.org/XML/1998/namespace";
    public static final String NamespaceMathml = "http://www.w3.org/1998/Math/MathML";
    public static final String NamespaceSvg = "http://www.w3.org/2000/svg";
    private TreeBuilder treeBuilder;
    private ParseSettings settings;
    private boolean trackPosition = false;
    public static final String NamespaceHtml = "http://www.w3.org/1999/xhtml";
    private ParseErrorList errors;

    public Parser newInstance() {
        return new Parser(this);
    }

    public static Document parseBodyFragment(String bodyHtml, String baseUri) {
        Document doc = Document.createShell((String)baseUri);
        Element body = doc.body();
        List<Node> nodeList = Parser.parseFragment(bodyHtml, body, baseUri);
        Node[] nodes = nodeList.toArray(new Node[0]);
        for (int i = nodes.length - 1; i > 0; --i) {
            nodes[i].remove();
        }
        Node[] nodeArray = nodes;
        int n = nodeArray.length;
        int n2 = 0;
        while (n2 < n) {
            Node node = nodeArray[n2];
            body.appendChild(node);
            ++n2;
        }
        return doc;
    }

    public static Parser xmlParser() {
        return new Parser((TreeBuilder)new XmlTreeBuilder());
    }

    public Parser setTrackPosition(boolean trackPosition) {
        this.trackPosition = trackPosition;
        return this;
    }

    public String defaultNamespace() {
        return this.getTreeBuilder().defaultNamespace();
    }

    public boolean isTrackPosition() {
        return this.trackPosition;
    }

    public static Parser htmlParser() {
        return new Parser((TreeBuilder)new HtmlTreeBuilder());
    }

    public static List<Node> parseFragment(String fragmentHtml, Element context, String baseUri) {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        return treeBuilder.parseFragment(fragmentHtml, context, baseUri, new Parser((TreeBuilder)treeBuilder));
    }

    public static List<Node> parseFragment(String fragmentHtml, Element context, String baseUri, ParseErrorList errorList) {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        Parser parser = new Parser((TreeBuilder)treeBuilder);
        parser.errors = errorList;
        return treeBuilder.parseFragment(fragmentHtml, context, baseUri, parser);
    }

    public boolean isTrackErrors() {
        return this.errors.getMaxSize() > 0;
    }

    public Parser setTreeBuilder(TreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        treeBuilder.parser = this;
        return this;
    }

    public static Document parse(String html, String baseUri) {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        return treeBuilder.parse((Reader)new StringReader(html), baseUri, new Parser((TreeBuilder)treeBuilder));
    }

    public Parser setTrackErrors(int maxErrors) {
        this.errors = maxErrors > 0 ? ParseErrorList.tracking((int)maxErrors) : ParseErrorList.noTracking();
        return this;
    }

    public static List<Node> parseXmlFragment(String fragmentXml, String baseUri) {
        XmlTreeBuilder treeBuilder = new XmlTreeBuilder();
        return treeBuilder.parseFragment(fragmentXml, baseUri, new Parser((TreeBuilder)treeBuilder));
    }

    public Parser settings(ParseSettings settings) {
        this.settings = settings;
        return this;
    }

    private Parser(Parser copy) {
        this.treeBuilder = copy.treeBuilder.newInstance();
        this.errors = new ParseErrorList(copy.errors);
        this.settings = new ParseSettings(copy.settings);
        this.trackPosition = copy.trackPosition;
    }

    public Document parseInput(Reader inputHtml, String baseUri) {
        return this.treeBuilder.parse(inputHtml, baseUri, this);
    }

    public Document parseInput(String html, String baseUri) {
        return this.treeBuilder.parse((Reader)new StringReader(html), baseUri, this);
    }

    public Parser(TreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
        this.settings = treeBuilder.defaultSettings();
        this.errors = ParseErrorList.noTracking();
    }

    public boolean isContentForTagData(String normalName) {
        return this.getTreeBuilder().isContentForTagData(normalName);
    }

    public ParseSettings settings() {
        return this.settings;
    }

    public ParseErrorList getErrors() {
        return this.errors;
    }

    public TreeBuilder getTreeBuilder() {
        return this.treeBuilder;
    }

    public List<Node> parseFragmentInput(String fragment, Element context, String baseUri) {
        return this.treeBuilder.parseFragment(fragment, context, baseUri, this);
    }

    public static String unescapeEntities(String string, boolean inAttribute) {
        Parser parser = Parser.htmlParser();
        parser.treeBuilder.initialiseParse((Reader)new StringReader(string), "", parser);
        Tokeniser tokeniser = new Tokeniser(parser.treeBuilder);
        return tokeniser.unescapeEntities(inAttribute);
    }
}
