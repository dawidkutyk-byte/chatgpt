/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.AuthScope
 *  org.apache.http.auth.Credentials
 */
package org.apache.http.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;

public interface CredentialsProvider {
    public void clear();

    public void setCredentials(AuthScope var1, Credentials var2);

    public Credentials getCredentials(AuthScope var1);
}
