/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public final class Formatter {
    public static TagResolver joining(@TagPattern @NotNull String key, ComponentLike ... components) {
        return Formatter.joining(key, Arrays.asList(components));
    }

    @NotNull
    public static TagResolver date(@TagPattern @NotNull String key, @NotNull TemporalAccessor time) {
        return TagResolver.resolver((String)key, (argumentQueue, context) -> {
            String format = argumentQueue.popOr("Format expected.").value();
            return Tag.inserting((Component)context.deserialize(DateTimeFormatter.ofPattern(format).format(time)));
        });
    }

    @NotNull
    public static TagResolver number(@TagPattern @NotNull String key, @NotNull Number number) {
        return TagResolver.resolver((String)key, (argumentQueue, context) -> {
            NumberFormat decimalFormat;
            if (argumentQueue.hasNext()) {
                String locale = argumentQueue.pop().value();
                if (argumentQueue.hasNext()) {
                    String format = argumentQueue.pop().value();
                    decimalFormat = new DecimalFormat(format, new DecimalFormatSymbols(Locale.forLanguageTag(locale)));
                } else {
                    decimalFormat = locale.contains(".") ? new DecimalFormat(locale, DecimalFormatSymbols.getInstance()) : DecimalFormat.getInstance(Locale.forLanguageTag(locale));
                }
            } else {
                decimalFormat = DecimalFormat.getInstance();
            }
            return Tag.inserting((Component)context.deserialize(decimalFormat.format(number)));
        });
    }

    public static TagResolver booleanChoice(@TagPattern @NotNull String key, boolean value) {
        return TagResolver.resolver((String)key, (argumentQueue, context) -> {
            String trueCase = argumentQueue.popOr("True format expected.").value();
            String falseCase = argumentQueue.popOr("False format expected.").value();
            return Tag.inserting((Component)context.deserialize(value ? trueCase : falseCase));
        });
    }

    public static TagResolver joining(@TagPattern @NotNull String key, @NotNull Iterable<? extends ComponentLike> components) {
        return TagResolver.resolver((String)key, (argumentQueue, context) -> {
            if (!argumentQueue.hasNext()) {
                return Tag.inserting((Component)Component.join((JoinConfiguration)JoinConfiguration.noSeparators(), (Iterable)components));
            }
            String separator = argumentQueue.pop().value();
            JoinConfiguration.Builder configBuilder = JoinConfiguration.builder().separator((ComponentLike)context.deserialize(separator));
            if (argumentQueue.hasNext()) {
                String lastSeparator = argumentQueue.pop().value();
                configBuilder.lastSeparator((ComponentLike)context.deserialize(lastSeparator));
            }
            if (argumentQueue.hasNext()) {
                String lastSeparatorIfSerial = argumentQueue.pop().value();
                configBuilder.lastSeparatorIfSerial((ComponentLike)context.deserialize(lastSeparatorIfSerial));
            }
            JoinConfiguration config = (JoinConfiguration)configBuilder.build();
            return Tag.inserting((Component)Component.join((JoinConfiguration)config, (Iterable)components));
        });
    }

    private Formatter() {
    }

    @NotNull
    public static TagResolver choice(@TagPattern @NotNull String key, Number number) {
        return TagResolver.resolver((String)key, (argumentQueue, context) -> {
            String format = argumentQueue.popOr("Format expected.").value();
            ChoiceFormat choiceFormat = new ChoiceFormat(format);
            return Tag.inserting((Component)context.deserialize(choiceFormat.format(number)));
        });
    }
}
