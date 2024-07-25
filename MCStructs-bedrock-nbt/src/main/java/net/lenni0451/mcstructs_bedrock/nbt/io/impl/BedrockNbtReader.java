package net.lenni0451.mcstructs_bedrock.nbt.io.impl;

import net.lenni0451.mcstructs.nbt.INbtTag;
import net.lenni0451.mcstructs.nbt.NbtType;
import net.lenni0451.mcstructs.nbt.exceptions.NbtReadException;
import net.lenni0451.mcstructs.nbt.io.NbtHeader;
import net.lenni0451.mcstructs.nbt.io.NbtReadTracker;
import net.lenni0451.mcstructs.nbt.io.types.INbtReader;
import net.lenni0451.mcstructs.nbt.tags.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ParametersAreNonnullByDefault
public class BedrockNbtReader implements INbtReader {

    @Nonnull
    @Override
    public NbtType readType(DataInput in, NbtReadTracker readTracker) throws IOException {
        byte id = in.readByte();
        NbtType type = NbtType.byId(id);
        if (type == null || type.equals(NbtType.LONG_ARRAY)) throw new IOException("Unknown Nbt type: " + id);
        return type;
    }

    @Nonnull
    @Override
    public NbtHeader readHeader(DataInput in, NbtReadTracker readTracker) throws IOException {
        NbtType type = this.readType(in, readTracker);
        if (NbtType.END.equals(type)) return NbtHeader.END;
        return new NbtHeader(type, BedrockReadTypes.readString(in));
    }

    @Nonnull
    @Override
    public ByteTag readByte(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(9);
        return new ByteTag(in.readByte());
    }

    @Nonnull
    @Override
    public ShortTag readShort(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(10);
        return new ShortTag(in.readShort());
    }

    @Nonnull
    @Override
    public IntTag readInt(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(12);
        return new IntTag(BedrockReadTypes.readVarInt(in));
    }

    @Nonnull
    @Override
    public LongTag readLong(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(16);
        return new LongTag(BedrockReadTypes.readVarLong(in));
    }

    @Nonnull
    @Override
    public FloatTag readFloat(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(12);
        return new FloatTag(in.readFloat());
    }

    @Nonnull
    @Override
    public DoubleTag readDouble(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(16);
        return new DoubleTag(in.readDouble());
    }

    @Nonnull
    @Override
    public ByteArrayTag readByteArray(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(24);
        int length = BedrockReadTypes.readVarInt(in);
        readTracker.read(length);
        byte[] value = new byte[length];
        in.readFully(value);
        return new ByteArrayTag(value);
    }

    @Nonnull
    @Override
    public StringTag readString(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(36);
        String value = BedrockReadTypes.readString(in);
        readTracker.read(2 * value.length());
        return new StringTag(value);
    }

    @Nonnull
    @Override
    public ListTag<?> readList(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(37);
        NbtType type = this.readType(in, readTracker);
        int count = BedrockReadTypes.readVarInt(in);
        if (type == NbtType.END && count > 0) throw new NbtReadException("ListTag with type END and count > 0");
        if (count < 0) throw new NbtReadException("ListTag with negative count");
        readTracker.read(4 * count);
        List<INbtTag> value = new ArrayList<>(Math.min(count, 512));
        for (int i = 0; i < count; i++) {
            readTracker.pushDepth();
            INbtTag tag = this.read(type, in, readTracker);
            readTracker.popDepth();
            value.add(tag);
        }
        return new ListTag<>(type, value);
    }

    @Nonnull
    @Override
    public CompoundTag readCompound(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(48);
        Map<String, INbtTag> value = new HashMap<>();
        while (true) {
            NbtHeader header = this.readHeader(in, readTracker);
            if (header.isEnd()) break;
            readTracker.read(28 + 2 * header.getName().length());

            readTracker.pushDepth();
            INbtTag tag = this.read(header.getType(), in, readTracker);
            readTracker.popDepth();
            if (value.put(header.getName(), tag) != null) readTracker.read(36);
        }
        return new CompoundTag(value);
    }

    @Nonnull
    @Override
    public IntArrayTag readIntArray(DataInput in, NbtReadTracker readTracker) throws IOException {
        readTracker.read(24);
        int length = BedrockReadTypes.readVarInt(in);
        readTracker.read(4 * length);
        int[] value = new int[length];
        for (int i = 0; i < value.length; i++) value[i] = BedrockReadTypes.readVarInt(in);
        return new IntArrayTag(value);
    }

    @Nonnull
    @Override
    public LongArrayTag readLongArray(DataInput in, NbtReadTracker readTracker) {
        throw new UnsupportedOperationException("LongArrayTag is not supported in Bedrock Nbt");
    }

}
