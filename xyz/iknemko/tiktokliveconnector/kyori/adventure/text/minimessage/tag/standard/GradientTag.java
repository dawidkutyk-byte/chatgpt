/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag$Argument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.AbstractColorChangingTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.AbstractColorChangingTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike;

class GradientTag
extends AbstractColorChangingTag {
    private static final TextColor DEFAULT_WHITE = TextColor.color((int)0xFFFFFF);
    private static final TextColor DEFAULT_BLACK = TextColor.color((int)0);
    static final TagResolver RESOLVER = SerializableResolver.claimingComponent((String)"gradient", GradientTag::create, AbstractColorChangingTag::claimComponent);
    private int index = 0;
    @Range(from=-1L, to=1L) double phase;
    private final boolean negativePhase;
    private static final String GRADIENT = "gradient";
    private double multiplier = 1.0;
    private final TextColor[] colors;

    public int hashCode() {
        int result = Objects.hash(this.index, this.phase);
        result = 31 * result + Arrays.hashCode(this.colors);
        return result;
    }

    protected TextColor color() {
        double position = (double)this.index * this.multiplier + this.phase;
        int lowUnclamped = (int)Math.floor(position);
        int high = (int)Math.ceil(position) % this.colors.length;
        int low = lowUnclamped % this.colors.length;
        return TextColor.lerp((float)((float)position - (float)lowUnclamped), (RGBLike)this.colors[low], (RGBLike)this.colors[high]);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"phase", (double)this.phase), ExaminableProperty.of((String)"colors", (Object)this.colors));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        GradientTag that = (GradientTag)((Object)other);
        return this.index == that.index && this.phase == that.phase && Arrays.equals(this.colors, that.colors);
    }

    protected void advanceColor() {
        ++this.index;
    }

    static Tag create(ArgumentQueue args, Context ctx) {
        List<TextColor> textColors;
        double phase = 0.0;
        if (!args.hasNext()) {
            textColors = Collections.emptyList();
            return new GradientTag(phase, textColors, ctx);
        }
        textColors = new ArrayList();
        while (args.hasNext()) {
            OptionalDouble possiblePhase;
            Tag.Argument arg = args.pop();
            String argValue = arg.value();
            TextColor color = ColorTagResolver.resolveColorOrNull((String)argValue);
            if (color != null) {
                textColors.add(color);
                continue;
            }
            if (args.hasNext() || !(possiblePhase = arg.asDouble()).isPresent()) throw ctx.newException(String.format("Unable to parse a color from '%s'. Please use named colors or hex (#RRGGBB) colors.", argValue), args);
            phase = possiblePhase.getAsDouble();
            if (!(phase < -1.0) && !(phase > 1.0)) break;
            throw ctx.newException(String.format("Gradient phase is out of range (%s). Must be in the range [-1.0, 1.0] (inclusive).", phase), args);
        }
        if (textColors.size() != 1) return new GradientTag(phase, textColors, ctx);
        throw ctx.newException("Invalid gradient, not enough colors. Gradients must have at least two colors.", args);
    }

    protected void init() {
        this.multiplier = this.size() == 1 ? 0.0 : (double)(this.colors.length - 1) / (double)(this.size() - 1);
        this.phase *= (double)(this.colors.length - 1);
        this.index = 0;
    }

    GradientTag(double phase, List<TextColor> colors, Context ctx) {
        super(ctx);
        this.colors = colors.isEmpty() ? new TextColor[]{DEFAULT_WHITE, DEFAULT_BLACK} : colors.toArray(new TextColor[0]);
        if (phase < 0.0) {
            this.negativePhase = true;
            this.phase = 1.0 + phase;
            Collections.reverse(Arrays.asList(this.colors));
        } else {
            this.negativePhase = false;
            this.phase = phase;
        }
    }

    @NotNull
    protected Consumer<TokenEmitter> preserveData() {
        double phase;
        TextColor[] colors;
        if (this.negativePhase) {
            colors = Arrays.copyOf(this.colors, this.colors.length);
            Collections.reverse(Arrays.asList(colors));
            phase = this.phase - 1.0;
        } else {
            colors = this.colors;
            phase = this.phase;
        }
        return emit -> {
            emit.tag(GRADIENT);
            if (colors.length != 2 || !colors[0].equals(DEFAULT_WHITE) || !colors[1].equals(DEFAULT_BLACK)) {
                for (TextColor color : colors) {
                    if (color instanceof NamedTextColor) {
                        emit.argument((String)NamedTextColor.NAMES.keyOrThrow((Object)((NamedTextColor)color)));
                        continue;
                    }
                    emit.argument(color.asHexString());
                }
            }
            if (phase == 0.0) return;
            emit.argument(Double.toString(phase));
        };
    }
}
