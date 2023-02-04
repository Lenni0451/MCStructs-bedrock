package net.lenni0451.mcstructs_bedrock.nbt.io;

import net.lenni0451.mcstructs_bedrock.nbt.io.impl.BedrockNbtReader;
import net.lenni0451.mcstructs_bedrock.nbt.io.impl.BedrockNbtWriter;

public class NbtIO {

    /**
     * Implementation of the Bedrock Edition Nbt format.
     */
    public static final net.lenni0451.mcstructs.nbt.io.NbtIO BEDROCK = new net.lenni0451.mcstructs.nbt.io.NbtIO(new BedrockNbtReader(), new BedrockNbtWriter());

}
