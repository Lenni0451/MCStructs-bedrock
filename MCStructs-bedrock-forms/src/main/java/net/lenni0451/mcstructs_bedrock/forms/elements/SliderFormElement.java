package net.lenni0451.mcstructs_bedrock.forms.elements;

import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

/**
 * A slider form element for the {@link CustomForm}.
 */
public class SliderFormElement extends AFormElement {

    private final float min;
    private final float max;
    private final int step;
    private final float defaultValue;
    private float current;

    public SliderFormElement(final String text, final float min, final float max, final int step) {
        this(text, min, max, step, min);
    }

    public SliderFormElement(final String text, final float min, final float max, final int step, final float defaultValue) {
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
    public int getStep() {
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
    public void setCurrent(float current) {
        this.current = current;
    }

}
