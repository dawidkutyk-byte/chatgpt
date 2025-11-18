/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.impl.auth.NTLMEngine
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl$HMACMD5
 *  org.apache.http.impl.auth.NTLMEngineImpl$MD4
 *  org.apache.http.impl.auth.NTLMEngineImpl$Type1Message
 *  org.apache.http.impl.auth.NTLMEngineImpl$Type2Message
 *  org.apache.http.impl.auth.NTLMEngineImpl$Type3Message
 */
package org.apache.http.impl.auth;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.Consts;
import org.apache.http.impl.auth.NTLMEngine;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

final class NTLMEngineImpl
implements NTLMEngine {
    private static final String TYPE_1_MESSAGE;
    static final int FLAG_REQUEST_SIGN = 16;
    static final int FLAG_REQUEST_NTLMv1 = 512;
    static final int FLAG_REQUEST_EXPLICIT_KEY_EXCH = 0x40000000;
    static final int MSV_AV_DNS_DOMAIN_NAME = 4;
    static final int FLAG_TARGETINFO_PRESENT = 0x800000;
    private static final byte[] SEAL_MAGIC_SERVER;
    static final int FLAG_REQUEST_56BIT_ENCRYPTION = Integer.MIN_VALUE;
    static final int MSV_AV_TIMESTAMP = 7;
    private static final byte[] SIGN_MAGIC_SERVER;
    static final int FLAG_REQUEST_ALWAYS_SIGN = 32768;
    static final int MSV_AV_FLAGS_ACCOUNT_AUTH_CONSTAINED = 1;
    static final int MSV_AV_FLAGS_MIC = 2;
    private static final byte[] SIGNATURE;
    static final int MSV_AV_CHANNEL_BINDINGS = 10;
    static final int MSV_AV_DNS_COMPUTER_NAME = 3;
    static final int FLAG_WORKSTATION_PRESENT = 8192;
    private static final Charset DEFAULT_CHARSET;
    static final int FLAG_DOMAIN_PRESENT = 4096;
    static final int FLAG_REQUEST_UNICODE_ENCODING = 1;
    static final int MSV_AV_EOL = 0;
    private static final byte[] MAGIC_TLS_SERVER_ENDPOINT;
    static final int FLAG_REQUEST_VERSION = 0x2000000;
    static final int MSV_AV_TARGET_NAME = 9;
    private static final Charset UNICODE_LITTLE_UNMARKED;
    static final int MSV_AV_DNS_TREE_NAME = 5;
    static final int MSV_AV_FLAGS = 6;
    static final int MSV_AV_FLAGS_UNTRUSTED_TARGET_SPN = 4;
    static final int MSV_AV_SINGLE_HOST = 8;
    static final int FLAG_REQUEST_128BIT_KEY_EXCH = 0x20000000;
    static final int FLAG_REQUEST_SEAL = 32;
    static final int FLAG_REQUEST_TARGET = 4;
    static final int MSV_AV_NB_DOMAIN_NAME = 2;
    static final int MSV_AV_NB_COMPUTER_NAME = 1;
    static final int FLAG_REQUEST_LAN_MANAGER_KEY = 128;
    private static final SecureRandom RND_GEN;
    static final int FLAG_REQUEST_OEM_ENCODING = 2;
    static final int FLAG_REQUEST_NTLM2_SESSION = 524288;
    private static final byte[] SIGN_MAGIC_CLIENT;
    private static final byte[] SEAL_MAGIC_CLIENT;

    static int F(int x, int y, int z) {
        return x & y | ~x & z;
    }

    private static String stripDotSuffix(String value) {
        if (value == null) {
            return null;
        }
        int index = value.indexOf(46);
        if (index == -1) return value;
        return value.substring(0, index);
    }

    private static byte[] lmHash(String password) throws NTLMEngineException {
        try {
            byte[] oemPassword = password.toUpperCase(Locale.ROOT).getBytes(Consts.ASCII);
            int length = Math.min(oemPassword.length, 14);
            byte[] keyBytes = new byte[14];
            System.arraycopy(oemPassword, 0, keyBytes, 0, length);
            Key lowKey = NTLMEngineImpl.createDESKey(keyBytes, 0);
            Key highKey = NTLMEngineImpl.createDESKey(keyBytes, 7);
            byte[] magicConstant = "KGS!@#$%".getBytes(Consts.ASCII);
            Cipher des = Cipher.getInstance("DES/ECB/NoPadding");
            des.init(1, lowKey);
            byte[] lowHash = des.doFinal(magicConstant);
            des.init(1, highKey);
            byte[] highHash = des.doFinal(magicConstant);
            byte[] lmHash = new byte[16];
            System.arraycopy(lowHash, 0, lmHash, 0, 8);
            System.arraycopy(highHash, 0, lmHash, 8, 8);
            return lmHash;
        }
        catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
    }

    static /* synthetic */ byte[] access$900(byte[] x0, byte[] x1, byte[] x2) {
        return NTLMEngineImpl.lmv2Response(x0, x1, x2);
    }

    static /* synthetic */ byte[] access$1500(int x0) {
        return NTLMEngineImpl.encodeLong(x0);
    }

    static MessageDigest getMD5() {
        try {
            return MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("MD5 message digest doesn't seem to exist - fatal error: " + ex.getMessage(), ex);
        }
    }

    static /* synthetic */ byte[] access$1800() {
        return SIGNATURE;
    }

    private static Charset getCharset(int flags) throws NTLMEngineException {
        if ((flags & 1) == 0) {
            return DEFAULT_CHARSET;
        }
        if (UNICODE_LITTLE_UNMARKED != null) return UNICODE_LITTLE_UNMARKED;
        throw new NTLMEngineException("Unicode not supported");
    }

    static /* synthetic */ String access$2300(String x0) {
        return NTLMEngineImpl.convertDomain(x0);
    }

    public String generateType3Msg(String username, String password, String domain, String workstation, String challenge) throws NTLMEngineException {
        Type2Message t2m = new Type2Message(challenge);
        return NTLMEngineImpl.getType3Message(username, password, workstation, domain, t2m.getChallenge(), t2m.getFlags(), t2m.getTarget(), t2m.getTargetInfo());
    }

    static void writeUShort(byte[] buffer, int value, int offset) {
        buffer[offset] = (byte)(value & 0xFF);
        buffer[offset + 1] = (byte)(value >> 8 & 0xFF);
    }

    static /* synthetic */ byte[] access$1400() {
        return SEAL_MAGIC_SERVER;
    }

    private static byte[] lmv2Response(byte[] hash, byte[] challenge, byte[] clientData) {
        HMACMD5 hmacMD5 = new HMACMD5(hash);
        hmacMD5.update(challenge);
        hmacMD5.update(clientData);
        byte[] mac = hmacMD5.getOutput();
        byte[] lmv2Response = new byte[mac.length + clientData.length];
        System.arraycopy(mac, 0, lmv2Response, 0, mac.length);
        System.arraycopy(clientData, 0, lmv2Response, mac.length, clientData.length);
        return lmv2Response;
    }

    static /* synthetic */ byte[] access$400(byte[] x0, byte[] x1) throws NTLMEngineException {
        return NTLMEngineImpl.lmResponse(x0, x1);
    }

    static /* synthetic */ SecureRandom access$000() {
        return RND_GEN;
    }

    private static String convertHost(String host) {
        return NTLMEngineImpl.stripDotSuffix(host);
    }

    static byte[] RC4(byte[] value, byte[] key) throws NTLMEngineException {
        try {
            Cipher rc4 = Cipher.getInstance("RC4");
            rc4.init(1, new SecretKeySpec(key, "RC4"));
            return rc4.doFinal(value);
        }
        catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static byte[] makeSecondaryKey(Random random) {
        byte[] rval = new byte[16];
        Random random2 = random;
        synchronized (random2) {
            random.nextBytes(rval);
        }
        return rval;
    }

    private static byte[] ntlmHash(String password) throws NTLMEngineException {
        if (UNICODE_LITTLE_UNMARKED == null) {
            throw new NTLMEngineException("Unicode not supported");
        }
        byte[] unicodePassword = password.getBytes(UNICODE_LITTLE_UNMARKED);
        MD4 md4 = new MD4();
        md4.update(unicodePassword);
        return md4.getOutput();
    }

    static /* synthetic */ byte[] access$200(Random x0) {
        return NTLMEngineImpl.makeSecondaryKey(x0);
    }

    static int G(int x, int y, int z) {
        return x & y | x & z | y & z;
    }

    static /* synthetic */ byte[] access$2100(byte[] x0, int x1) {
        return NTLMEngineImpl.readSecurityBuffer(x0, x1);
    }

    static byte[] ntlm2SessionResponse(byte[] ntlmHash, byte[] challenge, byte[] clientChallenge) throws NTLMEngineException {
        try {
            MessageDigest md5 = NTLMEngineImpl.getMD5();
            md5.update(challenge);
            md5.update(clientChallenge);
            byte[] digest = md5.digest();
            byte[] sessionHash = new byte[8];
            System.arraycopy(digest, 0, sessionHash, 0, 8);
            return NTLMEngineImpl.lmResponse(ntlmHash, sessionHash);
        }
        catch (Exception e) {
            if (!(e instanceof NTLMEngineException)) throw new NTLMEngineException(e.getMessage(), (Throwable)e);
            throw (NTLMEngineException)e;
        }
    }

    static /* synthetic */ byte[] access$100(Random x0) {
        return NTLMEngineImpl.makeRandomChallenge(x0);
    }

    static /* synthetic */ Charset access$1700() {
        return DEFAULT_CHARSET;
    }

    static /* synthetic */ byte[] access$600(String x0, String x1, byte[] x2) throws NTLMEngineException {
        return NTLMEngineImpl.lmv2Hash(x0, x1, x2);
    }

    static /* synthetic */ Charset access$2400() {
        return UNICODE_LITTLE_UNMARKED;
    }

    static {
        UNICODE_LITTLE_UNMARKED = Charset.forName("UnicodeLittleUnmarked");
        DEFAULT_CHARSET = Consts.ASCII;
        SecureRandom rnd = null;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        }
        catch (Exception exception) {
            // empty catch block
        }
        RND_GEN = rnd;
        SIGNATURE = NTLMEngineImpl.getNullTerminatedAsciiString("NTLMSSP");
        SIGN_MAGIC_SERVER = NTLMEngineImpl.getNullTerminatedAsciiString("session key to server-to-client signing key magic constant");
        SIGN_MAGIC_CLIENT = NTLMEngineImpl.getNullTerminatedAsciiString("session key to client-to-server signing key magic constant");
        SEAL_MAGIC_SERVER = NTLMEngineImpl.getNullTerminatedAsciiString("session key to server-to-client sealing key magic constant");
        SEAL_MAGIC_CLIENT = NTLMEngineImpl.getNullTerminatedAsciiString("session key to client-to-server sealing key magic constant");
        MAGIC_TLS_SERVER_ENDPOINT = "tls-server-end-point:".getBytes(Consts.ASCII);
        TYPE_1_MESSAGE = new Type1Message().getResponse();
    }

    private static Key createDESKey(byte[] bytes, int offset) {
        byte[] keyBytes = new byte[7];
        System.arraycopy(bytes, offset, keyBytes, 0, 7);
        byte[] material = new byte[]{keyBytes[0], (byte)(keyBytes[0] << 7 | (keyBytes[1] & 0xFF) >>> 1), (byte)(keyBytes[1] << 6 | (keyBytes[2] & 0xFF) >>> 2), (byte)(keyBytes[2] << 5 | (keyBytes[3] & 0xFF) >>> 3), (byte)(keyBytes[3] << 4 | (keyBytes[4] & 0xFF) >>> 4), (byte)(keyBytes[4] << 3 | (keyBytes[5] & 0xFF) >>> 5), (byte)(keyBytes[5] << 2 | (keyBytes[6] & 0xFF) >>> 6), (byte)(keyBytes[6] << 1)};
        NTLMEngineImpl.oddParity(material);
        return new SecretKeySpec(material, "DES");
    }

    private static byte[] ntlmv2Hash(String domain, String user, byte[] ntlmHash) throws NTLMEngineException {
        if (UNICODE_LITTLE_UNMARKED == null) {
            throw new NTLMEngineException("Unicode not supported");
        }
        HMACMD5 hmacMD5 = new HMACMD5(ntlmHash);
        hmacMD5.update(user.toUpperCase(Locale.ROOT).getBytes(UNICODE_LITTLE_UNMARKED));
        if (domain == null) return hmacMD5.getOutput();
        hmacMD5.update(domain.getBytes(UNICODE_LITTLE_UNMARKED));
        return hmacMD5.getOutput();
    }

    static /* synthetic */ byte[] access$2600() {
        return MAGIC_TLS_SERVER_ENDPOINT;
    }

    private static String convertDomain(String domain) {
        return NTLMEngineImpl.stripDotSuffix(domain);
    }

    static /* synthetic */ String access$2200(String x0) {
        return NTLMEngineImpl.convertHost(x0);
    }

    public String generateType1Msg(String domain, String workstation) throws NTLMEngineException {
        return NTLMEngineImpl.getType1Message(workstation, domain);
    }

    private static byte[] createBlob(byte[] clientChallenge, byte[] targetInformation, byte[] timestamp) {
        byte[] blobSignature = new byte[]{1, 1, 0, 0};
        byte[] reserved = new byte[]{0, 0, 0, 0};
        byte[] unknown1 = new byte[]{0, 0, 0, 0};
        byte[] unknown2 = new byte[]{0, 0, 0, 0};
        byte[] blob = new byte[blobSignature.length + reserved.length + timestamp.length + 8 + unknown1.length + targetInformation.length + unknown2.length];
        int offset = 0;
        System.arraycopy(blobSignature, 0, blob, offset, blobSignature.length);
        System.arraycopy(reserved, 0, blob, offset += blobSignature.length, reserved.length);
        System.arraycopy(timestamp, 0, blob, offset += reserved.length, timestamp.length);
        System.arraycopy(clientChallenge, 0, blob, offset += timestamp.length, 8);
        System.arraycopy(unknown1, 0, blob, offset += 8, unknown1.length);
        System.arraycopy(targetInformation, 0, blob, offset += unknown1.length, targetInformation.length);
        System.arraycopy(unknown2, 0, blob, offset += targetInformation.length, unknown2.length);
        offset += unknown2.length;
        return blob;
    }

    static /* synthetic */ byte[] access$700(String x0, String x1, byte[] x2) throws NTLMEngineException {
        return NTLMEngineImpl.ntlmv2Hash(x0, x1, x2);
    }

    static /* synthetic */ byte[] access$1300() {
        return SIGN_MAGIC_SERVER;
    }

    static byte[] hmacMD5(byte[] value, byte[] key) throws NTLMEngineException {
        HMACMD5 hmacMD5 = new HMACMD5(key);
        hmacMD5.update(value);
        return hmacMD5.getOutput();
    }

    static int H(int x, int y, int z) {
        return x ^ y ^ z;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static byte[] makeRandomChallenge(Random random) {
        byte[] rval = new byte[8];
        Random random2 = random;
        synchronized (random2) {
            random.nextBytes(rval);
        }
        return rval;
    }

    static /* synthetic */ Charset access$2500(int x0) throws NTLMEngineException {
        return NTLMEngineImpl.getCharset(x0);
    }

    static /* synthetic */ byte[] access$1200() {
        return SEAL_MAGIC_CLIENT;
    }

    static /* synthetic */ int access$1900(byte[] x0, int x1) {
        return NTLMEngineImpl.readUShort(x0, x1);
    }

    static /* synthetic */ Key access$1000(byte[] x0, int x1) {
        return NTLMEngineImpl.createDESKey(x0, x1);
    }

    static /* synthetic */ int access$2000(byte[] x0, int x1) {
        return NTLMEngineImpl.readULong(x0, x1);
    }

    static /* synthetic */ byte[] access$500(String x0) throws NTLMEngineException {
        return NTLMEngineImpl.ntlmHash(x0);
    }

    private static byte[] getNullTerminatedAsciiString(String source) {
        byte[] bytesWithoutNull = source.getBytes(Consts.ASCII);
        byte[] target = new byte[bytesWithoutNull.length + 1];
        System.arraycopy(bytesWithoutNull, 0, target, 0, bytesWithoutNull.length);
        target[bytesWithoutNull.length] = 0;
        return target;
    }

    static /* synthetic */ byte[] access$800(byte[] x0, byte[] x1, byte[] x2) {
        return NTLMEngineImpl.createBlob(x0, x1, x2);
    }

    static /* synthetic */ byte[] access$1100() {
        return SIGN_MAGIC_CLIENT;
    }

    static String getType3Message(String user, String password, String host, String domain, byte[] nonce, int type2Flags, String target, byte[] targetInformation, Certificate peerServerCertificate, byte[] type1Message, byte[] type2Message) throws NTLMEngineException {
        return new Type3Message(domain, host, user, password, nonce, type2Flags, target, targetInformation, peerServerCertificate, type1Message, type2Message).getResponse();
    }

    private static byte[] lmResponse(byte[] hash, byte[] challenge) throws NTLMEngineException {
        try {
            byte[] keyBytes = new byte[21];
            System.arraycopy(hash, 0, keyBytes, 0, 16);
            Key lowKey = NTLMEngineImpl.createDESKey(keyBytes, 0);
            Key middleKey = NTLMEngineImpl.createDESKey(keyBytes, 7);
            Key highKey = NTLMEngineImpl.createDESKey(keyBytes, 14);
            Cipher des = Cipher.getInstance("DES/ECB/NoPadding");
            des.init(1, lowKey);
            byte[] lowResponse = des.doFinal(challenge);
            des.init(1, middleKey);
            byte[] middleResponse = des.doFinal(challenge);
            des.init(1, highKey);
            byte[] highResponse = des.doFinal(challenge);
            byte[] lmResponse = new byte[24];
            System.arraycopy(lowResponse, 0, lmResponse, 0, 8);
            System.arraycopy(middleResponse, 0, lmResponse, 8, 8);
            System.arraycopy(highResponse, 0, lmResponse, 16, 8);
            return lmResponse;
        }
        catch (Exception e) {
            throw new NTLMEngineException(e.getMessage(), (Throwable)e);
        }
    }

    private static int readUShort(byte[] src, int index) {
        if (src.length >= index + 2) return src[index] & 0xFF | (src[index + 1] & 0xFF) << 8;
        return 0;
    }

    private static int readULong(byte[] src, int index) {
        if (src.length >= index + 4) return src[index] & 0xFF | (src[index + 1] & 0xFF) << 8 | (src[index + 2] & 0xFF) << 16 | (src[index + 3] & 0xFF) << 24;
        return 0;
    }

    static int rotintlft(int val, int numbits) {
        return val << numbits | val >>> 32 - numbits;
    }

    NTLMEngineImpl() {
    }

    static String getType3Message(String user, String password, String host, String domain, byte[] nonce, int type2Flags, String target, byte[] targetInformation) throws NTLMEngineException {
        return new Type3Message(domain, host, user, password, nonce, type2Flags, target, targetInformation).getResponse();
    }

    static /* synthetic */ void access$1600(byte[] x0, int x1, int x2) {
        NTLMEngineImpl.encodeLong(x0, x1, x2);
    }

    private static byte[] lmv2Hash(String domain, String user, byte[] ntlmHash) throws NTLMEngineException {
        if (UNICODE_LITTLE_UNMARKED == null) {
            throw new NTLMEngineException("Unicode not supported");
        }
        HMACMD5 hmacMD5 = new HMACMD5(ntlmHash);
        hmacMD5.update(user.toUpperCase(Locale.ROOT).getBytes(UNICODE_LITTLE_UNMARKED));
        if (domain == null) return hmacMD5.getOutput();
        hmacMD5.update(domain.toUpperCase(Locale.ROOT).getBytes(UNICODE_LITTLE_UNMARKED));
        return hmacMD5.getOutput();
    }

    static /* synthetic */ byte[] access$300(String x0) throws NTLMEngineException {
        return NTLMEngineImpl.lmHash(x0);
    }

    private static void encodeLong(byte[] buf, int offset, int value) {
        buf[offset + 0] = (byte)(value & 0xFF);
        buf[offset + 1] = (byte)(value >> 8 & 0xFF);
        buf[offset + 2] = (byte)(value >> 16 & 0xFF);
        buf[offset + 3] = (byte)(value >> 24 & 0xFF);
    }

    private static void oddParity(byte[] bytes) {
        int i = 0;
        while (i < bytes.length) {
            boolean needsParity;
            byte b = bytes[i];
            boolean bl = needsParity = ((b >>> 7 ^ b >>> 6 ^ b >>> 5 ^ b >>> 4 ^ b >>> 3 ^ b >>> 2 ^ b >>> 1) & 1) == 0;
            if (needsParity) {
                int n = i;
                bytes[n] = (byte)(bytes[n] | 1);
            } else {
                int n = i;
                bytes[n] = (byte)(bytes[n] & 0xFFFFFFFE);
            }
            ++i;
        }
    }

    private static byte[] encodeLong(int value) {
        byte[] enc = new byte[4];
        NTLMEngineImpl.encodeLong(enc, 0, value);
        return enc;
    }

    private static byte[] readSecurityBuffer(byte[] src, int index) {
        int length = NTLMEngineImpl.readUShort(src, index);
        int offset = NTLMEngineImpl.readULong(src, index + 4);
        if (src.length < offset + length) {
            return new byte[length];
        }
        byte[] buffer = new byte[length];
        System.arraycopy(src, offset, buffer, 0, length);
        return buffer;
    }

    static void writeULong(byte[] buffer, int value, int offset) {
        buffer[offset] = (byte)(value & 0xFF);
        buffer[offset + 1] = (byte)(value >> 8 & 0xFF);
        buffer[offset + 2] = (byte)(value >> 16 & 0xFF);
        buffer[offset + 3] = (byte)(value >> 24 & 0xFF);
    }

    static String getType1Message(String host, String domain) {
        return TYPE_1_MESSAGE;
    }
}
