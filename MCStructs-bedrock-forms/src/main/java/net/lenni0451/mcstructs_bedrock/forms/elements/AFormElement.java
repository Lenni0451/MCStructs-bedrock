package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import javax.annotation.Nullable;

public abstract class AFormElement {

    protected static Gson GSON = new Gson();


    private final FormElementType type;
    private final String text;

    public AFormElement(final FormElementType type, final String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * @return The type of the element
     */
    public FormElementType getType() {
        return this.type;
    }

    /**
     * @return The text of the element
     */
    public String getText() {
        return this.text;
    }

    /**
     * Serialize the element to a json element.
     *
     * @return The serialized element
     */
    @Nullable
    public abstract JsonElement serialize();

    /**
     * Deserialize the element from a json element.
     *
     * @param element The serialized element
     * @throws JsonParseException If the element is not valid
     */
    public abstract void deserialize(final JsonElement element) throws JsonParseException;

}
