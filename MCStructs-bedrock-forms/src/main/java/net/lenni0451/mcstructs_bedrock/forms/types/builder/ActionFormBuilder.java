package net.lenni0451.mcstructs_bedrock.forms.types.builder;

import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ActionFormBuilder {

    private String title;
    private String text;
    private final List<ActionForm.Button> buttons = new ArrayList<>();

    /**
     * Set the title of the form.
     *
     * @param title The title
     * @return The builder instance
     */
    public ActionFormBuilder title(@Nonnull final String title) {
        this.title = title;
        return this;
    }

    /**
     * Set the text of the form.
     *
     * @param text The text
     * @return The builder instance
     */
    public ActionFormBuilder text(@Nonnull final String text) {
        this.text = text;
        return this;
    }

    /**
     * Add a button to the form.
     *
     * @param text The text of the button
     * @return The builder instance
     */
    public ActionFormBuilder button(@Nonnull final String text) {
        this.buttons.add(new ActionForm.Button(text));
        return this;
    }

    /**
     * Add a button with an image to the form.
     *
     * @param text  The text of the button
     * @param image The image of the button
     * @return The builder instance
     */
    public ActionFormBuilder button(@Nonnull final String text, @Nullable final FormImage image) {
        this.buttons.add(new ActionForm.Button(text, image));
        return this;
    }

    /**
     * Add a button with a path image to the form.
     *
     * @param text The text of the button
     * @param path The path of the image
     * @return The builder instance
     */
    public ActionFormBuilder pathButton(@Nonnull final String text, @Nonnull final String path) {
        this.buttons.add(new ActionForm.Button(text, new FormImage(FormImage.Type.PATH, path)));
        return this;
    }

    /**
     * Add a button with an url image to the form.
     *
     * @param text The text of the button
     * @param url  The url of the image
     * @return The builder instance
     */
    public ActionFormBuilder urlButton(@Nonnull final String text, @Nonnull final String url) {
        this.buttons.add(new ActionForm.Button(text, new FormImage(FormImage.Type.URL, url)));
        return this;
    }

    /**
     * @return The built action form
     */
    public ActionForm build() {
        return new ActionForm(this.title, this.text, this.buttons.toArray(new ActionForm.Button[0]));
    }

}
