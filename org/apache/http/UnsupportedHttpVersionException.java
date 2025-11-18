/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ProtocolException
 */
package org.apache.http;

import org.apache.http.ProtocolException;

public class UnsupportedHttpVersionException
extends ProtocolException {
    private static final long serialVersionUID = -1348448090193107031L;

    public UnsupportedHttpVersionException() {
    }

    public UnsupportedHttpVersionException(String message) {
        super(message);
    }
}
