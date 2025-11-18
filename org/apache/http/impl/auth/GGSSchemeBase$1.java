/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.GGSSchemeBase$State
 */
package org.apache.http.impl.auth;

import org.apache.http.impl.auth.GGSSchemeBase;

/*
 * Exception performing whole class analysis ignored.
 */
static class GGSSchemeBase.1 {
    static final /* synthetic */ int[] $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State;

    static {
        $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State = new int[GGSSchemeBase.State.values().length];
        try {
            GGSSchemeBase.1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[GGSSchemeBase.State.UNINITIATED.ordinal()] = 1;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            GGSSchemeBase.1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[GGSSchemeBase.State.FAILED.ordinal()] = 2;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            GGSSchemeBase.1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[GGSSchemeBase.State.CHALLENGE_RECEIVED.ordinal()] = 3;
        }
        catch (NoSuchFieldError ex) {
            // empty catch block
        }
        try {
            GGSSchemeBase.1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[GGSSchemeBase.State.TOKEN_GENERATED.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
