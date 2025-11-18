/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType$Impl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.EndBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTagImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TrackingDataInput
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagScope;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteArrayBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ByteBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.DoubleBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.EndBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.FloatBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntArrayBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.IntBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongArrayBinaryTagImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.LongBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ShortBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TrackingDataInput;

public final class BinaryTagTypes {
    public static final BinaryTagType<ByteBinaryTag> BYTE;
    public static final BinaryTagType<StringBinaryTag> STRING;
    public static final BinaryTagType<FloatBinaryTag> FLOAT;
    public static final BinaryTagType<ByteArrayBinaryTag> BYTE_ARRAY;
    public static final BinaryTagType<EndBinaryTag> END;
    public static final BinaryTagType<IntArrayBinaryTag> INT_ARRAY;
    public static final BinaryTagType<LongBinaryTag> LONG;
    public static final BinaryTagType<CompoundBinaryTag> COMPOUND;
    public static final BinaryTagType<IntBinaryTag> INT;
    public static final BinaryTagType<ShortBinaryTag> SHORT;
    public static final BinaryTagType<BinaryTag> LIST_WILDCARD;
    public static final BinaryTagType<LongArrayBinaryTag> LONG_ARRAY;
    public static final BinaryTagType<DoubleBinaryTag> DOUBLE;
    public static final BinaryTagType<ListBinaryTag> LIST;

    private BinaryTagTypes() {
    }

