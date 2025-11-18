/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ssl.PrivateKeyDetails
 *  org.apache.http.ssl.PrivateKeyStrategy
 */
package org.apache.http.ssl;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;

static class SSLContextBuilder.KeyManagerDelegate
extends X509ExtendedKeyManager {
    private final PrivateKeyStrategy aliasStrategy;
    private final X509ExtendedKeyManager keyManager;

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return this.keyManager.getPrivateKey(alias);
    }

    @Override
    public String chooseEngineClientAlias(String[] keyTypes, Principal[] issuers, SSLEngine sslEngine) {
        Map<String, PrivateKeyDetails> validAliases = this.getClientAliasMap(keyTypes, issuers);
        return this.aliasStrategy.chooseAlias(validAliases, null);
    }

    public Map<String, PrivateKeyDetails> getServerAliasMap(String keyType, Principal[] issuers) {
        HashMap<String, PrivateKeyDetails> validAliases = new HashMap<String, PrivateKeyDetails>();
        String[] aliases = this.keyManager.getServerAliases(keyType, issuers);
        if (aliases == null) return validAliases;
        String[] arr$ = aliases;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String alias = arr$[i$];
            validAliases.put(alias, new PrivateKeyDetails(keyType, this.keyManager.getCertificateChain(alias)));
            ++i$;
        }
        return validAliases;
    }

    SSLContextBuilder.KeyManagerDelegate(X509ExtendedKeyManager keyManager, PrivateKeyStrategy aliasStrategy) {
        this.keyManager = keyManager;
        this.aliasStrategy = aliasStrategy;
    }

    @Override
    public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine sslEngine) {
        Map<String, PrivateKeyDetails> validAliases = this.getServerAliasMap(keyType, issuers);
        return this.aliasStrategy.chooseAlias(validAliases, null);
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return this.keyManager.getServerAliases(keyType, issuers);
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        Map<String, PrivateKeyDetails> validAliases = this.getServerAliasMap(keyType, issuers);
        return this.aliasStrategy.chooseAlias(validAliases, socket);
    }

    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        Map<String, PrivateKeyDetails> validAliases = this.getClientAliasMap(keyTypes, issuers);
        return this.aliasStrategy.chooseAlias(validAliases, socket);
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return this.keyManager.getClientAliases(keyType, issuers);
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return this.keyManager.getCertificateChain(alias);
    }

    public Map<String, PrivateKeyDetails> getClientAliasMap(String[] keyTypes, Principal[] issuers) {
        HashMap<String, PrivateKeyDetails> validAliases = new HashMap<String, PrivateKeyDetails>();
        String[] arr$ = keyTypes;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String keyType = arr$[i$];
            String[] aliases = this.keyManager.getClientAliases(keyType, issuers);
            if (aliases != null) {
                for (String alias : aliases) {
                    validAliases.put(alias, new PrivateKeyDetails(keyType, this.keyManager.getCertificateChain(alias)));
                }
            }
            ++i$;
        }
        return validAliases;
    }
}
