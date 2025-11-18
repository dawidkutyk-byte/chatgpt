/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$ClickEventValueMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$HoverEventValueMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$ShadowColorEmitMode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions$ShowItemHoverDataMode
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema$Mutable
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Versioned
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

public final class JSONOptions {
    private static final int VERSION_1_16 = 2526;
    private static final OptionState MOST_COMPATIBLE;
    private static final OptionSchema.Mutable UNSAFE_SCHEMA;
    private static final int VERSION_1_20_5 = 3819;
    private static final int VERSION_1_20_3 = 3679;
    public static final Option<Boolean> EMIT_COMPACT_TEXT_COMPONENT;
    private static final int VERSION_1_21_5 = 4298;
    public static final Option<Boolean> EMIT_HOVER_SHOW_ENTITY_ID_AS_INT_ARRAY;
    private static final int VERSION_INITIAL = 0;
    private static final int VERSION_1_21_6 = 4422;
    public static final Option<HoverEventValueMode> EMIT_HOVER_EVENT_TYPE;
    private static final OptionSchema SCHEMA;
    public static final Option<ClickEventValueMode> EMIT_CLICK_EVENT_TYPE;
    public static final Option<Boolean> EMIT_HOVER_SHOW_ENTITY_KEY_AS_TYPE_AND_UUID_AS_ID;
    private static final OptionState.Versioned BY_DATA_VERSION;
    private static final int VERSION_1_21_4 = 4174;
    public static final Option<Boolean> EMIT_RGB;
    public static final Option<Boolean> VALIDATE_STRICT_EVENTS;
    public static final Option<Boolean> EMIT_CHANGE_PAGE_CLICK_EVENT_PAGE_AS_STRING;
    public static final Option<ShowItemHoverDataMode> SHOW_ITEM_HOVER_DATA_MODE;
    public static final Option<Boolean> EMIT_DEFAULT_ITEM_HOVER_QUANTITY;
    public static final Option<ShadowColorEmitMode> SHADOW_COLOR_MODE;

    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull OptionState.Versioned byDataVersion() {
        return BY_DATA_VERSION;
    }

    private JSONOptions() {
    }

    @NotNull
    public static OptionState compatibility() {
        return MOST_COMPATIBLE;
    }

    private static String key(String value) {
        return "adventure:json/" + value;
    }

    @NotNull
    public static OptionSchema schema() {
        return SCHEMA;
    }

