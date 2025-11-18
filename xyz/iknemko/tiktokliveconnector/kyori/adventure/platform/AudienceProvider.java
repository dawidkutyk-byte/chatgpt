/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;

public interface AudienceProvider
extends AutoCloseable {
    @NotNull
    public Audience players();

    @NotNull
    public Audience console();

    @NotNull
    public ComponentFlattener flattener();

    @NotNull
    public Audience permission(@NotNull String var1);

    @Override
    public void close();

    @NotNull
    public Audience server(@NotNull String var1);

    @NotNull
    public Audience all();

    @NotNull
    default public Audience permission(@NotNull Key permission) {
        return this.permission(permission.namespace() + '.' + permission.value());
    }

    @NotNull
    public Audience player(@NotNull UUID var1);

    @NotNull
    public Audience world(@NotNull Key var1);
}
