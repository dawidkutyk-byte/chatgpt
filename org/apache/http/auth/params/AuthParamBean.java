/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.params.AuthParams
 *  org.apache.http.params.HttpAbstractParamBean
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.auth.params;

import org.apache.http.auth.params.AuthParams;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@Deprecated
public class AuthParamBean
extends HttpAbstractParamBean {
    public void setCredentialCharset(String charset) {
        AuthParams.setCredentialCharset((HttpParams)this.params, (String)charset);
    }

    public AuthParamBean(HttpParams params) {
        super(params);
    }
}
