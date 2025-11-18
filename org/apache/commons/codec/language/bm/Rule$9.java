/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.9
implements Rule.RPattern {
    final /* synthetic */ boolean val$shouldMatch;
    final /* synthetic */ String val$bContent;

    Rule.9(String string, boolean bl) {
        this.val$bContent = string;
        this.val$shouldMatch = bl;
    }

    @Override
    public boolean isMatch(CharSequence input) {
        return input.length() > 0 && Rule.access$300((CharSequence)this.val$bContent, (char)input.charAt(input.length() - 1)) == this.val$shouldMatch;
    }
}
