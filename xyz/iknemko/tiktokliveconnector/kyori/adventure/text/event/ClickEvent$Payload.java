/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ZX\u00f3v
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl$CustomImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl$DialogImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl$IntImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl$TextImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl;

public static interface ClickEvent.Payload
extends Examinable {
    public static // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Payload.Int integer(int integer) {
        return new PayloadImpl.IntImpl(integer);
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Payload.Custom custom(@NotNull Key key, @NotNull String data) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(data, "data");
        return new PayloadImpl.CustomImpl(key, data);
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Payload.Dialog dialog(@NotNull ZX\u00f3v dialog) {
        Objects.requireNonNull(dialog, "dialog");
        return new PayloadImpl.DialogImpl(dialog);
    }

    public static // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Payload.Text string(@NotNull String value) {
        Objects.requireNonNull(value, "value");
        return new PayloadImpl.TextImpl(value);
    }
}
