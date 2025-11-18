/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.helper.W3CDom$W3CBuilder
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.DocumentType
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.select.NodeTraversor
 *  org.jsoup.select.NodeVisitor
 *  org.jsoup.select.Selector$SelectorParseException
 */
package org.jsoup.helper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.jsoup.helper.Validate;
import org.jsoup.helper.W3CDom;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.Selector;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NodeList;

/*
 * Exception performing whole class analysis ignored.
 */
public class W3CDom {
    protected DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static final String ContextNodeProperty = "jsoupContextNode";
    private static final String ContextProperty = "jsoupContextSource";
    private boolean namespaceAware = true;
    public static final String SourceProperty = "jsoupSource";
    public static final String XPathFactoryProperty = "javax.xml.xpath.XPathFactory:jsoup";

    public NodeList selectXpath(String xpath, org.w3c.dom.Document doc) {
        return this.selectXpath(xpath, (org.w3c.dom.Node)doc);
    }

    public static HashMap<String, String> OutputXml() {
        return W3CDom.methodMap("xml");
    }

    public org.w3c.dom.Document fromJsoup(Element in) {
        Validate.notNull((Object)in);
        try {
            org.jsoup.nodes.DocumentType doctype;
            DocumentBuilder builder = this.factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();
            org.w3c.dom.Document out = builder.newDocument();
            Document inDoc = in.ownerDocument();
            org.jsoup.nodes.DocumentType documentType = doctype = inDoc != null ? inDoc.documentType() : null;
            if (doctype != null) {
                try {
                    DocumentType documentType2 = impl.createDocumentType(doctype.name(), doctype.publicId(), doctype.systemId());
                    out.appendChild(documentType2);
                }
                catch (DOMException documentType2) {
                    // empty catch block
                }
            }
            out.setXmlStandalone(true);
            Element context = in instanceof Document ? in.firstElementChild() : in;
            out.setUserData("jsoupContextSource", context, null);
            this.convert((Element)(inDoc != null ? inDoc : in), out);
            return out;
        }
        catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public String asString(org.w3c.dom.Document doc) {
        return W3CDom.asString(doc, null);
    }

    public org.w3c.dom.Node contextNode(org.w3c.dom.Document wDoc) {
        return (org.w3c.dom.Node)wDoc.getUserData("jsoupContextNode");
    }

    public void convert(Document in, org.w3c.dom.Document out) {
        this.convert((Element)in, out);
    }

    public void convert(Element in, org.w3c.dom.Document out) {
        W3CBuilder builder = new W3CBuilder(out);
        W3CBuilder.access$002((W3CBuilder)builder, (boolean)this.namespaceAware);
        Document inDoc = in.ownerDocument();
        if (inDoc != null) {
            if (!StringUtil.isBlank((String)inDoc.location())) {
                out.setDocumentURI(inDoc.location());
            }
            W3CBuilder.access$102((W3CBuilder)builder, (Document.OutputSettings.Syntax)inDoc.outputSettings().syntax());
        }
        Element rootEl = in instanceof Document ? in.firstElementChild() : in;
        NodeTraversor.traverse((NodeVisitor)builder, (Node)rootEl);
    }

    public W3CDom() {
        this.factory.setNamespaceAware(true);
    }

    public static String asString(org.w3c.dom.Document doc, @7\u015aCz Map<String, String> properties) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            if (properties != null) {
                transformer.setOutputProperties(W3CDom.propertiesFromMap(properties));
            }
            if (doc.getDoctype() != null) {
                DocumentType doctype = doc.getDoctype();
                if (!StringUtil.isBlank((String)doctype.getPublicId())) {
                    transformer.setOutputProperty("doctype-public", doctype.getPublicId());
                }
                if (!StringUtil.isBlank((String)doctype.getSystemId())) {
                    transformer.setOutputProperty("doctype-system", doctype.getSystemId());
                } else if (doctype.getName().equalsIgnoreCase("html") && StringUtil.isBlank((String)doctype.getPublicId()) && StringUtil.isBlank((String)doctype.getSystemId())) {
                    transformer.setOutputProperty("doctype-system", "about:legacy-compat");
                }
            }
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }

    public W3CDom namespaceAware(boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
        this.factory.setNamespaceAware(namespaceAware);
        return this;
    }

    public org.w3c.dom.Document fromJsoup(Document in) {
        return this.fromJsoup((Element)in);
    }

    public NodeList selectXpath(String xpath, org.w3c.dom.Node contextNode) {
        NodeList nodeList;
        Validate.notEmptyParam((String)xpath, (String)"xpath");
        Validate.notNullParam((Object)contextNode, (String)"contextNode");
        try {
            String property = System.getProperty("javax.xml.xpath.XPathFactory:jsoup");
            XPathFactory xPathFactory = property != null ? XPathFactory.newInstance("jsoup") : XPathFactory.newInstance();
            XPathExpression expression = xPathFactory.newXPath().compile(xpath);
            nodeList = (NodeList)expression.evaluate(contextNode, XPathConstants.NODESET);
            Validate.notNull((Object)nodeList);
        }
        catch (XPathExpressionException | XPathFactoryConfigurationException e) {
            throw new Selector.SelectorParseException((Throwable)e, "Could not evaluate XPath query [%s]: %s", new Object[]{xpath, e.getMessage()});
        }
        return nodeList;
    }

    public static HashMap<String, String> OutputHtml() {
        return W3CDom.methodMap("html");
    }

    static Properties propertiesFromMap(Map<String, String> map) {
        Properties props = new Properties();
        props.putAll(map);
        return props;
    }

    public <T extends Node> List<T> sourceNodes(NodeList nodeList, Class<T> nodeType) {
        Validate.notNull((Object)nodeList);
        Validate.notNull(nodeType);
        ArrayList<Node> nodes = new ArrayList<Node>(nodeList.getLength());
        int i = 0;
        while (i < nodeList.getLength()) {
            org.w3c.dom.Node node = nodeList.item(i);
            Object source = node.getUserData("jsoupSource");
            if (nodeType.isInstance(source)) {
                nodes.add((Node)nodeType.cast(source));
            }
            ++i;
        }
        return nodes;
    }

    private static HashMap<String, String> methodMap(String method) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("method", method);
        return map;
    }

    public boolean namespaceAware() {
        return this.namespaceAware;
    }

    public static org.w3c.dom.Document convert(Document in) {
        return new W3CDom().fromJsoup(in);
    }
}
