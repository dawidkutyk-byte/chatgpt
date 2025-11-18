/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyPattern$Namespace
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.key;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyPattern;

public interface Namespaced {
    @KeyPattern.Namespace
    @NotNull
    public String namespace();
}
