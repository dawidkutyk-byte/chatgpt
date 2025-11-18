/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.DataNode
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.FormElement
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.NodeFilter
 *  org.jsoup.select.NodeTraversor
 *  org.jsoup.select.NodeVisitor
 *  org.jsoup.select.QueryParser
 *  org.jsoup.select.Selector
 */
package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;

public class Elements
extends ArrayList<Element> {
    public Elements parents() {
        LinkedHashSet<Element> combo = new LinkedHashSet<Element>();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element e = (Element)iterator.next();
            combo.addAll(e.parents());
        }
        return new Elements((Collection<Element>)combo);
    }

    public Elements nextAll() {
        return this.siblings(null, true, true);
    }

    public Elements unwrap() {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.unwrap();
        }
        return this;
    }

    public Elements eq(int index) {
        return this.size() > index ? new Elements((Element)this.get(index)) : new Elements();
    }

    public String attr(String attributeKey) {
        Element element;
        Iterator iterator = this.iterator();
        do {
            if (!iterator.hasNext()) return "";
        } while (!(element = (Element)iterator.next()).hasAttr(attributeKey));
        return element.attr(attributeKey);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean anyRemoved = false;
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            anyRemoved |= this.remove(o);
        }
        return anyRemoved;
    }

    @Override
    public boolean remove(Object o) {
        int index = super.indexOf(o);
        if (index == -1) {
            return false;
        }
        this.remove(index);
        return true;
    }

    public Elements removeClass(String className) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.removeClass(className);
        }
        return this;
    }

    public Elements next() {
        return this.siblings(null, true, false);
    }

    public Elements removeAttr(String attributeKey) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.removeAttr(attributeKey);
        }
        return this;
    }

    public Elements next(String query) {
        return this.siblings(query, true, false);
    }

    public List<DataNode> dataNodes() {
        return this.childNodesOfType(DataNode.class);
    }

    public Elements attr(String attributeKey, String attributeValue) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.attr(attributeKey, attributeValue);
        }
        return this;
    }

    public Elements wrap(String html) {
        Validate.notEmpty((String)html);
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.wrap(html);
        }
        return this;
    }

    public String val() {
        if (this.size() <= 0) return "";
        return this.first().val();
    }

    public List<Comment> comments() {
        return this.childNodesOfType(Comment.class);
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.html());
        }
        return StringUtil.releaseBuilder((StringBuilder)sb);
    }

    public Elements prev(String query) {
        return this.siblings(query, false, false);
    }

    public Elements html(String html) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.html(html);
        }
        return this;
    }

    @Override
    public void replaceAll(UnaryOperator<Element> operator) {
        int i = 0;
        while (i < this.size()) {
            this.set(i, (Element)operator.apply((Element)this.get(i)));
            ++i;
        }
    }

    private Elements siblings(@7\u015aCz String query, boolean next, boolean all) {
        Elements els = new Elements();
        Evaluator eval = query != null ? QueryParser.parse((String)query) : null;
        Iterator iterator = this.iterator();
        block0: while (iterator.hasNext()) {
            Element e = (Element)iterator.next();
            do {
                Element sib;
                if ((sib = next ? e.nextElementSibling() : e.previousElementSibling()) == null) continue block0;
                if (eval == null) {
                    els.add(sib);
                } else if (sib.is(eval)) {
                    els.add(sib);
                }
                e = sib;
            } while (all);
        }
        return els;
    }

    public Elements select(String query) {
        return Selector.select((String)query, (Iterable)this);
    }

    public Elements tagName(String tagName) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.tagName(tagName);
        }
        return this;
    }

    public String text() {
        StringBuilder sb = StringUtil.borrowBuilder();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(element.text());
        }
        return StringUtil.releaseBuilder((StringBuilder)sb);
    }

    public Elements not(String query) {
        Elements out = Selector.select((String)query, (Iterable)this);
        return Selector.filterOut((Collection)this, (Collection)out);
    }

    public Elements(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public Elements clone() {
        Elements clone = new Elements(this.size());
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element e = (Element)iterator.next();
            clone.add(e.clone());
        }
        return clone;
    }

    @Override
    public boolean removeIf(Predicate<? super Element> filter) {
        boolean anyRemoved = false;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            Element el = (Element)it.next();
            if (!filter.test((Element)el)) continue;
            it.remove();
            anyRemoved = true;
        }
        return anyRemoved;
    }

    @Override
    public String toString() {
        return this.outerHtml();
    }

    private <T extends Node> List<T> childNodesOfType(Class<T> tClass) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Iterator iterator = this.iterator();
        block0: while (iterator.hasNext()) {
            Element el = (Element)iterator.next();
            int i = 0;
            while (true) {
                if (i >= el.childNodeSize()) continue block0;
                Node node = el.childNode(i);
                if (tClass.isInstance(node)) {
                    nodes.add((Node)tClass.cast(node));
                }
                ++i;
            }
            break;
        }
        return nodes;
    }

    @Override
    public Element remove(int index) {
        Element old = (Element)super.remove(index);
        old.remove();
        return old;
    }

    public List<TextNode> textNodes() {
        return this.childNodesOfType(TextNode.class);
    }

    public Elements nextAll(String query) {
        return this.siblings(query, true, true);
    }

    public Elements val(String value) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.val(value);
        }
        return this;
    }

    public List<FormElement> forms() {
        ArrayList<FormElement> forms = new ArrayList<FormElement>();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element el = (Element)iterator.next();
            if (!(el instanceof FormElement)) continue;
            forms.add((FormElement)el);
        }
        return forms;
    }

    public Elements prevAll() {
        return this.siblings(null, false, true);
    }

    public @7\u015aCz Element first() {
        return this.isEmpty() ? null : (Element)this.get(0);
    }

    @Override
    public void clear() {
        this.remove();
        super.clear();
    }

    public Elements(Collection<Element> elements) {
        super(elements);
    }

    public Elements(List<Element> elements) {
        super(elements);
    }

    public Elements(Element ... elements) {
        super(Arrays.asList(elements));
    }

    @Override
    public Element set(int index, Element element) {
        Validate.notNull((Object)element);
        Element old = super.set(index, element);
        old.replaceWith((Node)element);
        return old;
    }

    public boolean is(String query) {
        Element e;
        Evaluator eval = QueryParser.parse((String)query);
        Iterator iterator = this.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(e = (Element)iterator.next()).is(eval));
        return true;
    }

    public Elements before(String html) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.before(html);
        }
        return this;
    }

    public boolean hasAttr(String attributeKey) {
        Element element;
        Iterator iterator = this.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(element = (Element)iterator.next()).hasAttr(attributeKey));
        return true;
    }

    public @7\u015aCz Element last() {
        return this.isEmpty() ? null : (Element)this.get(this.size() - 1);
    }

    public Elements traverse(NodeVisitor nodeVisitor) {
        NodeTraversor.traverse((NodeVisitor)nodeVisitor, (Elements)this);
        return this;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean anyRemoved = false;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            Element el = (Element)it.next();
            if (c.contains(el)) continue;
            it.remove();
            anyRemoved = true;
        }
        return anyRemoved;
    }

    public Elements addClass(String className) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.addClass(className);
        }
        return this;
    }

    public Elements() {
    }

    public Elements filter(NodeFilter nodeFilter) {
        NodeTraversor.filter((NodeFilter)nodeFilter, (Elements)this);
        return this;
    }

    public boolean hasText() {
        Element element;
        Iterator iterator = this.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(element = (Element)iterator.next()).hasText());
        return true;
    }

    public List<String> eachAttr(String attributeKey) {
        ArrayList<String> attrs = new ArrayList<String>(this.size());
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            if (!element.hasAttr(attributeKey)) continue;
            attrs.add(element.attr(attributeKey));
        }
        return attrs;
    }

    public Elements toggleClass(String className) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.toggleClass(className);
        }
        return this;
    }

    public Elements append(String html) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.append(html);
        }
        return this;
    }

    public Elements prepend(String html) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.prepend(html);
        }
        return this;
    }

    public String outerHtml() {
        StringBuilder sb = StringUtil.borrowBuilder();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            if (sb.length() != 0) {
                sb.append("\n");
            }
            sb.append(element.outerHtml());
        }
        return StringUtil.releaseBuilder((StringBuilder)sb);
    }

    public Elements remove() {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.remove();
        }
        return this;
    }

    public Elements after(String html) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.after(html);
        }
        return this;
    }

    public boolean hasClass(String className) {
        Element element;
        Iterator iterator = this.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(element = (Element)iterator.next()).hasClass(className));
        return true;
    }

    public Elements empty() {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element element = (Element)iterator.next();
            element.empty();
        }
        return this;
    }

    public List<String> eachText() {
        ArrayList<String> texts = new ArrayList<String>(this.size());
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Element el = (Element)iterator.next();
            if (!el.hasText()) continue;
            texts.add(el.text());
        }
        return texts;
    }

    public Elements prevAll(String query) {
        return this.siblings(query, false, true);
    }

    public Elements prev() {
        return this.siblings(null, false, false);
    }
}
