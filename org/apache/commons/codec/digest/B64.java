/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.digest;

import java.util.Random;

class B64 {
    static final String B64T = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static String getRandomSalt(int num) {
        StringBuilder saltString = new StringBuilder();
        int i = 1;
        while (i <= num) {
            saltString.append(B64T.charAt(new Random().nextInt(B64T.length())));
            ++i;
        }
        return saltString.toString();
    }

    B64() {
    }

    static void b64from24bit(byte b2, byte b1, byte b0, int outLen, StringBuilder buffer) {
        int w = b2 << 16 & 0xFFFFFF | b1 << 8 & 0xFFFF | b0 & 0xFF;
        int n = outLen;
        while (n-- > 0) {
            buffer.append(B64T.charAt(w & 0x3F));
            w >>= 6;
        }
    }
}
