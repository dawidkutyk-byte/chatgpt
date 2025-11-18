/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.handshake.HandshakeBuilder
 */
package org.java_websocket.handshake;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import org.java_websocket.handshake.HandshakeBuilder;

public class HandshakedataImpl1
implements HandshakeBuilder {
    private byte[] content;
    private TreeMap<String, String> map = new TreeMap(String.CASE_INSENSITIVE_ORDER);

    public void put(String name, String value) {
        this.map.put(name, value);
    }

    public boolean hasFieldValue(String name) {
        return this.map.containsKey(name);
    }

    public String getFieldValue(String name) {
        String s = this.map.get(name);
        if (s != null) return s;
        return "";
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Iterator<String> iterateHttpFields() {
        return Collections.unmodifiableSet(this.map.keySet()).iterator();
    }

    public byte[] getContent() {
        return this.content;
    }
}
