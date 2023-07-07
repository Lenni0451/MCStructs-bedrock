package net.lenni0451.mcstructs_bedrock.forms.types;

import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;
import net.lenni0451.mcstructs_bedrock.forms.types.builder.ActionFormBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * Representation of an action form.<br>
 * An action form has multiple buttons and a text.
 */
public class ActionForm extends AForm {

    /**
     * @return A new builder for an action form
     */
    public static ActionFormBuilder builder() {
        return new ActionFormBuilder();
    }


    private final String text;
    private final Button[] buttons;
    private int clickedButton;

    /**
     * @param title   The title of the form
     * @param text    The text displayed in the form
     * @param buttons The buttons of the form
     */
    public ActionForm(@Nonnull final String title, @Nonnull final String text, @Nonnull final Button... buttons) {
        super(FormType.ACTION, title);
        this.text = text;
        this.buttons = buttons;
    }

    @Override
    public void setTranslator(@Nonnull Function<String, String> translator) {
        super.setTranslator(translator);
        for (Button button : this.buttons) button.setTranslator(translator);
    }

    /**
     * @return The content of the form
     */
    @Nonnull
    public String getText(final boolean translate) {
        if (translate) return this.translator.apply(this.text);
        else return this.text;
    }

    /**
     * @return The buttons of the form
     */
    @Nonnull
    public Button[] getButtons() {
        return this.buttons;
    }

    /**
     * Set the clicked button of the form.<br>
     * This is required for the response.
     *
     * @param clickedButton The clicked button
     */
    public void setClickedButton(final int clickedButton) {
        this.clickedButton = clickedButton;
    }

    /**
     * Get the clicked button of the form.<br>
     * This is required for the response.
     *
     * @return The clicked button
     */
    public int getClickedButton() {
        return this.clickedButton;
    }

    @Override
    public String serializeResponse() {
        return String.valueOf(this.clickedButton);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NumberFormatException If the response is not a number
     */
    @Override
    public void deserializeResponse(String response) {
        this.clickedButton = Integer.parseInt(response);
    }


    /**
     * A button of an action form.<br>
     * The button has a text and an optional image.
     */
    public static class Button {
        private final String text;
        private final FormImage image;
        private Function<String, String> translator = AForm.DEFAULT_TRANSLATOR;

        public Button(final String text) {
            this(text, null);
        }

        public Button(final String text, @Nullable final FormImage image) {
            this.text = text;
            this.image = image;
        }

        /**
         * @return The text of the button
         */
        public String getText(final boolean translate) {
            if (translate) return this.translator.apply(this.text);
            else return this.text;
        }

        /**
         * @return The image of the button
         */
        @Nullable
        public FormImage getImage() {
            return this.image;
        }

        /**
         * Set the translator for the button.
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

}
