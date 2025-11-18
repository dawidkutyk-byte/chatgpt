/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl
 *  org.apache.http.impl.auth.NTLMEngineImpl$MD4
 */
package org.apache.http.impl.auth;

import java.security.Key;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
protected static class NTLMEngineImpl.CipherGen {
    protected byte[] lm2SessionResponse = null;
    protected byte[] ntlmHash = null;
    protected byte[] secondaryKey;
    protected byte[] lmUserSessionKey = null;
    protected final Random random;
    protected final String password;
    protected byte[] ntlmv2Response = null;
    protected final String user;
    protected byte[] clientChallenge;
    protected byte[] ntlmv2Blob = null;
    protected byte[] ntlmv2Hash = null;
    protected final byte[] challenge;
    protected final String target;
    protected final String domain;
    protected byte[] clientChallenge2;
    protected byte[] ntlmResponse = null;
    protected byte[] lanManagerSessionKey = null;
    protected byte[] lmv2Response = null;
    protected final long currentTime;
    protected byte[] ntlm2SessionResponse = null;
    protected byte[] timestamp;
    protected final byte[] targetInformation;
    protected byte[] ntlmUserSessionKey = null;
    protected byte[] lmHash = null;
    protected byte[] ntlmv2UserSessionKey = null;
    protected byte[] lmv2Hash = null;
    protected byte[] ntlm2SessionResponseUserSessionKey = null;
    protected byte[] lmResponse = null;

    public byte[] getLMUserSessionKey() throws NTLMEngineException {
        if (this.lmUserSessionKey != null) return this.lmUserSessionKey;
        this.lmUserSessionKey = new byte[16];
        System.arraycopy(this.getLMHash(), 0, this.lmUserSessionKey, 0, 8);
        Arrays.fill(this.lmUserSessionKey, 8, 16, (byte)0);
        return this.lmUserSessionKey;
    }

    public byte[] getLMResponse() throws NTLMEngineException {
        if (this.lmResponse != null) return this.lmResponse;
        this.lmResponse = NTLMEngineImpl.access$400((byte[])this.getLMHash(), (byte[])this.challenge);
        return this.lmResponse;
    }

    public NTLMEngineImpl.CipherGen(Random random, long currentTime, String domain, String user, String password, byte[] challenge, String target, byte[] targetInformation, byte[] clientChallenge, byte[] clientChallenge2, byte[] secondaryKey, byte[] timestamp) {
        this.random = random;
        this.currentTime = currentTime;
        this.domain = domain;
        this.target = target;
        this.user = user;
        this.password = password;
        this.challenge = challenge;
        this.targetInformation = targetInformation;
        this.clientChallenge = clientChallenge;
        this.clientChallenge2 = clientChallenge2;
        this.secondaryKey = secondaryKey;
        this.timestamp = timestamp;
    }

    @Deprecated
    public NTLMEngineImpl.CipherGen(String domain, String user, String password, byte[] challenge, String target, byte[] targetInformation) {
        this(NTLMEngineImpl.access$000(), System.currentTimeMillis(), domain, user, password, challenge, target, targetInformation);
    }

    public byte[] getNTLMv2UserSessionKey() throws NTLMEngineException {
        if (this.ntlmv2UserSessionKey != null) return this.ntlmv2UserSessionKey;
        byte[] ntlmv2hash = this.getNTLMv2Hash();
        byte[] truncatedResponse = new byte[16];
        System.arraycopy(this.getNTLMv2Response(), 0, truncatedResponse, 0, 16);
        this.ntlmv2UserSessionKey = NTLMEngineImpl.hmacMD5((byte[])truncatedResponse, (byte[])ntlmv2hash);
        return this.ntlmv2UserSessionKey;
    }

    public byte[] getNTLMHash() throws NTLMEngineException {
        if (this.ntlmHash != null) return this.ntlmHash;
        this.ntlmHash = NTLMEngineImpl.access$500((String)this.password);
        return this.ntlmHash;
    }

