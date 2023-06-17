package net.lenni0451.mcstructs_bedrock.forms;

import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;
import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;

public class TestForms {

    public static ActionForm createActionForm() {
        return ActionForm.builder().title("Action Form Title").text("Action Form Test")
                .button("Button without image")
                .pathButton("Button with image", "textures/items/diamond_shovel")
                .build();
    }

    public static CustomForm createCustomForm() {
        return CustomForm.builder().title("Custom Form Title")
                .checkbox("Checkbox", true)
                .dropdown("Dropdown", 1, "Option 1", "Option 2 (default)")
                .label("Label Text")
                .slider("Slider", 10, 20, 2, 12)
                .stepSlider("Step Slider", 1, "Option 1", "Option 2 (default)", "Option 3")
                .textField("Text Field", "Placeholder", "Default Text")
                .build();
    }

    public static ModalForm createModalForm() {
        return ModalForm.builder().title("Modal Form Title").text("Modal Form Test")
                .buttons("Button 1", "Button 2")
                .build();
    }

}
