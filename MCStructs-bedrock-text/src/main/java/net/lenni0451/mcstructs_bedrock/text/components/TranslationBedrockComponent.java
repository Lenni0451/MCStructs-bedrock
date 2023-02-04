package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranslationBedrockComponent extends ABedrockComponent {

    private static final Pattern S_ARGS_PATTERN = Pattern.compile("%([^%\\s]*)%s");
    private static final Pattern ARGS_PATTERN = Pattern.compile("%([^%\\s]*)%(s|\\d)?");

    private final String key;
    private final Object[] args;
    private Function<String, String> translator = s -> s;

    public TranslationBedrockComponent(final String key, final List<Object> args) {
        this.key = key;
        this.args = args.toArray();
    }

    public TranslationBedrockComponent(final String key, final Object... args) {
        this.key = key;
        this.args = args;
    }

    /**
     * @return The key of this component
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return The arguments of this component
     */
    public Object[] getArgs() {
        return this.args;
    }

    /**
     * Set the translator function used to translate the key.
     *
     * @param translator The translator function
     */
    public void setTranslator(final Function<String, String> translator) {
        this.translator = translator;
    }

    @Override
    public String asString() {
        StringBuilder out = new StringBuilder();

        String translated = this.translator.apply(this.key);
        int argIndex = 0;
        int numOffset = this.countSArgs(translated);
        Matcher matcher = ARGS_PATTERN.matcher(translated);
        int start = 0;
        while (matcher.find()) {
            int matchStart = matcher.start();
            int matchEnd = matcher.end();
            if (matchStart > start) out.append(translated.substring(start, matchStart).replace("%", ""));
            start = matchEnd;

            out.append(matcher.group(1));
            if (matcher.group(2) == null) {
                out.append("%");
            } else {
                String argType = matcher.group(2);
                Object arg;
                if (argType.equals("s")) arg = this.getArg(argIndex++);
                else arg = this.getArg(numOffset + Integer.parseInt(argType) - 1);
                if (arg instanceof ABedrockComponent) out.append(((ABedrockComponent) arg).asString());
                else out.append(arg);
            }
        }
        if (start < translated.length()) out.append(translated.substring(start).replace("%", ""));
        return out.toString();
    }

    private int countSArgs(final String s) {
        int count = 0;
        Matcher matcher = S_ARGS_PATTERN.matcher(s);
        while (matcher.find()) count++;
        return count;
    }

    private Object getArg(final int index) {
        if (index < 0 || index >= this.args.length) return "";
        return this.args[index];
    }

    @Override
    public ABedrockComponent copy() {
        Object[] copyArgs = new Object[this.args.length];
        for (int i = 0; i < this.args.length; i++) {
            Object arg = this.args[i];
            if (arg instanceof ABedrockComponent) copyArgs[i] = ((ABedrockComponent) arg).copy();
            else copyArgs[i] = arg;
        }
        TranslationBedrockComponent component = new TranslationBedrockComponent(this.key, copyArgs);
        component.translator = this.translator;
        return component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationBedrockComponent that = (TranslationBedrockComponent) o;
        return Objects.equals(key, that.key) && Arrays.equals(args, that.args) && Objects.equals(translator, that.translator);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(key, translator);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    @Override
    public String toString() {
        return "TranslationBedrockComponent{" +
                "key='" + key + '\'' +
                ", args=" + Arrays.toString(args) +
                ", translator=" + translator +
                '}';
    }

}
