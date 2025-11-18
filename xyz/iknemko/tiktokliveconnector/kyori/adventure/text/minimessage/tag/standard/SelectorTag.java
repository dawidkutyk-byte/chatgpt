/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.StandardTags
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.Set;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.StandardTags;

final class SelectorTag {
    private static final String SELECTOR = "selector";
    private static final String SEL = "sel";
    static final TagResolver RESOLVER = SerializableResolver.claimingComponent((Set)StandardTags.names((String[])new String[]{"sel", "selector"}), SelectorTag::create, SelectorTag::claim);

    private SelectorTag() {
    }

    @Nullable
    static Emitable claim(Component input) {
        if (!(input instanceof SelectorComponent)) {
            return null;
        }
        SelectorComponent st = (SelectorComponent)input;
        return emit -> {
            emit.tag(SEL);
            emit.argument(st.pattern());
            if (st.separator() == null) return;
            emit.argument(st.separator());
        };
    }

    static Tag create(ArgumentQueue args, Context ctx) throws ParsingException {
        String key = args.popOr("A selection key is required").value();
        Component separator = null;
        if (!args.hasNext()) return Tag.inserting((Component)Component.selector((String)key, separator));
        separator = ctx.deserialize(args.pop().value());
        return Tag.inserting((Component)Component.selector((String)key, separator));
    }
}
