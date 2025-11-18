/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.CDataNode
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.DocumentType
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Entities$EscapeMode
 *  org.jsoup.nodes.LeafNode
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.nodes.XmlDeclaration
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Parser
 *  org.jsoup.parser.Tag
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$Comment
 *  org.jsoup.parser.Token$Doctype
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.TreeBuilder
 *  org.jsoup.parser.XmlTreeBuilder$1
 */
package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.parser.Token;
import org.jsoup.parser.TreeBuilder;
import org.jsoup.parser.XmlTreeBuilder;

public class XmlTreeBuilder
extends TreeBuilder {
    private static final int maxQueueDepth = 256;

    Document parse(Reader input, String baseUri) {
        return this.parse(input, baseUri, new Parser((TreeBuilder)this));
    }

    List<Node> parseFragment(String inputFragment, String baseUri, Parser parser) {
        this.initialiseParse(new StringReader(inputFragment), baseUri, parser);
        this.runParser();
        return this.doc.childNodes();
    }

    protected void popStackToClose(Token.EndTag endTag) {
        Element next;
        int pos;
        String elName = this.settings.normalizeTag(endTag.tagName);
        Element firstFound = null;
        int bottom = this.stack.size() - 1;
        int upper = bottom >= 256 ? bottom - 256 : 0;
        for (pos = this.stack.size() - 1; pos >= upper; --pos) {
            next = (Element)this.stack.get(pos);
            if (!next.nodeName().equals(elName)) continue;
            firstFound = next;
            break;
        }
        if (firstFound == null) {
            return;
        }
        pos = this.stack.size() - 1;
        while (pos >= 0) {
            next = this.pop();
            if (next == firstFound) {
                return;
            }
            --pos;
        }
    }

    void insertLeafNode(LeafNode node) {
        this.currentElement().appendChild((Node)node);
        this.onNodeInserted((Node)node);
    }

    void insertCharacterFor(Token.Character token) {
        String data = token.getData();
        this.insertLeafNode((LeafNode)(token.isCData() ? new CDataNode(data) : new TextNode(data)));
    }

    @Deprecated
    protected void insertNode(Node node) {
        this.currentElement().appendChild(node);
        this.onNodeInserted(node);
    }

    Document parse(String input, String baseUri) {
        return this.parse(new StringReader(input), baseUri, new Parser((TreeBuilder)this));
    }

    public String defaultNamespace() {
        return "http://www.w3.org/XML/1998/namespace";
    }

    XmlTreeBuilder newInstance() {
        return new XmlTreeBuilder();
    }

    void insertDoctypeFor(Token.Doctype token) {
        DocumentType doctypeNode = new DocumentType(this.settings.normalizeTag(token.getName()), token.getPublicIdentifier(), token.getSystemIdentifier());
        doctypeNode.setPubSysKey(token.getPubSysKey());
        this.insertLeafNode((LeafNode)doctypeNode);
    }

    @Deprecated
    protected void insertNode(Node node, Token token) {
        this.currentElement().appendChild(node);
        this.onNodeInserted(node);
    }

    ParseSettings defaultSettings() {
        return ParseSettings.preserveCase;
    }

    void insertElementFor(Token.StartTag startTag) {
        Tag tag = this.tagFor(startTag.name(), this.settings);
        if (startTag.attributes != null) {
            startTag.attributes.deduplicate(this.settings);
        }
        Element el = new Element(tag, null, this.settings.normalizeAttributes(startTag.attributes));
        this.currentElement().appendChild((Node)el);
        this.push(el);
        if (!startTag.isSelfClosing()) return;
        tag.setSelfClosing();
        this.pop();
    }

    protected boolean process(Token token) {
        this.currentToken = token;
        switch (1.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
            case 1: {
                this.insertElementFor(token.asStartTag());
                break;
            }
            case 2: {
                this.popStackToClose(token.asEndTag());
                break;
            }
            case 3: {
                this.insertCommentFor(token.asComment());
                break;
            }
            case 4: {
                this.insertCharacterFor(token.asCharacter());
                break;
            }
            case 5: {
                this.insertDoctypeFor(token.asDoctype());
                break;
            }
            case 6: {
                break;
            }
            default: {
                Validate.fail((String)("Unexpected token type: " + token.type));
            }
        }
        return true;
    }

    List<Node> parseFragment(String inputFragment, Element context, String baseUri, Parser parser) {
        return this.parseFragment(inputFragment, baseUri, parser);
    }

    void insertCommentFor(Token.Comment commentToken) {
        XmlDeclaration decl;
        Comment comment;
        Comment insert = comment = new Comment(commentToken.getData());
        if (commentToken.bogus && comment.isXmlDeclaration() && (decl = comment.asXmlDeclaration()) != null) {
            insert = decl;
        }
        this.insertLeafNode((LeafNode)insert);
    }

    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        super.initialiseParse(input, baseUri, parser);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml).prettyPrint(false);
    }
}
