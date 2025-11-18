/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

public interface Inserting
extends Tag {
    default public boolean allowsChildren() {
        return true;
    }

    @NotNull
    public Component value();
}
