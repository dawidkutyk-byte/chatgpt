/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.ChangeNotifyingArrayList
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 */
package org.jsoup.nodes;

import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

private static final class Element.NodeList
extends ChangeNotifyingArrayList<Node> {
    private final Element owner;

    Element.NodeList(Element owner, int initialCapacity) {
        super(initialCapacity);
        this.owner = owner;
    }

    public void onContentsChanged() {
        this.owner.nodelistChanged();
    }
}
