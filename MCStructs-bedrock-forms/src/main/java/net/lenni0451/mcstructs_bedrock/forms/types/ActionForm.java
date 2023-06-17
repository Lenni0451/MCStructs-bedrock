package net.lenni0451.mcstructs_bedrock.forms.types;

import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;

import javax.annotation.Nullable;

/**
 * Representation of an action form.<br>
 * An action form has multiple buttons and a text.
 */
public class ActionForm extends AForm {

    private final String text;
    private final Button[] buttons;

    /**
     * @param title   The title of the form
     * @param text    The text displayed in the form
     * @param buttons The buttons of the form
     */
    public ActionForm(final String title, final String text, final Button... buttons) {
        super(FormType.ACTION, title);
        this.text = text;
        this.buttons = buttons;
    }

    /**
     * @return The content of the form
     */
    public String getText() {
        return this.text;
    }

    /**
     * @return The buttons of the form
     */
    public Button[] getButtons() {
        return this.buttons;
    }


    /**
     * A button of an action form.<br>
     * The button has a text and an optional image.
     */
    public static class Button {
        private final String text;
        private final FormImage image;

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
        public String getText() {
            return this.text;
        }

        /**
         * @return The image of the button
         */
        @Nullable
        public FormImage getImage() {
            return this.image;
        }
    }

}
