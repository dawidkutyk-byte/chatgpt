/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ZX\u00f3v
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback$Options
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackInternals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Int
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Text
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackOptionsImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;

/*
 * Exception performing whole class analysis ignored.
 */
public final class ClickEvent
implements StyleBuilderApplicable,
Examinable {
    private final Action action;
    private final Payload payload;

    @NotNull
    public static ClickEvent showDialog(@NotNull ZX\u00f3v dialog) {
        Objects.requireNonNull(dialog, "dialog");
        return new ClickEvent(Action.SHOW_DIALOG, (Payload)Payload.dialog((ZX\u00f3v)dialog));
    }

    @NotNull
    public static ClickEvent copyToClipboard(@NotNull String text) {
        return new ClickEvent(Action.COPY_TO_CLIPBOARD, (Payload)Payload.string((String)text));
    }

    @NotNull
    public static ClickEvent openFile(@NotNull String file) {
        return new ClickEvent(Action.OPEN_FILE, (Payload)Payload.string((String)file));
    }

    @NotNull
    public static ClickEvent callback(@NotNull ClickCallback<Audience> function, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ClickCallback.Options options) {
        return ClickCallbackInternals.PROVIDER.create(Objects.requireNonNull(function, "function"), Objects.requireNonNull(options, "options"));
    }

    @NotNull
    public Payload payload() {
        return this.payload;
    }

    @NotNull
    public static ClickEvent callback(@NotNull ClickCallback<Audience> function) {
        return ClickCallbackInternals.PROVIDER.create(Objects.requireNonNull(function, "function"), ClickCallbackOptionsImpl.DEFAULT);
    }

    @NotNull
    public static ClickEvent openUrl(@NotNull URL url) {
        return ClickEvent.openUrl(url.toExternalForm());
    }

    @NotNull
    public static ClickEvent runCommand(@NotNull String command) {
        return new ClickEvent(Action.RUN_COMMAND, (Payload)Payload.string((String)command));
    }

    private ClickEvent(@NotNull Action action, @NotNull Payload payload) {
        if (!action.supports(payload)) {
            throw new IllegalArgumentException("Action " + action + " does not support payload " + payload);
        }
        this.action = Objects.requireNonNull(action, "action");
        this.payload = Objects.requireNonNull(payload, "payload");
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @Deprecated
    @NotNull
    public static ClickEvent clickEvent(@NotNull Action action, @NotNull String value) {
        if (action == Action.CHANGE_PAGE) {
            return ClickEvent.changePage(value);
        }
        if (action.payloadType().equals(Payload.Text.class)) return new ClickEvent(action, (Payload)Payload.string((String)value));
        throw new IllegalArgumentException("Action " + action + " does not support string payloads");
    }

    @NotNull
    public static ClickEvent openUrl(@NotNull String url) {
        return new ClickEvent(Action.OPEN_URL, (Payload)Payload.string((String)url));
    }

    @NotNull
    public static ClickEvent custom(@NotNull Key key, @NotNull String data) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(data, "data");
        return new ClickEvent(Action.CUSTOM, (Payload)Payload.custom((Key)key, (String)data));
    }

    @NotNull
    public Action action() {
        return this.action;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        ClickEvent that = (ClickEvent)other;
        return this.action == that.action && Objects.equals(this.payload, that.payload);
    }

    @NotNull
    public static ClickEvent suggestCommand(@NotNull String command) {
        return new ClickEvent(Action.SUGGEST_COMMAND, (Payload)Payload.string((String)command));
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"action", (Object)this.action), ExaminableProperty.of((String)"payload", (Object)this.payload));
    }

    public int hashCode() {
        int result = this.action.hashCode();
        result = 31 * result + this.payload.hashCode();
        return result;
    }

    @NotNull
    public static ClickEvent changePage(int page) {
        return new ClickEvent(Action.CHANGE_PAGE, (Payload)Payload.integer((int)page));
    }

    @NotNull
    public static ClickEvent callback(@NotNull ClickCallback<Audience> function, @NotNull @NotNull Consumer<// Could not load outer class - annotation placement on inner may be incorrect
    @NotNull ClickCallback.Options.Builder> optionsBuilder) {
        return ClickCallbackInternals.PROVIDER.create(Objects.requireNonNull(function, "function"), (ClickCallback.Options)AbstractBuilder.configureAndBuild((AbstractBuilder)ClickCallback.Options.builder(), Objects.requireNonNull(optionsBuilder, "optionsBuilder")));
    }

    @Deprecated
    @NotNull
    public static ClickEvent changePage(@NotNull String page) {
        Objects.requireNonNull(page, "page");
        return new ClickEvent(Action.CHANGE_PAGE, (Payload)Payload.integer((int)Integer.parseInt(page)));
    }

    public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder style) {
        style.clickEvent(this);
    }

    @Deprecated
    @NotNull
    public String value() {
        if (this.payload instanceof Payload.Text) {
            return ((Payload.Text)this.payload).value();
        }
        if (this.action != Action.CHANGE_PAGE) throw new IllegalStateException("Payload is not a string payload, is " + this.payload);
        return String.valueOf(((Payload.Int)this.payload).integer());
    }
}
