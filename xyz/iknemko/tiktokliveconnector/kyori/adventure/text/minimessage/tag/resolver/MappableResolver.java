/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

interface MappableResolver {
    public boolean contributeToMap(@NotNull Map<String, Tag> var1);
}