    public byte[] getNTLMv2Hash() throws NTLMEngineException {
        if (this.ntlmv2Hash != null) return this.ntlmv2Hash;
        this.ntlmv2Hash = NTLMEngineImpl.access$700((String)this.domain, (String)this.user, (byte[])this.getNTLMHash());
        return this.ntlmv2Hash;
    }

    public byte[] getLM2SessionResponse() throws NTLMEngineException {
        if (this.lm2SessionResponse != null) return this.lm2SessionResponse;
        byte[] clntChallenge = this.getClientChallenge();
        this.lm2SessionResponse = new byte[24];
        System.arraycopy(clntChallenge, 0, this.lm2SessionResponse, 0, clntChallenge.length);
        Arrays.fill(this.lm2SessionResponse, clntChallenge.length, this.lm2SessionResponse.length, (byte)0);
        return this.lm2SessionResponse;
    }

    public byte[] getTimestamp() {
        if (this.timestamp != null) return this.timestamp;
        long time = this.currentTime;
        time += 11644473600000L;
        time *= 10000L;
        this.timestamp = new byte[8];
        int i = 0;
        while (i < 8) {
            this.timestamp[i] = (byte)time;
            time >>>= 8;
            ++i;
        }
        return this.timestamp;
    }

    public byte[] getNTLM2SessionResponseUserSessionKey() throws NTLMEngineException {
        if (this.ntlm2SessionResponseUserSessionKey != null) return this.ntlm2SessionResponseUserSessionKey;
        byte[] ntlm2SessionResponseNonce = this.getLM2SessionResponse();
        byte[] sessionNonce = new byte[this.challenge.length + ntlm2SessionResponseNonce.length];
        System.arraycopy(this.challenge, 0, sessionNonce, 0, this.challenge.length);
        System.arraycopy(ntlm2SessionResponseNonce, 0, sessionNonce, this.challenge.length, ntlm2SessionResponseNonce.length);
        this.ntlm2SessionResponseUserSessionKey = NTLMEngineImpl.hmacMD5((byte[])sessionNonce, (byte[])this.getNTLMUserSessionKey());
        return this.ntlm2SessionResponseUserSessionKey;
    }

    public byte[] getLMv2Hash() throws NTLMEngineException {
        if (this.lmv2Hash != null) return this.lmv2Hash;
        this.lmv2Hash = NTLMEngineImpl.access$600((String)this.domain, (String)this.user, (byte[])this.getNTLMHash());
        return this.lmv2Hash;
    }

    public byte[] getLMv2Response() throws NTLMEngineException {
        if (this.lmv2Response != null) return this.lmv2Response;
        this.lmv2Response = NTLMEngineImpl.access$900((byte[])this.getLMv2Hash(), (byte[])this.challenge, (byte[])this.getClientChallenge());
        return this.lmv2Response;
    }

    public byte[] getNTLMUserSessionKey() throws NTLMEngineException {
        if (this.ntlmUserSessionKey != null) return this.ntlmUserSessionKey;
        NTLMEngineImpl.MD4 md4 = new NTLMEngineImpl.MD4();
        md4.update(this.getNTLMHash());
        this.ntlmUserSessionKey = md4.getOutput();
        return this.ntlmUserSessionKey;
    }

    @Deprecated
    public NTLMEngineImpl.CipherGen(String domain, String user, String password, byte[] challenge, String target, byte[] targetInformation, byte[] clientChallenge, byte[] clientChallenge2, byte[] secondaryKey, byte[] timestamp) {
        this(NTLMEngineImpl.access$000(), System.currentTimeMillis(), domain, user, password, challenge, target, targetInformation, clientChallenge, clientChallenge2, secondaryKey, timestamp);
    }

    public byte[] getClientChallenge2() throws NTLMEngineException {
        if (this.clientChallenge2 != null) return this.clientChallenge2;
        this.clientChallenge2 = NTLMEngineImpl.access$100((Random)this.random);
        return this.clientChallenge2;
    }

