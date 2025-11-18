/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.string.MultiLineStringExaminer
 *  net.kyori.examination.string.StringExaminer
 */
package net.kyori.examination.string;

import net.kyori.examination.string.MultiLineStringExaminer;
import net.kyori.examination.string.StringExaminer;

private static final class MultiLineStringExaminer.Instances {
    static final MultiLineStringExaminer SIMPLE_ESCAPING = new MultiLineStringExaminer(StringExaminer.simpleEscaping());

    private MultiLineStringExaminer.Instances() {
    }
}
