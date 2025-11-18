/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;

final class LocalePointered
implements Pointered {
    private final Pointers pointers;

    @NotNull
    public Pointers pointers() {
        return this.pointers;
    }

    LocalePointered(@NotNull Locale locale) {
        this.pointers = (Pointers)Pointers.builder().withStatic(Identity.LOCALE, (Object)locale).build();
    }
}
