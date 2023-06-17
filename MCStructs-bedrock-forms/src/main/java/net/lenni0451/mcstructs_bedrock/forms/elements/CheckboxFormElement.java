package net.lenni0451.mcstructs_bedrock.forms.elements;

import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

/**
 * A checkbox form element for the {@link CustomForm}.
 */
public class CheckboxFormElement extends AFormElement {

    private final boolean defaultValue;
    private boolean checked;

    public CheckboxFormElement(final String text) {
        this(text, false);
    }

    public CheckboxFormElement(final String text, final boolean defaultValue) {
        super(FormElementType.CHECKBOX, text);
        this.defaultValue = defaultValue;
        this.checked = defaultValue;
    }

    /**
     * @return The default value of the checkbox
     */
    public boolean getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @return The current value of the checkbox
     */
    public boolean isChecked() {
        return this.checked;
    }

    /**
     * Set the current value of the checkbox.
     *
     * @param checked The new value
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
