package net.lenni0451.mcstructs_bedrock.forms.types;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import net.lenni0451.mcstructs_bedrock.forms.Form;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormElement;
import net.lenni0451.mcstructs_bedrock.forms.elements.ModifiableFormElement;
import net.lenni0451.mcstructs_bedrock.forms.types.builder.CustomFormBuilder;

import javax.annotation.Nonnull;
import java.util.function.Function;

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
public class CustomForm extends Form {

    /**
     * @return A new builder for a custom form
     */
    public static CustomFormBuilder builder() {
        return new CustomFormBuilder();
    }


    private final FormElement[] elements;

    /**
     * @param title    The title of the form
     * @param elements The elements of the form
     */
    public CustomForm(@Nonnull final String title, final FormElement... elements) {
        super(FormType.CUSTOM, title);
        this.elements = elements;
    }

    @Override
    public void setTranslator(@Nonnull Function<String, String> translator) {
        super.setTranslator(translator);
        for (FormElement element : this.elements) element.setTranslator(translator);
    }

    /**
     * @return The elements of the form
     */
    @Nonnull
    public FormElement[] getElements() {
        return this.elements;
    }

    @Override
    public String serializeResponse() {
        JsonArray response = new JsonArray();
        for (FormElement element : this.elements) {
            if (element instanceof ModifiableFormElement) {
                response.add(((ModifiableFormElement) element).serialize());
            } else {
                response.add(JsonNull.INSTANCE);
            }
        }
        return response.toString();
    }

    /**
     * {@inheritDoc}
     *
     * @throws JsonParseException If the response is not a valid json array
     */
    @Override
    public void deserializeResponse(String response) throws JsonParseException {
        JsonArray responseArray = JsonParser.parseString(response).getAsJsonArray();
        if (responseArray.size() != this.elements.length) {
            throw new JsonParseException("Invalid response size: expected " + this.elements.length + ", got " + responseArray.size());
        }
        for (int i = 0; i < this.elements.length; i++) {
            FormElement element = this.elements[i];
            if (!(element instanceof ModifiableFormElement)) continue;
            ((ModifiableFormElement) element).deserialize(responseArray.get(i));
        }
    }

}
