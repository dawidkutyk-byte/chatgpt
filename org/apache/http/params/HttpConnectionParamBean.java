/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.params.HttpAbstractParamBean
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

@Deprecated
public class HttpConnectionParamBean
extends HttpAbstractParamBean {
    public HttpConnectionParamBean(HttpParams params) {
        super(params);
    }

    public void setConnectionTimeout(int connectionTimeout) {
        HttpConnectionParams.setConnectionTimeout((HttpParams)this.params, (int)connectionTimeout);
    }

    public void setStaleCheckingEnabled(boolean staleCheckingEnabled) {
        HttpConnectionParams.setStaleCheckingEnabled((HttpParams)this.params, (boolean)staleCheckingEnabled);
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        HttpConnectionParams.setTcpNoDelay((HttpParams)this.params, (boolean)tcpNoDelay);
    }

    public void setSocketBufferSize(int socketBufferSize) {
        HttpConnectionParams.setSocketBufferSize((HttpParams)this.params, (int)socketBufferSize);
    }

    public void setLinger(int linger) {
        HttpConnectionParams.setLinger((HttpParams)this.params, (int)linger);
    }

    public void setSoTimeout(int soTimeout) {
        HttpConnectionParams.setSoTimeout((HttpParams)this.params, (int)soTimeout);
    }
}
