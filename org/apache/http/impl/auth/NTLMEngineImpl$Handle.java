/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl
 *  org.apache.http.impl.auth.NTLMEngineImpl$HMACMD5
 *  org.apache.http.impl.auth.NTLMEngineImpl$Mode
 */
package org.apache.http.impl.auth;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.Handle {
    private final boolean isConnection;
    final NTLMEngineImpl.Mode mode;
    private byte[] sealingKey;
    private final byte[] exportedSessionKey;
    int sequenceNumber = 0;
    private byte[] signingKey;
    private final Cipher rc4;

    public byte[] signAndEncryptMessage(byte[] cleartextMessage) throws NTLMEngineException {
        byte[] encryptedMessage = this.encrypt(cleartextMessage);
        byte[] signature = this.computeSignature(cleartextMessage);
        byte[] outMessage = new byte[signature.length + encryptedMessage.length];
        System.arraycopy(signature, 0, outMessage, 0, signature.length);
        System.arraycopy(encryptedMessage, 0, outMessage, signature.length, encryptedMessage.length);
        this.advanceMessageSequence();
        return outMessage;
    }

    public byte[] getSigningKey() {
        return this.signingKey;
    }

    private byte[] decrypt(byte[] data) {
        return this.rc4.update(data);
    }

    public byte[] getSealingKey() {
        return this.sealingKey;
    }

    public byte[] decryptAndVerifySignedMessage(byte[] inMessage) throws NTLMEngineException {
        byte[] signature = new byte[16];
        System.arraycopy(inMessage, 0, signature, 0, signature.length);
        byte[] encryptedMessage = new byte[inMessage.length - 16];
        System.arraycopy(inMessage, 16, encryptedMessage, 0, encryptedMessage.length);
        byte[] cleartextMessage = this.decrypt(encryptedMessage);
        if (!this.validateSignature(signature, cleartextMessage)) {
            throw new NTLMEngineException("Wrong signature");
        }
        this.advanceMessageSequence();
        return cleartextMessage;
    }

    NTLMEngineImpl.Handle(byte[] exportedSessionKey, NTLMEngineImpl.Mode mode, boolean isConnection) throws NTLMEngineException {
        this.exportedSessionKey = exportedSessionKey;
        this.isConnection = isConnection;
        this.mode = mode;
        try {
            MessageDigest signMd5 = NTLMEngineImpl.getMD5();
            MessageDigest sealMd5 = NTLMEngineImpl.getMD5();
            signMd5.update(exportedSessionKey);
            sealMd5.update(exportedSessionKey);
            if (mode == NTLMEngineImpl.Mode.CLIENT) {
                signMd5.update(NTLMEngineImpl.access$1100());
                sealMd5.update(NTLMEngineImpl.access$1200());
            } else {
                signMd5.update(NTLMEngineImpl.access$1300());
                sealMd5.update(NTLMEngineImpl.access$1400());
            }
            this.signingKey = signMd5.digest();
            this.sealingKey = sealMd5.digest();
        }
        catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
        this.rc4 = this.initCipher();
    }

    private boolean validateSignature(byte[] signature, byte[] message) {
        byte[] computedSignature = this.computeSignature(message);
        return Arrays.equals(signature, computedSignature);
    }

    private Cipher initCipher() throws NTLMEngineException {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RC4");
            if (this.mode == NTLMEngineImpl.Mode.CLIENT) {
                cipher.init(1, new SecretKeySpec(this.sealingKey, "RC4"));
            } else {
                cipher.init(2, new SecretKeySpec(this.sealingKey, "RC4"));
            }
        }
        catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
        return cipher;
    }

    private byte[] encrypt(byte[] data) {
        return this.rc4.update(data);
    }

    private byte[] computeSignature(byte[] message) {
        byte[] sig = new byte[16];
        sig[0] = 1;
        sig[1] = 0;
        sig[2] = 0;
        sig[3] = 0;
        NTLMEngineImpl.HMACMD5 hmacMD5 = new NTLMEngineImpl.HMACMD5(this.signingKey);
        hmacMD5.update(NTLMEngineImpl.access$1500((int)this.sequenceNumber));
        hmacMD5.update(message);
        byte[] hmac = hmacMD5.getOutput();
        byte[] trimmedHmac = new byte[8];
        System.arraycopy(hmac, 0, trimmedHmac, 0, 8);
        byte[] encryptedHmac = this.encrypt(trimmedHmac);
        System.arraycopy(encryptedHmac, 0, sig, 4, 8);
        NTLMEngineImpl.access$1600((byte[])sig, (int)12, (int)this.sequenceNumber);
        return sig;
    }

    private void advanceMessageSequence() throws NTLMEngineException {
        if (!this.isConnection) {
            MessageDigest sealMd5 = NTLMEngineImpl.getMD5();
            sealMd5.update(this.sealingKey);
            byte[] seqNumBytes = new byte[4];
            NTLMEngineImpl.writeULong((byte[])seqNumBytes, (int)this.sequenceNumber, (int)0);
            sealMd5.update(seqNumBytes);
            this.sealingKey = sealMd5.digest();
            this.initCipher();
        }
        ++this.sequenceNumber;
    }
}
