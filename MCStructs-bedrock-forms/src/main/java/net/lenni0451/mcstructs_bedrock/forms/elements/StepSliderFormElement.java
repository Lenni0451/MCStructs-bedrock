package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nullable;

/**
 * A step slider form element for the {@link CustomForm}.
 */
public class StepSliderFormElement extends AFormElement {

    private final int defaultStep;
    private final String[] steps;
    private int selected;

    public StepSliderFormElement(final String text, final String... steps) {
        this(text, 0, steps);
    }

    public StepSliderFormElement(final String text, final int defaultStep, final String... steps) {
        super(FormElementType.STEP_SLIDER, text);
        this.defaultStep = defaultStep;
        this.steps = steps;
        this.selected = defaultStep;
    }

    /**
     * @return The default step of the step slider
     */
    public int getDefaultStep() {
        return this.defaultStep;
    }

    /**
     * @return The steps of the step slider
     */
    public String[] getSteps() {
        return this.steps;
    }

    /**
     * @return The selected step of the step slider
     */
    public int getSelected() {
        return this.selected;
    }

    /**
     * Set the selected step of the step slider.
     *
     * @param selected The new selected step
     */
    public void setSelected(final int selected) {
        this.selected = selected;
    }

    @Nullable
    @Override
    public JsonElement serialize() {
        return GSON.toJsonTree(this.selected);
    }

    @Override
    public void deserialize(JsonElement element) throws JsonParseException {
        this.selected = element.getAsInt();
    }

}
