package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nullable;

/**
 * A text field form element for the {@link CustomForm}.
 */
public class TextFieldFormElement extends FormElement implements ModifiableFormElement {

    private final String placeholder;
    private final String defaultValue;
    private String value;

    public TextFieldFormElement(final String text, final String placeholder) {
        this(text, placeholder, null);
        this.value = "";
    }

    public TextFieldFormElement(final String text, final String placeholder, final String defaultValue) {
        super(FormElementType.TEXT_FIELD, text);
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    /**
     * @return The placeholder of the text field
     */
    public String getPlaceholder() {
        return this.placeholder;
    }

    /**
     * @return The default value of the text field
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @return The current value of the text field
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the current value of the text field.
     *
     * @param value The new value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    @Nullable
    @Override
    public JsonElement serialize() {
        return GSON.toJsonTree(this.value);
    }

    @Override
    public void deserialize(JsonElement element) throws JsonParseException {
        this.value = element.getAsString();
    }

}
