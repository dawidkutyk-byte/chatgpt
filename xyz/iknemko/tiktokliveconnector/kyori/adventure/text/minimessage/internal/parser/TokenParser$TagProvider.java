/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag$Argument
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

@ApiStatus.Internal
public static interface TokenParser.TagProvider {
    @Nullable
    default public Tag resolve(@NotNull String name) {
        return this.resolve(name, Collections.emptyList(), null);
    }

    @Nullable
    public Tag resolve(@NotNull String var1, @NotNull List<? extends Tag.Argument> var2, @Nullable Token var3);

    @Nullable
    default public Tag resolve(@NotNull TagNode node) {
        return this.resolve(TokenParser.TagProvider.sanitizePlaceholderName(node.name()), node.parts().subList(1, node.parts().size()), node.token());
    }

    @NotNull
    public static String sanitizePlaceholderName(@NotNull String name) {
        return name.toLowerCase(Locale.ROOT);
    }
}
