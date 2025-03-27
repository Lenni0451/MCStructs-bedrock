package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.BedrockComponent;

import java.util.Objects;

public class StringBedrockComponent extends BedrockComponent {

    private final String text;

    public StringBedrockComponent(final String text) {
        this.text = text;
    }

    /**
     * @return The text of this component
     */
    public String getText() {
        return this.text;
    }

    @Override
    public String asString() {
        return this.text;
    }

    @Override
    public BedrockComponent copy() {
        return new StringBedrockComponent(this.text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringBedrockComponent that = (StringBedrockComponent) o;
        return Objects.equals(this.text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.text);
    }

    @Override
    public String toString() {
        return "StringBedrockComponent{" +
                "text='" + this.text + '\'' +
                '}';
    }

}
