/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

private static final class ComponentFlattenerImpl.StackEntry {
    final int depth;
    final Component component;

    ComponentFlattenerImpl.StackEntry(Component component, int depth) {
        this.component = component;
        this.depth = depth;
    }
}
