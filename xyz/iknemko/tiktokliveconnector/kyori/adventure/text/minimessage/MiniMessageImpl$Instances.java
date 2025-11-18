/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

static final class MiniMessageImpl.Instances {
    static final MiniMessage INSTANCE = MiniMessageImpl.access$000().map(MiniMessage.Provider::miniMessage).orElseGet(() -> new MiniMessageImpl(TagResolver.standard(), false, true, null, MiniMessageImpl.DEFAULT_NO_OP, MiniMessageImpl.DEFAULT_COMPACTING_METHOD));

    MiniMessageImpl.Instances() {
    }
}
