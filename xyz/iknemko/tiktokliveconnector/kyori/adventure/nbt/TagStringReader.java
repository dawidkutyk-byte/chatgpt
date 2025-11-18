/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CharBuffer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringTagParseException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.Tokens
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CharBuffer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.NumberBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringTagParseException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.Tokens;

final class TagStringReader {
    private boolean acceptLegacy;
    private static final int[] EMPTY_INT_ARRAY;
    private final CharBuffer buffer;
    private static final long[] EMPTY_LONG_ARRAY;
    private static final int HEX_RADIX = 16;
    private boolean acceptHeterogenousLists;
    private static final int BINARY_RADIX = 2;
    private static final int DECIMAL_RADIX = 10;
    private static final int MAX_DEPTH = 512;
    private static final byte[] EMPTY_BYTE_ARRAY;
    private int depth;

    @Nullable
    private NumberBinaryTag parseNumberTag(String s, char typeToken, int radix, boolean signed) {
        switch (typeToken) {
            case 'b': {
                return ByteBinaryTag.byteBinaryTag((byte)this.parseByte(s, radix, signed));
            }
            case 's': {
                return ShortBinaryTag.shortBinaryTag((short)this.parseShort(s, radix, signed));
            }
            case 'i': {
                return IntBinaryTag.intBinaryTag((int)this.parseInt(s, radix, signed));
            }
            case 'l': {
                return LongBinaryTag.longBinaryTag((long)this.parseLong(s, radix, signed));
            }
            case 'f': {
                float floatValue = Float.parseFloat(s);
                if (!Float.isFinite(floatValue)) return null;
                return FloatBinaryTag.floatBinaryTag((float)floatValue);
            }
            case 'd': {
                double doubleValue = Double.parseDouble(s);
                if (!Double.isFinite(doubleValue)) return null;
                return DoubleBinaryTag.doubleBinaryTag((double)doubleValue);
            }
        }
        return null;
    }

    public BinaryTag array(char elementType) throws StringTagParseException {
        this.buffer.expect('[').expect(elementType).expect(';');
        elementType = Character.toLowerCase(elementType);
        if (elementType == 'b') {
            return ByteArrayBinaryTag.byteArrayBinaryTag((byte[])this.byteArray());
        }
        if (elementType == 'i') {
            return IntArrayBinaryTag.intArrayBinaryTag((int[])this.intArray());
        }
        if (elementType != 'l') throw this.buffer.makeError("Type " + elementType + " is not a valid element type in an array!");
        return LongArrayBinaryTag.longArrayBinaryTag((long[])this.longArray());
    }

    private long parseLong(String s, int radix, boolean signed) {
        return signed ? Long.parseLong(s, radix) : Long.parseUnsignedLong(s, radix);
    }

    public void legacy(boolean acceptLegacy) {
        this.acceptLegacy = acceptLegacy;
    }

    private BinaryTag scalar() throws StringTagParseException {
        String original;
        block18: {
            char signChar;
            boolean signed;
            StringBuilder builder = new StringBuilder();
            while (this.buffer.hasMore()) {
                char current = this.buffer.peek();
                if (current == '\\') {
                    this.buffer.advance();
                    current = this.buffer.take();
                } else {
                    if (!Tokens.id((char)current)) break;
                    this.buffer.advance();
                }
                builder.append(current);
            }
            if (builder.length() == 0) {
                throw this.buffer.makeError("Expected a value but got nothing");
            }
            original = builder.toString();
            int radix = this.extractRadix(builder, original);
            char last = builder.charAt(builder.length() - 1);
            boolean hasSignToken = false;
            boolean bl = signed = radix != 16;
            if (builder.length() > 2 && ((signChar = builder.charAt(builder.length() - 2)) == 's' || signChar == 'u')) {
                hasSignToken = true;
                signed = signChar == 's';
                builder.deleteCharAt(builder.length() - 2);
            }
            boolean hasTypeToken = false;
            char typeToken = 'i';
            if (Tokens.numericType((char)last) && (hasSignToken || radix != 16)) {
                hasTypeToken = true;
                typeToken = Character.toLowerCase(last);
                builder.deleteCharAt(builder.length() - 1);
            }
            if (!signed) {
                if (typeToken == 102) throw this.buffer.makeError("Cannot create unsigned floating point numbers");
                if (typeToken == 'd') {
                    throw this.buffer.makeError("Cannot create unsigned floating point numbers");
                }
            }
            String strippedString = builder.toString().replace("_", "");
            if (hasTypeToken) {
                try {
                    NumberBinaryTag tag = this.parseNumberTag(strippedString, typeToken, radix, signed);
                    if (tag != null) {
                        return tag;
                    }
                }
                catch (NumberFormatException tag) {}
            } else {
                try {
                    return IntBinaryTag.intBinaryTag((int)this.parseInt(strippedString, radix, signed));
                }
                catch (NumberFormatException ex) {
                    if (strippedString.indexOf(46) == -1) break block18;
                    try {
                        return DoubleBinaryTag.doubleBinaryTag((double)Double.parseDouble(strippedString));
                    }
                    catch (NumberFormatException numberFormatException) {
                        // empty catch block
                    }
                }
            }
        }
        if (original.equalsIgnoreCase("true")) {
            return ByteBinaryTag.ONE;
        }
        if (!original.equalsIgnoreCase("false")) return StringBinaryTag.stringBinaryTag((String)original);
        return ByteBinaryTag.ZERO;
    }

