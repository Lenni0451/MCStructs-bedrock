package net.lenni0451.mcstructs_bedrock.forms.types;

import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.elements.AFormElement;
import net.lenni0451.mcstructs_bedrock.forms.types.builder.CustomFormBuilder;

import javax.annotation.Nonnull;

/**
 * Representation of a custom form.<br>
 * A custom form can have multiple elements:<br>
 * - Checkbox<br>
 * - Dropdown<br>
 * - Label<br>
 * - Slider<br>
 * - Step Slider<br>
 * - Text Field
 */
public class CustomForm extends AForm {

    /**
     * @return A new builder for a custom form
     */
    public static CustomFormBuilder builder() {
        return new CustomFormBuilder();
    }


    private final AFormElement[] elements;

    /**
     * @param title    The title of the form
     * @param elements The elements of the form
     */
    public CustomForm(@Nonnull final String title, final AFormElement... elements) {
        super(FormType.CUSTOM, title);
        this.elements = elements;
    }

    /**
     * @return The elements of the form
     */
    @Nonnull
    public AFormElement[] getElements() {
        return this.elements;
    }

}
