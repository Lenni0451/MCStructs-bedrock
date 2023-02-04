package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RootBedrockComponent extends ABedrockComponent {

    private final List<ABedrockComponent> components;

    public RootBedrockComponent() {
        this.components = new ArrayList<>();
    }

    public RootBedrockComponent(final ABedrockComponent... components) {
        this.components = new ArrayList<>();
        this.components.addAll(Arrays.asList(components));
    }

    public RootBedrockComponent(final List<ABedrockComponent> components) {
        this.components = components;
    }

    /**
     * @return The components of this root component
     */
    public List<ABedrockComponent> getComponents() {
        return this.components;
    }

    /**
     * Add a component to this root component.
     *
     * @param component The component to add
     */
    public void addComponent(final ABedrockComponent component) {
        this.components.add(component);
    }

    @Override
    public String asString() {
        StringBuilder out = new StringBuilder();
        for (ABedrockComponent component : this.components) out.append(component.asString());
        return out.toString();
    }

    @Override
    public ABedrockComponent copy() {
        RootBedrockComponent out = new RootBedrockComponent();
        for (ABedrockComponent component : this.components) out.addComponent(component.copy());
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RootBedrockComponent that = (RootBedrockComponent) o;
        return Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "RootBedrockComponent{" +
                "components=" + components +
                '}';
    }

}
