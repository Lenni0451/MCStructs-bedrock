package net.lenni0451.mcstructs_bedrock.forms.types;

import net.lenni0451.mcstructs_bedrock.forms.Form;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormElement;
import net.lenni0451.mcstructs_bedrock.forms.types.builder.ActionFormBuilder;

import javax.annotation.Nonnull;

/**
 * Representation of an action form.<br>
 * An action form has multiple buttons and a text.
 */
public class ActionForm extends Form {

    /**
     * @return A new builder for an action form
     */
    public static ActionFormBuilder builder() {
        return new ActionFormBuilder();
    }


    private final String text;
    private final FormElement[] elements;
    private int clickedButton;

    /**
     * @param title    The title of the form
     * @param text     The text displayed in the form
     * @param elements The elements of the form
     */
    public ActionForm(@Nonnull final String title, @Nonnull final String text, @Nonnull final FormElement... elements) {
        super(FormType.ACTION, title);
        if (text == null) throw new NullPointerException("The text of a form cannot be null");
        this.text = text;
        this.elements = elements;
    }

    /**
     * @return The translated text of the form
     */
    public String getText() {
        return this.getText(true);
    }

    /**
     * Get the text of the form.
     *
     * @param translate If the text should be translated
     * @return The content
     */
    @Nonnull
    public String getText(final boolean translate) {
        if (translate) return this.translator.apply(this.text);
        else return this.text;
    }

    /**
     * @return The elements of the form
     */
    @Nonnull
    public FormElement[] getElements() {
        return this.elements;
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

}
