/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineImpl
 */
package org.apache.http.impl.auth;

import java.security.MessageDigest;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.HMACMD5 {
    protected final byte[] ipad;
    protected final MessageDigest md5;
    protected final byte[] opad;

    void update(byte[] input, int offset, int length) {
        this.md5.update(input, offset, length);
    }

    byte[] getOutput() {
        byte[] digest = this.md5.digest();
        this.md5.update(this.opad);
        return this.md5.digest(digest);
    }

    NTLMEngineImpl.HMACMD5(byte[] input) {
        int i;
        byte[] key = input;
        this.md5 = NTLMEngineImpl.getMD5();
        this.ipad = new byte[64];
        this.opad = new byte[64];
        int keyLength = key.length;
        if (keyLength > 64) {
            this.md5.update(key);
            key = this.md5.digest();
            keyLength = key.length;
        }
        for (i = 0; i < keyLength; ++i) {
            this.ipad[i] = (byte)(key[i] ^ 0x36);
            this.opad[i] = (byte)(key[i] ^ 0x5C);
        }
        while (true) {
            if (i >= 64) {
                this.md5.reset();
                this.md5.update(this.ipad);
                return;
            }
            this.ipad[i] = 54;
            this.opad[i] = 92;
            ++i;
        }
    }

    void update(byte[] input) {
        this.md5.update(input);
    }
}
