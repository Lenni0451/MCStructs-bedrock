package net.lenni0451.mcstructs_bedrock.forms.elements;

import com.google.gson.Gson;
import net.lenni0451.mcstructs_bedrock.forms.Form;

import java.util.function.Function;

public abstract class FormElement {

    protected static final Gson GSON = new Gson();

    private final FormElementType type;
    private final String text;
    protected Function<String, String> translator = Form.DEFAULT_TRANSLATOR;

    public FormElement(final FormElementType type, final String text) {
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
     * @return The translated text of the element
     */
    public String getText() {
        return this.getText(true);
    }

    /**
     * Get the text of the element.
     *
     * @param translate If the text should be translated
     * @return The text
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

}
