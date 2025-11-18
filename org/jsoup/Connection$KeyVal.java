/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 */
package org.jsoup;

import java.io.InputStream;

public static interface Connection.KeyVal {
    public String key();

    public @7\u015aCz String contentType();

    public String value();

    public Connection.KeyVal key(String var1);

    public Connection.KeyVal contentType(String var1);

    public Connection.KeyVal value(String var1);

    public boolean hasInputStream();

    public Connection.KeyVal inputStream(InputStream var1);

    public @7\u015aCz InputStream inputStream();
}
