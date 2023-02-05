package net.lenni0451.mcstructs_bedrock.text.utils;

import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BedrockTranslator {

    private static final Pattern TRANSLATION_KEY_PATTERN = Pattern.compile("%%|%(?:([\\w._\\-]+)([^\\w._\\-]))?");
    private static final Pattern DOLLAR_REPLACE = Pattern.compile("\\$[ds]");
    private static final Pattern S_ARGS_PATTERN = Pattern.compile("%[ds]");
    private static final Pattern ARGS_PATTERN = Pattern.compile("%([ds\\d])");

    /**
     * Translate a key with the given translator and arguments.<br>
     * This method is made to comply with the vanilla bedrock client.<br>
     * <br>
     * The bedrock edition has a special way of appending data to a translation key:<br>
     * If only the key is given, the client will try to translate it.<br>
     * Translation keys <i>can</i> start with a {@code %} sign. When having multiple translation keys in a single message this is required.<br>
     * Translation keys can contain the following characters: {@code a-z, A-Z, 0-9, _, -, .}<br>
     * If the key ends with any other character, the client will append it to the translated string (even unescaped {@code %} signs).<br>
     * To insert a {@code %} sign into the message, you have to escape it with another {@code %} sign.<br>
     * All {@code $d} and {@code $s} will be removed from the translated string.<br>
     * {@code %d}, {@code %s} and {@code %[1-9]} will be replaced with the given arguments.<br>
     * When specifying a number, the argument of the given index will be used. The index starts at 1 and is offset by the number of {@code %s} and {@code %d} in the key.
     *
     * @param key        The key to translate
     * @param translator The translator function
     * @param args       The arguments to insert
     * @return The translated string
     */
    public static String translate(final String key, final Function<String, String> translator, final Object[] args) {
        StringBuilder out = new StringBuilder();

        String translated = fillTranslations(key, translator);
        translated = replaceDollar(translated);
        if (args.length != 0) {
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
        } else {
            out.append(translated);
        }
        return out.toString();
    }

    private static String fillTranslations(final String s, final Function<String, String> translator) {
        StringBuilder out = new StringBuilder();
        Matcher matcher = TRANSLATION_KEY_PATTERN.matcher(s);
        int start = 0;
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            if (matchStart > start) out.append(s, start, matchStart);
            start = matchEnd;

            String match = matcher.group();
            if (match.equals("%%")) {
                out.append("%");
            } else {
                String key = matcher.group(1);
                if (key != null) {
                    String terminator = matcher.group(2);
                    out.append(translator.apply(key));
                    if (terminator != null) out.append(terminator);
                }
            }
        }
        if (start == 0) out.append(translator.apply(s));
        else if (start < s.length()) out.append(s, start, s.length());
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
