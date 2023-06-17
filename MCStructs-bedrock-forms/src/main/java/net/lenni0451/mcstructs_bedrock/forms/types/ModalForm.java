package net.lenni0451.mcstructs_bedrock.forms.types;

import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.FormType;

/**
 * Representation of a modal form.<br>
 * A modal form has two buttons and a text.
 */
public class ModalForm extends AForm {

    private final String text;
    private final String button1;
    private final String button2;

    /**
     * @param title   The title of the form
     * @param text    The text displayed in the form
     * @param button1 The text of the first button
     * @param button2 The text of the second button
     */
    public ModalForm(final String title, final String text, final String button1, final String button2) {
        super(FormType.MODAL, title);
        this.text = text;
        this.button1 = button1;
        this.button2 = button2;
    }

    /**
     * @return The content of the form
     */
    public String getText() {
        return this.text;
    }

    /**
     * @return The text of the first button
     */
    public String getButton1() {
        return this.button1;
    }

    /**
     * @return The text of the second button
     */
    public String getButton2() {
        return this.button2;
    }

}
