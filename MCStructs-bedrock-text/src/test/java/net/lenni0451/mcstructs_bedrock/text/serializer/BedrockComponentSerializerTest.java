package net.lenni0451.mcstructs_bedrock.text.serializer;

import net.lenni0451.mcstructs_bedrock.text.components.RootBedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.components.StringBedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.components.TranslationBedrockComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BedrockComponentSerializerTest {

    private static final String RAW_COMPONENT = "{\"rawtext\":[{\"translate\":\"translation.test.complex\",\"with\":{\"rawtext\":[{\"text\":\"A\"},{\"text\":\"B\"},{\"text\":\"C\"},{\"text\":\"D\"}]}}]}";
    private static final RootBedrockComponent COMPONENT = new RootBedrockComponent().addComponent(new TranslationBedrockComponent("translation.test.complex", new StringBedrockComponent("A"), new StringBedrockComponent("B"), new StringBedrockComponent("C"), new StringBedrockComponent("D")));

    @Test
    void serialize() {
        assertEquals(RAW_COMPONENT, BedrockComponentSerializer.serialize(COMPONENT));
    }

    @Test
    void deserialize() {
        assertEquals(COMPONENT, BedrockComponentSerializer.deserialize(RAW_COMPONENT));
    }

}
