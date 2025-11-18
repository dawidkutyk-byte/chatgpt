/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineImpl
 */
package org.apache.http.impl.auth;

import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.MD4 {
    protected final byte[] dataBuffer = new byte[64];
    protected int B = -271733879;
    protected int D = 271733878;
    protected long count = 0L;
    protected int C = -1732584194;
    protected int A = 1732584193;

    NTLMEngineImpl.MD4() {
    }

    protected void round3(int[] d) {
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.H((int)this.B, (int)this.C, (int)this.D) + d[0] + 1859775393), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.H((int)this.A, (int)this.B, (int)this.C) + d[8] + 1859775393), (int)9);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.H((int)this.D, (int)this.A, (int)this.B) + d[4] + 1859775393), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.H((int)this.C, (int)this.D, (int)this.A) + d[12] + 1859775393), (int)15);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.H((int)this.B, (int)this.C, (int)this.D) + d[2] + 1859775393), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.H((int)this.A, (int)this.B, (int)this.C) + d[10] + 1859775393), (int)9);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.H((int)this.D, (int)this.A, (int)this.B) + d[6] + 1859775393), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.H((int)this.C, (int)this.D, (int)this.A) + d[14] + 1859775393), (int)15);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.H((int)this.B, (int)this.C, (int)this.D) + d[1] + 1859775393), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.H((int)this.A, (int)this.B, (int)this.C) + d[9] + 1859775393), (int)9);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.H((int)this.D, (int)this.A, (int)this.B) + d[5] + 1859775393), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.H((int)this.C, (int)this.D, (int)this.A) + d[13] + 1859775393), (int)15);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.H((int)this.B, (int)this.C, (int)this.D) + d[3] + 1859775393), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.H((int)this.A, (int)this.B, (int)this.C) + d[11] + 1859775393), (int)9);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.H((int)this.D, (int)this.A, (int)this.B) + d[7] + 1859775393), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.H((int)this.C, (int)this.D, (int)this.A) + d[15] + 1859775393), (int)15);
    }

    protected void round1(int[] d) {
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.F((int)this.B, (int)this.C, (int)this.D) + d[0]), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.F((int)this.A, (int)this.B, (int)this.C) + d[1]), (int)7);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.F((int)this.D, (int)this.A, (int)this.B) + d[2]), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.F((int)this.C, (int)this.D, (int)this.A) + d[3]), (int)19);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.F((int)this.B, (int)this.C, (int)this.D) + d[4]), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.F((int)this.A, (int)this.B, (int)this.C) + d[5]), (int)7);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.F((int)this.D, (int)this.A, (int)this.B) + d[6]), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.F((int)this.C, (int)this.D, (int)this.A) + d[7]), (int)19);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.F((int)this.B, (int)this.C, (int)this.D) + d[8]), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.F((int)this.A, (int)this.B, (int)this.C) + d[9]), (int)7);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.F((int)this.D, (int)this.A, (int)this.B) + d[10]), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.F((int)this.C, (int)this.D, (int)this.A) + d[11]), (int)19);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.F((int)this.B, (int)this.C, (int)this.D) + d[12]), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.F((int)this.A, (int)this.B, (int)this.C) + d[13]), (int)7);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.F((int)this.D, (int)this.A, (int)this.B) + d[14]), (int)11);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.F((int)this.C, (int)this.D, (int)this.A) + d[15]), (int)19);
    }

    byte[] getOutput() {
        int bufferIndex = (int)(this.count & 0x3FL);
        int padLen = bufferIndex < 56 ? 56 - bufferIndex : 120 - bufferIndex;
        byte[] postBytes = new byte[padLen + 8];
        postBytes[0] = -128;
        int i = 0;
        while (true) {
            if (i >= 8) {
                this.update(postBytes);
                byte[] result = new byte[16];
                NTLMEngineImpl.writeULong((byte[])result, (int)this.A, (int)0);
                NTLMEngineImpl.writeULong((byte[])result, (int)this.B, (int)4);
                NTLMEngineImpl.writeULong((byte[])result, (int)this.C, (int)8);
                NTLMEngineImpl.writeULong((byte[])result, (int)this.D, (int)12);
                return result;
            }
            postBytes[padLen + i] = (byte)(this.count * 8L >>> 8 * i);
            ++i;
        }
    }

    void update(byte[] input) {
        int curBufferPos = (int)(this.count & 0x3FL);
        int inputIndex = 0;
        while (true) {
            int transferAmt;
            if (input.length - inputIndex + curBufferPos < this.dataBuffer.length) {
                if (inputIndex >= input.length) return;
                transferAmt = input.length - inputIndex;
                System.arraycopy(input, inputIndex, this.dataBuffer, curBufferPos, transferAmt);
                this.count += (long)transferAmt;
                curBufferPos += transferAmt;
                return;
            }
            transferAmt = this.dataBuffer.length - curBufferPos;
            System.arraycopy(input, inputIndex, this.dataBuffer, curBufferPos, transferAmt);
            this.count += (long)transferAmt;
            curBufferPos = 0;
            inputIndex += transferAmt;
            this.processBuffer();
        }
    }

    protected void round2(int[] d) {
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.G((int)this.B, (int)this.C, (int)this.D) + d[0] + 1518500249), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.G((int)this.A, (int)this.B, (int)this.C) + d[4] + 1518500249), (int)5);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.G((int)this.D, (int)this.A, (int)this.B) + d[8] + 1518500249), (int)9);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.G((int)this.C, (int)this.D, (int)this.A) + d[12] + 1518500249), (int)13);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.G((int)this.B, (int)this.C, (int)this.D) + d[1] + 1518500249), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.G((int)this.A, (int)this.B, (int)this.C) + d[5] + 1518500249), (int)5);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.G((int)this.D, (int)this.A, (int)this.B) + d[9] + 1518500249), (int)9);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.G((int)this.C, (int)this.D, (int)this.A) + d[13] + 1518500249), (int)13);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.G((int)this.B, (int)this.C, (int)this.D) + d[2] + 1518500249), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.G((int)this.A, (int)this.B, (int)this.C) + d[6] + 1518500249), (int)5);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.G((int)this.D, (int)this.A, (int)this.B) + d[10] + 1518500249), (int)9);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.G((int)this.C, (int)this.D, (int)this.A) + d[14] + 1518500249), (int)13);
        this.A = NTLMEngineImpl.rotintlft((int)(this.A + NTLMEngineImpl.G((int)this.B, (int)this.C, (int)this.D) + d[3] + 1518500249), (int)3);
        this.D = NTLMEngineImpl.rotintlft((int)(this.D + NTLMEngineImpl.G((int)this.A, (int)this.B, (int)this.C) + d[7] + 1518500249), (int)5);
        this.C = NTLMEngineImpl.rotintlft((int)(this.C + NTLMEngineImpl.G((int)this.D, (int)this.A, (int)this.B) + d[11] + 1518500249), (int)9);
        this.B = NTLMEngineImpl.rotintlft((int)(this.B + NTLMEngineImpl.G((int)this.C, (int)this.D, (int)this.A) + d[15] + 1518500249), (int)13);
    }

    protected void processBuffer() {
        int[] d = new int[16];
        int i = 0;
        while (true) {
            if (i >= 16) {
                int AA = this.A;
                int BB = this.B;
                int CC = this.C;
                int DD = this.D;
                this.round1(d);
                this.round2(d);
                this.round3(d);
                this.A += AA;
                this.B += BB;
                this.C += CC;
                this.D += DD;
                return;
            }
            d[i] = (this.dataBuffer[i * 4] & 0xFF) + ((this.dataBuffer[i * 4 + 1] & 0xFF) << 8) + ((this.dataBuffer[i * 4 + 2] & 0xFF) << 16) + ((this.dataBuffer[i * 4 + 3] & 0xFF) << 24);
            ++i;
        }
    }
}
