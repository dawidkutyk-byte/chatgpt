/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.AuthProtocolState
 */
package org.apache.http.impl.auth;

import org.apache.http.auth.AuthProtocolState;

static class HttpAuthenticator.1 {
    static final /* synthetic */ int[] $SwitchMap$org$apache$http$auth$AuthProtocolState;

    static {
        $SwitchMap$org$apache$http$auth$AuthProtocolState = new int[AuthProtocolState.values().length];
        try {
            HttpAuthenticator.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.CHALLENGED.ordinal()] = 1;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            HttpAuthenticator.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.HANDSHAKE.ordinal()] = 2;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            HttpAuthenticator.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 3;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            HttpAuthenticator.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 4;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            HttpAuthenticator.1.$SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.UNCHALLENGED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
