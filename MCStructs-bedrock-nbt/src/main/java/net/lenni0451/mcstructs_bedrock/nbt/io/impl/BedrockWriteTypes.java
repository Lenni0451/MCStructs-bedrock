package net.lenni0451.mcstructs_bedrock.nbt.io.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BedrockWriteTypes {

    public static void writeVarInt(final DataOutput out, final int value) throws IOException {
        writeUnsignedVarLong(out, ((long) value << 1) ^ (value >> 31));
    }

    public static void writeUnsignedVarInt(final DataOutput out, final int value) throws IOException {
        writeUnsignedVarLong(out, value & 0xFFFFFFFFL);
    }

    public static void writeVarLong(final DataOutput out, final long value) throws IOException {
        writeUnsignedVarLong(out, (value << 1) ^ (value >> 63));
    }

    public static void writeUnsignedVarLong(final DataOutput out, long value) throws IOException {
        while ((value & ~0b0111_1111L) != 0L) {
            out.writeByte((int) (value & 0x7F) | 0x80);
            value >>>= 7;
        }
        out.writeByte((int) value);
    }

    public static void writeByteArray(final DataOutput out, final byte[] bytes) throws IOException {
        writeUnsignedVarInt(out, bytes.length);
        out.write(bytes);
    }

    public static void writeString(final DataOutput out, final String string) throws IOException {
        writeByteArray(out, string.getBytes(StandardCharsets.UTF_8));
    }

}
