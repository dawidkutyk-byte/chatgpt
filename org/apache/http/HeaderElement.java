/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.NameValuePair
 */
package org.apache.http;

import org.apache.http.NameValuePair;

public interface HeaderElement {
    public NameValuePair getParameterByName(String var1);

    public String getValue();

    public int getParameterCount();

    public NameValuePair[] getParameters();

    public NameValuePair getParameter(int var1);

    public String getName();
}
