/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

/*
 * Exception performing whole class analysis ignored.
 */
final class DecorationMap.EntrySet
extends AbstractSet<Map.Entry<TextDecoration, TextDecoration.State>> {
    DecorationMap.EntrySet() {
    }

    @Override
    @NotNull
    public Iterator<Map.Entry<TextDecoration, TextDecoration.State>> iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @Override
    public int size() {
        return DecorationMap.access$100();
    }
}
