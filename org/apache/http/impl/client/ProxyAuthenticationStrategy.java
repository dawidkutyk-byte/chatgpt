/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.impl.client.AuthenticationStrategyImpl
 */
package org.apache.http.impl.client;

import java.util.Collection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.AuthenticationStrategyImpl;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ProxyAuthenticationStrategy
extends AuthenticationStrategyImpl {
    public static final ProxyAuthenticationStrategy INSTANCE = new ProxyAuthenticationStrategy();

    Collection<String> getPreferredAuthSchemes(RequestConfig config) {
        return config.getProxyPreferredAuthSchemes();
    }

    public ProxyAuthenticationStrategy() {
        super(407, "Proxy-Authenticate");
    }
}
