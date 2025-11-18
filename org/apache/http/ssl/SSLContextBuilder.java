/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ssl.PrivateKeyStrategy
 *  org.apache.http.ssl.SSLContextBuilder$KeyManagerDelegate
 *  org.apache.http.ssl.SSLContextBuilder$TrustManagerDelegate
 *  org.apache.http.ssl.TrustStrategy
 *  org.apache.http.util.Args
 */
package org.apache.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.Args;

public class SSLContextBuilder {
    static final String TLS = "TLS";
    private String keyManagerFactoryAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
    private String keyStoreType = KeyStore.getDefaultType();
    private final Set<KeyManager> keyManagers;
    private String protocol;
    private SecureRandom secureRandom;
    private final Set<TrustManager> trustManagers;
    private String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
    private Provider provider;

    public SSLContextBuilder() {
        this.keyManagers = new LinkedHashSet<KeyManager>();
        this.trustManagers = new LinkedHashSet<TrustManager>();
    }

    public SSLContextBuilder setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
        return this;
    }

    public SSLContext build() throws KeyManagementException, NoSuchAlgorithmException {
        String protocolStr = this.protocol != null ? this.protocol : TLS;
        SSLContext sslContext = this.provider != null ? SSLContext.getInstance(protocolStr, this.provider) : SSLContext.getInstance(protocolStr);
        this.initSSLContext(sslContext, this.keyManagers, this.trustManagers, this.secureRandom);
        return sslContext;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SSLContextBuilder loadKeyMaterial(File file, char[] storePassword, char[] keyPassword, PrivateKeyStrategy aliasStrategy) throws IOException, UnrecoverableKeyException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        Args.notNull((Object)file, (String)"Keystore file");
        KeyStore identityStore = KeyStore.getInstance(this.keyStoreType);
        FileInputStream inStream = new FileInputStream(file);
        try {
            identityStore.load(inStream, storePassword);
        }
        finally {
            inStream.close();
        }
        return this.loadKeyMaterial(identityStore, keyPassword, aliasStrategy);
    }

    public SSLContextBuilder loadTrustMaterial(URL url, char[] storePassword) throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException {
        return this.loadTrustMaterial(url, storePassword, null);
    }

    public SSLContextBuilder loadTrustMaterial(KeyStore truststore, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(this.trustManagerFactoryAlgorithm == null ? TrustManagerFactory.getDefaultAlgorithm() : this.trustManagerFactoryAlgorithm);
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
            this.trustManagers.add(tm);
            ++i$;
        }
        return this;
    }

    public SSLContextBuilder loadTrustMaterial(File file, char[] storePassword) throws IOException, NoSuchAlgorithmException, KeyStoreException, CertificateException {
        return this.loadTrustMaterial(file, storePassword, null);
    }

    public SSLContextBuilder loadTrustMaterial(File file) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException {
        return this.loadTrustMaterial(file, null);
    }

    public SSLContextBuilder setProvider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keystore, char[] keyPassword, PrivateKeyStrategy aliasStrategy) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(this.keyManagerFactoryAlgorithm == null ? KeyManagerFactory.getDefaultAlgorithm() : this.keyManagerFactoryAlgorithm);
        kmfactory.init(keystore, keyPassword);
        KeyManager[] kms = kmfactory.getKeyManagers();
        if (kms == null) return this;
        if (aliasStrategy != null) {
            for (int i = 0; i < kms.length; ++i) {
                KeyManager km = kms[i];
                if (!(km instanceof X509ExtendedKeyManager)) continue;
                kms[i] = new KeyManagerDelegate((X509ExtendedKeyManager)km, aliasStrategy);
            }
        }
        KeyManager[] arr$ = kms;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            KeyManager km = arr$[i$];
            this.keyManagers.add(km);
            ++i$;
        }
        return this;
    }

    public SSLContextBuilder setTrustManagerFactoryAlgorithm(String trustManagerFactoryAlgorithm) {
        this.trustManagerFactoryAlgorithm = trustManagerFactoryAlgorithm;
        return this;
    }

    public SSLContextBuilder setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SSLContextBuilder loadKeyMaterial(URL url, char[] storePassword, char[] keyPassword, PrivateKeyStrategy aliasStrategy) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, IOException, CertificateException {
        Args.notNull((Object)url, (String)"Keystore URL");
        KeyStore identityStore = KeyStore.getInstance(this.keyStoreType);
        InputStream inStream = url.openStream();
        try {
            identityStore.load(inStream, storePassword);
        }
        finally {
            inStream.close();
        }
        return this.loadKeyMaterial(identityStore, keyPassword, aliasStrategy);
    }

    public SSLContextBuilder loadKeyMaterial(File file, char[] storePassword, char[] keyPassword) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {
        return this.loadKeyMaterial(file, storePassword, keyPassword, null);
    }

    @Deprecated
    public SSLContextBuilder useProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public SSLContextBuilder loadKeyMaterial(KeyStore keystore, char[] keyPassword) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        return this.loadKeyMaterial(keystore, keyPassword, null);
    }

    public SSLContextBuilder loadTrustMaterial(TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException {
        return this.loadTrustMaterial(null, trustStrategy);
    }

    public SSLContextBuilder setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SSLContextBuilder loadTrustMaterial(File file, char[] storePassword, TrustStrategy trustStrategy) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        Args.notNull((Object)file, (String)"Truststore file");
        KeyStore trustStore = KeyStore.getInstance(this.keyStoreType);
        FileInputStream inStream = new FileInputStream(file);
        try {
            trustStore.load(inStream, storePassword);
        }
        finally {
            inStream.close();
        }
        return this.loadTrustMaterial(trustStore, trustStrategy);
    }

    public String toString() {
        return "[provider=" + this.provider + ", protocol=" + this.protocol + ", keyStoreType=" + this.keyStoreType + ", keyManagerFactoryAlgorithm=" + this.keyManagerFactoryAlgorithm + ", keyManagers=" + this.keyManagers + ", trustManagerFactoryAlgorithm=" + this.trustManagerFactoryAlgorithm + ", trustManagers=" + this.trustManagers + ", secureRandom=" + this.secureRandom + "]";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SSLContextBuilder loadTrustMaterial(URL url, char[] storePassword, TrustStrategy trustStrategy) throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        Args.notNull((Object)url, (String)"Truststore URL");
        KeyStore trustStore = KeyStore.getInstance(this.keyStoreType);
        InputStream inStream = url.openStream();
        try {
            trustStore.load(inStream, storePassword);
        }
        finally {
            inStream.close();
        }
        return this.loadTrustMaterial(trustStore, trustStrategy);
    }

    protected void initSSLContext(SSLContext sslContext, Collection<KeyManager> keyManagers, Collection<TrustManager> trustManagers, SecureRandom secureRandom) throws KeyManagementException {
        sslContext.init(!keyManagers.isEmpty() ? keyManagers.toArray(new KeyManager[keyManagers.size()]) : null, !trustManagers.isEmpty() ? trustManagers.toArray(new TrustManager[trustManagers.size()]) : null, secureRandom);
    }

    public SSLContextBuilder loadKeyMaterial(URL url, char[] storePassword, char[] keyPassword) throws UnrecoverableKeyException, IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        return this.loadKeyMaterial(url, storePassword, keyPassword, null);
    }

    public static SSLContextBuilder create() {
        return new SSLContextBuilder();
    }

    public SSLContextBuilder setKeyManagerFactoryAlgorithm(String keyManagerFactoryAlgorithm) {
        this.keyManagerFactoryAlgorithm = keyManagerFactoryAlgorithm;
        return this;
    }

    public SSLContextBuilder setProvider(String name) {
        this.provider = Security.getProvider(name);
        return this;
    }
}
