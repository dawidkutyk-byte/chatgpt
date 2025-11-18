/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.select.Evaluator
 */
package org.jsoup.select;

import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.select.Evaluator;

public static abstract class Evaluator.AttributeKeyPair
extends Evaluator {
    final String key;
    final String value;

    public Evaluator.AttributeKeyPair(String key, String value) {
        this(key, value, true);
    }

    public Evaluator.AttributeKeyPair(String key, String value, boolean trimValue) {
        boolean isStringLiteral;
        Validate.notEmpty((String)key);
        Validate.notEmpty((String)value);
        this.key = Normalizer.normalize((String)key);
        boolean bl = isStringLiteral = value.startsWith("'") && value.endsWith("'") || value.startsWith("\"") && value.endsWith("\"");
        if (isStringLiteral) {
            value = value.substring(1, value.length() - 1);
        }
        this.value = trimValue ? Normalizer.normalize((String)value) : Normalizer.normalize((String)value, (boolean)isStringLiteral);
    }
}
