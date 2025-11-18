/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.select.Evaluator$IsNthOfType
 */
package org.jsoup.select;

import org.jsoup.select.Evaluator;

public static final class Evaluator.IsFirstOfType
extends Evaluator.IsNthOfType {
    public String toString() {
        return ":first-of-type";
    }

    public Evaluator.IsFirstOfType() {
        super(0, 1);
    }
}
