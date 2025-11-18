/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.DataNode
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.TextNode
 *  org.jsoup.safety.Cleaner
 *  org.jsoup.safety.Cleaner$ElementMeta
 *  org.jsoup.select.NodeVisitor
 */
package org.jsoup.safety;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.safety.Cleaner;
import org.jsoup.select.NodeVisitor;

/*
 * Exception performing whole class analysis ignored.
 */
private final class Cleaner.CleaningVisitor
implements NodeVisitor {
    private final Element root;
    private int numDiscarded = 0;
    private Element destination;

    public void tail(Node source, int depth) {
        if (!(source instanceof Element)) return;
        if (!Cleaner.access$000((Cleaner)Cleaner.this).isSafeTag(source.normalName())) return;
        this.destination = this.destination.parent();
    }

    static /* synthetic */ int access$300(Cleaner.CleaningVisitor x0) {
        return x0.numDiscarded;
    }

    private Cleaner.CleaningVisitor(Element root, Element destination) {
        this.root = root;
        this.destination = destination;
    }

    public void head(Node source, int depth) {
        if (source instanceof Element) {
            Element sourceEl = (Element)source;
            if (Cleaner.access$000((Cleaner)Cleaner.this).isSafeTag(sourceEl.normalName())) {
                Cleaner.ElementMeta meta = Cleaner.access$100((Cleaner)Cleaner.this, (Element)sourceEl);
                Element destChild = meta.el;
                this.destination.appendChild((Node)destChild);
                this.numDiscarded += meta.numAttribsDiscarded;
                this.destination = destChild;
            } else {
                if (source == this.root) return;
                ++this.numDiscarded;
            }
        } else if (source instanceof TextNode) {
            TextNode sourceText = (TextNode)source;
            TextNode destText = new TextNode(sourceText.getWholeText());
            this.destination.appendChild((Node)destText);
        } else if (source instanceof DataNode && Cleaner.access$000((Cleaner)Cleaner.this).isSafeTag(source.parent().normalName())) {
            DataNode sourceData = (DataNode)source;
            DataNode destData = new DataNode(sourceData.getWholeData());
            this.destination.appendChild((Node)destData);
        } else {
            ++this.numDiscarded;
        }
    }
}