    static {
        END = BinaryTagType.register(EndBinaryTag.class, (byte)0, input -> EndBinaryTag.endBinaryTag(), null);
        BYTE = BinaryTagType.registerNumeric(ByteBinaryTag.class, (byte)1, input -> ByteBinaryTag.byteBinaryTag((byte)input.readByte()), (tag, output) -> output.writeByte(tag.value()));
        SHORT = BinaryTagType.registerNumeric(ShortBinaryTag.class, (byte)2, input -> ShortBinaryTag.shortBinaryTag((short)input.readShort()), (tag, output) -> output.writeShort(tag.value()));
        INT = BinaryTagType.registerNumeric(IntBinaryTag.class, (byte)3, input -> IntBinaryTag.intBinaryTag((int)input.readInt()), (tag, output) -> output.writeInt(tag.value()));
        LONG = BinaryTagType.registerNumeric(LongBinaryTag.class, (byte)4, input -> LongBinaryTag.longBinaryTag((long)input.readLong()), (tag, output) -> output.writeLong(tag.value()));
        FLOAT = BinaryTagType.registerNumeric(FloatBinaryTag.class, (byte)5, input -> FloatBinaryTag.floatBinaryTag((float)input.readFloat()), (tag, output) -> output.writeFloat(tag.value()));
        DOUBLE = BinaryTagType.registerNumeric(DoubleBinaryTag.class, (byte)6, input -> DoubleBinaryTag.doubleBinaryTag((double)input.readDouble()), (tag, output) -> output.writeDouble(tag.value()));
        BYTE_ARRAY = BinaryTagType.register(ByteArrayBinaryTag.class, (byte)7, input -> {
            int length = input.readInt();
            try (BinaryTagScope ignored = TrackingDataInput.enter((DataInput)input, (long)length);){
                byte[] value = new byte[length];
                input.readFully(value);
                ByteArrayBinaryTag byteArrayBinaryTag = ByteArrayBinaryTag.byteArrayBinaryTag((byte[])value);
                return byteArrayBinaryTag;
            }
        }, (tag, output) -> {
            byte[] value = ByteArrayBinaryTagImpl.value((ByteArrayBinaryTag)tag);
            output.writeInt(value.length);
            output.write(value);
        });
        STRING = BinaryTagType.register(StringBinaryTag.class, (byte)8, input -> StringBinaryTag.stringBinaryTag((String)input.readUTF()), (tag, output) -> output.writeUTF(tag.value()));
        LIST = BinaryTagType.register(ListBinaryTag.class, (byte)9, input -> {
            BinaryTagType type = BinaryTagType.binaryTagType((byte)input.readByte());
            int length = input.readInt();
            try (BinaryTagScope ignored = TrackingDataInput.enter((DataInput)input, (long)((long)length * 8L));){
                ArrayList<BinaryTag> tags = new ArrayList<BinaryTag>(length);
                for (int i = 0; i < length; ++i) {
                    tags.add(type.read(input));
                }
                ListBinaryTag listBinaryTag = ListBinaryTag.listBinaryTag((BinaryTagType)type, tags);
                return listBinaryTag;
            }
        }, (rawTag, output) -> {
            ListBinaryTag tag = rawTag.wrapHeterogeneity();
            output.writeByte(tag.elementType().id());
            int size = tag.size();
            output.writeInt(size);
            Iterator iterator = tag.iterator();
            while (iterator.hasNext()) {
                BinaryTag item = (BinaryTag)iterator.next();
                BinaryTagType.writeUntyped((BinaryTagType)item.type(), (BinaryTag)item, (DataOutput)output);
            }
        });
        COMPOUND = BinaryTagType.register(CompoundBinaryTag.class, (byte)10, input -> {
            try (BinaryTagScope ignored = TrackingDataInput.enter((DataInput)input);){
                BinaryTagType type;
                HashMap<String, BinaryTag> tags = new HashMap<String, BinaryTag>();
                while ((type = BinaryTagType.binaryTagType((byte)input.readByte())) != END) {
                    String key = input.readUTF();
                    BinaryTag tag = type.read(input);
                    tags.put(key, tag);
                }
                CompoundBinaryTagImpl compoundBinaryTagImpl = new CompoundBinaryTagImpl(tags);
                return compoundBinaryTagImpl;
            }
        }, (tag, output) -> {
            Iterator iterator = tag.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    output.writeByte(END.id());
                    return;
                }
                Map.Entry entry = (Map.Entry)iterator.next();
                BinaryTag value = (BinaryTag)entry.getValue();
                if (value == null) continue;
                BinaryTagType type = value.type();
                output.writeByte(type.id());
                if (type == END) continue;
                output.writeUTF((String)entry.getKey());
                BinaryTagType.writeUntyped((BinaryTagType)type, (BinaryTag)value, (DataOutput)output);
            }
        });
        INT_ARRAY = BinaryTagType.register(IntArrayBinaryTag.class, (byte)11, input -> {
            int length = input.readInt();
            try (BinaryTagScope ignored = TrackingDataInput.enter((DataInput)input, (long)((long)length * 4L));){
                int[] value = new int[length];
                for (int i = 0; i < length; ++i) {
                    value[i] = input.readInt();
                }
                IntArrayBinaryTag intArrayBinaryTag = IntArrayBinaryTag.intArrayBinaryTag((int[])value);
                return intArrayBinaryTag;
            }
        }, (tag, output) -> {
            int[] value = IntArrayBinaryTagImpl.value((IntArrayBinaryTag)tag);
            int length = value.length;
            output.writeInt(length);
            int i = 0;
            while (i < length) {
                output.writeInt(value[i]);
                ++i;
            }
        });
        LONG_ARRAY = BinaryTagType.register(LongArrayBinaryTag.class, (byte)12, input -> {
            int length = input.readInt();
            try (BinaryTagScope ignored = TrackingDataInput.enter((DataInput)input, (long)((long)length * 8L));){
                long[] value = new long[length];
                for (int i = 0; i < length; ++i) {
                    value[i] = input.readLong();
                }
                LongArrayBinaryTag longArrayBinaryTag = LongArrayBinaryTag.longArrayBinaryTag((long[])value);
                return longArrayBinaryTag;
            }
        }, (tag, output) -> {
            long[] value = LongArrayBinaryTagImpl.value((LongArrayBinaryTag)tag);
            int length = value.length;
            output.writeInt(length);
            int i = 0;
            while (i < length) {
                output.writeLong(value[i]);
                ++i;
            }
        });
        LIST_WILDCARD = new BinaryTagType.Impl(BinaryTag.class, 127, input -> {
            throw new IllegalArgumentException("Unable to read values of placeholder type. This tag type exists only to indicate heterogeneous lists");
        }, (tag, output) -> {
            throw new IllegalArgumentException("Unable to write values of placeholder type. This tag type exists only to indicate heterogeneous lists");
        });
    }
}
