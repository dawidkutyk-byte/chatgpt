/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ssl.PrivateKeyDetails
 *  org.apache.http.conn.ssl.PrivateKeyStrategy
 */
package org.apache.http.conn.ssl;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.X509KeyManager;
import org.apache.http.conn.ssl.PrivateKeyDetails;
import org.apache.http.conn.ssl.PrivateKeyStrategy;

static class SSLContextBuilder.KeyManagerDelegate
implements X509KeyManager {
    private final PrivateKeyStrategy aliasStrategy;
    private final X509KeyManager keyManager;

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return this.keyManager.getServerAliases(keyType, issuers);
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return this.keyManager.getCertificateChain(alias);
    }

    SSLContextBuilder.KeyManagerDelegate(X509KeyManager keyManager, PrivateKeyStrategy aliasStrategy) {
        this.keyManager = keyManager;
        this.aliasStrategy = aliasStrategy;
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        HashMap<String, PrivateKeyDetails> validAliases = new HashMap<String, PrivateKeyDetails>();
        String[] aliases = this.keyManager.getServerAliases(keyType, issuers);
        if (aliases == null) return this.aliasStrategy.chooseAlias(validAliases, socket);
        String[] arr$ = aliases;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String alias = arr$[i$];
            validAliases.put(alias, new PrivateKeyDetails(keyType, this.keyManager.getCertificateChain(alias)));
            ++i$;
        }
        return this.aliasStrategy.chooseAlias(validAliases, socket);
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return this.keyManager.getClientAliases(keyType, issuers);
    }

    @Override
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
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
        return this.aliasStrategy.chooseAlias(validAliases, socket);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return this.keyManager.getPrivateKey(alias);
    }
}
