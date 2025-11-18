/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage$Signature
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;

static final class SignedMessageImpl.SignatureImpl
implements SignedMessage.Signature {
    final byte[] signature;

    public byte[] bytes() {
        return this.signature;
    }

    SignedMessageImpl.SignatureImpl(byte[] signature) {
        this.signature = signature;
    }
}
