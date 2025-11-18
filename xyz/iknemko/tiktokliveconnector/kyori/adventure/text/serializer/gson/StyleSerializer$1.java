/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Action
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;

static class StyleSerializer.1 {
    static final /* synthetic */ int[] $SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action;

    static {
        $SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action = new int[ClickEvent.Action.values().length];
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.OPEN_URL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.RUN_COMMAND.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.SUGGEST_COMMAND.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.CHANGE_PAGE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.COPY_TO_CLIPBOARD.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.CUSTOM.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.SHOW_DIALOG.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            StyleSerializer.1.$SwitchMap$net$kyori$adventure$text$event$ClickEvent$Action[ClickEvent.Action.OPEN_FILE.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
