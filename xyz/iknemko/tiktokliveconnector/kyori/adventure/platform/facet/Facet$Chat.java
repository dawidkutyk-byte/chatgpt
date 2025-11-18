/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public static interface Facet.Chat<V, M>
extends Facet.Message<V, M> {
    public void sendMessage(@NotNull V var1, @NotNull Identity var2, @NotNull M var3, @NotNull Object var4);
}
