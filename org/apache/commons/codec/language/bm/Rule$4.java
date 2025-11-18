/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.4
implements Rule.RPattern {
    final /* synthetic */ String val$content;

    Rule.4(String string) {
        this.val$content = string;
    }

    @Override
    public boolean isMatch(CharSequence input) {
        return input.equals(this.val$content);
    }
}
