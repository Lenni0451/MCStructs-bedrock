package net.lenni0451.mcstructs_bedrock.forms.elements;

import javax.annotation.Nullable;

public class ButtonFormElement extends FormElement {

    @Nullable
    private final FormImage image;

    public ButtonFormElement(final String text) {
        this(text, null);
    }

    public ButtonFormElement(final String text, @Nullable final FormImage image) {
        super(FormElementType.BUTTON, text);
        this.image = image;
    }

    /**
     * @return The image of the button
     */
    @Nullable
    public FormImage getImage() {
        return this.image;
    }

}
