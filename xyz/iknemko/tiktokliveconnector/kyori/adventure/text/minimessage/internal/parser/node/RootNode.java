/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node$Root
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node;

public final class RootNode
extends ElementNode
implements Node.Root {
    private final String beforePreprocessing;

    @NotNull
    public String input() {
        return this.beforePreprocessing;
    }

    public RootNode(@NotNull String sourceMessage, @NotNull String beforePreprocessing) {
        super(null, null, sourceMessage);
        this.beforePreprocessing = beforePreprocessing;
    }
}
