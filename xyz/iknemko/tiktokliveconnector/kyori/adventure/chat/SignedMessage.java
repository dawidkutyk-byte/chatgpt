/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage$Signature
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessageImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessageImpl$SignatureImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.chat;

import java.time.Instant;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.chat.SignedMessageImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;

@ApiStatus.NonExtendable
public interface SignedMessage
extends Identified,
Examinable {
    @NotNull
    @Contract(pure=true)
    public Instant timestamp();

    @NotNull
    @Contract(pure=true)
    public String message();

    @Contract(value="_, _ -> new", pure=true)
    @NotNull
    public static SignedMessage system(@NotNull String message, @Nullable ComponentLike unsignedContent) {
        return new SignedMessageImpl(message, ComponentLike.unbox((ComponentLike)unsignedContent));
    }

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"timestamp", (Object)this.timestamp()), ExaminableProperty.of((String)"salt", (long)this.salt()), ExaminableProperty.of((String)"signature", (Object)this.signature()), ExaminableProperty.of((String)"unsignedContent", (Object)this.unsignedContent()), ExaminableProperty.of((String)"message", (String)this.message()));
    }

    @NotNull
    @Contract(value="_ -> new", pure=true)
    public static Signature signature(byte[] signature) {
        return new SignedMessageImpl.SignatureImpl(signature);
    }

    @Contract(pure=true)
    default public boolean canDelete() {
        return this.signature() != null;
    }

    @Contract(pure=true)
    public long salt();

    @Nullable
    @Contract(pure=true)
    public Signature signature();

    @Contract(pure=true)
    default public boolean isSystem() {
        return this.identity() == Identity.nil();
    }

    @Contract(pure=true)
    @Nullable
    public Component unsignedContent();
}
