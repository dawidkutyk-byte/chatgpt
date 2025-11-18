/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl
 *  org.apache.http.impl.auth.NTLMEngineImpl$NTLMMessage
 */
package org.apache.http.impl.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.Type2Message
extends NTLMEngineImpl.NTLMMessage {
    protected byte[] targetInfo;
    protected final byte[] challenge = new byte[8];
    protected String target;
    protected final int flags;

    NTLMEngineImpl.Type2Message(byte[] message) throws NTLMEngineException {
        super(message, 2);
        byte[] bytes;
        this.readBytes(this.challenge, 24);
        this.flags = this.readULong(20);
        this.target = null;
        if (this.getMessageLength() >= 20 && (bytes = this.readSecurityBuffer(12)).length != 0) {
            this.target = new String(bytes, NTLMEngineImpl.access$2500((int)this.flags));
        }
        this.targetInfo = null;
        if (this.getMessageLength() < 48) return;
        bytes = this.readSecurityBuffer(40);
        if (bytes.length == 0) return;
        this.targetInfo = bytes;
    }

    byte[] getChallenge() {
        return this.challenge;
    }

    int getFlags() {
        return this.flags;
    }

    byte[] getTargetInfo() {
        return this.targetInfo;
    }

    String getTarget() {
        return this.target;
    }

    NTLMEngineImpl.Type2Message(String messageBody) throws NTLMEngineException {
        this(Base64.decodeBase64(messageBody.getBytes(NTLMEngineImpl.access$1700())));
    }
}
