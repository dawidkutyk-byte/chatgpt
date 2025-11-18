/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
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
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.StandardTags;

final class NbtTag {
    private static final String NBT = "nbt";
    private static final String ENTITY = "entity";
    private static final String BLOCK = "block";
    private static final String INTERPRET = "interpret";
    private static final String STORAGE = "storage";
    static final TagResolver RESOLVER = SerializableResolver.claimingComponent((Set)StandardTags.names((String[])new String[]{"nbt", "data"}), NbtTag::resolve, NbtTag::emit);
    private static final String DATA = "data";

    @Nullable
    static Emitable emit(Component comp) {
        String id;
        String type;
        if (comp instanceof BlockNBTComponent) {
            type = BLOCK;
            id = ((BlockNBTComponent)comp).pos().asString();
        } else if (comp instanceof EntityNBTComponent) {
            type = ENTITY;
            id = ((EntityNBTComponent)comp).selector();
        } else {
            if (!(comp instanceof StorageNBTComponent)) return null;
            type = STORAGE;
            id = ((StorageNBTComponent)comp).storage().asString();
        }
        return out -> {
            NBTComponent nbt = (NBTComponent)comp;
            out.tag(NBT).argument(type).argument(id).argument(nbt.nbtPath());
            if (nbt.separator() != null) {
                out.argument(nbt.separator());
            }
            if (!nbt.interpret()) return;
            out.argument(INTERPRET);
        };
    }

    private NbtTag() {
    }

    static Tag resolve(ArgumentQueue args, Context ctx) throws ParsingException {
        BlockNBTComponent.Builder builder;
        String type = args.popOr("a type of block, entity, or storage is required").lowerValue();
        if (BLOCK.equals(type)) {
            String pos = args.popOr("A position is required").value();
            try {
                builder = Component.blockNBT().pos(BlockNBTComponent.Pos.fromString((String)pos));
            }
            catch (IllegalArgumentException ex) {
                throw ctx.newException(ex.getMessage(), args);
            }
        } else if (ENTITY.equals(type)) {
            builder = Component.entityNBT().selector(args.popOr("A selector is required").value());
        } else {
            if (!STORAGE.equals(type)) throw ctx.newException("Unknown nbt tag type '" + type + "'", args);
            builder = Component.storageNBT().storage(Key.key((String)args.popOr("A storage key is required").value()));
        }
        builder.nbtPath(args.popOr("An NBT path is required").value());
        if (!args.hasNext()) return Tag.inserting((Component)builder.build());
        String popped = args.pop().value();
        if (INTERPRET.equalsIgnoreCase(popped)) {
            builder.interpret(true);
        } else {
            builder.separator((ComponentLike)ctx.deserialize(popped));
            if (!args.hasNext()) return Tag.inserting((Component)builder.build());
            if (!args.pop().value().equalsIgnoreCase(INTERPRET)) return Tag.inserting((Component)builder.build());
            builder.interpret(true);
        }
        return Tag.inserting((Component)builder.build());
    }
}
