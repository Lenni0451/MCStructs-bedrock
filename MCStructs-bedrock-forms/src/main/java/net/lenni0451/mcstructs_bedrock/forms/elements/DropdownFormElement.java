package net.lenni0451.mcstructs_bedrock.forms.elements;

import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

/**
 * A dropdown form element for the {@link CustomForm}.
 */
public class DropdownFormElement extends AFormElement {

    private final int defaultOption;
    private final String[] options;
    private int selected;

    public DropdownFormElement(final String text, final String... options) {
        this(text, 0, options);
    }

    public DropdownFormElement(final String text, final int defaultOption, final String... options) {
        super(FormElementType.DROPDOWN, text);
        this.defaultOption = defaultOption;
        this.options = options;
        this.selected = defaultOption;
    }

    /**
     * @return The default option of the dropdown
     */
    public int getDefaultOption() {
        return this.defaultOption;
    }

    /**
     * @return The options of the dropdown
     */
    public String[] getOptions() {
        return this.options;
    }

    /**
     * @return The selected option of the dropdown
     */
    public int getSelected() {
        return this.selected;
    }

    /**
     * Set the selected option of the dropdown.
     *
     * @param selected The new selected option
     */
    public void setSelected(int selected) {
        this.selected = selected;
    }

}
