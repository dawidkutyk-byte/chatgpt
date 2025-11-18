/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Range
 */
package org.jsoup.nodes;

import org.jsoup.nodes.Range;

public static class Range.AttributeRange {
    static final Range.AttributeRange UntrackedAttr = new Range.AttributeRange(Range.Untracked, Range.Untracked);
    private final Range valueRange;
    private final Range nameRange;

    public Range.AttributeRange(Range nameRange, Range valueRange) {
        this.nameRange = nameRange;
        this.valueRange = valueRange;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Range.AttributeRange that = (Range.AttributeRange)o;
        if (this.nameRange.equals((Object)that.nameRange)) return this.valueRange.equals((Object)that.valueRange);
        return false;
    }

    public int hashCode() {
        int result = this.nameRange.hashCode();
        result = 31 * result + this.valueRange.hashCode();
        return result;
    }

    public Range valueRange() {
        return this.valueRange;
    }

    public Range nameRange() {
        return this.nameRange;
    }

    public String toString() {
        return this.nameRange().toString() + "=" + this.valueRange().toString();
    }
}
