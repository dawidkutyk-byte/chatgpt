/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node;

@ApiStatus.OverrideOnly
public interface Modifying
extends Tag {
    public Component apply(@NotNull Component var1, int var2);

    default public void postVisit() {
    }

    default public void visit(@NotNull Node current, int depth) {
    }
}
