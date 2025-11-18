/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node;

@ApiStatus.NonExtendable
public static interface Node.Root
extends Node {
    @NotNull
    public String input();
}
