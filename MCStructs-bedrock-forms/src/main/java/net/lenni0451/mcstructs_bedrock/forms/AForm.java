package net.lenni0451.mcstructs_bedrock.forms;

import javax.annotation.Nonnull;
import java.util.function.Function;

public abstract class AForm {

    public static final Function<String, String> DEFAULT_TRANSLATOR = s -> s;

    private final FormType type;
    private final String title;
    protected Function<String, String> translator = DEFAULT_TRANSLATOR;

    public AForm(final FormType type, final String title) {
        this.type = type;
        this.title = title;
    }

    /**
     * @return The type of the form
     */
    public FormType getType() {
        return this.type;
    }

    /**
     * @return The untranslated title of the form
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get the title of the form.
     *
     * @param translate If the title should be translated
     * @return The title
     */
    public String getTitle(final boolean translate) {
        if (translate) return this.translator.apply(this.title);
        else return this.title;
    }

    /**
     * Set the translator for the form.
     *
     * @param translator The translator
     */
    public void setTranslator(@Nonnull final Function<String, String> translator) {
        this.translator = translator;
    }

    /**
     * @return The used translator
     */
    @Nonnull
    public Function<String, String> getTranslator() {
        return this.translator;
    }

    /**
     * Serialize the response of the form to a string.
     *
     * @return The serialized response
     */
    public abstract String serializeResponse();

    /**
     * Deserialize the response of the form from a string.
     *
     * @param response The serialized response
     */
    public abstract void deserializeResponse(final String response);

}
