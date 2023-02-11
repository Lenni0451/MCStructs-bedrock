package net.lenni0451.mcstructs_bedrock.nbt.io;

import net.lenni0451.mcstructs.nbt.io.NbtReadTracker;
import net.lenni0451.mcstructs.nbt.tags.CompoundTag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NbtIOTest {

    private static byte[] rawTag;
    private static CompoundTag tag;

    @BeforeAll
    static void init() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = NbtIOTest.class.getClassLoader().getResourceAsStream("test.nbt");
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1) baos.write(buf, 0, len);
        is.close();
        rawTag = baos.toByteArray();

        tag = new CompoundTag();
        tag.addString("LootTable", "loot_tables/chests/simple_dungeon.json");
        tag.addByte("Findable", (byte) 0);
        tag.addList("Items");
        tag.addByte("isMovable", (byte) 1);
        tag.addString("id", "Chest");
        tag.addInt("LootTableSeed", 231164302);
        tag.addInt("x", -194);
        tag.addInt("y", -30);
        tag.addInt("z", 168);
    }

    @Test
    void read() {
        this.readTag(rawTag);
    }

    @Test
    void write() {
        assertDoesNotThrow(() -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            NbtIO.BEDROCK.write(new DataOutputStream(baos), "", tag);
            this.readTag(baos.toByteArray());
        });
    }

    private void readTag(final byte[] bytes) {
        assertDoesNotThrow(() -> {
            CompoundTag tag = (CompoundTag) NbtIO.BEDROCK.read(new DataInputStream(new ByteArrayInputStream(bytes)), NbtReadTracker.unlimited());
            assertEquals(NbtIOTest.tag, tag);
        });
    }

}