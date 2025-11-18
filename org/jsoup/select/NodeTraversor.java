/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.select.Elements
 *  org.jsoup.select.NodeFilter
 *  org.jsoup.select.NodeFilter$FilterResult
 *  org.jsoup.select.NodeVisitor
 */
package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeVisitor;

public class NodeTraversor {
    public static void filter(NodeFilter filter, Elements elements) {
        Element el;
        Validate.notNull((Object)filter);
        Validate.notNull((Object)elements);
        Iterator iterator = elements.iterator();
        do {
            if (!iterator.hasNext()) return;
        } while (NodeTraversor.filter(filter, (Node)(el = (Element)iterator.next())) != NodeFilter.FilterResult.STOP);
    }

    public static void traverse(NodeVisitor visitor, Node root) {
        Validate.notNull((Object)visitor);
        Validate.notNull((Object)root);
        Node node = root;
        int depth = 0;
        while (node != null) {
            Node parent = node.parentNode();
            int origSize = parent != null ? parent.childNodeSize() : 0;
            Node next = node.nextSibling();
            visitor.head(node, depth);
            if (parent != null && !node.hasParent()) {
                if (origSize == parent.childNodeSize()) {
                    node = parent.childNode(node.siblingIndex());
                } else {
                    node = next;
                    if (node != null) continue;
                    node = parent;
                    --depth;
                    continue;
                }
            }
            if (node.childNodeSize() > 0) {
                node = node.childNode(0);
                ++depth;
                continue;
            }
            while (true) {
                assert (node != null);
                if (node.nextSibling() != null || depth <= 0) break;
                visitor.tail(node, depth);
                node = node.parentNode();
                --depth;
            }
            visitor.tail(node, depth);
            if (node == root) {
                return;
            }
            node = node.nextSibling();
        }
    }

    public static void traverse(NodeVisitor visitor, Elements elements) {
        Validate.notNull((Object)visitor);
        Validate.notNull((Object)elements);
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element el = (Element)iterator.next();
            NodeTraversor.traverse(visitor, (Node)el);
        }
    }

    public static NodeFilter.FilterResult filter(NodeFilter filter, Node root) {
        Node node = root;
        int depth = 0;
        while (node != null) {
            Node prev;
            NodeFilter.FilterResult result = filter.head(node, depth);
            if (result == NodeFilter.FilterResult.STOP) {
                return result;
            }
            if (result == NodeFilter.FilterResult.CONTINUE && node.childNodeSize() > 0) {
                node = node.childNode(0);
                ++depth;
                continue;
            }
            while (true) {
                assert (node != null);
                if (node.nextSibling() != null || depth <= 0) break;
                if ((result == NodeFilter.FilterResult.CONTINUE || result == NodeFilter.FilterResult.SKIP_CHILDREN) && (result = filter.tail(node, depth)) == NodeFilter.FilterResult.STOP) {
                    return result;
                }
                prev = node;
                node = node.parentNode();
                --depth;
                if (result == NodeFilter.FilterResult.REMOVE) {
                    prev.remove();
                }
                result = NodeFilter.FilterResult.CONTINUE;
            }
            if ((result == NodeFilter.FilterResult.CONTINUE || result == NodeFilter.FilterResult.SKIP_CHILDREN) && (result = filter.tail(node, depth)) == NodeFilter.FilterResult.STOP) {
                return result;
            }
            if (node == root) {
                return result;
            }
            prev = node;
            node = node.nextSibling();
            if (result != NodeFilter.FilterResult.REMOVE) continue;
            prev.remove();
        }
        return NodeFilter.FilterResult.CONTINUE;
    }
}
