/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.params.HttpAbstractParamBean
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 */
package org.apache.http.params;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

@Deprecated
public class HttpProtocolParamBean
extends HttpAbstractParamBean {
    public void setUseExpectContinue(boolean useExpectContinue) {
        HttpProtocolParams.setUseExpectContinue((HttpParams)this.params, (boolean)useExpectContinue);
    }

    public void setContentCharset(String contentCharset) {
        HttpProtocolParams.setContentCharset((HttpParams)this.params, (String)contentCharset);
    }

    public HttpProtocolParamBean(HttpParams params) {
        super(params);
    }

    public void setUserAgent(String userAgent) {
        HttpProtocolParams.setUserAgent((HttpParams)this.params, (String)userAgent);
    }

    public void setHttpElementCharset(String httpElementCharset) {
        HttpProtocolParams.setHttpElementCharset((HttpParams)this.params, (String)httpElementCharset);
    }

    public void setVersion(HttpVersion version) {
        HttpProtocolParams.setVersion((HttpParams)this.params, (ProtocolVersion)version);
    }
}
