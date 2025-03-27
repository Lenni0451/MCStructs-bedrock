package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import javax.annotation.Nullable;

public interface ModifiableFormElement {

    /**
     * Serialize the element to a json element.
     *
     * @return The serialized element
     */
    @Nullable
    JsonElement serialize();

    /**
     * Deserialize the element from a json element.
     *
     * @param element The serialized element
     * @throws JsonParseException If the element is not valid
     */
    void deserialize(final JsonElement element) throws JsonParseException;


}
