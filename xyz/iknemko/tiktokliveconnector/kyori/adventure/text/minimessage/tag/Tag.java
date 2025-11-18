/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.CallbackStylingTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.InsertingImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcessTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.StylingTagImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.CallbackStylingTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.InsertingImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcessTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.StylingTagImpl;

public interface Tag {
    @NotNull
    public static Tag selfClosingInserting(@NotNull ComponentLike value) {
        return Tag.selfClosingInserting(Objects.requireNonNull(value, "value").asComponent());
    }

    @NotNull
    public static PreProcess preProcessParsed(@NotNull String content) {
        return new PreProcessTagImpl(Objects.requireNonNull(content, "content"));
    }

    @NotNull
    public static Tag selfClosingInserting(@NotNull Component content) {
        return new InsertingImpl(false, Objects.requireNonNull(content, "content must not be null"));
    }

    @NotNull
    public static Tag inserting(@NotNull ComponentLike value) {
        return Tag.inserting(Objects.requireNonNull(value, "value").asComponent());
    }

    @NotNull
    public static Tag styling(Consumer<Style.Builder> styles) {
        return new CallbackStylingTagImpl(styles);
    }

    @NotNull
    public static Tag inserting(@NotNull Component content) {
        return new InsertingImpl(true, Objects.requireNonNull(content, "content must not be null"));
    }

    @NotNull
    public static Tag styling(StyleBuilderApplicable ... actions) {
        Objects.requireNonNull(actions, "actions");
        int i = 0;
        int length = actions.length;
        while (i < length) {
            if (actions[i] == null) {
                throw new NullPointerException("actions[" + i + "]");
            }
            ++i;
        }
        return new StylingTagImpl(Arrays.copyOf(actions, actions.length));
    }
}
