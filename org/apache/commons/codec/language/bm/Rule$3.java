/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.3
implements Rule.RPattern {
    @Override
    public boolean isMatch(CharSequence input) {
        return input.length() == 0;
    }

    Rule.3() {
    }
}
