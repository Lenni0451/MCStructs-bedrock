package net.lenni0451.mcstructs_bedrock.text.utils;

import net.lenni0451.mcstructs_bedrock.text.BedrockTextFormatting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BedrockTextUtilsTest {

    @Test
    void getStyleAt() {
        BedrockTextUtils.LegacyStyle style0 = BedrockTextUtils.getStyleAt("§4§kHello", 0);
        assertNull(style0.getColor());
        assertTrue(style0.getStyles().isEmpty());

        BedrockTextUtils.LegacyStyle style2 = BedrockTextUtils.getStyleAt("§4§kHello", 2);
        assertEquals(BedrockTextFormatting.DARK_RED, style2.getColor());
        assertTrue(style2.getStyles().isEmpty());

        BedrockTextUtils.LegacyStyle style3 = BedrockTextUtils.getStyleAt("§4§kHello", 3);
        assertEquals(BedrockTextFormatting.DARK_RED, style3.getColor());
        assertEquals(1, style3.getStyles().size());
        assertTrue(style3.getStyles().contains(BedrockTextFormatting.OBFUSCATED));

        BedrockTextUtils.LegacyStyle style40 = BedrockTextUtils.getStyleAt("§4§kHello", 40);
        assertEquals(style3, style40);

        BedrockTextUtils.LegacyStyle ignoreInvalidStyle6 = BedrockTextUtils.getStyleAt("§4H§zi", 6);
        assertEquals(BedrockTextFormatting.DARK_RED, ignoreInvalidStyle6.getColor());
        assertTrue(ignoreInvalidStyle6.getStyles().isEmpty());
    }

    @Test
    void split() {
        String[] parts = BedrockTextUtils.split("§4§kHello\nWorld\nTest", "\n");
        assertEquals(3, parts.length);
        assertEquals("§4§kHello", parts[0]);
        assertEquals("§4§kWorld", parts[1]);
        assertEquals("§4§kTest", parts[2]);

        parts = BedrockTextUtils.split("§4§k§bHi\nWorld\nTest", "\n");
        assertEquals(3, parts.length);
        assertEquals("§4§k§bHi", parts[0]);
        assertEquals("§bWorld", parts[1]);
        assertEquals("§bTest", parts[2]);

        parts = BedrockTextUtils.split("§4Hello\n§eWorld\n§lTest\n3", "\n");
        assertEquals(4, parts.length);
        assertEquals("§4Hello", parts[0]);
        assertEquals("§4§eWorld", parts[1]);
        assertEquals("§e§lTest", parts[2]);
        assertEquals("§e§l3", parts[3]);
    }

}
