/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$Base
 *  org.jsoup.nodes.Document
 */
package org.jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public static interface Connection.Response
extends Connection.Base<Connection.Response> {
    public Connection.Response charset(String var1);

    public Document parse() throws IOException;

    public Connection.Response bufferUp();

    public String statusMessage();

    public @7\u015aCz String contentType();

    public @7\u015aCz String charset();

    public byte[] bodyAsBytes();

    public BufferedInputStream bodyStream();

    public int statusCode();

    public String body();
}
