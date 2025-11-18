/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.StandardTags;

final class TranslatableTag {
    static final TagResolver RESOLVER = SerializableResolver.claimingComponent((Set)StandardTags.names((String[])new String[]{"lang", "translate", "tr"}), TranslatableTag::create, TranslatableTag::claim);
    private static final String TR = "tr";
    private static final String TRANSLATE = "translate";
    private static final String LANG = "lang";

    static Tag create(ArgumentQueue args, Context ctx) throws ParsingException {
        List with;
        String key = args.popOr("A translation key is required").value();
        if (!args.hasNext()) {
            with = Collections.emptyList();
            return Tag.inserting((Component)Component.translatable((String)key, with));
        }
        with = new ArrayList();
        while (args.hasNext()) {
            with.add(ctx.deserialize(args.pop().value()));
        }
        return Tag.inserting((Component)Component.translatable((String)key, with));
    }

    private TranslatableTag() {
    }

    @Nullable
    static Emitable claim(Component input) {
        if (!(input instanceof TranslatableComponent)) return null;
        if (((TranslatableComponent)input).fallback() != null) {
            return null;
        }
        TranslatableComponent tr = (TranslatableComponent)input;
        return emit -> {
            emit.tag(LANG);
            emit.argument(tr.key());
            Iterator iterator = tr.arguments().iterator();
            while (iterator.hasNext()) {
                TranslationArgument with = (TranslationArgument)iterator.next();
                emit.argument(with.asComponent());
            }
        };
    }
}
