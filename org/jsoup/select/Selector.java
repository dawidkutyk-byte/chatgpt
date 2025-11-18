/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Collector
 *  org.jsoup.select.Elements
 *  org.jsoup.select.Evaluator
 *  org.jsoup.select.QueryParser
 */
package org.jsoup.select;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.QueryParser;

public class Selector {
    private Selector() {
    }

    static Elements filterOut(Collection<Element> elements, Collection<Element> outs) {
        Elements output = new Elements();
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element el = iterator.next();
            boolean found = false;
            for (Element out : outs) {
                if (!el.equals((Object)out)) continue;
                found = true;
                break;
            }
            if (found) continue;
            output.add((Object)el);
        }
        return output;
    }

    public static Elements select(Evaluator evaluator, Element root) {
        Validate.notNull((Object)evaluator);
        Validate.notNull((Object)root);
        return Collector.collect((Evaluator)evaluator, (Element)root);
    }

    public static Elements select(String query, Element root) {
        Validate.notEmpty((String)query);
        return Selector.select(QueryParser.parse((String)query), root);
    }

    public static Elements select(String query, Iterable<Element> roots) {
        Validate.notEmpty((String)query);
        Validate.notNull(roots);
        Evaluator evaluator = QueryParser.parse((String)query);
        Elements elements = new Elements();
        IdentityHashMap<Element, Boolean> seenElements = new IdentityHashMap<Element, Boolean>();
        Iterator<Element> iterator = roots.iterator();
        block0: while (iterator.hasNext()) {
            Element root = iterator.next();
            Elements found = Selector.select(evaluator, root);
            Iterator iterator2 = found.iterator();
            while (true) {
                if (!iterator2.hasNext()) continue block0;
                Element el = (Element)iterator2.next();
                if (seenElements.put(el, Boolean.TRUE) != null) continue;
                elements.add((Object)el);
            }
            break;
        }
        return elements;
    }

    public static @7\u015aCz Element selectFirst(String cssQuery, Element root) {
        Validate.notEmpty((String)cssQuery);
        return Collector.findFirst((Evaluator)QueryParser.parse((String)cssQuery), (Element)root);
    }
}
