package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.utils.BedrockTranslator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class TranslationBedrockComponent extends ABedrockComponent {

    private static final Function<String, String> PASSTHROUGH_TRANSLATOR = s -> s;

    private final String key;
    private final Object[] args;
    private Function<String, String> translator = PASSTHROUGH_TRANSLATOR;

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
        return BedrockTranslator.translate(this.key, this.translator, this.args);
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
        return Objects.equals(this.key, that.key) && Arrays.equals(this.args, that.args) && Objects.equals(this.translator, that.translator);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.key, this.translator);
        result = 31 * result + Arrays.hashCode(this.args);
        return result;
    }

    @Override
    public String toString() {
        return "TranslationBedrockComponent{" +
                "key='" + this.key + '\'' +
                ", args=" + Arrays.toString(this.args) +
                ", translator=" + this.translator +
                '}';
    }

}
