/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 */
package org.apache.http;

import java.util.Iterator;
import org.apache.http.Header;

public interface HeaderIterator
extends Iterator<Object> {
    @Override
    public boolean hasNext();

    public Header nextHeader();
}
