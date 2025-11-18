/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree;

import java.util.List;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApiStatus.NonExtendable
public interface Node {
    @NotNull
    public List<? extends Node> children();

    @NotNull
    public String toString();

    @Nullable
    public Node parent();
}