    private int[] intArray() throws StringTagParseException {
        if (this.buffer.takeIf(']')) {
            return EMPTY_INT_ARRAY;
        }
        IntStream.Builder builder = IntStream.builder();
        do {
            if (!this.buffer.hasMore()) throw this.buffer.makeError("Reached end of document without array close");
            BinaryTag value = this.tag();
            if (!(value instanceof IntBinaryTag)) {
                throw this.buffer.makeError("All elements of an int array must be ints!");
            }
            builder.add(((IntBinaryTag)value).intValue());
        } while (!this.separatorOrCompleteWith(']'));
        return builder.build().toArray();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String key() throws StringTagParseException {
        this.buffer.skipWhitespace();
        char starChar = this.buffer.peek();
        try {
            if (starChar == '\'' || starChar == '\"') {
                String string = TagStringReader.unescape(this.buffer.takeUntil(this.buffer.take()).toString());
                return string;
            }
            StringBuilder builder = new StringBuilder();
            while (this.buffer.hasMore()) {
                char peek = this.buffer.peek();
                if (!Tokens.id((char)peek)) {
                    if (!this.acceptLegacy) break;
                    if (peek == '\\') {
                        this.buffer.take();
                        continue;
                    }
                    if (peek == ':') break;
                    builder.append(this.buffer.take());
                    continue;
                }
                builder.append(this.buffer.take());
            }
            String string = builder.toString();
            return string;
        }
        finally {
            this.buffer.expect(':');
        }
    }

    private static String unescape(String withEscapes) {
        int escapeIdx = withEscapes.indexOf(92);
        if (escapeIdx == -1) {
            return withEscapes;
        }
        int lastEscape = 0;
        StringBuilder output = new StringBuilder(withEscapes.length());
        do {
            output.append(withEscapes, lastEscape, escapeIdx);
        } while ((escapeIdx = withEscapes.indexOf(92, (lastEscape = escapeIdx + 1) + 1)) != -1);
        output.append(withEscapes.substring(lastEscape));
        return output.toString();
    }

    private int extractRadix(StringBuilder builder, String original) {
        int radixPrefixOffset = 0;
        char first = builder.charAt(0);
        if (first == '+' || first == '-') {
            radixPrefixOffset = 1;
        }
        int radix = original.startsWith("0b", radixPrefixOffset) || original.startsWith("0B", radixPrefixOffset) ? 2 : (original.startsWith("0x", radixPrefixOffset) || original.startsWith("0X", radixPrefixOffset) ? 16 : 10);
        if (radix == 10) return radix;
        builder.delete(radixPrefixOffset, 2 + radixPrefixOffset);
        return radix;
    }

    public BinaryTag tag() throws StringTagParseException {
        if (this.depth++ > 512) {
            throw this.buffer.makeError("Exceeded maximum allowed depth of 512 when reading tag");
        }
        try {
            char startToken = this.buffer.skipWhitespace().peek();
            switch (startToken) {
                case '{': {
                    CompoundBinaryTag compoundBinaryTag = this.compound();
                    return compoundBinaryTag;
                }
                case '[': {
                    if (this.buffer.hasMore(2) && this.buffer.peek(2) == ';') {
                        BinaryTag binaryTag = this.array(this.buffer.peek(1));
                        return binaryTag;
                    }
                    ListBinaryTag listBinaryTag = this.list();
                    return listBinaryTag;
                }
                case '\"': 
                case '\'': {
                    this.buffer.advance();
                    StringBinaryTag stringBinaryTag = StringBinaryTag.stringBinaryTag((String)TagStringReader.unescape(this.buffer.takeUntil(startToken).toString()));
                    return stringBinaryTag;
                }
            }
            BinaryTag binaryTag = this.scalar();
            return binaryTag;
        }
        finally {
            --this.depth;
        }
    }

    TagStringReader(CharBuffer buffer) {
        this.buffer = buffer;
    }

    private byte[] byteArray() throws StringTagParseException {
        if (this.buffer.takeIf(']')) {
            return EMPTY_BYTE_ARRAY;
        }
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        do {
            if (!this.buffer.hasMore()) throw this.buffer.makeError("Reached end of document without array close");
            CharSequence value = this.buffer.skipWhitespace().takeUntil('b');
            try {
                bytes.add(Byte.valueOf(value.toString()));
            }
            catch (NumberFormatException ex) {
                throw this.buffer.makeError("All elements of a byte array must be bytes!");
            }
        } while (!this.separatorOrCompleteWith(']'));
        byte[] result = new byte[bytes.size()];
        int i = 0;
        while (i < bytes.size()) {
            result[i] = (Byte)bytes.get(i);
            ++i;
        }
        return result;
    }

    public CompoundBinaryTag compound() throws StringTagParseException {
        this.buffer.expect('{');
        if (this.buffer.takeIf('}')) {
            return CompoundBinaryTag.empty();
        }
        CompoundBinaryTag.Builder builder = CompoundBinaryTag.builder();
        do {
            if (!this.buffer.hasMore()) throw this.buffer.makeError("Unterminated compound tag!");
            builder.put(this.key(), this.tag());
        } while (!this.separatorOrCompleteWith('}'));
        return builder.build();
    }

    private byte parseByte(String s, int radix, boolean signed) {
        if (signed) {
            return Byte.parseByte(s, radix);
        }
        int parsedInt = Integer.parseInt(s, radix);
        if (parsedInt >> 8 != 0) throw new NumberFormatException();
        return (byte)parsedInt;
    }

    private short parseShort(String s, int radix, boolean signed) {
        if (signed) {
            return Short.parseShort(s, radix);
        }
        int parsedInt = Integer.parseInt(s, radix);
        if (parsedInt >> 16 != 0) throw new NumberFormatException();
        return (short)parsedInt;
    }

    private long[] longArray() throws StringTagParseException {
        if (this.buffer.takeIf(']')) {
            return EMPTY_LONG_ARRAY;
        }
        LongStream.Builder longs = LongStream.builder();
        do {
            if (!this.buffer.hasMore()) throw this.buffer.makeError("Reached end of document without array close");
            CharSequence value = this.buffer.skipWhitespace().takeUntil('l');
            try {
                longs.add(Long.parseLong(value.toString()));
            }
            catch (NumberFormatException ex) {
                throw this.buffer.makeError("All elements of a long array must be longs!");
            }
        } while (!this.separatorOrCompleteWith(']'));
        return longs.build().toArray();
    }

    private int parseInt(String s, int radix, boolean signed) {
        return signed ? Integer.parseInt(s, radix) : Integer.parseUnsignedInt(s, radix);
    }

    private boolean separatorOrCompleteWith(char endCharacter) throws StringTagParseException {
        if (this.buffer.takeIf(endCharacter)) {
            return true;
        }
        this.buffer.expect(',');
        return this.buffer.takeIf(endCharacter);
    }

    static {
        EMPTY_BYTE_ARRAY = new byte[0];
        EMPTY_INT_ARRAY = new int[0];
        EMPTY_LONG_ARRAY = new long[0];
    }

    public ListBinaryTag list() throws StringTagParseException {
        boolean prefixedIndex;
        ListBinaryTag.Builder builder = ListBinaryTag.builder();
        this.buffer.expect('[');
        boolean bl = prefixedIndex = this.acceptLegacy && this.buffer.peek() == '0' && this.buffer.peek(1) == ':';
        if (!prefixedIndex && this.buffer.takeIf(']')) {
            return ListBinaryTag.empty();
        }
        do {
            if (!this.buffer.hasMore()) throw this.buffer.makeError("Reached end of file without end of list tag!");
            if (prefixedIndex) {
                this.buffer.takeUntil(':');
            }
            BinaryTag next = this.tag();
            builder.add(next);
        } while (!this.separatorOrCompleteWith(']'));
        return builder.build();
    }
}
