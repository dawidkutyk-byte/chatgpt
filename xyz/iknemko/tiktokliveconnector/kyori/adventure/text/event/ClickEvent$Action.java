/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Custom
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Dialog
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Int
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload$Text
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public static enum ClickEvent.Action {
    OPEN_URL("open_url", true, ClickEvent.Payload.Text.class),
    OPEN_FILE("open_file", false, ClickEvent.Payload.Text.class),
    RUN_COMMAND("run_command", true, ClickEvent.Payload.Text.class),
    SUGGEST_COMMAND("suggest_command", true, ClickEvent.Payload.Text.class),
    CHANGE_PAGE("change_page", true, ClickEvent.Payload.Int.class),
    COPY_TO_CLIPBOARD("copy_to_clipboard", true, ClickEvent.Payload.Text.class),
    SHOW_DIALOG("show_dialog", false, ClickEvent.Payload.Dialog.class),
    CUSTOM("custom", true, ClickEvent.Payload.Custom.class);

    private final String name;
    private final boolean readable;
    private final Class<? extends ClickEvent.Payload> payloadType;
    public static final Index<String, ClickEvent.Action> NAMES;

    @NotNull
    public Class<? extends ClickEvent.Payload> payloadType() {
        return this.payloadType;
    }

    @NotNull
    public String toString() {
        return this.name;
    }

    public boolean supports(@NotNull ClickEvent.Payload payload) {
        Objects.requireNonNull(payload, "payload");
        return this.payloadType.isAssignableFrom(payload.getClass());
    }

    private ClickEvent.Action(@NotNull String name, boolean readable, Class<? extends ClickEvent.Payload> payloadType) {
        this.name = name;
        this.readable = readable;
        this.payloadType = payloadType;
    }

    static {
        NAMES = Index.create(ClickEvent.Action.class, constant -> constant.name);
    }

    public boolean readable() {
        return this.readable;
    }
}
