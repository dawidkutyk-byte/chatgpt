/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointer;

@ApiStatus.Internal
public final class FacetPointers {
    private static final String NAMESPACE = "adventure_platform";
    public static final Pointer<Type> TYPE;
    public static final Pointer<Key> WORLD;
    public static final Pointer<String> SERVER;

    static {
        SERVER = Pointer.pointer(String.class, (Key)Key.key((String)NAMESPACE, (String)"server"));
        WORLD = Pointer.pointer(Key.class, (Key)Key.key((String)NAMESPACE, (String)"world"));
        TYPE = Pointer.pointer(Type.class, (Key)Key.key((String)NAMESPACE, (String)"type"));
    }

    private FacetPointers() {
    }
}
