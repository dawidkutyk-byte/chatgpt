/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.internal.StringUtil$StringJoiner
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.CDataNode
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.DataNode
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Element$NodeList
 *  org.jsoup.nodes.Element$TextAccumulator
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.NodeUtils
 *  org.jsoup.nodes.Range
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Tag
 *  org.jsoup.parser.TokenQueue
 *  org.jsoup.select.Collector
 *  org.jsoup.select.Elements
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.Evaluator$AllElements
 *  org.jsoup.select.Evaluator$Attribute
 *  org.jsoup.select.Evaluator$AttributeStarting
 *  org.jsoup.select.Evaluator$AttributeWithValue
 *  org.jsoup.select.Evaluator$AttributeWithValueContaining
 *  org.jsoup.select.Evaluator$AttributeWithValueEnding
 *  org.jsoup.select.Evaluator$AttributeWithValueMatching
 *  org.jsoup.select.Evaluator$AttributeWithValueNot
 *  org.jsoup.select.Evaluator$AttributeWithValueStarting
 *  org.jsoup.select.Evaluator$Class
 *  org.jsoup.select.Evaluator$ContainsOwnText
 *  org.jsoup.select.Evaluator$ContainsText
 *  org.jsoup.select.Evaluator$Id
 *  org.jsoup.select.Evaluator$IndexEquals
 *  org.jsoup.select.Evaluator$IndexGreaterThan
 *  org.jsoup.select.Evaluator$IndexLessThan
 *  org.jsoup.select.Evaluator$Matches
 *  org.jsoup.select.Evaluator$MatchesOwn
 *  org.jsoup.select.Evaluator$Tag
 *  org.jsoup.select.NodeFilter
 *  org.jsoup.select.NodeFilter$FilterResult
 *  org.jsoup.select.NodeTraversor
 *  org.jsoup.select.NodeVisitor
 *  org.jsoup.select.QueryParser
 *  org.jsoup.select.Selector
 */
package org.jsoup.nodes;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.NodeUtils;
import org.jsoup.nodes.Range;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tag;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;

