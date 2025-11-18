/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;

public static interface Facet.Pointers<V>
extends Facet<V> {
    public void contributePointers(V var1, Pointers.Builder var2);
}
