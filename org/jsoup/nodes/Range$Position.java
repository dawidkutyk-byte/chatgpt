/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Range
 */
package org.jsoup.nodes;

import org.jsoup.nodes.Range;

/*
 * Exception performing whole class analysis ignored.
 */
public static class Range.Position {
    private final int pos;
    private final int lineNumber;
    private final int columnNumber;

    public int columnNumber() {
        return this.columnNumber;
    }

    static /* synthetic */ int access$000(Range.Position x0) {
        return x0.pos;
    }

    public int pos() {
        return this.pos;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Range.Position position = (Range.Position)o;
        if (this.pos != position.pos) {
            return false;
        }
        if (this.lineNumber == position.lineNumber) return this.columnNumber == position.columnNumber;
        return false;
    }

    public boolean isTracked() {
        return this != Range.access$100();
    }

    public String toString() {
        return this.lineNumber + "," + this.columnNumber + ":" + this.pos;
    }

    public Range.Position(int pos, int lineNumber, int columnNumber) {
        this.pos = pos;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public int lineNumber() {
        return this.lineNumber;
    }

    public int hashCode() {
        int result = this.pos;
        result = 31 * result + this.lineNumber;
        result = 31 * result + this.columnNumber;
        return result;
    }
}
