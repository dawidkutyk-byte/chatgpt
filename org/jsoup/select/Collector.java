/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Elements
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import java.util.Optional;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

public class Collector {
    public static Elements collect(Evaluator eval, Element root) {
        eval.reset();
        return root.stream().filter(eval.asPredicate(root)).collect(Collectors.toCollection(Elements::new));
    }

    private Collector() {
    }

    public static @7\u015aCz Element findFirst(Evaluator eval, Element root) {
        eval.reset();
        Optional<Object> first = root.stream().filter(eval.asPredicate(root)).findFirst();
        return first.orElse(null);
    }
}
