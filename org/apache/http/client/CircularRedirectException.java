/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.RedirectException
 */
package org.apache.http.client;

import org.apache.http.client.RedirectException;

public class CircularRedirectException
extends RedirectException {
    private static final long serialVersionUID = 6830063487001091803L;

    public CircularRedirectException() {
    }

    public CircularRedirectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircularRedirectException(String message) {
        super(message);
    }
}
