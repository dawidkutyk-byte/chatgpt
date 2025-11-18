/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

static enum MiniMessageSerializer.Collector.TagState {
    TEXT(false),
    MID(true),
    MID_SELF_CLOSING(true);

    final boolean isTag;

    private MiniMessageSerializer.Collector.TagState(boolean isTag) {
        this.isTag = isTag;
    }
}
