package net.lenni0451.mcstructs_bedrock.forms;

import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;
import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;

import javax.annotation.Nullable;

public enum FormType {

    MODAL(ModalForm.class, "modal"),
    ACTION(ActionForm.class, "form"),
    CUSTOM(CustomForm.class, "custom_form");

    /**
     * Get a form type by its name.
     *
     * @param name The name of the form type
     * @return The form type or null if not found
     */
    @Nullable
    public static FormType byName(final String name) {
        for (FormType value : values()) {
            if (value.getName().equals(name)) return value;
        }
        return null;
    }


    private final Class<? extends AForm> type;
    private final String name;

    FormType(Class<? extends AForm> type, String name) {
        this.type = type;
        this.name = name;
    }

    public Class<? extends AForm> getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

}
