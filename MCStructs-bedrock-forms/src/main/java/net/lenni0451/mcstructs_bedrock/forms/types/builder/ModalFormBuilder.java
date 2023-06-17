package net.lenni0451.mcstructs_bedrock.forms.types.builder;

import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;

import javax.annotation.Nonnull;

public class ModalFormBuilder {

    private String title;
    private String text;
    private String button1;
    private String button2;

    /**
     * Set the title of the form.
     *
     * @param title The title
     * @return The builder instance
     */
    public ModalFormBuilder title(@Nonnull final String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the text of the form.
     *
     * @param text The text
     * @return The builder instance
     */
    public ModalFormBuilder text(@Nonnull final String text) {
        this.text = text;
        return this;
    }

    /**
     * Set the text of the first button.
     *
     * @param button1 The text of the first button
     * @return The builder instance
     */
    public ModalFormBuilder button1(@Nonnull final String button1) {
        this.button1 = button1;
        return this;
    }

    /**
     * Set the text of the second button.
     *
     * @param button2 The text of the second button
     * @return The builder instance
     */
    public ModalFormBuilder button2(@Nonnull final String button2) {
        this.button2 = button2;
        return this;
    }

    /**
     * Set the text of both buttons.
     *
     * @param button1 The text of the first button
     * @param button2 The text of the second button
     * @return The builder instance
     */
    public ModalFormBuilder buttons(@Nonnull final String button1, @Nonnull final String button2) {
        this.button1 = button1;
        this.button2 = button2;
        return this;
    }

    /**
     * @return The built modal form
     */
    public ModalForm build() {
        return new ModalForm(this.title, this.text, this.button1, this.button2);
    }

}
