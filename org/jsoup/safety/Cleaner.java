/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.parser.ParseErrorList
 *  org.jsoup.parser.Parser
 *  org.jsoup.safety.Cleaner$CleaningVisitor
 *  org.jsoup.safety.Cleaner$ElementMeta
 *  org.jsoup.safety.Safelist
 *  org.jsoup.select.NodeTraversor
 *  org.jsoup.select.NodeVisitor
 */
package org.jsoup.safety;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/*
 * Exception performing whole class analysis ignored.
 */
public class Cleaner {
    private final Safelist safelist;

    static /* synthetic */ ElementMeta access$100(Cleaner x0, Element x1) {
        return x0.createSafeElement(x1);
    }

    private int copySafeNodes(Element source, Element dest) {
        CleaningVisitor cleaningVisitor = new CleaningVisitor(this, source, dest, null);
        NodeTraversor.traverse((NodeVisitor)cleaningVisitor, (Node)source);
        return CleaningVisitor.access$300((CleaningVisitor)cleaningVisitor);
    }

    public boolean isValidBodyHtml(String bodyHtml) {
        Document clean = Document.createShell((String)"");
        Document dirty = Document.createShell((String)"");
        ParseErrorList errorList = ParseErrorList.tracking((int)1);
        List nodes = Parser.parseFragment((String)bodyHtml, (Element)dirty.body(), (String)"", (ParseErrorList)errorList);
        dirty.body().insertChildren(0, (Collection)nodes);
        int numDiscarded = this.copySafeNodes(dirty.body(), clean.body());
        return numDiscarded == 0 && errorList.isEmpty();
    }

    public Document clean(Document dirtyDocument) {
        Validate.notNull((Object)dirtyDocument);
        Document clean = Document.createShell((String)dirtyDocument.baseUri());
        this.copySafeNodes(dirtyDocument.body(), clean.body());
        clean.outputSettings(dirtyDocument.outputSettings().clone());
        return clean;
    }

    public Cleaner(Safelist safelist) {
        Validate.notNull((Object)safelist);
        this.safelist = safelist;
    }

    private ElementMeta createSafeElement(Element sourceEl) {
        Element dest = sourceEl.shallowClone();
        String sourceTag = sourceEl.tagName();
        Attributes destAttrs = dest.attributes();
        dest.clearAttributes();
        int numDiscarded = 0;
        Attributes sourceAttrs = sourceEl.attributes();
        Iterator iterator = sourceAttrs.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                Attributes enforcedAttrs = this.safelist.getEnforcedAttributes(sourceTag);
                destAttrs.addAll(enforcedAttrs);
                dest.attributes().addAll(destAttrs);
                return new ElementMeta(dest, numDiscarded);
            }
            Attribute sourceAttr = (Attribute)iterator.next();
            if (this.safelist.isSafeAttribute(sourceTag, sourceEl, sourceAttr)) {
                destAttrs.put(sourceAttr);
                continue;
            }
            ++numDiscarded;
        }
    }

    static /* synthetic */ Safelist access$000(Cleaner x0) {
        return x0.safelist;
    }

    public boolean isValid(Document dirtyDocument) {
        Validate.notNull((Object)dirtyDocument);
        Document clean = Document.createShell((String)dirtyDocument.baseUri());
        int numDiscarded = this.copySafeNodes(dirtyDocument.body(), clean.body());
        return numDiscarded == 0 && dirtyDocument.head().childNodes().isEmpty();
    }
}
