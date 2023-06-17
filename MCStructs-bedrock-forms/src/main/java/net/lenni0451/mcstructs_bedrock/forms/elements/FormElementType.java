package net.lenni0451.mcstructs_bedrock.forms.elements;

import javax.annotation.Nullable;

public enum FormElementType {

    CHECKBOX(CheckboxFormElement.class, "toggle"),
    DROPDOWN(DropdownFormElement.class, "dropdown"),
    LABEL(LabelFormElement.class, "label"),
    SLIDER(SliderFormElement.class, "slider"),
    STEP_SLIDER(StepSliderFormElement.class, "step_slider"),
    TEXT_FIELD(TextFieldFormElement.class, "input");

    /**
     * Get a form element type by its name.
     *
     * @param name The name of the form element type
     * @return The form element type or null if not found
     */
    @Nullable
    public static FormElementType byName(final String name) {
        for (FormElementType type : FormElementType.values()) {
            if (type.getName().equals(name)) return type;
        }
        return null;
    }


    private final Class<? extends AFormElement> type;
    private final String name;

    FormElementType(Class<? extends AFormElement> type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @return The type of the element
     */
    public Class<? extends AFormElement> getType() {
        return this.type;
    }

    /**
     * @return The name of the element
     */
    public String getName() {
        return this.name;
    }

}
