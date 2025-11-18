/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.conn;

import java.net.Proxy;

static class ProxySelectorRoutePlanner.1 {
    static final /* synthetic */ int[] $SwitchMap$java$net$Proxy$Type;

    static {
        $SwitchMap$java$net$Proxy$Type = new int[Proxy.Type.values().length];
        try {
            ProxySelectorRoutePlanner.1.$SwitchMap$java$net$Proxy$Type[Proxy.Type.DIRECT.ordinal()] = 1;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            ProxySelectorRoutePlanner.1.$SwitchMap$java$net$Proxy$Type[Proxy.Type.HTTP.ordinal()] = 2;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            ProxySelectorRoutePlanner.1.$SwitchMap$java$net$Proxy$Type[Proxy.Type.SOCKS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
