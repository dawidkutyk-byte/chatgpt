/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.select.Evaluator;

public static abstract class Evaluator.IndexEvaluator
extends Evaluator {
    final int index;

    public Evaluator.IndexEvaluator(int index) {
        this.index = index;
    }
}
