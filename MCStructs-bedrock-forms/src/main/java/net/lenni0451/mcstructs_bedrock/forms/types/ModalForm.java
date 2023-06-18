package net.lenni0451.mcstructs_bedrock.forms.types;

import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.types.builder.ModalFormBuilder;

import javax.annotation.Nonnull;

/**
 * Representation of a modal form.<br>
 * A modal form has two buttons and a text.
 */
public class ModalForm extends AForm {

    /**
     * @return A new builder for a modal form
     */
    public static ModalFormBuilder builder() {
        return new ModalFormBuilder();
    }


    private final String text;
    private final String button1;
    private final String button2;
    private int clickedButton;

    /**
     * @param title   The title of the form
     * @param text    The text displayed in the form
     * @param button1 The text of the first button
     * @param button2 The text of the second button
     */
    public ModalForm(@Nonnull final String title, @Nonnull final String text, @Nonnull final String button1, @Nonnull final String button2) {
        super(FormType.MODAL, title);
        this.text = text;
        this.button1 = button1;
        this.button2 = button2;
    }

    /**
     * @return The content of the form
     */
    @Nonnull
    public String getText() {
        return this.text;
    }

    /**
     * @return The text of the first button
     */
    @Nonnull
    public String getButton1() {
        return this.button1;
    }

    /**
     * @return The text of the second button
     */
    @Nonnull
    public String getButton2() {
        return this.button2;
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
        return String.valueOf(this.clickedButton == 0);
    }

    @Override
    public void deserializeResponse(String response) {
        this.clickedButton = Boolean.parseBoolean(response) ? 0 : 1;
    }

}
