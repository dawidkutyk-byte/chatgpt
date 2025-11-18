/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializerImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.json.JSONOptions;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

static final class GsonComponentSerializerImpl.Instances {
    static final GsonComponentSerializer INSTANCE = GsonComponentSerializerImpl.access$000().map(GsonComponentSerializer.Provider::gson).orElseGet(() -> new GsonComponentSerializerImpl((OptionState)JSONOptions.byDataVersion(), null));
    static final GsonComponentSerializer LEGACY_INSTANCE = GsonComponentSerializerImpl.access$000().map(GsonComponentSerializer.Provider::gsonLegacy).orElseGet(() -> new GsonComponentSerializerImpl((OptionState)JSONOptions.byDataVersion().at(2525), null));

    GsonComponentSerializerImpl.Instances() {
    }
}
