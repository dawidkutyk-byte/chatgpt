/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattenerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener;

@FunctionalInterface
static interface ComponentFlattenerImpl.Handler {
    public void handle(ComponentFlattenerImpl var1, Component var2, FlattenerListener var3, int var4, int var5);
}
