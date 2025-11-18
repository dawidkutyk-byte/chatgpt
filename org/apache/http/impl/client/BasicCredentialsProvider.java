/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthScope
 *  org.apache.http.auth.Credentials
 *  org.apache.http.client.CredentialsProvider
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public class BasicCredentialsProvider
implements CredentialsProvider {
    private final ConcurrentHashMap<AuthScope, Credentials> credMap = new ConcurrentHashMap();

    public void setCredentials(AuthScope authscope, Credentials credentials) {
        Args.notNull((Object)authscope, (String)"Authentication scope");
        this.credMap.put(authscope, credentials);
    }

    private static Credentials matchCredentials(Map<AuthScope, Credentials> map, AuthScope authscope) {
        Credentials creds = map.get(authscope);
        if (creds != null) return creds;
        int bestMatchFactor = -1;
        AuthScope bestMatch = null;
        Iterator<AuthScope> i$ = map.keySet().iterator();
        while (true) {
            if (!i$.hasNext()) {
                if (bestMatch == null) return creds;
                creds = map.get(bestMatch);
                return creds;
            }
            AuthScope current = i$.next();
            int factor = authscope.match(current);
            if (factor <= bestMatchFactor) continue;
            bestMatchFactor = factor;
            bestMatch = current;
        }
    }

    public String toString() {
        return this.credMap.toString();
    }

    public void clear() {
        this.credMap.clear();
    }

    public Credentials getCredentials(AuthScope authscope) {
        Args.notNull((Object)authscope, (String)"Authentication scope");
        return BasicCredentialsProvider.matchCredentials(this.credMap, authscope);
    }
}
