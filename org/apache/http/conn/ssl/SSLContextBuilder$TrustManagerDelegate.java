/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ssl.TrustStrategy
 */
package org.apache.http.conn.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.TrustStrategy;

static class SSLContextBuilder.TrustManagerDelegate
implements X509TrustManager {
    private final TrustStrategy trustStrategy;
    private final X509TrustManager trustManager;

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return this.trustManager.getAcceptedIssuers();
    }

    SSLContextBuilder.TrustManagerDelegate(X509TrustManager trustManager, TrustStrategy trustStrategy) {
        this.trustManager = trustManager;
        this.trustStrategy = trustStrategy;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        this.trustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (this.trustStrategy.isTrusted(chain, authType)) return;
        this.trustManager.checkServerTrusted(chain, authType);
    }
}
