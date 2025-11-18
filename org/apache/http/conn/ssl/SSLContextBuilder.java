/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ssl.PrivateKeyStrategy
 *  org.apache.http.conn.ssl.SSLContextBuilder$KeyManagerDelegate
 *  org.apache.http.conn.ssl.SSLContextBuilder$TrustManagerDelegate
 *  org.apache.http.conn.ssl.TrustStrategy
 */
package org.apache.http.conn.ssl;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.PrivateKeyStrategy;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;

@Deprecated
public class SSLContextBuilder {
    private String protocol;
    private SecureRandom secureRandom;
    static final String TLS = "TLS";
    private final Set<KeyManager> keymanagers = new LinkedHashSet<KeyManager>();
    static final String SSL = "SSL";
    private final Set<TrustManager> trustmanagers = new LinkedHashSet<TrustManager>();

    public SSLContextBuilder loadTrustMaterial(KeyStore truststore) throws NoSuchAlgorithmException, KeyStoreException {
        return this.loadTrustMaterial(truststore, null);
    }

    public SSLContextBuilder useProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public SSLContextBuilder useTLS() {
        this.protocol = TLS;
        return this;
    }

    public SSLContextBuilder useSSL() {
        this.protocol = SSL;
        return this;
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keystore, char[] keyPassword) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        this.loadKeyMaterial(keystore, keyPassword, null);
        return this;
    }

    public SSLContextBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore truststore, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmfactory.init(truststore);
        TrustManager[] tms = tmfactory.getTrustManagers();
        if (tms == null) return this;
        if (trustStrategy != null) {
            for (int i = 0; i < tms.length; ++i) {
                TrustManager tm = tms[i];
                if (!(tm instanceof X509TrustManager)) continue;
                tms[i] = new TrustManagerDelegate((X509TrustManager)tm, trustStrategy);
            }
        }
        TrustManager[] arr$ = tms;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            TrustManager tm = arr$[i$];
            this.trustmanagers.add(tm);
            ++i$;
        }
        return this;
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keystore, char[] keyPassword, PrivateKeyStrategy aliasStrategy) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmfactory.init(keystore, keyPassword);
        KeyManager[] kms = kmfactory.getKeyManagers();
        if (kms == null) return this;
        if (aliasStrategy != null) {
            for (int i = 0; i < kms.length; ++i) {
                KeyManager km = kms[i];
                if (!(km instanceof X509KeyManager)) continue;
                kms[i] = new KeyManagerDelegate((X509KeyManager)km, aliasStrategy);
            }
        }
        KeyManager[] arr$ = kms;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            KeyManager km = arr$[i$];
            this.keymanagers.add(km);
            ++i$;
        }
        return this;
    }

    public SSLContext build() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslcontext = SSLContext.getInstance(this.protocol != null ? this.protocol : TLS);
        sslcontext.init(!this.keymanagers.isEmpty() ? this.keymanagers.toArray(new KeyManager[this.keymanagers.size()]) : null, !this.trustmanagers.isEmpty() ? this.trustmanagers.toArray(new TrustManager[this.trustmanagers.size()]) : null, this.secureRandom);
        return sslcontext;
    }
}
