/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 */
package org.apache.http;

import java.io.IOException;
import org.apache.http.HttpException;

public class ConnectionClosedException
extends IOException {
    private static final long serialVersionUID = 617550366255636674L;

    public ConnectionClosedException(String message) {
        super(HttpException.clean((String)message));
    }

    public ConnectionClosedException() {
        super("Connection is closed");
    }

    public ConnectionClosedException(String format, Object ... args) {
        super(HttpException.clean((String)String.format(format, args)));
    }
}
