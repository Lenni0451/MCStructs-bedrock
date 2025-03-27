package net.lenni0451.mcstructs_bedrock.text;

import net.lenni0451.mcstructs_bedrock.text.components.*;

/**
 * The base bedrock text component class.<br>
 * Implementations of this class are:<br>
 * - {@link RootBedrockComponent}<br>
 * - {@link StringBedrockComponent}<br>
 * - {@link TranslationBedrockComponent}<br>
 * - {@link ScoreBedrockComponent}<br>
 * - {@link SelectorBedrockComponent}
 */
public abstract class BedrockComponent {

    /**
     * @return The string representation of this component
     */
    public abstract String asString();

    /**
     * @return A copy of this component
     */
    public abstract BedrockComponent copy();

    public abstract boolean equals(final Object o);

    public abstract int hashCode();

    public abstract String toString();

}
