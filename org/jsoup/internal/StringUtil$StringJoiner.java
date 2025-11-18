/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 */
package org.jsoup.internal;

import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;

/*
 * Exception performing whole class analysis ignored.
 */
public static class StringUtil.StringJoiner {
    boolean first = true;
    final String separator;
    @7\u015aCz StringBuilder sb = StringUtil.borrowBuilder();

    public StringUtil.StringJoiner add(Object stringy) {
        Validate.notNull((Object)this.sb);
        if (!this.first) {
            this.sb.append(this.separator);
        }
        this.sb.append(stringy);
        this.first = false;
        return this;
    }

    public String complete() {
        String string = StringUtil.releaseBuilder((StringBuilder)this.sb);
        this.sb = null;
        return string;
    }

    public StringUtil.StringJoiner(String separator) {
        this.separator = separator;
    }

    public StringUtil.StringJoiner append(Object stringy) {
        Validate.notNull((Object)this.sb);
        this.sb.append(stringy);
        return this;
    }
}
