package net.lenni0451.mcstructs_bedrock.text.components;

import net.lenni0451.mcstructs_bedrock.text.BedrockComponent;

import java.util.Objects;

public class ScoreBedrockComponent extends BedrockComponent {

    private final String name;
    private final String objective;

    public ScoreBedrockComponent(final String name, final String objective) {
        this.name = name;
        this.objective = objective;
    }

    /**
     * @return The name of this component
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The objective of this component
     */
    public String getObjective() {
        return this.objective;
    }

    @Override
    public String asString() {
        return "";
    }

    @Override
    public BedrockComponent copy() {
        return new ScoreBedrockComponent(this.name, this.objective);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBedrockComponent that = (ScoreBedrockComponent) o;
        return Objects.equals(this.name, that.name) && Objects.equals(this.objective, that.objective);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.objective);
    }

    @Override
    public String toString() {
        return "ScoreBedrockComponent{" +
                "name='" + this.name + '\'' +
                ", objective='" + this.objective + '\'' +
                '}';
    }

}
