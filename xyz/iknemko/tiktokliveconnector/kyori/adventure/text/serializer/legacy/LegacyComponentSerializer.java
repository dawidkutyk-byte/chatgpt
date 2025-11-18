/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$Instances
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public interface LegacyComponentSerializer
extends ComponentSerializer<Component, TextComponent, String>,
Buildable<LegacyComponentSerializer, Builder> {
    public static final char AMPERSAND_CHAR = '&';
    public static final char SECTION_CHAR = '\u00a7';
    public static final char HEX_CHAR = '#';

    @NotNull
    public TextComponent deserialize(@NotNull String var1);

    @NotNull
    public static LegacyComponentSerializer legacyAmpersand() {
        return LegacyComponentSerializerImpl.Instances.AMPERSAND;
    }

    @Nullable
    public static LegacyFormat parseChar(char character) {
        return LegacyComponentSerializerImpl.legacyFormat((char)character);
    }

    @NotNull
    public String serialize(@NotNull Component var1);

    @NotNull
    public static Builder builder() {
        return new LegacyComponentSerializerImpl.BuilderImpl();
    }

    @NotNull
    public static LegacyComponentSerializer legacySection() {
        return LegacyComponentSerializerImpl.Instances.SECTION;
    }

    @NotNull
    public static LegacyComponentSerializer legacy(char legacyCharacter) {
        if (legacyCharacter == '\u00a7') {
            return LegacyComponentSerializer.legacySection();
        }
        if (legacyCharacter != '&') return LegacyComponentSerializer.builder().character(legacyCharacter).build();
        return LegacyComponentSerializer.legacyAmpersand();
    }
}
