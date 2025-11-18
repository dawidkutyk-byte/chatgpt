/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Int
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl;

static final class PayloadImpl.IntImpl
extends PayloadImpl
implements ClickEvent.Payload.Int {
    private final int integer;

    PayloadImpl.IntImpl(int integer) {
        this.integer = integer;
    }

    public int integer() {
        return this.integer;
    }

    public int hashCode() {
        return this.integer;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"integer", (int)this.integer));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        PayloadImpl.IntImpl that = (PayloadImpl.IntImpl)((Object)other);
        return Objects.equals(this.integer, that.integer);
    }
}
