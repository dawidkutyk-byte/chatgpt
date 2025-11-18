/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;

class BukkitFacet<V extends CommandSender>
extends FacetBase<V> {
    protected BukkitFacet(@Nullable Class<? extends V> viewerClass) {
        super(viewerClass);
    }
}
