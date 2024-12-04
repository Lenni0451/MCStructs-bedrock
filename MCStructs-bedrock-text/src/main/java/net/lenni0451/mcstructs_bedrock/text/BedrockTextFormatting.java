package net.lenni0451.mcstructs_bedrock.text;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The formattings are used to style texts in minecraft.
 *
 * @see <a href="https://minecraft.wiki/w/Formatting_codes">minecraft wiki</a>
 */
public enum BedrockTextFormatting {

    BLACK("black", '0', 0x00_00_00),
    DARK_BLUE("dark_blue", '1', 0x00_00_AA),
    DARK_GREEN("dark_green", '2', 0x00_AA_00),
    DARK_AQUA("dark_aqua", '3', 0x00_AA_AA),
    DARK_RED("dark_red", '4', 0xAA_00_00),
    DARK_PURPLE("dark_purple", '5', 0xAA_00_AA),
    GOLD("gold", '6', 0xFF_AA_00),
    GRAY("gray", '7', 0xAA_AA_AA),
    DARK_GRAY("dark_gray", '8', 0x55_55_55),
    BLUE("blue", '9', 0x55_55_FF),
    GREEN("green", 'a', 0x55_FF_55),
    AQUA("aqua", 'b', 0x55_FF_FF),
    RED("red", 'c', 0xFF_55_55),
    LIGHT_PURPLE("light_purple", 'd', 0xFF_55_FF),
    YELLOW("yellow", 'e', 0xFF_FF_55),
    WHITE("white", 'f', 0xFF_FF_FF),
    MINECOIN_GOLD("minecoin_gold", 'g', 0xDD_D6_05),
    MATERIAL_QUARTZ("material_quartz", 'h', 0xE3_D4_D1),
    MATERIAL_IRON("material_iron", 'i', 0xCE_CA_CA),
    MATERIAL_NETHERITE("material_netherite", 'j', 0x44_3A_3B),
    MATERIAL_REDSTONE("material_redstone", 'm', 0x97_16_07),
    MATERIAL_COPPER("material_copper", 'n', 0xB4_68_4D),
    MATERIAL_GOLD("material_gold", 'p', 0xDE_B1_2D),
    MATERIAL_EMERALD("material_emerald", 'q', 0x47_A0_36),
    MATERIAL_DIAMOND("material_diamond", 's', 0x2C_BA_A8),
    MATERIAL_LAPIS("material_lapis", 't', 0x21_49_7B),
    MATERIAL_AMETHYST("material_amethyst", 'u', 0x9A_5C_C6),
    MATERIAL_RESIN("material_resin", 'v', 0xEB_71_14),

    OBFUSCATED("obfuscated", 'k'),
    BOLD("bold", 'l'),
    ITALIC("italic", 'o'),
    RESET("reset", 'r');

    /**
     * All text formattings and colors.
     */
    public static final Map<String, BedrockTextFormatting> ALL = new LinkedHashMap<>();
    /**
     * All text colors.
     */
    public static final Map<String, BedrockTextFormatting> COLORS = new LinkedHashMap<>();
    /**
     * All text formattings.
     */
    public static final Map<String, BedrockTextFormatting> FORMATTINGS = new LinkedHashMap<>();
    /**
     * The color char used for formatting.
     */
    public static final char COLOR_CHAR = '\u00A7';

    static {
        for (BedrockTextFormatting formatting : values()) {
            ALL.put(formatting.getName(), formatting);
            if (formatting.isColor()) COLORS.put(formatting.getName(), formatting);
            else if (formatting.isFormatting()) FORMATTINGS.put(formatting.getName(), formatting);
            else throw new IllegalStateException("Formatting " + formatting.name() + " is neither a color nor a formatting");
        }
    }

    /**
     * Get a formatting by ordinal.<br>
     * <br>
     * Example:<br>
     * {@code TextFormatting.getByOrdinal(0)} -{@literal >} {@link #BLACK}
     *
     * @param ordinal The ordinal of the formatting
     * @return The formatting or null if not found
     */
    @Nullable
    public static BedrockTextFormatting getByOrdinal(final int ordinal) {
        for (BedrockTextFormatting formatting : values()) {
            if (formatting.ordinal() == ordinal) return formatting;
        }
        return null;
    }

    /**
     * Get a formatting by name.<br>
     * <br>
     * Example:<br>
     * {@code TextFormatting.getByName("RED")} -{@literal >} {@link #RED}
     *
     * @param name The name of the formatting
     * @return The formatting or null if not found
     */
    @Nullable
    public static BedrockTextFormatting getByName(final String name) {
        return ALL.get(name.toLowerCase());
    }

    /**
     * Get a formatting by code.<br>
     * <br>
     * Example:<br>
     * {@code TextFormatting.getByCode('c')} -{@literal >} {@link #RED}
     *
     * @param code The code of the formatting
     * @return The formatting or null if not found
     */
    @Nullable
    public static BedrockTextFormatting getByCode(final char code) {
        for (BedrockTextFormatting formatting : values()) {
            if (formatting.getCode() == code) return formatting;
        }
        return null;
    }

    /**
     * Get the closest formatting color to the given rgb color.
     *
     * @param rgb The rgb color
     * @return The closest formatting color
     */
    public static BedrockTextFormatting getClosestFormattingColor(final int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;

        BedrockTextFormatting closest = null;
        int closestDistance = Integer.MAX_VALUE;
        for (BedrockTextFormatting color : COLORS.values()) {
            int colorR = (color.getRgbValue() >> 16) & 0xFF;
            int colorG = (color.getRgbValue() >> 8) & 0xFF;
            int colorB = color.getRgbValue() & 0xFF;

            int distance = (r - colorR) * (r - colorR) + (g - colorG) * (g - colorG) + (b - colorB) * (b - colorB);
            if (distance < closestDistance) {
                closest = color;
                closestDistance = distance;
            }
        }
        return closest;
    }


    private final Type type;
    private final String name;
    private final char code;
    private final int rgbValue;

    BedrockTextFormatting(final String name, final char code, final int rgbValue) {
        this.type = Type.COLOR;
        this.name = name;
        this.code = code;
        this.rgbValue = rgbValue;
    }

    BedrockTextFormatting(final String name, final char code) {
        this.type = Type.FORMATTING;
        this.name = name;
        this.code = code;
        this.rgbValue = -1;
    }

    /**
     * @return If the formatting is a color
     */
    public boolean isColor() {
        return Type.COLOR.equals(this.type);
    }

    /**
     * @return If the formatting is a formatting (e.g. {@link #OBFUSCATED}, {@link #BOLD}, ...)
     */
    public boolean isFormatting() {
        return Type.FORMATTING.equals(this.type);
    }

    /**
     * @return The name of the formatting (e.g. "RED")
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The code of the formatting (e.g. 'c')
     */
    public char getCode() {
        return this.code;
    }

    /**
     * @return The rgb value of the formatting or -1 if it's not a color
     */
    public int getRgbValue() {
        return this.rgbValue;
    }

    /**
     * @return The formatting as a string (e.g. "§c")
     */
    public String asString() {
        return "§" + this.code;
    }


    private enum Type {
        COLOR, FORMATTING
    }

}
