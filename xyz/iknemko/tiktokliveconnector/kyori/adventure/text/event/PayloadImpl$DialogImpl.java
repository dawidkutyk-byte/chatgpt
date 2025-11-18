/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ZX\u00f3v
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Dialog
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl;

static final class PayloadImpl.DialogImpl
extends PayloadImpl
implements ClickEvent.Payload.Dialog {
    private final ZX\u00f3v dialogLike;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"dialog", (Object)this.dialogLike));
    }

    @NotNull
    public ZX\u00f3v dialog() {
        return this.dialogLike;
    }

    PayloadImpl.DialogImpl(@NotNull ZX\u00f3v dialogLike) {
        this.dialogLike = dialogLike;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        PayloadImpl.DialogImpl that = (PayloadImpl.DialogImpl)((Object)other);
        return Objects.equals(this.dialogLike, that.dialogLike);
    }

    public int hashCode() {
        return this.dialogLike.hashCode();
    }
}
