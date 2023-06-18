package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import javax.annotation.Nullable;

/**
 * A label form element for the {@link CustomForm}.
 */
public class LabelFormElement extends AFormElement {

    public LabelFormElement(final String text) {
        super(FormElementType.LABEL, text);
    }

    @Nullable
    @Override
    public JsonElement serialize() {
        return null;
    }

    @Override
    public void deserialize(JsonElement element) throws JsonParseException {
    }

}
