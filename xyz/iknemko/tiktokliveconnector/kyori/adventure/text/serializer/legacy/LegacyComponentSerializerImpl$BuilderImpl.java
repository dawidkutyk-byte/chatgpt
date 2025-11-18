/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.Objects;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class LegacyComponentSerializerImpl.BuilderImpl
implements LegacyComponentSerializer.Builder {
    private ComponentFlattener flattener;
    private char character = (char)167;
    private TextReplacementConfig urlReplacementConfig = null;
    private boolean hexColours = false;
    private char hexCharacter = (char)35;
    private boolean useTerriblyStupidHexFormat = false;

    LegacyComponentSerializerImpl.BuilderImpl(@NotNull LegacyComponentSerializerImpl serializer) {
        this();
        this.character = LegacyComponentSerializerImpl.access$400((LegacyComponentSerializerImpl)serializer);
        this.hexCharacter = LegacyComponentSerializerImpl.access$800((LegacyComponentSerializerImpl)serializer);
        this.urlReplacementConfig = LegacyComponentSerializerImpl.access$900((LegacyComponentSerializerImpl)serializer);
        this.hexColours = LegacyComponentSerializerImpl.access$1000((LegacyComponentSerializerImpl)serializer);
        this.useTerriblyStupidHexFormat = LegacyComponentSerializerImpl.access$1100((LegacyComponentSerializerImpl)serializer);
        this.flattener = LegacyComponentSerializerImpl.access$1200((LegacyComponentSerializerImpl)serializer);
    }

    @NotNull
    public LegacyComponentSerializer build() {
        return new LegacyComponentSerializerImpl(this.character, this.hexCharacter, this.urlReplacementConfig, this.hexColours, this.useTerriblyStupidHexFormat, this.flattener);
    }

    @NotNull
    public LegacyComponentSerializer.Builder flattener(@NotNull ComponentFlattener flattener) {
        this.flattener = Objects.requireNonNull(flattener, "flattener");
        return this;
    }

    @NotNull
    public LegacyComponentSerializer.Builder hexColors() {
        this.hexColours = true;
        return this;
    }

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls(@NotNull Pattern pattern, @Nullable Style style) {
        Objects.requireNonNull(pattern, "pattern");
        this.urlReplacementConfig = (TextReplacementConfig)TextReplacementConfig.builder().match(pattern).replacement(url -> {
            String clickUrl = url.content();
            if (LegacyComponentSerializerImpl.URL_SCHEME_PATTERN.matcher(clickUrl).find()) return (style == null ? url : (TextComponent.Builder)url.style(style)).clickEvent(ClickEvent.openUrl((String)clickUrl));
            clickUrl = "http://" + clickUrl;
            return (style == null ? url : (TextComponent.Builder)url.style(style)).clickEvent(ClickEvent.openUrl((String)clickUrl));
        }).build();
        return this;
    }

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls(@NotNull Pattern pattern) {
        return this.extractUrls(pattern, null);
    }

    @NotNull
    public LegacyComponentSerializer.Builder character(char legacyCharacter) {
        this.character = legacyCharacter;
        return this;
    }

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls(@Nullable Style style) {
        return this.extractUrls(LegacyComponentSerializerImpl.DEFAULT_URL_PATTERN, style);
    }

    LegacyComponentSerializerImpl.BuilderImpl() {
        this.flattener = ComponentFlattener.basic();
        LegacyComponentSerializerImpl.BUILDER.accept(this);
    }

    @NotNull
    public LegacyComponentSerializer.Builder extractUrls() {
        return this.extractUrls(LegacyComponentSerializerImpl.DEFAULT_URL_PATTERN, null);
    }

    @NotNull
    public LegacyComponentSerializer.Builder hexCharacter(char legacyHexCharacter) {
        this.hexCharacter = legacyHexCharacter;
        return this;
    }

    @NotNull
    public LegacyComponentSerializer.Builder useUnusualXRepeatedCharacterHexFormat() {
        this.useTerriblyStupidHexFormat = true;
        return this;
    }
}
