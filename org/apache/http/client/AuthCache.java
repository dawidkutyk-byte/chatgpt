/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.auth.AuthScheme
 */
package org.apache.http.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;

public interface AuthCache {
    public void clear();

    public void remove(HttpHost var1);

    public AuthScheme get(HttpHost var1);

    public void put(HttpHost var1, AuthScheme var2);
}
