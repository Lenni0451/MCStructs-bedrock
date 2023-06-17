package net.lenni0451.mcstructs_bedrock.forms.elements;

public abstract class AFormElement {

    private final FormElementType type;
    private final String text;

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
    public String getText() {
        return this.text;
    }

}
