package net.lenni0451.mcstructs_bedrock.forms;

public abstract class AForm {

    private final FormType type;
    private final String title;

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
     * @return The title of the form
     */
    public String getTitle() {
        return this.title;
    }

}
