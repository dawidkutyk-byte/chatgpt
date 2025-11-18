/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import org.bukkit.command.CommandSender;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

static final class BukkitFacet.CommandSenderPointers
extends BukkitFacet<CommandSender>
implements Facet.Pointers<CommandSender> {
    BukkitFacet.CommandSenderPointers() {
        super(CommandSender.class);
    }

    public void contributePointers(CommandSender viewer, Pointers.Builder builder) {
        builder.withDynamic(Identity.NAME, () -> ((CommandSender)viewer).getName());
        builder.withStatic(PermissionChecker.POINTER, perm -> {
            if (!viewer.isPermissionSet(perm)) return TriState.NOT_SET;
            return viewer.hasPermission(perm) ? TriState.TRUE : TriState.FALSE;
        });
    }
}
