package net.lenni0451.mcstructs_bedrock.forms.elements;

import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

/**
 * A label form element for the {@link CustomForm}.
 */
public class LabelFormElement extends AFormElement {

    public LabelFormElement(final String text) {
        super(FormElementType.LABEL, text);
    }

}
