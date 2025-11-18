/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CharBuffer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringTagParseException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringIO$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringReader
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringWriter
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.IOException;
import java.io.Writer;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CharBuffer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringTagParseException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringReader;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringWriter;

/*
 * Exception performing whole class analysis ignored.
 */
public final class TagStringIO {
    private static final TagStringIO INSTANCE = new TagStringIO(new Builder());
    private final boolean emitLegacy;
    private final boolean acceptLegacy;
    private final String indent;

    @NotNull
    public static TagStringIO get() {
        return INSTANCE;
    }

    public CompoundBinaryTag asCompound(String input) throws IOException {
        try {
            CharBuffer buffer = new CharBuffer((CharSequence)input);
            TagStringReader parser = new TagStringReader(buffer);
            parser.legacy(this.acceptLegacy);
            CompoundBinaryTag tag = parser.compound();
            if (!buffer.skipWhitespace().hasMore()) return tag;
            throw new IOException("Document had trailing content after first CompoundTag");
        }
        catch (StringTagParseException ex) {
            throw new IOException(ex);
        }
    }

    public String asString(CompoundBinaryTag input) throws IOException {
        return this.asString((BinaryTag)input);
    }

    public void toWriter(CompoundBinaryTag input, Writer dest) throws IOException {
        try (TagStringWriter emit = new TagStringWriter((Appendable)dest, this.indent);){
            emit.legacy(this.emitLegacy);
            emit.writeTag((BinaryTag)input);
        }
    }

    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    private TagStringIO(@NotNull Builder builder) {
        this.acceptLegacy = Builder.access$000((Builder)builder);
        this.emitLegacy = Builder.access$100((Builder)builder);
        this.indent = Builder.access$200((Builder)builder);
    }

    public String asString(BinaryTag input) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (TagStringWriter emit = new TagStringWriter((Appendable)sb, this.indent);){
            emit.legacy(this.emitLegacy);
            emit.writeTag(input);
        }
        return sb.toString();
    }
}
