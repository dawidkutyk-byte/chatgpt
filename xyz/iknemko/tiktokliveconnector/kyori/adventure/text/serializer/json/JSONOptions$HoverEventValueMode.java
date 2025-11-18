/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json;

public static enum JSONOptions.HoverEventValueMode {
    SNAKE_CASE,
    CAMEL_CASE,
    VALUE_FIELD,
    ALL;

    @Deprecated
    public static final JSONOptions.HoverEventValueMode MODERN_ONLY;
    @Deprecated
    public static final JSONOptions.HoverEventValueMode LEGACY_ONLY;
    @Deprecated
    public static final JSONOptions.HoverEventValueMode BOTH;

    static {
        MODERN_ONLY = CAMEL_CASE;
        LEGACY_ONLY = VALUE_FIELD;
        BOTH = ALL;
    }
}
