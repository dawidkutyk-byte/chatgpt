/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.8
implements Rule.RPattern {
    final /* synthetic */ String val$bContent;
    final /* synthetic */ boolean val$shouldMatch;

    Rule.8(String string, boolean bl) {
        this.val$bContent = string;
        this.val$shouldMatch = bl;
    }

    @Override
    public boolean isMatch(CharSequence input) {
        return input.length() > 0 && Rule.access$300((CharSequence)this.val$bContent, (char)input.charAt(0)) == this.val$shouldMatch;
    }
}
