/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Text
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl;

static final class PayloadImpl.TextImpl
extends PayloadImpl
implements ClickEvent.Payload.Text {
    private final String value;

    PayloadImpl.TextImpl(@NotNull String value) {
        this.value = value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @NotNull
    public String value() {
        return this.value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (String)this.value));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        PayloadImpl.TextImpl that = (PayloadImpl.TextImpl)((Object)other);
        return Objects.equals(this.value, that.value);
    }
}
