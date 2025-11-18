/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Element
 *  org.jsoup.parser.Tag
 */
package org.jsoup.nodes;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class PseudoTextElement
extends Element {
    public PseudoTextElement(Tag tag, String baseUri, Attributes attributes) {
        super(tag, baseUri, attributes);
    }

    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) {
    }

    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) {
    }
}
