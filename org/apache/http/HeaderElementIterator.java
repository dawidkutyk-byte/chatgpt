/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 */
package org.apache.http;

import java.util.Iterator;
import org.apache.http.HeaderElement;

public interface HeaderElementIterator
extends Iterator<Object> {
    @Override
    public boolean hasNext();

    public HeaderElement nextElement();
}
