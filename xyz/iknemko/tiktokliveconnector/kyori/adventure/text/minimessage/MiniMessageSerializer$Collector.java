/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer$Collector$TagState
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.QuotingOverride
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.QuotingOverride;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;

/*
 * Exception performing whole class analysis ignored.
 */
static final class MiniMessageSerializer.Collector
implements ClaimConsumer,
TokenEmitter {
    private static final String MARK = "__<'\"\\MARK__";
    private TagState tagState;
    private final boolean strict;
    @Nullable
    Emitable componentClaim;
    private static final char[] TAG_TOKENS;
    final Set<String> claimedStyleElements;
    private final SerializableResolver resolver;
    private static final char[] DOUBLE_QUOTED_ESCAPES;
    private static final char[] SINGLE_QUOTED_ESCAPES;
    private static final char[] TEXT_ESCAPES;
    private int tagLevel = 0;
    private final StringBuilder consumer;
    private String[] activeTags = new String[4];

    @NotNull
    public MiniMessageSerializer.Collector pop() {
        this.emitClose(this.popTag(false));
        return this;
    }

    static {
        TEXT_ESCAPES = new char[]{'\\', '<'};
        TAG_TOKENS = new char[]{'>', ':'};
        SINGLE_QUOTED_ESCAPES = new char[]{'\\', '\''};
        DOUBLE_QUOTED_ESCAPES = new char[]{'\\', '\"'};
    }

    @NotNull
    public MiniMessageSerializer.Collector text(@NotNull String text) {
        this.completeTag();
        MiniMessageSerializer.Collector.appendEscaping(this.consumer, text, TEXT_ESCAPES, true);
        return this;
    }

    void popToMark() {
        String tag;
        if (this.tagLevel == 0) {
            return;
        }
        while ((tag = this.popTag(true)) != "__<'\"\\MARK__") {
            this.emitClose(tag);
        }
    }

    public boolean styleClaimed(@NotNull String claimId) {
        return this.claimedStyleElements.contains(claimId);
    }

    private void pushActiveTag(String tag) {
        if (this.tagLevel >= this.activeTags.length) {
            this.activeTags = Arrays.copyOf(this.activeTags, this.activeTags.length * 2);
        }
        this.activeTags[this.tagLevel++] = tag;
    }

    private String popTag(boolean allowMarks) {
        if (this.tagLevel-- <= 0) {
            throw new IllegalStateException("Unbalanced tags, tried to pop below depth");
        }
        String tag = this.activeTags[this.tagLevel];
        if (allowMarks) return tag;
        if (tag != "__<'\"\\MARK__") return tag;
        throw new IllegalStateException("Tried to pop past mark, tag stack: " + Arrays.toString(this.activeTags) + " @ " + this.tagLevel);
    }

    static void appendEscaping(StringBuilder builder, String text, char[] escapeChars, boolean allowEscapes) {
        int startIdx = 0;
        boolean unescapedFound = false;
        int i = 0;
        while (true) {
            if (i >= text.length()) {
                if (startIdx >= text.length()) return;
                if (!unescapedFound) return;
                builder.append(text, startIdx, text.length());
                return;
            }
            char test = text.charAt(i);
            boolean escaped = false;
            for (char c : escapeChars) {
                if (test != c) continue;
                if (!allowEscapes) {
                    throw new IllegalArgumentException("Invalid escapable character '" + test + "' found at index " + i + " in string '" + text + "'");
                }
                escaped = true;
                break;
            }
            if (escaped) {
                if (unescapedFound) {
                    builder.append(text, startIdx, i);
                }
                startIdx = i + 1;
                builder.append('\\').append(test);
            } else {
                unescapedFound = true;
            }
            ++i;
        }
    }

    public boolean component(@NotNull Emitable componentClaim) {
        if (this.componentClaim != null) {
            return false;
        }
        this.componentClaim = Objects.requireNonNull(componentClaim, "componentClaim");
        return true;
    }

    @Nullable
    Component flushClaims(Component component) {
        Component ret = null;
        if (this.componentClaim != null) {
            this.componentClaim.emit((TokenEmitter)this);
            ret = this.componentClaim.substitute();
            this.componentClaim = null;
        } else {
            if (!(component instanceof TextComponent)) throw new IllegalStateException("Unclaimed component " + component);
            this.text(((TextComponent)component).content());
        }
        this.claimedStyleElements.clear();
        return ret;
    }

    @NotNull
    public TokenEmitter selfClosingTag(@NotNull String token) {
        this.completeTag();
        this.consumer.append('<');
        this.escapeTagContent(token, QuotingOverride.UNQUOTED);
        this.tagState = TagState.MID_SELF_CLOSING;
        return this;
    }

    void mark() {
        this.pushActiveTag("__<'\"\\MARK__");
    }

    private void escapeTagContent(String content, @Nullable QuotingOverride preference) {
        boolean mustBeQuoted = preference == QuotingOverride.QUOTED;
        boolean hasSingleQuote = false;
        boolean hasDoubleQuote = false;
        for (int i = 0; i < content.length(); ++i) {
            char active = content.charAt(i);
            if (active == '>' || active == ':' || active == ' ') {
                mustBeQuoted = true;
                if (!hasSingleQuote || !hasDoubleQuote) continue;
                break;
            }
            if (active == '\'') {
                hasSingleQuote = true;
                break;
            }
            if (active != '\"') continue;
            hasDoubleQuote = true;
            if (mustBeQuoted && hasSingleQuote) break;
        }
        if (hasSingleQuote) {
            this.consumer.append('\"');
            MiniMessageSerializer.Collector.appendEscaping(this.consumer, content, DOUBLE_QUOTED_ESCAPES, true);
            this.consumer.append('\"');
        } else if (hasDoubleQuote || mustBeQuoted) {
            this.consumer.append('\'');
            MiniMessageSerializer.Collector.appendEscaping(this.consumer, content, SINGLE_QUOTED_ESCAPES, true);
            this.consumer.append('\'');
        } else {
            MiniMessageSerializer.Collector.appendEscaping(this.consumer, content, TAG_TOKENS, false);
        }
    }

    private void emitClose(@NotNull String tag) {
        if (this.tagState.isTag) {
            if (this.tagState == TagState.MID) {
                this.consumer.append('/');
            }
            this.consumer.append('>');
            this.tagState = TagState.TEXT;
        } else {
            this.consumer.append('<').append('/');
            this.escapeTagContent(tag, QuotingOverride.UNQUOTED);
            this.consumer.append('>');
        }
    }

    @NotNull
    public TokenEmitter argument(@NotNull String arg) {
        if (!this.tagState.isTag) {
            throw new IllegalStateException("Not within a tag!");
        }
        this.consumer.append(':');
        this.escapeTagContent(arg, null);
        return this;
    }

    @NotNull
    public TokenEmitter argument(@NotNull String arg, @NotNull QuotingOverride quotingPreference) {
        if (!this.tagState.isTag) {
            throw new IllegalStateException("Not within a tag!");
        }
        this.consumer.append(':');
        this.escapeTagContent(arg, Objects.requireNonNull(quotingPreference, "quotingPreference"));
        return this;
    }

    @NotNull
    public MiniMessageSerializer.Collector tag(@NotNull String token) {
        this.completeTag();
        this.consumer.append('<');
        this.escapeTagContent(token, QuotingOverride.UNQUOTED);
        this.tagState = TagState.MID;
        this.pushActiveTag(token);
        return this;
    }

    public boolean componentClaimed() {
        return this.componentClaim != null;
    }

    void completeTag() {
        if (!this.tagState.isTag) return;
        this.consumer.append('>');
        this.tagState = TagState.TEXT;
    }

    public void style(@NotNull String claimKey, @NotNull Emitable styleClaim) {
        if (!this.claimedStyleElements.add(Objects.requireNonNull(claimKey, "claimKey"))) return;
        styleClaim.emit((TokenEmitter)this);
    }

    void popAll() {
        while (this.tagLevel > 0) {
            String tag;
            if ((tag = this.activeTags[--this.tagLevel]) == "__<'\"\\MARK__") continue;
            this.emitClose(tag);
        }
    }

    MiniMessageSerializer.Collector(SerializableResolver resolver, boolean strict, StringBuilder consumer) {
        this.tagState = TagState.TEXT;
        this.claimedStyleElements = new HashSet<String>();
        this.resolver = resolver;
        this.strict = strict;
        this.consumer = consumer;
    }

    @NotNull
    public TokenEmitter argument(@NotNull Component arg) {
        String serialized = MiniMessageSerializer.serialize((Component)arg, (SerializableResolver)this.resolver, (boolean)this.strict);
        return this.argument(serialized, QuotingOverride.QUOTED);
    }
}
