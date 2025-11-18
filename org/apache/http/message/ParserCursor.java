/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.message;

public class ParserCursor {
    private int pos;
    private final int lowerBound;
    private final int upperBound;

    public ParserCursor(int lowerBound, int upperBound) {
        if (lowerBound < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        }
        if (lowerBound > upperBound) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.pos = lowerBound;
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        buffer.append(Integer.toString(this.lowerBound));
        buffer.append('>');
        buffer.append(Integer.toString(this.pos));
        buffer.append('>');
        buffer.append(Integer.toString(this.upperBound));
        buffer.append(']');
        return buffer.toString();
    }

    public int getPos() {
        return this.pos;
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public void updatePos(int pos) {
        if (pos < this.lowerBound) {
            throw new IndexOutOfBoundsException("pos: " + pos + " < lowerBound: " + this.lowerBound);
        }
        if (pos > this.upperBound) {
            throw new IndexOutOfBoundsException("pos: " + pos + " > upperBound: " + this.upperBound);
        }
        this.pos = pos;
    }
}
