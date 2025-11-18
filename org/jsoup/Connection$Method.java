/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup;

public static enum Connection.Method {
    GET(false),
    POST(true),
    PUT(true),
    DELETE(true),
    PATCH(true),
    HEAD(false),
    OPTIONS(false),
    TRACE(false);

    private final boolean hasBody;

    public final boolean hasBody() {
        return this.hasBody;
    }

    private Connection.Method(boolean hasBody) {
        this.hasBody = hasBody;
    }
}
