package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import net.lenni0451.mcstructs_bedrock.forms.AForm;

import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class AFormElement {

    protected static final Gson GSON = new Gson();

    private final FormElementType type;
    private final String text;
    protected Function<String, String> translator = AForm.DEFAULT_TRANSLATOR;

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
    public String getText(final boolean translate) {
        if (translate) return this.translator.apply(this.text);
        else return this.text;
    }

    /**
     * Set the translator for the element.
     *
     * @param translator The translator
     */
    public void setTranslator(final Function<String, String> translator) {
        this.translator = translator;
    }

    /**
     * @return The used translator
     */
    public Function<String, String> getTranslator() {
        return this.translator;
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
