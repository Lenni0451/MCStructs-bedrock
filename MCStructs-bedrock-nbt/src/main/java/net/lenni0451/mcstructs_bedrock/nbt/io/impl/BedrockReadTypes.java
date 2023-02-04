package net.lenni0451.mcstructs_bedrock.nbt.io.impl;

import java.io.DataInput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class BedrockReadTypes {

    public static int readVarInt(final DataInput in) throws IOException {
        int i = readUnsignedVarInt(in);
        return (i >>> 1) ^ -(i & 1);
    }

    public static int readUnsignedVarInt(final DataInput in) throws IOException {
        return (int) readUnsignedVarLong(in);
    }

    public static long readVarLong(final DataInput in) throws IOException {
        long i = readUnsignedVarLong(in);
        return (i >>> 1) ^ -(i & 1);
    }

    public static long readUnsignedVarLong(final DataInput in) throws IOException {
        long value = 0;
        int size = 0;
        byte b;
        do {
            b = in.readByte();
            value |= (long) (b & 0b0111_1111) << (size++ * 7);
            if (size > 10) throw new IOException("VarLong is too big");
        } while ((b & 0x80) != 0);
        return value;
    }

    public static byte[] readByteArray(final DataInput in) throws IOException {
        int length = readUnsignedVarInt(in);
        byte[] bytes = new byte[length];
        in.readFully(bytes);
        return bytes;
    }

    public static String readString(final DataInput in) throws IOException {
        return new String(readByteArray(in), StandardCharsets.UTF_8);
    }

}
