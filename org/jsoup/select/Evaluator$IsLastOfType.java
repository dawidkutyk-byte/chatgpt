/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.select.Evaluator$IsNthLastOfType
 */
package org.jsoup.select;

import org.jsoup.select.Evaluator;

public static final class Evaluator.IsLastOfType
extends Evaluator.IsNthLastOfType {
    public Evaluator.IsLastOfType() {
        super(0, 1);
    }

    public String toString() {
        return ":last-of-type";
    }
}
