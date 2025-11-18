/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.Range$Position
 */
package org.jsoup.nodes;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.Range;

/*
 * Exception performing whole class analysis ignored.
 */
public class Range {
    private final Position start;
    static final Range Untracked;
    private static final Position UntrackedPos;
    private final Position end;

    public String toString() {
        return this.start + "-" + this.end;
    }

    public int endPos() {
        return Position.access$000((Position)this.end);
    }

    public int hashCode() {
        int result = this.start.hashCode();
        result = 31 * result + this.end.hashCode();
        return result;
    }

    public boolean isTracked() {
        return this != Untracked;
    }

    static Range of(Node node, boolean start) {
        String key;
        String string = key = start ? "jsoup.start" : "jsoup.end";
        if (node.hasAttributes()) Object range;
        return (range = node.attributes().userData(key)) != null ? (Range)range : Untracked;
        return Untracked;
    }

    public boolean isImplicit() {
        if (this.isTracked()) return this.start.equals((Object)this.end);
        return false;
    }

    static {
        UntrackedPos = new Position(-1, -1, -1);
        Untracked = new Range(UntrackedPos, UntrackedPos);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Range range = (Range)o;
        if (this.start.equals((Object)range.start)) return this.end.equals((Object)range.end);
        return false;
    }

    public int startPos() {
        return Position.access$000((Position)this.start);
    }

    static /* synthetic */ Position access$100() {
        return UntrackedPos;
    }

    public Range(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    @Deprecated
    public void track(Node node, boolean start) {
    }

    public Position start() {
        return this.start;
    }

    public Position end() {
        return this.end;
    }
}
