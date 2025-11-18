/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Title
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

public static interface Facet.TitlePacket<V, M, C, T>
extends Facet.Title<V, M, C, T> {
    public static final int ACTION_TITLE = 0;
    public static final int ACTION_TIMES = 3;
    public static final int ACTION_ACTIONBAR = 2;
    public static final int ACTION_CLEAR = 4;
    public static final int ACTION_SUBTITLE = 1;
    public static final int ACTION_RESET = 5;
}
