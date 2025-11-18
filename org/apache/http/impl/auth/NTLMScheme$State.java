/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.impl.auth;

static enum NTLMScheme.State {
    UNINITIATED,
    CHALLENGE_RECEIVED,
    MSG_TYPE1_GENERATED,
    MSG_TYPE2_RECEVIED,
    MSG_TYPE3_GENERATED,
    FAILED;

}