    static {
        UNSAFE_SCHEMA = OptionSchema.globalSchema();
        EMIT_RGB = Option.booleanOption((String)JSONOptions.key("emit/rgb"), (boolean)true);
        EMIT_HOVER_EVENT_TYPE = UNSAFE_SCHEMA.enumOption(JSONOptions.key("emit/hover_value_mode"), HoverEventValueMode.class, (Enum)HoverEventValueMode.SNAKE_CASE);
        EMIT_CLICK_EVENT_TYPE = Option.enumOption((String)JSONOptions.key("emit/click_value_mode"), ClickEventValueMode.class, (Enum)ClickEventValueMode.SNAKE_CASE);
        EMIT_COMPACT_TEXT_COMPONENT = UNSAFE_SCHEMA.booleanOption(JSONOptions.key("emit/compact_text_component"), true);
        EMIT_HOVER_SHOW_ENTITY_ID_AS_INT_ARRAY = UNSAFE_SCHEMA.booleanOption(JSONOptions.key("emit/hover_show_entity_id_as_int_array"), true);
        EMIT_HOVER_SHOW_ENTITY_KEY_AS_TYPE_AND_UUID_AS_ID = UNSAFE_SCHEMA.booleanOption(JSONOptions.key("emit/hover_show_entity_key_as_type_and_uuid_as_id"), false);
        VALIDATE_STRICT_EVENTS = UNSAFE_SCHEMA.booleanOption(JSONOptions.key("validate/strict_events"), true);
        EMIT_DEFAULT_ITEM_HOVER_QUANTITY = UNSAFE_SCHEMA.booleanOption(JSONOptions.key("emit/default_item_hover_quantity"), true);
        SHOW_ITEM_HOVER_DATA_MODE = UNSAFE_SCHEMA.enumOption(JSONOptions.key("emit/show_item_hover_data"), ShowItemHoverDataMode.class, (Enum)ShowItemHoverDataMode.EMIT_EITHER);
        SHADOW_COLOR_MODE = UNSAFE_SCHEMA.enumOption(JSONOptions.key("emit/shadow_color"), ShadowColorEmitMode.class, (Enum)ShadowColorEmitMode.EMIT_INTEGER);
        EMIT_CHANGE_PAGE_CLICK_EVENT_PAGE_AS_STRING = UNSAFE_SCHEMA.booleanOption(JSONOptions.key("emit/change_page_click_event_page_as_string"), false);
        SCHEMA = OptionSchema.childSchema((OptionSchema)UNSAFE_SCHEMA).frozenView();
        BY_DATA_VERSION = SCHEMA.versionedStateBuilder().version(0, b -> b.value(EMIT_HOVER_EVENT_TYPE, (Object)HoverEventValueMode.VALUE_FIELD).value(EMIT_CLICK_EVENT_TYPE, (Object)ClickEventValueMode.CAMEL_CASE).value(EMIT_RGB, (Object)false).value(EMIT_HOVER_SHOW_ENTITY_ID_AS_INT_ARRAY, (Object)false).value(EMIT_HOVER_SHOW_ENTITY_KEY_AS_TYPE_AND_UUID_AS_ID, (Object)true).value(VALIDATE_STRICT_EVENTS, (Object)false).value(EMIT_DEFAULT_ITEM_HOVER_QUANTITY, (Object)false).value(SHOW_ITEM_HOVER_DATA_MODE, (Object)ShowItemHoverDataMode.EMIT_LEGACY_NBT).value(SHADOW_COLOR_MODE, (Object)ShadowColorEmitMode.NONE).value(EMIT_CHANGE_PAGE_CLICK_EVENT_PAGE_AS_STRING, (Object)true)).version(2526, b -> b.value(EMIT_HOVER_EVENT_TYPE, (Object)HoverEventValueMode.CAMEL_CASE).value(EMIT_RGB, (Object)true)).version(3679, b -> b.value(EMIT_COMPACT_TEXT_COMPONENT, (Object)true).value(EMIT_HOVER_SHOW_ENTITY_ID_AS_INT_ARRAY, (Object)true).value(VALIDATE_STRICT_EVENTS, (Object)true)).version(3819, b -> b.value(EMIT_DEFAULT_ITEM_HOVER_QUANTITY, (Object)true).value(SHOW_ITEM_HOVER_DATA_MODE, (Object)ShowItemHoverDataMode.EMIT_DATA_COMPONENTS)).version(4174, b -> b.value(SHADOW_COLOR_MODE, (Object)ShadowColorEmitMode.EMIT_INTEGER)).version(4298, b -> b.value(EMIT_HOVER_EVENT_TYPE, (Object)HoverEventValueMode.SNAKE_CASE).value(EMIT_CLICK_EVENT_TYPE, (Object)ClickEventValueMode.SNAKE_CASE).value(EMIT_HOVER_SHOW_ENTITY_KEY_AS_TYPE_AND_UUID_AS_ID, (Object)false)).version(4422, b -> b.value(EMIT_CHANGE_PAGE_CLICK_EVENT_PAGE_AS_STRING, (Object)false)).build();
        MOST_COMPATIBLE = SCHEMA.stateBuilder().value(EMIT_HOVER_EVENT_TYPE, (Object)HoverEventValueMode.ALL).value(EMIT_CLICK_EVENT_TYPE, (Object)ClickEventValueMode.BOTH).value(EMIT_HOVER_SHOW_ENTITY_ID_AS_INT_ARRAY, (Object)false).value(EMIT_COMPACT_TEXT_COMPONENT, (Object)false).value(VALIDATE_STRICT_EVENTS, (Object)false).value(SHOW_ITEM_HOVER_DATA_MODE, (Object)ShowItemHoverDataMode.EMIT_EITHER).value(SHADOW_COLOR_MODE, (Object)ShadowColorEmitMode.EMIT_INTEGER).build();
    }
}
