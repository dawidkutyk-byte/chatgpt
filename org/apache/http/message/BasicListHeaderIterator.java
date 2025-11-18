/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.message;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

public class BasicListHeaderIterator
implements HeaderIterator {
    protected int currentIndex;
    protected final List<Header> allHeaders;
    protected String headerName;
    protected int lastIndex;

    public void remove() throws UnsupportedOperationException {
        Asserts.check((this.lastIndex >= 0 ? 1 : 0) != 0, (String)"No header to remove");
        this.allHeaders.remove(this.lastIndex);
        this.lastIndex = -1;
        --this.currentIndex;
    }

    public final Object next() throws NoSuchElementException {
        return this.nextHeader();
    }

    public boolean hasNext() {
        return this.currentIndex >= 0;
    }

    public Header nextHeader() throws NoSuchElementException {
        int current = this.currentIndex;
        if (current < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        this.lastIndex = current;
        this.currentIndex = this.findNext(current);
        return this.allHeaders.get(current);
    }

    protected int findNext(int pos) {
        int from = pos;
        if (from < -1) {
            return -1;
        }
        int to = this.allHeaders.size() - 1;
        boolean found = false;
        while (!found && from < to) {
            found = this.filterHeader(++from);
        }
        return found ? from : -1;
    }

    protected boolean filterHeader(int index) {
        if (this.headerName == null) {
            return true;
        }
        String name = this.allHeaders.get(index).getName();
        return this.headerName.equalsIgnoreCase(name);
    }

    public BasicListHeaderIterator(List<Header> headers, String name) {
        this.allHeaders = (List)Args.notNull(headers, (String)"Header list");
        this.headerName = name;
        this.currentIndex = this.findNext(-1);
        this.lastIndex = -1;
    }
}
