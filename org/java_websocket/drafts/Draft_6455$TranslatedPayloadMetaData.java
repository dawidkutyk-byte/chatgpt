/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.drafts;

private class Draft_6455.TranslatedPayloadMetaData {
    private int realPackageSize;
    private int payloadLength;

    static /* synthetic */ int access$100(Draft_6455.TranslatedPayloadMetaData x0) {
        return x0.getRealPackageSize();
    }

    Draft_6455.TranslatedPayloadMetaData(int newPayloadLength, int newRealPackageSize) {
        this.payloadLength = newPayloadLength;
        this.realPackageSize = newRealPackageSize;
    }

    static /* synthetic */ int access$000(Draft_6455.TranslatedPayloadMetaData x0) {
        return x0.getPayloadLength();
    }

    private int getPayloadLength() {
        return this.payloadLength;
    }

    private int getRealPackageSize() {
        return this.realPackageSize;
    }
}
