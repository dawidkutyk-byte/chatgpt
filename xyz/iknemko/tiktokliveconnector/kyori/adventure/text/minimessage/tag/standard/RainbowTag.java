/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.AbstractColorChangingTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.AbstractColorChangingTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike;

final class RainbowTag
extends AbstractColorChangingTag {
    private static final String REVERSE = "!";
    private final double dividedPhase;
    static final TagResolver RESOLVER = SerializableResolver.claimingComponent((String)"rainbow", RainbowTag::create, AbstractColorChangingTag::claimComponent);
    private static final String RAINBOW = "rainbow";
    private final boolean reversed;
    private int colorIndex = 0;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"phase", (double)this.dividedPhase));
    }

    public int hashCode() {
        return Objects.hash(this.colorIndex, this.dividedPhase);
    }

    private RainbowTag(boolean reversed, int phase, Context ctx) {
        super(ctx);
        this.reversed = reversed;
        this.dividedPhase = (double)phase / 10.0;
    }

    @NotNull
    protected Consumer<TokenEmitter> preserveData() {
        boolean reversed = this.reversed;
        int phase = (int)Math.round(this.dividedPhase * 10.0);
        return emit -> {
            emit.tag(RAINBOW);
            if (reversed && phase != 0) {
                emit.argument(REVERSE + phase);
            } else if (reversed) {
                emit.argument(REVERSE);
            } else {
                if (phase == 0) return;
                emit.argument(Integer.toString(phase));
            }
        };
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        RainbowTag that = (RainbowTag)((Object)other);
        return this.colorIndex == that.colorIndex && this.dividedPhase == that.dividedPhase;
    }

    protected void advanceColor() {
        this.colorIndex = this.reversed ? (this.colorIndex == 0 ? this.size() - 1 : --this.colorIndex) : ++this.colorIndex;
    }

    static Tag create(ArgumentQueue args, Context ctx) {
        boolean reversed = false;
        int phase = 0;
        if (!args.hasNext()) return new RainbowTag(reversed, phase, ctx);
        String value = args.pop().value();
        if (value.startsWith(REVERSE)) {
            reversed = true;
            value = value.substring(REVERSE.length());
        }
        if (value.length() <= 0) return new RainbowTag(reversed, phase, ctx);
        try {
            phase = Integer.parseInt(value);
        }
        catch (NumberFormatException ex) {
            throw ctx.newException("Expected phase, got " + value);
        }
        return new RainbowTag(reversed, phase, ctx);
    }

    protected void init() {
        if (!this.reversed) return;
        this.colorIndex = this.size() - 1;
    }

    protected TextColor color() {
        float index = this.colorIndex;
        float hue = (float)(((double)(index / (float)this.size()) + this.dividedPhase) % 1.0);
        return TextColor.color((HSVLike)HSVLike.hsvLike((float)hue, (float)1.0f, (float)1.0f));
    }
}
