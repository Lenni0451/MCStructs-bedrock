package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nullable;

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
     * Get the options of the dropdown.
     *
     * @param translate If the options should be translated
     * @return The options
     */
    public String[] getOptions(final boolean translate) {
        if (translate) {
            String[] translated = new String[this.options.length];
            for (int i = 0; i < this.options.length; i++) translated[i] = this.translator.apply(this.options[i]);
            return translated;
        } else {
            return this.options;
        }
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
