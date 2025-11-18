/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Rule;

static final class Rule.10
implements Rule.RPattern {
    final /* synthetic */ String val$regex;
    Pattern pattern;

    @Override
    public boolean isMatch(CharSequence input) {
        Matcher matcher = this.pattern.matcher(input);
        return matcher.find();
    }

    Rule.10(String string) {
        this.val$regex = string;
        this.pattern = Pattern.compile(this.val$regex);
    }
}
