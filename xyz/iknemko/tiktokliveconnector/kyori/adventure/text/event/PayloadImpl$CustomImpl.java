/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Custom
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.PayloadImpl;

static final class PayloadImpl.CustomImpl
extends PayloadImpl
implements ClickEvent.Payload.Custom {
    private final String data;
    private final Key key;

    @NotNull
    public String data() {
        return this.data;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        PayloadImpl.CustomImpl that = (PayloadImpl.CustomImpl)((Object)other);
        return Objects.equals(this.key, that.key) && Objects.equals(this.data, that.data);
    }

    @NotNull
    public Key key() {
        return this.key;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"key", (Object)this.key), ExaminableProperty.of((String)"data", (String)this.data));
    }

    public int hashCode() {
        int result = this.key.hashCode();
        result = 31 * result + this.data.hashCode();
        return result;
    }

    PayloadImpl.CustomImpl(@NotNull Key key, @NotNull String data) {
        this.key = key;
        this.data = data;
    }
}
