package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;

import java.util.Objects;

public class SelectorBedrockComponent extends ABedrockComponent {

    private final String selector;

    public SelectorBedrockComponent(final String selector) {
        this.selector = selector;
    }

    /**
     * @return The selector of this component
     */
    public String getSelector() {
        return this.selector;
    }

    @Override
    public String asString() {
        return this.selector;
    }

    @Override
    public ABedrockComponent copy() {
        return new SelectorBedrockComponent(this.selector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectorBedrockComponent that = (SelectorBedrockComponent) o;
        return Objects.equals(selector, that.selector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selector);
    }

    @Override
    public String toString() {
        return "SelectorBedrockComponent{" +
                "selector='" + selector + '\'' +
                '}';
    }

}
