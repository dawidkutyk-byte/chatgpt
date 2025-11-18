/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ssl.TrustStrategy
 */
package org.apache.http.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ssl.TrustStrategy;

static class SSLContextBuilder.TrustManagerDelegate
implements X509TrustManager {
    private final X509TrustManager trustManager;
    private final TrustStrategy trustStrategy;

    SSLContextBuilder.TrustManagerDelegate(X509TrustManager trustManager, TrustStrategy trustStrategy) {
        this.trustManager = trustManager;
        this.trustStrategy = trustStrategy;
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return this.trustManager.getAcceptedIssuers();
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (this.trustStrategy.isTrusted(chain, authType)) return;
        this.trustManager.checkServerTrusted(chain, authType);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        this.trustManager.checkClientTrusted(chain, authType);
    }
}
