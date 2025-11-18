/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$BossBar
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.connection.UserConnection;
import java.util.Collection;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

public static class ViaFacet.BossBar.Builder<V>
extends ViaFacet<V>
implements Facet.BossBar.Builder<V, Facet.BossBar<V>> {
    public ViaFacet.BossBar.Builder(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction) {
        super(viewerClass, connectionFunction, 713);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Facet.BossBar<V> createBossBar(@NotNull Collection<V> viewer) {
        return new ViaFacet.BossBar("1_16", "1_15_2", this.viewerClass, arg_0 -> ((ViaFacet.BossBar.Builder)this).findConnection(arg_0), viewer, null);
    }
}
