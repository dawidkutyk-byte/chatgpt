/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl
 *  org.apache.http.impl.auth.NTLMEngineImpl$CipherGen
 *  org.apache.http.impl.auth.NTLMEngineImpl$HMACMD5
 *  org.apache.http.impl.auth.NTLMEngineImpl$NTLMMessage
 */
package org.apache.http.impl.auth;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Locale;
import java.util.Random;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.Type3Message
extends NTLMEngineImpl.NTLMMessage {
    protected final byte[] sessionKey;
    protected final byte[] type2Message;
    protected byte[] ntResp;
    protected final int type2Flags;
    protected final byte[] type1Message;
    protected byte[] lmResp;
    protected final boolean computeMic;
    protected final byte[] domainBytes;
    protected final byte[] hostBytes;
    protected final byte[] userBytes;
    protected final byte[] exportedSessionKey;

    private byte[] addGssMicAvsToTargetInfo(byte[] originalTargetInfo, Certificate peerServerCertificate) throws NTLMEngineException {
        byte[] channelBindingsHash;
        byte[] newTargetInfo = new byte[originalTargetInfo.length + 8 + 20];
        int appendLength = originalTargetInfo.length - 4;
        System.arraycopy(originalTargetInfo, 0, newTargetInfo, 0, appendLength);
        NTLMEngineImpl.writeUShort((byte[])newTargetInfo, (int)6, (int)appendLength);
        NTLMEngineImpl.writeUShort((byte[])newTargetInfo, (int)4, (int)(appendLength + 2));
        NTLMEngineImpl.writeULong((byte[])newTargetInfo, (int)2, (int)(appendLength + 4));
        NTLMEngineImpl.writeUShort((byte[])newTargetInfo, (int)10, (int)(appendLength + 8));
        NTLMEngineImpl.writeUShort((byte[])newTargetInfo, (int)16, (int)(appendLength + 10));
        try {
            byte[] certBytes = peerServerCertificate.getEncoded();
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] certHashBytes = sha256.digest(certBytes);
            byte[] channelBindingStruct = new byte[20 + NTLMEngineImpl.access$2600().length + certHashBytes.length];
            NTLMEngineImpl.writeULong((byte[])channelBindingStruct, (int)53, (int)16);
            System.arraycopy(NTLMEngineImpl.access$2600(), 0, channelBindingStruct, 20, NTLMEngineImpl.access$2600().length);
            System.arraycopy(certHashBytes, 0, channelBindingStruct, 20 + NTLMEngineImpl.access$2600().length, certHashBytes.length);
            MessageDigest md5 = NTLMEngineImpl.getMD5();
            channelBindingsHash = md5.digest(channelBindingStruct);
        }
        catch (CertificateEncodingException e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
        catch (NoSuchAlgorithmException e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
        System.arraycopy(channelBindingsHash, 0, newTargetInfo, appendLength + 12, 16);
        return newTargetInfo;
    }

    public byte[] getExportedSessionKey() {
        return this.exportedSessionKey;
    }

    public byte[] getEncryptedRandomSessionKey() {
        return this.sessionKey;
    }

    NTLMEngineImpl.Type3Message(Random random, long currentTime, String domain, String host, String user, String password, byte[] nonce, int type2Flags, String target, byte[] targetInformation) throws NTLMEngineException {
        this(random, currentTime, domain, host, user, password, nonce, type2Flags, target, targetInformation, null, null, null);
    }

    protected void buildMessage() {
        int ntRespLen = this.ntResp.length;
        int lmRespLen = this.lmResp.length;
        int domainLen = this.domainBytes != null ? this.domainBytes.length : 0;
        int hostLen = this.hostBytes != null ? this.hostBytes.length : 0;
        int userLen = this.userBytes.length;
        int sessionKeyLen = this.sessionKey != null ? this.sessionKey.length : 0;
        int lmRespOffset = 72 + (this.computeMic ? 16 : 0);
        int ntRespOffset = lmRespOffset + lmRespLen;
        int domainOffset = ntRespOffset + ntRespLen;
        int userOffset = domainOffset + domainLen;
        int hostOffset = userOffset + userLen;
        int sessionKeyOffset = hostOffset + hostLen;
        int finalLength = sessionKeyOffset + sessionKeyLen;
        this.prepareResponse(finalLength, 3);
        this.addUShort(lmRespLen);
        this.addUShort(lmRespLen);
        this.addULong(lmRespOffset);
        this.addUShort(ntRespLen);
        this.addUShort(ntRespLen);
        this.addULong(ntRespOffset);
        this.addUShort(domainLen);
        this.addUShort(domainLen);
        this.addULong(domainOffset);
        this.addUShort(userLen);
        this.addUShort(userLen);
        this.addULong(userOffset);
        this.addUShort(hostLen);
        this.addUShort(hostLen);
        this.addULong(hostOffset);
        this.addUShort(sessionKeyLen);
        this.addUShort(sessionKeyLen);
        this.addULong(sessionKeyOffset);
        this.addULong(this.type2Flags);
        this.addUShort(261);
        this.addULong(2600);
        this.addUShort(3840);
        int micPosition = -1;
        if (this.computeMic) {
            micPosition = this.currentOutputPosition;
            this.currentOutputPosition += 16;
        }
        this.addBytes(this.lmResp);
        this.addBytes(this.ntResp);
        this.addBytes(this.domainBytes);
        this.addBytes(this.userBytes);
        this.addBytes(this.hostBytes);
        if (this.sessionKey != null) {
            this.addBytes(this.sessionKey);
        }
        if (!this.computeMic) return;
        NTLMEngineImpl.HMACMD5 hmacMD5 = new NTLMEngineImpl.HMACMD5(this.exportedSessionKey);
        hmacMD5.update(this.type1Message);
        hmacMD5.update(this.type2Message);
        hmacMD5.update(this.messageContents);
        byte[] mic = hmacMD5.getOutput();
        System.arraycopy(mic, 0, this.messageContents, micPosition, mic.length);
    }

    NTLMEngineImpl.Type3Message(String domain, String host, String user, String password, byte[] nonce, int type2Flags, String target, byte[] targetInformation) throws NTLMEngineException {
        this(domain, host, user, password, nonce, type2Flags, target, targetInformation, null, null, null);
    }

    NTLMEngineImpl.Type3Message(Random random, long currentTime, String domain, String host, String user, String password, byte[] nonce, int type2Flags, String target, byte[] targetInformation, Certificate peerServerCertificate, byte[] type1Message, byte[] type2Message) throws NTLMEngineException {
        byte[] userSessionKey;
        if (random == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        this.type2Flags = type2Flags;
        this.type1Message = type1Message;
        this.type2Message = type2Message;
        String unqualifiedHost = NTLMEngineImpl.access$2200((String)host);
        String unqualifiedDomain = NTLMEngineImpl.access$2300((String)domain);
        byte[] responseTargetInformation = targetInformation;
        if (peerServerCertificate != null) {
            responseTargetInformation = this.addGssMicAvsToTargetInfo(targetInformation, peerServerCertificate);
            this.computeMic = true;
        } else {
            this.computeMic = false;
        }
        NTLMEngineImpl.CipherGen gen = new NTLMEngineImpl.CipherGen(random, currentTime, unqualifiedDomain, user, password, nonce, target, responseTargetInformation);
        try {
            if ((type2Flags & 0x800000) != 0 && targetInformation != null && target != null) {
                this.ntResp = gen.getNTLMv2Response();
                this.lmResp = gen.getLMv2Response();
                userSessionKey = (type2Flags & 0x80) != 0 ? gen.getLanManagerSessionKey() : gen.getNTLMv2UserSessionKey();
            } else if ((type2Flags & 0x80000) != 0) {
                this.ntResp = gen.getNTLM2SessionResponse();
                this.lmResp = gen.getLM2SessionResponse();
                userSessionKey = (type2Flags & 0x80) != 0 ? gen.getLanManagerSessionKey() : gen.getNTLM2SessionResponseUserSessionKey();
            } else {
                this.ntResp = gen.getNTLMResponse();
                this.lmResp = gen.getLMResponse();
                userSessionKey = (type2Flags & 0x80) != 0 ? gen.getLanManagerSessionKey() : gen.getNTLMUserSessionKey();
            }
        }
        catch (NTLMEngineException e) {
            this.ntResp = new byte[0];
            this.lmResp = gen.getLMResponse();
            userSessionKey = (type2Flags & 0x80) != 0 ? gen.getLanManagerSessionKey() : gen.getLMUserSessionKey();
        }
        if ((type2Flags & 0x10) != 0) {
            if ((type2Flags & 0x40000000) != 0) {
                this.exportedSessionKey = gen.getSecondaryKey();
                this.sessionKey = NTLMEngineImpl.RC4((byte[])this.exportedSessionKey, (byte[])userSessionKey);
            } else {
                this.sessionKey = userSessionKey;
                this.exportedSessionKey = this.sessionKey;
            }
        } else {
            if (this.computeMic) {
                throw new NTLMEngineException("Cannot sign/seal: no exported session key");
            }
            this.sessionKey = null;
            this.exportedSessionKey = null;
        }
        Charset charset = NTLMEngineImpl.access$2500((int)type2Flags);
        this.hostBytes = unqualifiedHost != null ? unqualifiedHost.getBytes(charset) : null;
        this.domainBytes = unqualifiedDomain != null ? unqualifiedDomain.toUpperCase(Locale.ROOT).getBytes(charset) : null;
        this.userBytes = user.getBytes(charset);
    }

    NTLMEngineImpl.Type3Message(String domain, String host, String user, String password, byte[] nonce, int type2Flags, String target, byte[] targetInformation, Certificate peerServerCertificate, byte[] type1Message, byte[] type2Message) throws NTLMEngineException {
        this(NTLMEngineImpl.access$000(), System.currentTimeMillis(), domain, host, user, password, nonce, type2Flags, target, targetInformation, peerServerCertificate, type1Message, type2Message);
    }
}
