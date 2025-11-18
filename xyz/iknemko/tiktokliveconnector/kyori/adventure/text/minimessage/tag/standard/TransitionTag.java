/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag$Argument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
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
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.ColorTagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.RGBLike;

public final class TransitionTag
implements Examinable,
Inserting {
    private final float phase;
    private final boolean negativePhase;
    public static final String TRANSITION = "transition";
    private final TextColor[] colors;
    static final TagResolver RESOLVER = TagResolver.resolver((String)"transition", TransitionTag::create);

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"phase", (float)this.phase), ExaminableProperty.of((String)"colors", (Object)this.colors));
    }

    @NotNull
    public Component value() {
        return Component.text((String)"", (TextColor)this.color());
    }

    public int hashCode() {
        int result = Objects.hash(Float.valueOf(this.phase));
        result = 31 * result + Arrays.hashCode(this.colors);
        return result;
    }

    private TextColor color() {
        float steps = 1.0f / (float)(this.colors.length - 1);
        int colorIndex = 1;
        while (colorIndex < this.colors.length) {
            float val = (float)colorIndex * steps;
            if (val >= this.phase) {
                float factor = 1.0f + (this.phase - val) * (float)(this.colors.length - 1);
                if (!this.negativePhase) return TextColor.lerp((float)factor, (RGBLike)this.colors[colorIndex - 1], (RGBLike)this.colors[colorIndex]);
                return TextColor.lerp((float)(1.0f - factor), (RGBLike)this.colors[colorIndex], (RGBLike)this.colors[colorIndex - 1]);
            }
            ++colorIndex;
        }
        return this.colors[0];
    }

    static Tag create(ArgumentQueue args, Context ctx) {
        List<TextColor> textColors;
        float phase = 0.0f;
        if (!args.hasNext()) {
            textColors = Collections.emptyList();
            return new TransitionTag(phase, textColors);
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
            phase = (float)possiblePhase.getAsDouble();
            if (!(phase < -1.0f) && !(phase > 1.0f)) break;
            throw ctx.newException(String.format("Gradient phase is out of range (%s). Must be in the range [-1.0f, 1.0f] (inclusive).", Float.valueOf(phase)), args);
        }
        if (textColors.size() >= 2) return new TransitionTag(phase, textColors);
        throw ctx.newException("Invalid transition, not enough colors. Transitions must have at least two colors.", args);
    }

    private TransitionTag(float phase, List<TextColor> colors) {
        if (phase < 0.0f) {
            this.negativePhase = true;
            this.phase = 1.0f + phase;
            Collections.reverse(colors);
        } else {
            this.negativePhase = false;
            this.phase = phase;
        }
        this.colors = colors.isEmpty() ? new TextColor[]{TextColor.color((int)0xFFFFFF), TextColor.color((int)0)} : colors.toArray(new TextColor[0]);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        TransitionTag that = (TransitionTag)other;
        return this.phase == that.phase && Arrays.equals(this.colors, that.colors);
    }
}
