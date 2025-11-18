/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Evaluator;

public static abstract class Evaluator.CssNthEvaluator
extends Evaluator {
    protected final int a;
    protected final int b;

    protected abstract int calculatePosition(Element var1, Element var2);

    public Evaluator.CssNthEvaluator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public boolean matches(Element root, Element element) {
        Element p = element.parent();
        if (p == null) return false;
        if (p instanceof Document) {
            return false;
        }
        int pos = this.calculatePosition(root, element);
        if (this.a != 0) return (pos - this.b) * this.a >= 0 && (pos - this.b) % this.a == 0;
        return pos == this.b;
    }

    protected abstract String getPseudoClass();

    public Evaluator.CssNthEvaluator(int b) {
        this(0, b);
    }

    public String toString() {
        if (this.a == 0) {
            return String.format(":%s(%d)", this.getPseudoClass(), this.b);
        }
        if (this.b != 0) return String.format(":%s(%dn%+d)", this.getPseudoClass(), this.a, this.b);
        return String.format(":%s(%dn)", this.getPseudoClass(), this.a);
    }
}
