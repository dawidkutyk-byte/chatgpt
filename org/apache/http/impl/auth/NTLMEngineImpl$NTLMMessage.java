/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl
 */
package org.apache.http.impl.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.NTLMMessage {
    protected byte[] messageContents = null;
    protected int currentOutputPosition = 0;

    NTLMEngineImpl.NTLMMessage() {
    }

    protected void addBytes(byte[] bytes) {
        if (bytes == null) {
            return;
        }
        byte[] arr$ = bytes;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            byte b;
            this.messageContents[this.currentOutputPosition] = b = arr$[i$];
            ++this.currentOutputPosition;
            ++i$;
        }
    }

    protected void addUShort(int value) {
        this.addByte((byte)(value & 0xFF));
        this.addByte((byte)(value >> 8 & 0xFF));
    }

    protected void addByte(byte b) {
        this.messageContents[this.currentOutputPosition] = b;
        ++this.currentOutputPosition;
    }

    protected int readUShort(int position) throws NTLMEngineException {
        return NTLMEngineImpl.access$1900((byte[])this.messageContents, (int)position);
    }

    protected void addULong(int value) {
        this.addByte((byte)(value & 0xFF));
        this.addByte((byte)(value >> 8 & 0xFF));
        this.addByte((byte)(value >> 16 & 0xFF));
        this.addByte((byte)(value >> 24 & 0xFF));
    }

    protected int getMessageLength() {
        return this.currentOutputPosition;
    }

    protected byte[] readSecurityBuffer(int position) throws NTLMEngineException {
        return NTLMEngineImpl.access$2100((byte[])this.messageContents, (int)position);
    }

    public byte[] getBytes() {
        if (this.messageContents == null) {
            this.buildMessage();
        }
        if (this.messageContents.length <= this.currentOutputPosition) return this.messageContents;
        byte[] tmp = new byte[this.currentOutputPosition];
        System.arraycopy(this.messageContents, 0, tmp, 0, this.currentOutputPosition);
        this.messageContents = tmp;
        return this.messageContents;
    }

    protected void readBytes(byte[] buffer, int position) throws NTLMEngineException {
        if (this.messageContents.length < position + buffer.length) {
            throw new NTLMEngineException("NTLM: Message too short");
        }
        System.arraycopy(this.messageContents, position, buffer, 0, buffer.length);
    }

    NTLMEngineImpl.NTLMMessage(String messageBody, int expectedType) throws NTLMEngineException {
        this(Base64.decodeBase64(messageBody.getBytes(NTLMEngineImpl.access$1700())), expectedType);
    }

    protected void prepareResponse(int maxlength, int messageType) {
        this.messageContents = new byte[maxlength];
        this.currentOutputPosition = 0;
        this.addBytes(NTLMEngineImpl.access$1800());
        this.addULong(messageType);
    }

    protected byte readByte(int position) throws NTLMEngineException {
        if (this.messageContents.length >= position + 1) return this.messageContents[position];
        throw new NTLMEngineException("NTLM: Message too short");
    }

    protected void buildMessage() {
        throw new RuntimeException("Message builder not implemented for " + this.getClass().getName());
    }

    protected int readULong(int position) throws NTLMEngineException {
        return NTLMEngineImpl.access$2000((byte[])this.messageContents, (int)position);
    }

    public String getResponse() {
        return new String(Base64.encodeBase64(this.getBytes()), Consts.ASCII);
    }

    protected int getPreambleLength() {
        return NTLMEngineImpl.access$1800().length + 4;
    }

    NTLMEngineImpl.NTLMMessage(byte[] message, int expectedType) throws NTLMEngineException {
        this.messageContents = message;
        if (this.messageContents.length < NTLMEngineImpl.access$1800().length) {
            throw new NTLMEngineException("NTLM message decoding error - packet too short");
        }
        for (int i = 0; i < NTLMEngineImpl.access$1800().length; ++i) {
            if (this.messageContents[i] == NTLMEngineImpl.access$1800()[i]) continue;
            throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
        }
        int type = this.readULong(NTLMEngineImpl.access$1800().length);
        if (type != expectedType) {
            throw new NTLMEngineException("NTLM type " + Integer.toString(expectedType) + " message expected - instead got type " + Integer.toString(type));
        }
        this.currentOutputPosition = this.messageContents.length;
    }
}
