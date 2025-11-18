/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.AuthProtocolState
 */
package org.apache.http.client.protocol;

import org.apache.http.auth.AuthProtocolState;

static class RequestAuthenticationBase.1 {
    static final /* synthetic */ int[] $SwitchMap$org$apache$http$auth$AuthProtocolState;

    static {
        $SwitchMap$org$apache$http$auth$AuthProtocolState = new int[AuthProtocolState.values().length];
        try {
            RequestAuthenticationBase.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 1;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            RequestAuthenticationBase.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 2;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            RequestAuthenticationBase.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
