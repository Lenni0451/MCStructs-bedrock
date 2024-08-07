package net.lenni0451.mcstructs_bedrock.text.utils;

import net.lenni0451.mcstructs_bedrock.text.BedrockTextFormatting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

public class BedrockTextUtils {

    /**
     * Get the legacy style of a string at the given position.<br>
     * If the position is negative the style will be empty.<br>
     * If the position is greater than the length of the string the last style will be returned.
     *
     * @param s        The string to get the style from
     * @param position The position to get the style at
     * @return The style at the given position
     */
    public static LegacyStyle getStyleAt(final String s, final int position) {
        return getStyleAt(s, position, BedrockTextFormatting::getByCode);
    }

    /**
     * Get the legacy style of a string at the given position.<br>
     * If the position is negative the style will be empty.<br>
     * If the position is greater than the length of the string the last style will be returned.<br>
     * The {@code formattingResolver} should return a formatting for the given char or {@code null} if the previous formatting should be kept.<br>
     * When returning a color the previous formattings like {@code bold, italic, etc.} will be reset.
     *
     * @param s                  The string to get the style from
     * @param position           The position to get the style at
     * @param formattingResolver The function that resolves the formatting for the given char
     * @return The style at the given position
     */
    public static LegacyStyle getStyleAt(final String s, final int position, final Function<Character, BedrockTextFormatting> formattingResolver) {
        char[] chars = s.toCharArray();
        LegacyStyle legacyStyle = new LegacyStyle();

        for (int i = 0; i < Math.min(chars.length, position); i++) {
            char c = chars[i];
            if (c == BedrockTextFormatting.COLOR_CHAR) {
                if (i + 1 < chars.length) {
                    char code = chars[++i];
                    BedrockTextFormatting formatting = formattingResolver.apply(code);
                    if (formatting == null) continue;

                    if (BedrockTextFormatting.RESET.equals(formatting)) {
                        legacyStyle.setColor(null);
                        legacyStyle.getStyles().clear();
                    } else if (formatting.isColor()) {
                        legacyStyle.setColor(formatting);
                    } else {
                        legacyStyle.getStyles().add(formatting);
                    }
                }
            }
        }
        return legacyStyle;
    }

    /**
     * Split a string by a given split string and keep the legacy style of the previous part.
     *
     * @param s     The string to split
     * @param split The split string
     * @return The split string
     */
    public static String[] split(final String s, final String split) {
        return split(s, split, BedrockTextFormatting::getByCode);
    }

    /**
     * Split a string by a given split string and keep the legacy style of the previous part.<br>
     * The {@code formattingResolver} should return a formatting for the given char or {@code null} if the previous formatting should be kept.<br>
     * When returning a color the previous formattings like {@code bold, italic, etc.} will be reset.
     *
     * @param s                  The string to split
     * @param split              The split string
     * @param formattingResolver The function that resolves the formatting for the given char
     * @return The split string
     */
    public static String[] split(final String s, final String split, final Function<Character, BedrockTextFormatting> formattingResolver) {
        String[] parts = s.split(Pattern.quote(split));
        for (int i = 1; i < parts.length; i++) {
            String prev = parts[i - 1];
            LegacyStyle style = getStyleAt(prev, prev.length(), formattingResolver);
            parts[i] = style.toLegacy() + parts[i];
        }
        return parts;
    }


    public static class LegacyStyle {
        private BedrockTextFormatting color = null;
        private final Set<BedrockTextFormatting> styles = new HashSet<>();

        private LegacyStyle() {
        }

        /**
         * Set the color of the style.
         *
         * @param color The color to set
         */
        public void setColor(@Nullable final BedrockTextFormatting color) {
            this.color = color;
        }

        /**
         * @return The color of the style
         */
        @Nullable
        public BedrockTextFormatting getColor() {
            return this.color;
        }

        /**
         * @return The styles of the style
         */
        @Nonnull
        public Set<BedrockTextFormatting> getStyles() {
            return this.styles;
        }

        /**
         * @return The legacy string of the style (e.g. §4§l)
         */
        public String toLegacy() {
            StringBuilder out = new StringBuilder();
            if (this.color != null) out.append(this.color.asString());
            for (BedrockTextFormatting style : this.styles) out.append(style.asString());
            return out.toString();
        }

        @Override
        public String toString() {
            return "LegacyStyle{" +
                    "color=" + this.color +
                    ", styles=" + this.styles +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LegacyStyle that = (LegacyStyle) o;
            return Objects.equals(this.color, that.color) && Objects.equals(this.styles, that.styles);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.color, this.styles);
        }
    }

}
