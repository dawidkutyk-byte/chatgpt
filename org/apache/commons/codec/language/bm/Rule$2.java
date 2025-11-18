/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.language.bm.Rule;

static final class Rule.2
extends Rule {
    final /* synthetic */ String val$rCon;
    final /* synthetic */ int val$cLine;
    private final int myLine;
    final /* synthetic */ String val$location;
    final /* synthetic */ String val$pat;
    final /* synthetic */ String val$lCon;
    private final String loc;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rule");
        sb.append("{line=").append(this.myLine);
        sb.append(", loc='").append(this.loc).append('\'');
        sb.append(", pat='").append(this.val$pat).append('\'');
        sb.append(", lcon='").append(this.val$lCon).append('\'');
        sb.append(", rcon='").append(this.val$rCon).append('\'');
        sb.append('}');
        return sb.toString();
    }

    Rule.2(String pattern, String lContext, String rContext, Rule.PhonemeExpr phoneme, int n, String string, String string2, String string3, String string4) {
        this.val$cLine = n;
        this.val$location = string;
        this.val$pat = string2;
        this.val$lCon = string3;
        this.val$rCon = string4;
        super(pattern, lContext, rContext, phoneme);
        this.myLine = this.val$cLine;
        this.loc = this.val$location;
    }
}
