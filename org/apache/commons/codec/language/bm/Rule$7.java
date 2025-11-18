/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.7
implements Rule.RPattern {
    final /* synthetic */ boolean val$shouldMatch;
    final /* synthetic */ String val$bContent;

    Rule.7(String string, boolean bl) {
        this.val$bContent = string;
        this.val$shouldMatch = bl;
    }

    @Override
    public boolean isMatch(CharSequence input) {
        return input.length() == 1 && Rule.access$300((CharSequence)this.val$bContent, (char)input.charAt(0)) == this.val$shouldMatch;
    }
}
