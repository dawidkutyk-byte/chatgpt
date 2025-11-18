/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 */
package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;

static class FramedataImpl1.1 {
    static final /* synthetic */ int[] $SwitchMap$org$java_websocket$enums$Opcode;

    static {
        $SwitchMap$org$java_websocket$enums$Opcode = new int[Opcode.values().length];
        try {
            FramedataImpl1.1.$SwitchMap$org$java_websocket$enums$Opcode[Opcode.PING.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FramedataImpl1.1.$SwitchMap$org$java_websocket$enums$Opcode[Opcode.PONG.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FramedataImpl1.1.$SwitchMap$org$java_websocket$enums$Opcode[Opcode.TEXT.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FramedataImpl1.1.$SwitchMap$org$java_websocket$enums$Opcode[Opcode.BINARY.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FramedataImpl1.1.$SwitchMap$org$java_websocket$enums$Opcode[Opcode.CLOSING.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            FramedataImpl1.1.$SwitchMap$org$java_websocket$enums$Opcode[Opcode.CONTINUOUS.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
