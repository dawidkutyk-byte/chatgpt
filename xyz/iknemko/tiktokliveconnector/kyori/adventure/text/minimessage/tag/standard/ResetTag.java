/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.ParserDirective
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.ParserDirective;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class ResetTag {
    private static final String RESET = "reset";
    static final TagResolver RESOLVER = TagResolver.resolver((String)"reset", (Tag)ParserDirective.RESET);

    private ResetTag() {
    }
}
