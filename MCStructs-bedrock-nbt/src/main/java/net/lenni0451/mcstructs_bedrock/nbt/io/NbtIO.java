package net.lenni0451.mcstructs_bedrock.nbt.io;

import net.lenni0451.mcstructs_bedrock.nbt.io.impl.BedrockNbtReader;
import net.lenni0451.mcstructs_bedrock.nbt.io.impl.BedrockNbtWriter;

public class NbtIO {

    /**
     * Implementation of the Bedrock Edition Nbt format.
     *
     * @see <a href="https://mojang.github.io/bedrock-protocol-docs/html/CompoundTag.html">Bedrock Protocol Docs</a>
     */
    public static final net.lenni0451.mcstructs.nbt.io.NbtIO BEDROCK = new net.lenni0451.mcstructs.nbt.io.NbtIO(new BedrockNbtReader(), new BedrockNbtWriter());

}
