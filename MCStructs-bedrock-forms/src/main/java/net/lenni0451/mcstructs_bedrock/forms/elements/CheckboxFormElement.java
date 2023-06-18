package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nullable;

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
    public void setChecked(final boolean checked) {
        this.checked = checked;
    }

    @Nullable
    @Override
    public JsonElement serialize() {
        return GSON.toJsonTree(this.checked);
    }

    @Override
    public void deserialize(JsonElement element) throws JsonParseException {
        this.checked = element.getAsBoolean();
    }

}
