package net.lenni0451.mcstructs_bedrock.text;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BedrockTextFormattingTest {

    private static final Random RND = new Random();

    @Test
    void getByOrdinal() {
        for (BedrockTextFormatting formatting : BedrockTextFormatting.ALL.values()) assertEquals(formatting, BedrockTextFormatting.getByOrdinal(formatting.ordinal()));
    }

    @Test
    void getByName() {
        for (BedrockTextFormatting BedrockTextFormatting : BedrockTextFormatting.ALL.values()) {
            assertEquals(BedrockTextFormatting, BedrockTextFormatting.getByName(randomizeCase(BedrockTextFormatting)));
        }
    }

    @Test
    void getClosestFormattingColor() {
        for (BedrockTextFormatting color : BedrockTextFormatting.COLORS.values()) assertEquals(color, BedrockTextFormatting.getClosestFormattingColor(color.getRgbValue()));
    }

    @Test
    void isColor() {
        for (BedrockTextFormatting color : BedrockTextFormatting.COLORS.values()) {
            assertTrue(color.isColor());
            assertFalse(color.isFormatting());
        }
    }

    @Test
    void isFormatting() {
        for (BedrockTextFormatting formatting : BedrockTextFormatting.FORMATTINGS.values()) {
            assertFalse(formatting.isColor());
            assertTrue(formatting.isFormatting());
        }
    }


    private static String randomizeCase(final BedrockTextFormatting textFormatting) {
        String name = textFormatting.getName();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (RND.nextBoolean()) builder.append(Character.toUpperCase(name.charAt(i)));
            else builder.append(Character.toLowerCase(name.charAt(i)));
        }
        return builder.toString();
    }

}
