/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.ChatType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static interface ChatType.Bound
extends Examinable {
    @NotNull
    @Contract(pure=true)
    public Component name();

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"type", (Object)this.type()), ExaminableProperty.of((String)"name", (Object)this.name()), ExaminableProperty.of((String)"target", (Object)this.target()));
    }

    @Contract(pure=true)
    @Nullable
    public Component target();

    @Contract(pure=true)
    @NotNull
    public ChatType type();
}
