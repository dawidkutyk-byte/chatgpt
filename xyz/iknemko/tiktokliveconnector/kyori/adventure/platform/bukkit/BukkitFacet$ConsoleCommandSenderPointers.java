/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.ConsoleCommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.ConsoleCommandSender;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;

static final class BukkitFacet.ConsoleCommandSenderPointers
extends BukkitFacet<ConsoleCommandSender>
implements Facet.Pointers<ConsoleCommandSender> {
    BukkitFacet.ConsoleCommandSenderPointers() {
        super(ConsoleCommandSender.class);
    }

    public void contributePointers(ConsoleCommandSender viewer, Pointers.Builder builder) {
        builder.withStatic(FacetPointers.TYPE, (Object)FacetPointers.Type.CONSOLE);
    }
}
