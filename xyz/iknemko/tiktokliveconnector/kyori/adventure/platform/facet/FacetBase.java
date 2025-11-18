/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public abstract class FacetBase<V>
implements Facet<V> {
    protected final Class<? extends V> viewerClass;

    public boolean isSupported() {
        return this.viewerClass != null;
    }

    public boolean isApplicable(@NotNull V viewer) {
        return this.viewerClass != null && this.viewerClass.isInstance(viewer);
    }

    protected FacetBase(@Nullable Class<? extends V> viewerClass) {
        this.viewerClass = viewerClass;
    }
}
