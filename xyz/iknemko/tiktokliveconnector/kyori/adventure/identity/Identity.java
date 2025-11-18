/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.IdentityImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.NilIdentity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.identity;

import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identified;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.IdentityImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.NilIdentity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public interface Identity
extends Examinable,
Identified {
    public static final Pointer<Locale> LOCALE;
    public static final Pointer<String> NAME;
    public static final Pointer<UUID> UUID;
    public static final Pointer<Component> DISPLAY_NAME;

    @NotNull
    default public Identity identity() {
        return this;
    }

    @NotNull
    public UUID uuid();

    static {
        NAME = Pointer.pointer(String.class, (Key)Key.key((String)"adventure", (String)"name"));
        UUID = Pointer.pointer(UUID.class, (Key)Key.key((String)"adventure", (String)"uuid"));
        DISPLAY_NAME = Pointer.pointer(Component.class, (Key)Key.key((String)"adventure", (String)"display_name"));
        LOCALE = Pointer.pointer(Locale.class, (Key)Key.key((String)"adventure", (String)"locale"));
    }

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"uuid", (Object)this.uuid()));
    }

    @NotNull
    public static Identity identity(@NotNull UUID uuid) {
        if (!uuid.equals(NilIdentity.NIL_UUID)) return new IdentityImpl(uuid);
        return NilIdentity.INSTANCE;
    }

    @NotNull
    public static Identity nil() {
        return NilIdentity.INSTANCE;
    }
}
