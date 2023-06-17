package net.lenni0451.mcstructs_bedrock.forms.types.builder;

import net.lenni0451.mcstructs_bedrock.forms.elements.*;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CustomFormBuilder {

    private String title;
    private final List<AFormElement> elements = new ArrayList<>();

    /**
     * Set the title of the form.
     *
     * @param title The title
     * @return The builder instance
     */
    public CustomFormBuilder title(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Add a checkbox to the form.
     *
     * @param text The text of the checkbox
     * @return The builder instance
     */
    public CustomFormBuilder checkbox(@Nonnull final String text) {
        this.elements.add(new CheckboxFormElement(text));
        return this;
    }

    /**
     * Add a checkbox with a default value to the form.
     *
     * @param text         The text of the checkbox
     * @param defaultValue The default value of the checkbox
     * @return The builder instance
     */
    public CustomFormBuilder checkbox(@Nonnull final String text, final boolean defaultValue) {
        this.elements.add(new CheckboxFormElement(text, defaultValue));
        return this;
    }

    /**
     * Add a dropdown to the form.
     *
     * @param text    The text of the dropdown
     * @param options The options of the dropdown
     * @return The builder instance
     */
    public CustomFormBuilder dropdown(@Nonnull final String text, final String... options) {
        this.elements.add(new DropdownFormElement(text, options));
        return this;
    }

    /**
     * Add a dropdown with a default option to the form.
     *
     * @param text          The text of the dropdown
     * @param defaultOption The default option of the dropdown
     * @param options       The options of the dropdown
     * @return The builder instance
     */
    public CustomFormBuilder dropdown(@Nonnull final String text, final int defaultOption, final String... options) {
        this.elements.add(new DropdownFormElement(text, defaultOption, options));
        return this;
    }

    /**
     * Add a label to the form.
     *
     * @param text The text of the label
     * @return The builder instance
     */
    public CustomFormBuilder label(@Nonnull final String text) {
        this.elements.add(new LabelFormElement(text));
        return this;
    }

    /**
     * Add a slider to the form.
     *
     * @param text The text of the slider
     * @param min  The minimum value of the slider
     * @param max  The maximum value of the slider
     * @param step The step value of the slider
     * @return The builder instance
     */
    public CustomFormBuilder slider(@Nonnull final String text, final float min, final float max, final float step) {
        this.elements.add(new SliderFormElement(text, min, max, step));
        return this;
    }

    /**
     * Add a slider with a default value to the form.
     *
     * @param text         The text of the slider
     * @param min          The minimum value of the slider
     * @param max          The maximum value of the slider
     * @param step         The step value of the slider
     * @param defaultValue The default value of the slider
     * @return The builder instance
     */
    public CustomFormBuilder slider(@Nonnull final String text, final float min, final float max, final float step, final float defaultValue) {
        this.elements.add(new SliderFormElement(text, min, max, step, defaultValue));
        return this;
    }

    /**
     * Add a step slider to the form.
     *
     * @param text  The text of the step slider
     * @param steps The steps of the step slider
     * @return The builder instance
     */
    public CustomFormBuilder stepSlider(@Nonnull final String text, final String... steps) {
        this.elements.add(new StepSliderFormElement(text, steps));
        return this;
    }

    /**
     * Add a step slider with a default step to the form.
     *
     * @param text        The text of the step slider
     * @param defaultStep The default step of the step slider
     * @param steps       The steps of the step slider
     * @return The builder instance
     */
    public CustomFormBuilder stepSlider(@Nonnull final String text, final int defaultStep, final String... steps) {
        this.elements.add(new StepSliderFormElement(text, defaultStep, steps));
        return this;
    }

    /**
     * Add a text field to the form.
     *
     * @param text        The text of the text field
     * @param placeholder The placeholder of the text field
     * @return The builder instance
     */
    public CustomFormBuilder textField(@Nonnull final String text, @Nonnull final String placeholder) {
        this.elements.add(new TextFieldFormElement(text, placeholder));
        return this;
    }

    /**
     * Add a text field with a default value to the form.
     *
     * @param text         The text of the text field
     * @param placeholder  The placeholder of the text field
     * @param defaultValue The default value of the text field
     * @return The builder instance
     */
    public CustomFormBuilder textField(@Nonnull final String text, @Nonnull final String placeholder, @Nonnull final String defaultValue) {
        this.elements.add(new TextFieldFormElement(text, placeholder, defaultValue));
        return this;
    }

    /**
     * Build the custom form.
     *
     * @return The custom form
     */
    public CustomForm build() {
        return new CustomForm(this.title, this.elements.toArray(new AFormElement[0]));
    }

}
