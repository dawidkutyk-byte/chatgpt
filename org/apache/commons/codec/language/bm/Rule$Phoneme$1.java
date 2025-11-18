/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.Comparator;
import org.apache.commons.codec.language.bm.Rule;

static final class Rule.Phoneme.1
implements Comparator<Rule.Phoneme> {
    @Override
    public int compare(Rule.Phoneme o1, Rule.Phoneme o2) {
        int i = 0;
        while (true) {
            if (i >= Rule.Phoneme.access$000((Rule.Phoneme)o1).length()) {
                if (Rule.Phoneme.access$000((Rule.Phoneme)o1).length() >= Rule.Phoneme.access$000((Rule.Phoneme)o2).length()) return 0;
                return -1;
            }
            if (i >= Rule.Phoneme.access$000((Rule.Phoneme)o2).length()) {
                return 1;
            }
            int c = Rule.Phoneme.access$000((Rule.Phoneme)o1).charAt(i) - Rule.Phoneme.access$000((Rule.Phoneme)o2).charAt(i);
            if (c != 0) {
                return c;
            }
            ++i;
        }
    }

    Rule.Phoneme.1() {
    }
}
