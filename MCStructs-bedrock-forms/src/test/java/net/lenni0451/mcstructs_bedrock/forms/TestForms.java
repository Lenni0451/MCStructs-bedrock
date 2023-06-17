package net.lenni0451.mcstructs_bedrock.forms;

import net.lenni0451.mcstructs_bedrock.forms.elements.*;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm.Button;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;
import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;

public class TestForms {

    public static ActionForm createActionForm() {
        return new ActionForm("Action Form Title", "Action Form Test",
                new Button("Button without image"),
                new Button("Button with image", new FormImage(FormImage.Type.PATH, "textures/items/diamond_shovel")));
    }

    public static CustomForm createCustomForm() {
        return new CustomForm("Custom Form Title",
                new CheckboxFormElement("Checkbox", true),
                new DropdownFormElement("Dropdown", 1, "Option 1", "Option 2 (default)"),
                new LabelFormElement("Label Text"),
                new SliderFormElement("Slider", 10, 20, 2, 12),
                new StepSliderFormElement("Step Slider", 1, "Option 1", "Option 2 (default)", "Option 3"),
                new TextFieldFormElement("Text Field", "Placeholder", "Default Text"));
    }

    public static ModalForm createModalForm() {
        return new ModalForm("Modal Form Title", "Modal Form Test", "Button 1", "Button 2");
    }

}
