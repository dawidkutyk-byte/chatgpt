/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 */
package org.apache.http;

import org.apache.http.HttpException;

public class ProtocolException
extends HttpException {
    private static final long serialVersionUID = -2143571074341228994L;

    public ProtocolException(String message) {
        super(message);
    }

    public ProtocolException() {
    }

    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