    public byte[] getSecondaryKey() throws NTLMEngineException {
        if (this.secondaryKey != null) return this.secondaryKey;
        this.secondaryKey = NTLMEngineImpl.access$200((Random)this.random);
        return this.secondaryKey;
    }

    public byte[] getLMHash() throws NTLMEngineException {
        if (this.lmHash != null) return this.lmHash;
        this.lmHash = NTLMEngineImpl.access$300((String)this.password);
        return this.lmHash;
    }

    public byte[] getNTLMResponse() throws NTLMEngineException {
        if (this.ntlmResponse != null) return this.ntlmResponse;
        this.ntlmResponse = NTLMEngineImpl.access$400((byte[])this.getNTLMHash(), (byte[])this.challenge);
        return this.ntlmResponse;
    }

    public byte[] getNTLMv2Blob() throws NTLMEngineException {
        if (this.ntlmv2Blob != null) return this.ntlmv2Blob;
        this.ntlmv2Blob = NTLMEngineImpl.access$800((byte[])this.getClientChallenge2(), (byte[])this.targetInformation, (byte[])this.getTimestamp());
        return this.ntlmv2Blob;
    }

    public byte[] getLanManagerSessionKey() throws NTLMEngineException {
        if (this.lanManagerSessionKey != null) return this.lanManagerSessionKey;
        try {
            byte[] keyBytes = new byte[14];
            System.arraycopy(this.getLMHash(), 0, keyBytes, 0, 8);
            Arrays.fill(keyBytes, 8, keyBytes.length, (byte)-67);
            Key lowKey = NTLMEngineImpl.access$1000((byte[])keyBytes, (int)0);
            Key highKey = NTLMEngineImpl.access$1000((byte[])keyBytes, (int)7);
            byte[] truncatedResponse = new byte[8];
            System.arraycopy(this.getLMResponse(), 0, truncatedResponse, 0, truncatedResponse.length);
            Cipher des = Cipher.getInstance("DES/ECB/NoPadding");
            des.init(1, lowKey);
            byte[] lowPart = des.doFinal(truncatedResponse);
            des = Cipher.getInstance("DES/ECB/NoPadding");
            des.init(1, highKey);
            byte[] highPart = des.doFinal(truncatedResponse);
            this.lanManagerSessionKey = new byte[16];
            System.arraycopy(lowPart, 0, this.lanManagerSessionKey, 0, lowPart.length);
            System.arraycopy(highPart, 0, this.lanManagerSessionKey, lowPart.length, highPart.length);
        }
        catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
        return this.lanManagerSessionKey;
    }

    public byte[] getNTLM2SessionResponse() throws NTLMEngineException {
        if (this.ntlm2SessionResponse != null) return this.ntlm2SessionResponse;
        this.ntlm2SessionResponse = NTLMEngineImpl.ntlm2SessionResponse((byte[])this.getNTLMHash(), (byte[])this.challenge, (byte[])this.getClientChallenge());
        return this.ntlm2SessionResponse;
    }

    public byte[] getClientChallenge() throws NTLMEngineException {
        if (this.clientChallenge != null) return this.clientChallenge;
        this.clientChallenge = NTLMEngineImpl.access$100((Random)this.random);
        return this.clientChallenge;
    }

    public NTLMEngineImpl.CipherGen(Random random, long currentTime, String domain, String user, String password, byte[] challenge, String target, byte[] targetInformation) {
        this(random, currentTime, domain, user, password, challenge, target, targetInformation, null, null, null, null);
    }

    public byte[] getNTLMv2Response() throws NTLMEngineException {
        if (this.ntlmv2Response != null) return this.ntlmv2Response;
        this.ntlmv2Response = NTLMEngineImpl.access$900((byte[])this.getNTLMv2Hash(), (byte[])this.challenge, (byte[])this.getNTLMv2Blob());
        return this.ntlmv2Response;
    }
}
