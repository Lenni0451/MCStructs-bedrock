package net.lenni0451.mcstructs_bedrock.text.utils;

import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BedrockTranslator {

    private static final Pattern PERCENT_REPLACE = Pattern.compile("%%|(?:(?<=\\s|^|%)%)");
    private static final Pattern DOLLAR_REPLACE = Pattern.compile("\\$[ds]");
    private static final Pattern S_ARGS_PATTERN = Pattern.compile("%[ds]");
    private static final Pattern ARGS_PATTERN = Pattern.compile("%([ds\\d])");

    /**
     * Translate a key with the given translator and arguments.<br>
     * This method is made to comply with the vanilla bedrock client.<br>
     * <br>
     * How the translation is done:<br>
     * - All single percent signs at the beginning of a word are removed<br>
     * - All double percent signs are replaced with a single percent sign<br>
     * - The key is translated with the given translator<br>
     * - All dollar signs are removed if followed by a 'd' or 's'<br>
     * - All arguments using the percent sign followed by a 'd' or 's' are counted and used as the offset for the numbered arguments<br>
     * - All arguments are replaced with the given arguments
     *
     * @param key        The key to translate
     * @param translator The translator function
     * @param args       The arguments to insert
     * @return The translated string
     */
    public static String translate(final String key, final Function<String, String> translator, final Object... args) {
        StringBuilder out = new StringBuilder();

        String translated = replaceDollar(translator.apply(replacePercent(key)));
        Matcher matcher = ARGS_PATTERN.matcher(translated);
        int argIndex = 0;
        int numArgOffset = countSArgs(translated);
        int start = 0;
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            if (matchStart > start) out.append(translated, start, matchStart);
            start = matchEnd;

            String match = matcher.group(1);
            if (match.equals("d") || match.equals("s")) appendArg(out, getArg(args, argIndex++));
            else appendArg(out, getArg(args, numArgOffset + Integer.parseInt(match) - 1));
        }
        if (start < translated.length()) out.append(translated, start, translated.length());
        return out.toString();
    }

    private static String replacePercent(final String s) {
        StringBuilder out = new StringBuilder();
        Matcher matcher = PERCENT_REPLACE.matcher(s);
        int start = 0;
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            if (matchStart > start) out.append(s, start, matchStart);
            start = matchEnd;

            String match = matcher.group();
            if (match.equals("%%")) out.append('%');
        }
        if (start < s.length()) out.append(s, start, s.length());
        return out.toString();
    }

    private static String replaceDollar(final String s) {
        return DOLLAR_REPLACE.matcher(s).replaceAll("");
    }

    private static int countSArgs(final String s) {
        int count = 0;
        Matcher matcher = S_ARGS_PATTERN.matcher(s);
        while (matcher.find()) count++;
        return count;
    }

    private static Object getArg(final Object[] args, final int index) {
        if (index < 0 || index >= args.length) return "";
        return args[index];
    }

    private static void appendArg(final StringBuilder out, final Object arg) {
        if (arg instanceof ABedrockComponent) out.append(((ABedrockComponent) arg).asString());
        else out.append(arg);
    }

}
