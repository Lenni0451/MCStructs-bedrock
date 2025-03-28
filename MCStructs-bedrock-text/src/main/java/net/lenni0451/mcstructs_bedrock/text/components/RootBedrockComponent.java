package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.BedrockComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class RootBedrockComponent extends BedrockComponent {

    private final List<BedrockComponent> components;

    public RootBedrockComponent() {
        this.components = new ArrayList<>();
    }

    public RootBedrockComponent(final BedrockComponent... components) {
        this.components = new ArrayList<>();
        this.components.addAll(Arrays.asList(components));
    }

    public RootBedrockComponent(final List<BedrockComponent> components) {
        this.components = components;
    }

    /**
     * @return The components of this root component
     */
    public List<BedrockComponent> getComponents() {
        return this.components;
    }

    /**
     * Add a component to this root component.
     *
     * @param component The component to add
     * @return This root component
     */
    public RootBedrockComponent addComponent(final BedrockComponent component) {
        this.components.add(component);
        return this;
    }

    /**
     * Iterate over all components and their siblings.
     *
     * @param consumer The consumer that will be called for every component
     * @return This component
     */
    public RootBedrockComponent forEach(final Consumer<BedrockComponent> consumer) {
        consumer.accept(this);
        for (BedrockComponent component : this.components) {
            if (component instanceof RootBedrockComponent) ((RootBedrockComponent) component).forEach(consumer);
            else consumer.accept(component);
        }
        return this;
    }

    @Override
    public String asString() {
        StringBuilder out = new StringBuilder();
        for (BedrockComponent component : this.components) out.append(component.asString());
        return out.toString();
    }

    @Override
    public BedrockComponent copy() {
        RootBedrockComponent out = new RootBedrockComponent();
        for (BedrockComponent component : this.components) out.addComponent(component.copy());
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RootBedrockComponent that = (RootBedrockComponent) o;
        return Objects.equals(this.components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.components);
    }

    @Override
    public String toString() {
        return "RootBedrockComponent{" +
                "components=" + this.components +
                '}';
    }

}
