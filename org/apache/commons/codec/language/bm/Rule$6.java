/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.6
implements Rule.RPattern {
    final /* synthetic */ String val$content;

    @Override
    public boolean isMatch(CharSequence input) {
        return Rule.access$200((CharSequence)input, (CharSequence)this.val$content);
    }

    Rule.6(String string) {
        this.val$content = string;
    }
}
