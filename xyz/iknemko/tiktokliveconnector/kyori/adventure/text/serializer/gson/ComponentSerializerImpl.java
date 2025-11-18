/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Pos
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonHacks
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonHacks;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.SerializerFactory;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class ComponentSerializerImpl
extends TypeAdapter<Component> {
    private final Gson gson;
    static final Type TRANSLATABLE_ARGUMENT_LIST_TYPE;
    private final boolean emitCompactTextComponent;
    static final Type COMPONENT_LIST_TYPE;

    @Override
    public BuildableComponent<?, ?> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        if (token == JsonToken.STRING) return Component.text((String)GsonHacks.readString((JsonReader)in));
        if (token == JsonToken.NUMBER) return Component.text((String)GsonHacks.readString((JsonReader)in));
        if (token == JsonToken.BOOLEAN) {
            return Component.text((String)GsonHacks.readString((JsonReader)in));
        }
        if (token != JsonToken.BEGIN_ARRAY) {
            if (token != JsonToken.BEGIN_OBJECT) {
                throw ComponentSerializerImpl.notSureHowToDeserialize(in.getPath());
            }
        } else {
            ComponentBuilder parent = null;
            in.beginArray();
            while (in.hasNext()) {
                BuildableComponent<?, ?> child = this.read(in);
                if (parent == null) {
                    parent = child.toBuilder();
                    continue;
                }
                parent.append(child);
            }
            if (parent == null) {
                throw ComponentSerializerImpl.notSureHowToDeserialize(in.getPath());
            }
            in.endArray();
            return parent.build();
        }
        JsonObject style = new JsonObject();
        List extra = Collections.emptyList();
        String text = null;
        String translate = null;
        String translateFallback = null;
        List translateWith = null;
        String scoreName = null;
        String scoreObjective = null;
        String scoreValue = null;
        String selector = null;
        String keybind = null;
        String nbt = null;
        boolean nbtInterpret = false;
        BlockNBTComponent.Pos nbtBlock = null;
        String nbtEntity = null;
        Key nbtStorage = null;
        BuildableComponent<?, ?> separator = null;
        in.beginObject();
        while (true) {
            block43: {
                TextComponent.Builder builder;
                if (in.hasNext()) {
                    String fieldName = in.nextName();
                    if (fieldName.equals("text")) {
                        text = GsonHacks.readString((JsonReader)in);
                        continue;
                    }
                    if (fieldName.equals("translate")) {
                        translate = in.nextString();
                        continue;
                    }
                    if (fieldName.equals("fallback")) {
                        translateFallback = in.nextString();
                        continue;
                    }
                    if (fieldName.equals("with")) {
                        translateWith = (List)this.gson.fromJson(in, TRANSLATABLE_ARGUMENT_LIST_TYPE);
                        continue;
                    }
                    if (fieldName.equals("score")) {
                        in.beginObject();
                        break block43;
                    } else {
                        if (fieldName.equals("selector")) {
                            selector = in.nextString();
                            continue;
                        }
                        if (fieldName.equals("keybind")) {
                            keybind = in.nextString();
                            continue;
                        }
                        if (fieldName.equals("nbt")) {
                            nbt = in.nextString();
                            continue;
                        }
                        if (fieldName.equals("interpret")) {
                            nbtInterpret = in.nextBoolean();
                            continue;
                        }
                        if (fieldName.equals("block")) {
                            nbtBlock = (BlockNBTComponent.Pos)this.gson.fromJson(in, (Type)SerializerFactory.BLOCK_NBT_POS_TYPE);
                            continue;
                        }
                        if (fieldName.equals("entity")) {
                            nbtEntity = in.nextString();
                            continue;
                        }
                        if (fieldName.equals("storage")) {
                            nbtStorage = (Key)this.gson.fromJson(in, (Type)SerializerFactory.KEY_TYPE);
                            continue;
                        }
                        if (fieldName.equals("extra")) {
                            extra = (List)this.gson.fromJson(in, COMPONENT_LIST_TYPE);
                            continue;
                        }
                        if (fieldName.equals("separator")) {
                            separator = this.read(in);
                            continue;
                        }
                        style.add(fieldName, (JsonElement)this.gson.fromJson(in, (Type)((Object)JsonElement.class)));
                        continue;
                    }
                }
                if (text != null) {
                    builder = Component.text().content(text);
                } else if (translate != null) {
                    builder = translateWith != null ? Component.translatable().key(translate).fallback(translateFallback).arguments(translateWith) : Component.translatable().key(translate).fallback(translateFallback);
                } else if (scoreName != null && scoreObjective != null) {
                    builder = scoreValue == null ? Component.score().name(scoreName).objective(scoreObjective) : Component.score().name(scoreName).objective(scoreObjective).value(scoreValue);
                } else if (selector != null) {
                    builder = Component.selector().pattern(selector).separator(separator);
                } else if (keybind != null) {
                    builder = Component.keybind().keybind(keybind);
                } else {
                    if (nbt == null) throw ComponentSerializerImpl.notSureHowToDeserialize(in.getPath());
                    if (nbtBlock != null) {
                        builder = ComponentSerializerImpl.nbt(Component.blockNBT(), nbt, nbtInterpret, separator).pos(nbtBlock);
                    } else if (nbtEntity != null) {
                        builder = ComponentSerializerImpl.nbt(Component.entityNBT(), nbt, nbtInterpret, separator).selector(nbtEntity);
                    } else {
                        if (nbtStorage == null) throw ComponentSerializerImpl.notSureHowToDeserialize(in.getPath());
                        builder = ComponentSerializerImpl.nbt(Component.storageNBT(), nbt, nbtInterpret, separator).storage(nbtStorage);
                    }
                }
                builder.style((Style)this.gson.fromJson((JsonElement)style, SerializerFactory.STYLE_TYPE)).append(extra);
                in.endObject();
                return builder.build();
            }
            while (in.hasNext()) {
                String scoreFieldName = in.nextName();
                if (scoreFieldName.equals("name")) {
                    scoreName = in.nextString();
                    continue;
                }
                if (scoreFieldName.equals("objective")) {
                    scoreObjective = in.nextString();
                    continue;
                }
                if (scoreFieldName.equals("value")) {
                    scoreValue = in.nextString();
                    continue;
                }
                in.skipValue();
            }
            if (scoreName == null) throw new JsonParseException("A score component requires a name and objective");
            if (scoreObjective == null) {
                throw new JsonParseException("A score component requires a name and objective");
            }
            in.endObject();
        }
    }

    static JsonParseException notSureHowToDeserialize(Object element) {
        return new JsonParseException("Don't know how to turn " + element + " into a Component");
    }

    private void serializeSeparator(JsonWriter out, @Nullable Component separator) throws IOException {
        if (separator == null) return;
        out.name("separator");
        this.write(out, separator);
    }

    static TypeAdapter<Component> create(OptionState features, Gson gson) {
        return new ComponentSerializerImpl((Boolean)features.value(JSONOptions.EMIT_COMPACT_TEXT_COMPONENT), gson).nullSafe();
    }

    private static <C extends NBTComponent<C, B>, B extends NBTComponentBuilder<C, B>> B nbt(B builder, String nbt, boolean interpret, @Nullable Component separator) {
        return (B)builder.nbtPath(nbt).interpret(interpret).separator((ComponentLike)separator);
    }

    static {
        COMPONENT_LIST_TYPE = new /* Unavailable Anonymous Inner Class!! */.getType();
        TRANSLATABLE_ARGUMENT_LIST_TYPE = new /* Unavailable Anonymous Inner Class!! */.getType();
    }

    private ComponentSerializerImpl(boolean emitCompactTextComponent, Gson gson) {
        this.emitCompactTextComponent = emitCompactTextComponent;
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, Component value) throws IOException {
        JsonElement style;
        if (value instanceof TextComponent && value.children().isEmpty() && !value.hasStyling() && this.emitCompactTextComponent) {
            out.value(((TextComponent)value).content());
            return;
        }
        out.beginObject();
        if (value.hasStyling() && (style = this.gson.toJsonTree(value.style(), SerializerFactory.STYLE_TYPE)).isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : style.getAsJsonObject().entrySet()) {
                out.name(entry.getKey());
                this.gson.toJson(entry.getValue(), out);
            }
        }
        if (!value.children().isEmpty()) {
            out.name("extra");
            this.gson.toJson((Object)value.children(), COMPONENT_LIST_TYPE, out);
        }
        if (value instanceof TextComponent) {
            out.name("text");
            out.value(((TextComponent)value).content());
        } else if (value instanceof TranslatableComponent) {
            TranslatableComponent translatable = (TranslatableComponent)value;
            out.name("translate");
            out.value(translatable.key());
            @Nullable String fallback = translatable.fallback();
            if (fallback != null) {
                out.name("fallback");
                out.value(fallback);
            }
            if (!translatable.arguments().isEmpty()) {
                out.name("with");
                this.gson.toJson((Object)translatable.arguments(), TRANSLATABLE_ARGUMENT_LIST_TYPE, out);
            }
        } else if (value instanceof ScoreComponent) {
            ScoreComponent score = (ScoreComponent)value;
            out.name("score");
            out.beginObject();
            out.name("name");
            out.value(score.name());
            out.name("objective");
            out.value(score.objective());
            if (score.value() != null) {
                out.name("value");
                out.value(score.value());
            }
            out.endObject();
        } else if (value instanceof SelectorComponent) {
            SelectorComponent selector = (SelectorComponent)value;
            out.name("selector");
            out.value(selector.pattern());
            this.serializeSeparator(out, selector.separator());
        } else if (value instanceof KeybindComponent) {
            out.name("keybind");
            out.value(((KeybindComponent)value).keybind());
        } else {
            if (!(value instanceof NBTComponent)) throw ComponentSerializerImpl.notSureHowToSerialize(value);
            NBTComponent nbt = (NBTComponent)value;
            out.name("nbt");
            out.value(nbt.nbtPath());
            out.name("interpret");
            out.value(nbt.interpret());
            this.serializeSeparator(out, nbt.separator());
            if (value instanceof BlockNBTComponent) {
                out.name("block");
                this.gson.toJson((Object)((BlockNBTComponent)value).pos(), (Type)SerializerFactory.BLOCK_NBT_POS_TYPE, out);
            } else if (value instanceof EntityNBTComponent) {
                out.name("entity");
                out.value(((EntityNBTComponent)value).selector());
            } else {
                if (!(value instanceof StorageNBTComponent)) throw ComponentSerializerImpl.notSureHowToSerialize(value);
                out.name("storage");
                this.gson.toJson((Object)((StorageNBTComponent)value).storage(), (Type)SerializerFactory.KEY_TYPE, out);
            }
        }
        out.endObject();
    }

    private static IllegalArgumentException notSureHowToSerialize(Component component) {
        return new IllegalArgumentException("Don't know how to serialize " + component + " as a Component");
    }
}