public class Element
extends Node {
    private static final String BaseUriKey;
    private Tag tag;
    @7\u015aCz Attributes attributes;
    private static final Pattern ClassSplit;
    private static final List<Element> EmptyChildren;
    private @7\u015aCz WeakReference<List<Element>> shadowChildrenRef;
    List<Node> childNodes;

    protected void doSetBaseUri(String baseUri) {
        this.attributes().put(BaseUriKey, baseUri);
    }

    public Elements getElementsByAttributeValueNot(String key, String value) {
        return Collector.collect((Evaluator)new Evaluator.AttributeWithValueNot(key, value), (Element)this);
    }

    public Element expectFirst(String cssQuery) {
        return (Element)((Object)Validate.ensureNotNull((Object)((Object)Selector.selectFirst((String)cssQuery, (Element)this)), (String)(this.parent() != null ? "No elements matched the query '%s' on element '%s'." : "No elements matched the query '%s' in the document."), (Object[])new Object[]{cssQuery, this.tagName()}));
    }

    static {
        EmptyChildren = Collections.emptyList();
        ClassSplit = Pattern.compile("\\s+");
        BaseUriKey = Attributes.internalKey((String)"baseUri");
    }

    public Elements getElementsMatchingText(String regex) {
        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        }
        catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
        return this.getElementsMatchingText(pattern);
    }

    private boolean isInlineable(Document.OutputSettings out) {
        if (this.tag.isInline()) return (this.parent() == null || this.parent().isBlock()) && !this.isEffectivelyFirst() && !out.outline() && !this.nameIs("br");
        return false;
    }

    public String wholeText() {
        StringBuilder accum = StringUtil.borrowBuilder();
        this.nodeStream().forEach((? super T node) -> Element.appendWholeText(node, accum));
        return StringUtil.releaseBuilder((StringBuilder)accum);
    }

    public Element removeClass(String className) {
        Validate.notNull((Object)className);
        Set<String> classes = this.classNames();
        classes.remove(className);
        this.classNames(classes);
        return this;
    }

    public Elements getElementsContainingOwnText(String searchText) {
        return Collector.collect((Evaluator)new Evaluator.ContainsOwnText(searchText), (Element)this);
    }

    @Deprecated
    public Element forEach(Consumer<? super Element> action) {
        this.stream().forEach(action);
        return this;
    }

    public Elements select(String cssQuery) {
        return Selector.select((String)cssQuery, (Element)this);
    }

    public Element wrap(String html) {
        return (Element)super.wrap(html);
    }

    public Elements selectXpath(String xpath) {
        return new Elements(NodeUtils.selectXpath((String)xpath, (Element)this, Element.class));
    }

    public Element clone() {
        return (Element)super.clone();
    }

    public Elements getElementsMatchingOwnText(Pattern pattern) {
        return Collector.collect((Evaluator)new Evaluator.MatchesOwn(pattern), (Element)this);
    }

    public Tag tag() {
        return this.tag;
    }

    public Element addClass(String className) {
        Validate.notNull((Object)className);
        Set<String> classes = this.classNames();
        classes.add(className);
        this.classNames(classes);
        return this;
    }

    public Element child(int index) {
        return this.childElementsList().get(index);
    }

    public Element appendElement(String tagName) {
        return this.appendElement(tagName, this.tag.namespace());
    }

    public int childNodeSize() {
        return this.childNodes.size();
    }

    public boolean hasClass(String className) {
        if (this.attributes == null) {
            return false;
        }
        String classAttr = this.attributes.getIgnoreCase("class");
        int len = classAttr.length();
        int wantLen = className.length();
        if (len == 0) return false;
        if (len < wantLen) {
            return false;
        }
        if (len == wantLen) {
            return className.equalsIgnoreCase(classAttr);
        }
        boolean inClass = false;
        int start = 0;
        int i = 0;
        while (true) {
            if (i >= len) {
                if (!inClass) return false;
                if (len - start != wantLen) return false;
                return classAttr.regionMatches(true, start, className, 0, wantLen);
            }
            if (Character.isWhitespace(classAttr.charAt(i))) {
                if (inClass) {
                    if (i - start == wantLen && classAttr.regionMatches(true, start, className, 0, wantLen)) {
                        return true;
                    }
                    inClass = false;
                }
            } else if (!inClass) {
                inClass = true;
                start = i;
            }
            ++i;
        }
    }

    public Element toggleClass(String className) {
        Validate.notNull((Object)className);
        Set<String> classes = this.classNames();
        if (classes.contains(className)) {
            classes.remove(className);
        } else {
            classes.add(className);
        }
        this.classNames(classes);
        return this;
    }

    public Element prepend(String html) {
        Validate.notNull((Object)html);
        List nodes = NodeUtils.parser((Node)this).parseFragmentInput(html, this, this.baseUri());
        this.addChildren(0, nodes.toArray(new Node[0]));
        return this;
    }

    public boolean is(Evaluator evaluator) {
        return evaluator.matches((Element)this.root(), this);
    }

    public Elements getElementsByAttributeValueMatching(String key, String regex) {
        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        }
        catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
        return this.getElementsByAttributeValueMatching(key, pattern);
    }

    public Elements getElementsByAttributeStarting(String keyPrefix) {
        Validate.notEmpty((String)keyPrefix);
        keyPrefix = keyPrefix.trim();
        return Collector.collect((Evaluator)new Evaluator.AttributeStarting(keyPrefix), (Element)this);
    }

    public Elements getAllElements() {
        return Collector.collect((Evaluator)new Evaluator.AllElements(), (Element)this);
    }

    private <T> List<T> filterNodes(Class<T> clazz) {
        return this.childNodes.stream().filter(clazz::isInstance).map(clazz::cast).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public Element prependChild(Node child) {
        Validate.notNull((Object)child);
        this.addChildren(0, new Node[]{child});
        return this;
    }

    public Element val(String value) {
        if (this.elementIs("textarea", "http://www.w3.org/1999/xhtml")) {
            this.text(value);
        } else {
            this.attr("value", value);
        }
        return this;
    }

    protected List<Node> ensureChildNodes() {
        if (this.childNodes != EmptyNodes) return this.childNodes;
        this.childNodes = new NodeList(this, 4);
        return this.childNodes;
    }

    private static void appendWholeText(Node node, StringBuilder accum) {
        if (node instanceof TextNode) {
            accum.append(((TextNode)node).getWholeText());
        } else {
            if (!node.nameIs("br")) return;
            accum.append("\n");
        }
    }

    public Element firstElementSibling() {
        if (this.parent() == null) return this;
        return this.parent().firstElementChild();
    }

    public int childrenSize() {
        return this.childElementsList().size();
    }

    public Element appendElement(String tagName, String namespace) {
        Element child = new Element(Tag.valueOf((String)tagName, (String)namespace, (ParseSettings)NodeUtils.parser((Node)this).settings()), this.baseUri());
        this.appendChild(child);
        return child;
    }

    public String className() {
        return this.attr("class").trim();
    }

    public Elements getElementsByIndexGreaterThan(int index) {
        return Collector.collect((Evaluator)new Evaluator.IndexGreaterThan(index), (Element)this);
    }

    public Elements getElementsByTag(String tagName) {
        Validate.notEmpty((String)tagName);
        tagName = Normalizer.normalize((String)tagName);
        return Collector.collect((Evaluator)new Evaluator.Tag(tagName), (Element)this);
    }

    public Elements getElementsMatchingText(Pattern pattern) {
        return Collector.collect((Evaluator)new Evaluator.Matches(pattern), (Element)this);
    }

    public @7\u015aCz Element firstElementChild() {
        Node child = this.firstChild();
        while (child != null) {
            if (child instanceof Element) {
                return (Element)child;
            }
            child = child.nextSibling();
        }
        return null;
    }

    public Element text(String text) {
        Validate.notNull((Object)text);
        this.empty();
        Document owner = this.ownerDocument();
        if (owner != null && owner.parser().isContentForTagData(this.normalName())) {
            this.appendChild((Node)new DataNode(text));
        } else {
            this.appendChild((Node)new TextNode(text));
        }
        return this;
    }

    public Elements getElementsByAttributeValueEnding(String key, String valueSuffix) {
        return Collector.collect((Evaluator)new Evaluator.AttributeWithValueEnding(key, valueSuffix), (Element)this);
    }

    static boolean preserveWhitespace(@7\u015aCz Node node) {
        if (!(node instanceof Element)) return false;
        Element el = (Element)node;
        int i = 0;
        do {
            if (el.tag.preserveWhitespace()) {
                return true;
            }
            el = el.parent();
            if (++i >= 6) return false;
        } while (el != null);
        return false;
    }

    public Element forEachNode(Consumer<? super Node> action) {
        return (Element)super.forEachNode(action);
    }

    public Element clearAttributes() {
        if (this.attributes == null) return this;
        super.clearAttributes();
        if (this.attributes.size() != 0) return this;
        this.attributes = null;
        return this;
    }

    private boolean isFormatAsBlock(Document.OutputSettings out) {
        return this.tag.isBlock() || this.parent() != null && this.parent().tag().formatAsBlock() || out.outline();
    }

    public Range endSourceRange() {
        return Range.of((Node)this, (boolean)false);
    }

    private static String searchUpForAttribute(Element start, String key) {
        Element el = start;
        while (el != null) {
            if (el.attributes != null && el.attributes.hasKey(key)) {
                return el.attributes.get(key);
            }
            el = el.parent();
        }
        return "";
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (this.childNodes.isEmpty()) {
            if (this.tag.isSelfClosing()) return;
        }
        if (out.prettyPrint() && !this.childNodes.isEmpty() && (this.tag.formatAsBlock() && !Element.preserveWhitespace(this.parentNode) || out.outline() && (this.childNodes.size() > 1 || this.childNodes.size() == 1 && this.childNodes.get(0) instanceof Element))) {
            this.indent(accum, depth, out);
        }
        accum.append("</").append(this.tagName()).append('>');
    }

    public boolean isBlock() {
        return this.tag.isBlock();
    }

    public Element attr(String attributeKey, boolean attributeValue) {
        this.attributes().put(attributeKey, attributeValue);
        return this;
    }

    public String wholeOwnText() {
        StringBuilder accum = StringUtil.borrowBuilder();
        int size = this.childNodeSize();
        int i = 0;
        while (i < size) {
            Node node = this.childNodes.get(i);
            Element.appendWholeText(node, accum);
            ++i;
        }
        return StringUtil.releaseBuilder((StringBuilder)accum);
    }

    public Elements getElementsByAttributeValueMatching(String key, Pattern pattern) {
        return Collector.collect((Evaluator)new Evaluator.AttributeWithValueMatching(key, pattern), (Element)this);
    }

    public Element lastElementSibling() {
        if (this.parent() == null) return this;
        return this.parent().lastElementChild();
    }

    boolean shouldIndent(Document.OutputSettings out) {
        return out.prettyPrint() && this.isFormatAsBlock(out) && !this.isInlineable(out) && !Element.preserveWhitespace(this.parentNode);
    }

    public @7\u015aCz Element getElementById(String id) {
        Validate.notEmpty((String)id);
        Elements elements = Collector.collect((Evaluator)new Evaluator.Id(id), (Element)this);
        if (elements.size() <= 0) return null;
        return (Element)((Object)elements.get(0));
    }

    public Element removeAttr(String attributeKey) {
        return (Element)super.removeAttr(attributeKey);
    }

    public Element(String tag) {
        this(Tag.valueOf((String)tag, (String)"http://www.w3.org/1999/xhtml", (ParseSettings)ParseSettings.preserveCase), "", null);
    }

    public Elements getElementsByAttributeValueStarting(String key, String valuePrefix) {
        return Collector.collect((Evaluator)new Evaluator.AttributeWithValueStarting(key, valuePrefix), (Element)this);
    }

    private void ownText(StringBuilder accum) {
        int i = 0;
        while (i < this.childNodeSize()) {
            Node child = this.childNodes.get(i);
            if (child instanceof TextNode) {
                TextNode textNode = (TextNode)child;
                Element.appendNormalisedText(accum, textNode);
            } else if (child.nameIs("br") && !TextNode.lastCharIsWhitespace((StringBuilder)accum)) {
                accum.append(" ");
            }
            ++i;
        }
    }

    public @7\u015aCz Element selectFirst(Evaluator evaluator) {
        return Collector.findFirst((Evaluator)evaluator, (Element)this);
    }

    public Elements parents() {
        Elements parents = new Elements();
        Node parent = this.parent();
        while (parent != null) {
            if (parent.nameIs("#root")) return parents;
            parents.add((Object)parent);
            parent = parent.parent();
        }
        return parents;
    }

    public Element filter(NodeFilter nodeFilter) {
        return (Element)super.filter(nodeFilter);
    }

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (this.shouldIndent(out)) {
            if (accum instanceof StringBuilder) {
                if (((StringBuilder)accum).length() > 0) {
                    this.indent(accum, depth, out);
                }
            } else {
                this.indent(accum, depth, out);
            }
        }
        accum.append('<').append(this.tagName());
        if (this.attributes != null) {
            this.attributes.html(accum, out);
        }
        if (this.childNodes.isEmpty() && this.tag.isSelfClosing()) {
            if (out.syntax() == Document.OutputSettings.Syntax.html && this.tag.isEmpty()) {
                accum.append('>');
            } else {
                accum.append(" />");
            }
        } else {
            accum.append('>');
        }
    }

    public String html() {
        StringBuilder accum = StringUtil.borrowBuilder();
        this.html(accum);
        String html = StringUtil.releaseBuilder((StringBuilder)accum);
        return NodeUtils.outputSettings((Node)this).prettyPrint() ? html.trim() : html;
    }

    public <T extends Appendable> T html(T appendable) {
        int size = this.childNodes.size();
        int i = 0;
        while (i < size) {
            this.childNodes.get(i).outerHtml(appendable);
            ++i;
        }
        return appendable;
    }

    public Map<String, String> dataset() {
        return this.attributes().dataset();
    }

    static /* synthetic */ void access$000(StringBuilder x0, TextNode x1) {
        Element.appendNormalisedText(x0, x1);
    }

    public String tagName() {
        return this.tag.getName();
    }

    public Element before(String html) {
        return (Element)super.before(html);
    }

    public @7\u015aCz Element closest(Evaluator evaluator) {
        Validate.notNull((Object)evaluator);
        Element el = this;
        Node root = this.root();
        do {
            if (!evaluator.matches((Element)root, el)) continue;
            return el;
        } while ((el = el.parent()) != null);
        return null;
    }

    public Element id(String id) {
        Validate.notNull((Object)id);
        this.attr("id", id);
        return this;
    }

    public Elements getElementsByAttributeValueContaining(String key, String match) {
        return Collector.collect((Evaluator)new Evaluator.AttributeWithValueContaining(key, match), (Element)this);
    }

    public Element(Tag tag, @7\u015aCz String baseUri) {
        this(tag, baseUri, null);
    }

    public Element append(String html) {
        Validate.notNull((Object)html);
        List nodes = NodeUtils.parser((Node)this).parseFragmentInput(html, this, this.baseUri());
        this.addChildren(nodes.toArray(new Node[0]));
        return this;
    }

    public Element appendChildren(Collection<? extends Node> children) {
        this.insertChildren(-1, children);
        return this;
    }

    public boolean is(String cssQuery) {
        return this.is(QueryParser.parse((String)cssQuery));
    }

    public String id() {
        return this.attributes != null ? this.attributes.getIgnoreCase("id") : "";
    }

    public Elements getElementsByIndexEquals(int index) {
        return Collector.collect((Evaluator)new Evaluator.IndexEquals(index), (Element)this);
    }

    public String val() {
        if (!this.elementIs("textarea", "http://www.w3.org/1999/xhtml")) return this.attr("value");
        return this.text();
    }

    public Stream<Element> stream() {
        return NodeUtils.stream((Node)this, Element.class);
    }

    public String baseUri() {
        return Element.searchUpForAttribute(this, BaseUriKey);
    }

    public Elements getElementsContainingText(String searchText) {
        return Collector.collect((Evaluator)new Evaluator.ContainsText(searchText), (Element)this);
    }

    public Element after(String html) {
        return (Element)super.after(html);
    }

    protected boolean hasChildNodes() {
        return this.childNodes != EmptyNodes;
    }

    public int elementSiblingIndex() {
        if (this.parent() != null) return Element.indexInList(this, this.parent().childElementsList());
        return 0;
    }

    private String cssSelectorComponent() {
        String tagName = TokenQueue.escapeCssIdentifier((String)this.tagName()).replace("\\:", "|");
        StringBuilder selector = StringUtil.borrowBuilder().append(tagName);
        StringUtil.StringJoiner escapedClasses = new StringUtil.StringJoiner(".");
        for (String name : this.classNames()) {
            escapedClasses.add((Object)TokenQueue.escapeCssIdentifier((String)name));
        }
        String classes = escapedClasses.complete();
        if (classes.length() > 0) {
            selector.append('.').append(classes);
        }
        if (this.parent() == null) return StringUtil.releaseBuilder((StringBuilder)selector);
        if (this.parent() instanceof Document) {
            return StringUtil.releaseBuilder((StringBuilder)selector);
        }
        selector.insert(0, " > ");
        if (this.parent().select(selector.toString()).size() <= 1) return StringUtil.releaseBuilder((StringBuilder)selector);
        selector.append(String.format(":nth-child(%d)", this.elementSiblingIndex() + 1));
        return StringUtil.releaseBuilder((StringBuilder)selector);
    }

    public Element after(Node node) {
        return (Element)super.after(node);
    }

    public List<DataNode> dataNodes() {
        return this.filterNodes(DataNode.class);
    }

    public Element shallowClone() {
        String baseUri = this.baseUri();
        if (!baseUri.isEmpty()) return new Element(this.tag, baseUri, this.attributes == null ? null : this.attributes.clone());
        baseUri = null;
        return new Element(this.tag, baseUri, this.attributes == null ? null : this.attributes.clone());
    }

    public boolean elementIs(String normalName, String namespace) {
        return this.tag.normalName().equals(normalName) && this.tag.namespace().equals(namespace);
    }

    public Element before(Node node) {
        return (Element)super.before(node);
    }

    public Element appendChild(Node child) {
        Validate.notNull((Object)child);
        this.reparentChild(child);
        this.ensureChildNodes();
        this.childNodes.add(child);
        child.setSiblingIndex(this.childNodes.size() - 1);
        return this;
    }

    public Elements select(Evaluator evaluator) {
        return Selector.select((Evaluator)evaluator, (Element)this);
    }

    public String text() {
        StringBuilder accum = StringUtil.borrowBuilder();
        NodeTraversor.traverse((NodeVisitor)new TextAccumulator(accum), (Node)this);
        return StringUtil.releaseBuilder((StringBuilder)accum).trim();
    }

    void nodelistChanged() {
        super.nodelistChanged();
        this.shadowChildrenRef = null;
    }

    public Element tagName(String tagName) {
        return this.tagName(tagName, this.tag.namespace());
    }

    public Elements siblingElements() {
        if (this.parentNode == null) {
            return new Elements(0);
        }
        List<Element> elements = this.parent().childElementsList();
        Elements siblings = new Elements(elements.size() - 1);
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element el = iterator.next();
            if (el == this) continue;
            siblings.add((Object)el);
        }
        return siblings;
    }

    public Elements children() {
        return new Elements(this.childElementsList());
    }

    public @7\u015aCz Element selectFirst(String cssQuery) {
        return Selector.selectFirst((String)cssQuery, (Element)this);
    }

    public Element prependElement(String tagName, String namespace) {
        Element child = new Element(Tag.valueOf((String)tagName, (String)namespace, (ParseSettings)NodeUtils.parser((Node)this).settings()), this.baseUri());
        this.prependChild(child);
        return child;
    }

    public String normalName() {
        return this.tag.normalName();
    }

    public Element appendText(String text) {
        Validate.notNull((Object)text);
        TextNode node = new TextNode(text);
        this.appendChild((Node)node);
        return this;
    }

    public Element classNames(Set<String> classNames) {
        Validate.notNull(classNames);
        if (classNames.isEmpty()) {
            this.attributes().remove("class");
        } else {
            this.attributes().put("class", StringUtil.join(classNames, (String)" "));
        }
        return this;
    }

    public Element traverse(NodeVisitor nodeVisitor) {
        return (Element)super.traverse(nodeVisitor);
    }

    public <T extends Node> List<T> selectXpath(String xpath, Class<T> nodeType) {
        return NodeUtils.selectXpath((String)xpath, (Element)this, nodeType);
    }

    List<Element> childElementsList() {
        ArrayList<Element> children;
        if (this.childNodeSize() == 0) {
            return EmptyChildren;
        }
        if (this.shadowChildrenRef != null) {
            children = (ArrayList<Element>)this.shadowChildrenRef.get();
            if (children != null) return children;
        }
        int size = this.childNodes.size();
        children = new ArrayList<Element>(size);
        int i = 0;
        while (true) {
            if (i >= size) {
                this.shadowChildrenRef = new WeakReference(children);
                return children;
            }
            Node node = this.childNodes.get(i);
            if (node instanceof Element) {
                children.add((Element)node);
            }
            ++i;
        }
    }

    public final @7\u015aCz Element parent() {
        return (Element)this.parentNode;
    }

    private static <E extends Element> int indexInList(Element search, List<E> elements) {
        int size = elements.size();
        int i = 0;
        while (i < size) {
            if (elements.get(i) == search) {
                return i;
            }
            ++i;
        }
        return 0;
    }

    private static void appendNormalisedText(StringBuilder accum, TextNode textNode) {
        String text = textNode.getWholeText();
        if (Element.preserveWhitespace(textNode.parentNode) || textNode instanceof CDataNode) {
            accum.append(text);
        } else {
            StringUtil.appendNormalisedWhitespace((StringBuilder)accum, (String)text, (boolean)TextNode.lastCharIsWhitespace((StringBuilder)accum));
        }
    }

    public Elements getElementsByAttributeValue(String key, String value) {
        return Collector.collect((Evaluator)new Evaluator.AttributeWithValue(key, value), (Element)this);
    }

    public String data() {
        StringBuilder sb = StringUtil.borrowBuilder();
        this.traverse((childNode, depth) -> {
            if (childNode instanceof DataNode) {
                DataNode data = (DataNode)childNode;
                sb.append(data.getWholeData());
            } else if (childNode instanceof Comment) {
                Comment comment = (Comment)childNode;
                sb.append(comment.getData());
            } else {
                if (!(childNode instanceof CDataNode)) return;
                CDataNode cDataNode = (CDataNode)childNode;
                sb.append(cDataNode.getWholeText());
            }
        });
        return StringUtil.releaseBuilder((StringBuilder)sb);
    }

    public Elements getElementsMatchingOwnText(String regex) {
        Pattern pattern;
        try {
            pattern = Pattern.compile(regex);
        }
        catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + regex, e);
        }
        return this.getElementsMatchingOwnText(pattern);
    }

    public Elements previousElementSiblings() {
        return this.nextElementSiblings(false);
    }

    public Element root() {
        return (Element)super.root();
    }

    protected Element doClone(@7\u015aCz Node parent) {
        Element clone = (Element)super.doClone(parent);
        clone.attributes = this.attributes != null ? this.attributes.clone() : null;
        clone.childNodes = new NodeList(clone, this.childNodes.size());
        clone.childNodes.addAll(this.childNodes);
        return clone;
    }

    public String nodeName() {
        return this.tag.getName();
    }

    public Element prependElement(String tagName) {
        return this.prependElement(tagName, this.tag.namespace());
    }

    public Elements getElementsByIndexLessThan(int index) {
        return Collector.collect((Evaluator)new Evaluator.IndexLessThan(index), (Element)this);
    }

    public @7\u015aCz Element previousElementSibling() {
        Element prev = this;
        do {
            if ((prev = prev.previousSibling()) == null) return null;
        } while (!(prev instanceof Element));
        return prev;
    }

    public Element empty() {
        Iterator<Node> iterator = this.childNodes.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                this.childNodes.clear();
                return this;
            }
            Node child = iterator.next();
            child.parentNode = null;
        }
    }

    public Elements getElementsByAttribute(String key) {
        Validate.notEmpty((String)key);
        key = key.trim();
        return Collector.collect((Evaluator)new Evaluator.Attribute(key), (Element)this);
    }

    public Set<String> classNames() {
        String[] names = ClassSplit.split(this.className());
        LinkedHashSet<String> classNames = new LinkedHashSet<String>(Arrays.asList(names));
        classNames.remove("");
        return classNames;
    }

    public Element insertChildren(int index, Node ... children) {
        Validate.notNull((Object)children, (String)"Children collection to be inserted must not be null.");
        int currentSize = this.childNodeSize();
        if (index < 0) {
            index += currentSize + 1;
        }
        Validate.isTrue((index >= 0 && index <= currentSize ? 1 : 0) != 0, (String)"Insert position out of bounds.");
        this.addChildren(index, children);
        return this;
    }

    public Element attr(String attributeKey, String attributeValue) {
        super.attr(attributeKey, attributeValue);
        return this;
    }

    public String ownText() {
        StringBuilder sb = StringUtil.borrowBuilder();
        this.ownText(sb);
        return StringUtil.releaseBuilder((StringBuilder)sb).trim();
    }

    public @7\u015aCz Element closest(String cssQuery) {
        return this.closest(QueryParser.parse((String)cssQuery));
    }

    public Element(String tag, String namespace) {
        this(Tag.valueOf((String)tag, (String)namespace, (ParseSettings)ParseSettings.preserveCase), null);
    }

    public Element appendTo(Element parent) {
        Validate.notNull((Object)((Object)parent));
        parent.appendChild(this);
        return this;
    }

    public Element insertChildren(int index, Collection<? extends Node> children) {
        Validate.notNull(children, (String)"Children collection to be inserted must not be null.");
        int currentSize = this.childNodeSize();
        if (index < 0) {
            index += currentSize + 1;
        }
        Validate.isTrue((index >= 0 && index <= currentSize ? 1 : 0) != 0, (String)"Insert position out of bounds.");
        ArrayList<? extends Node> nodes = new ArrayList<Node>(children);
        Node[] nodeArray = nodes.toArray(new Node[0]);
        this.addChildren(index, nodeArray);
        return this;
    }

    public List<TextNode> textNodes() {
        return this.filterNodes(TextNode.class);
    }

    public Element html(String html) {
        this.empty();
        this.append(html);
        return this;
    }

    public Attributes attributes() {
        if (this.attributes != null) return this.attributes;
        this.attributes = new Attributes();
        return this.attributes;
    }

    public @7\u015aCz Element lastElementChild() {
        Node child = this.lastChild();
        while (child != null) {
            if (child instanceof Element) {
                return (Element)child;
            }
            child = child.previousSibling();
        }
        return null;
    }

    public @7\u015aCz Element nextElementSibling() {
        Element next = this;
        do {
            if ((next = next.nextSibling()) == null) return null;
        } while (!(next instanceof Element));
        return next;
    }

    public Attribute attribute(String key) {
        return this.hasAttributes() ? this.attributes().attribute(key) : null;
    }

    public Element prependChildren(Collection<? extends Node> children) {
        this.insertChildren(0, children);
        return this;
    }

    private Elements nextElementSiblings(boolean next) {
        Elements els = new Elements();
        if (this.parentNode == null) {
            return els;
        }
        els.add((Object)this);
        return next ? els.nextAll() : els.prevAll();
    }

    public Elements nextElementSiblings() {
        return this.nextElementSiblings(true);
    }

    public Elements getElementsByClass(String className) {
        Validate.notEmpty((String)className);
        return Collector.collect((Evaluator)new Evaluator.Class(className), (Element)this);
    }

    public String cssSelector() {
        if (this.id().length() > 0) {
            String idSel = "#" + TokenQueue.escapeCssIdentifier((String)this.id());
            Document doc = this.ownerDocument();
            if (doc == null) return idSel;
            Elements els = doc.select(idSel);
            if (els.size() == 1 && els.get(0) == this) {
                return idSel;
            }
        }
        StringBuilder selector = StringUtil.borrowBuilder();
        Element el = this;
        while (el != null) {
            if (el instanceof Document) return StringUtil.releaseBuilder((StringBuilder)selector);
            selector.insert(0, el.cssSelectorComponent());
            el = el.parent();
        }
        return StringUtil.releaseBuilder((StringBuilder)selector);
    }

    protected boolean hasAttributes() {
        return this.attributes != null;
    }

    public Element(Tag tag, @7\u015aCz String baseUri, @7\u015aCz Attributes attributes) {
        Validate.notNull((Object)tag);
        this.childNodes = EmptyNodes;
        this.attributes = attributes;
        this.tag = tag;
        if (baseUri == null) return;
        this.setBaseUri(baseUri);
    }

    public boolean hasText() {
        AtomicBoolean hasText = new AtomicBoolean(false);
        this.filter((node, depth) -> {
            if (!(node instanceof TextNode)) return NodeFilter.FilterResult.CONTINUE;
            TextNode textNode = (TextNode)node;
            if (textNode.isBlank()) return NodeFilter.FilterResult.CONTINUE;
            hasText.set(true);
            return NodeFilter.FilterResult.STOP;
        });
        return hasText.get();
    }

    static /* synthetic */ Tag access$100(Element x0) {
        return x0.tag;
    }

    public Element prependText(String text) {
        Validate.notNull((Object)text);
        TextNode node = new TextNode(text);
        this.prependChild((Node)node);
        return this;
    }

    public Element tagName(String tagName, String namespace) {
        Validate.notEmptyParam((String)tagName, (String)"tagName");
        Validate.notEmptyParam((String)namespace, (String)"namespace");
        this.tag = Tag.valueOf((String)tagName, (String)namespace, (ParseSettings)NodeUtils.parser((Node)this).settings());
        return this;
    }
}
