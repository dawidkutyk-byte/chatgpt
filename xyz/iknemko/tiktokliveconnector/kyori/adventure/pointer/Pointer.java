/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointerImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer;

import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.PointerImpl;

public interface Pointer<V>
extends Examinable {
    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"type", this.type()), ExaminableProperty.of((String)"key", (Object)this.key()));
    }

    @NotNull
    public Key key();

    @NotNull
    public static <V> Pointer<V> pointer(@NotNull Class<V> type, @NotNull Key key) {
        return new PointerImpl(type, key);
    }

    @NotNull
    public Class<V> type();
}
