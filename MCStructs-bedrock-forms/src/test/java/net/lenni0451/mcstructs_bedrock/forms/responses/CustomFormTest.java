package net.lenni0451.mcstructs_bedrock.forms.responses;

import net.lenni0451.mcstructs_bedrock.forms.TestForms;
import net.lenni0451.mcstructs_bedrock.forms.elements.*;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomFormTest {

    private CustomForm form;

    @BeforeEach
    void setUp() {
        this.form = TestForms.createCustomForm();
    }

    @Test
    void serialize() {
        Assertions.assertEquals("[true,1,null,12.0,1,\"Default Text\"]", this.form.serializeResponse());
    }

    @Test
    void deserialize() {
        this.form.deserializeResponse("[false,5,null,2.0,4,\"Test\"]");
        for (AFormElement element : this.form.getElements()) {
            switch (element.getType()) {
                case CHECKBOX:
                    assertFalse(((CheckboxFormElement) element).isChecked());
                    break;
                case DROPDOWN:
                    assertEquals(5, ((DropdownFormElement) element).getSelected());
                    break;
                case LABEL:
                    break;
                case SLIDER:
                    assertEquals(2.0, ((SliderFormElement) element).getCurrent());
                    break;
                case STEP_SLIDER:
                    assertEquals(4, ((StepSliderFormElement) element).getSelected());
                    break;
                case TEXT_FIELD:
                    assertEquals("Test", ((TextFieldFormElement) element).getValue());
                    break;
                default:
                    fail("Unknown element type: " + element.getType());
            }
        }
    }

}
