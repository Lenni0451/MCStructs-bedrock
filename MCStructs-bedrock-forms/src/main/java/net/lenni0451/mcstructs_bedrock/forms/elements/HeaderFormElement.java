package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import javax.annotation.Nullable;

public class HeaderFormElement extends AFormElement {

    public HeaderFormElement(final String text) {
        super(FormElementType.HEADER, text);
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
