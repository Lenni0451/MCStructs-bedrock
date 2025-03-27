package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nullable;

/**
 * A slider form element for the {@link CustomForm}.
 */
public class SliderFormElement extends FormElement implements ModifiableFormElement {

    private final float min;
    private final float max;
    private final float step;
    private final float defaultValue;
    private float current;

    public SliderFormElement(final String text, final float min, final float max, final float step) {
        this(text, min, max, step, min);
    }

    public SliderFormElement(final String text, final float min, final float max, final float step, final float defaultValue) {
        super(FormElementType.SLIDER, text);
        this.min = min;
        this.max = max;
        this.step = step;
        this.defaultValue = defaultValue;
        this.current = defaultValue;
    }

    /**
     * @return The minimum value of the slider
     */
    public float getMin() {
        return this.min;
    }

    /**
     * @return The maximum value of the slider
     */
    public float getMax() {
        return this.max;
    }

    /**
     * @return The step of the slider
     */
    public float getStep() {
        return this.step;
    }

    /**
     * @return The default value of the slider
     */
    public float getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @return The current value of the slider
     */
    public float getCurrent() {
        return this.current;
    }

    /**
     * Set the current value of the slider.
     *
     * @param current The new value
     */
    public void setCurrent(final float current) {
        this.current = current;
    }

    @Nullable
    @Override
    public JsonElement serialize() {
        return GSON.toJsonTree(this.current);
    }

    @Override
    public void deserialize(JsonElement element) throws JsonParseException {
        this.current = element.getAsFloat();
    }

}
