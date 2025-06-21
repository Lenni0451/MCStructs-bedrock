import net.lenni0451.mcstructs_bedrock.forms.serializer.FormSerializer;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

public class Test {

    public static void main(String[] args) {
//        ActionForm form = ActionForm.builder().title("Test").text("Texting your mom").button("Button 1").button("Button 2").button("Button 3").build();
//        String s = FormSerializer.serialize(form);
//        System.out.println(s);

//        CustomForm customForm = CustomForm.builder().title("Testing").header("Header").checkbox("CheckBox").dropdown("drop", "it", "like", "it's", "hot").label("Label").slider("slide me ", 0, 10, 1).textField("Field", "lol").stepSlider("stepping", "around", "in", "darkness").build();
//        System.out.println(FormSerializer.serialize(customForm));
//        System.out.println(customForm.serializeResponse());
//        customForm.deserializeResponse("[false,0,0.0,\"\",1]");
//        System.out.println(((StepSliderFormElement) customForm.getElements()[customForm.getElements().length - 1]).getSelected());

        CustomForm customFrom = (CustomForm) FormSerializer.deserialize("{\"title\":\"Test Form\",\"content\":[{\"type\":\"label\",\"text\":\"This is a label\"},{\"type\":\"toggle\",\"text\":\"This is a checkbox\",\"default\":true},{\"type\":\"divider\",\"text\":\"\"},{\"type\":\"dropdown\",\"text\":\"This is a dropdown\",\"options\":[\"Option 1\",\"Option 2\",\"Option 3\"],\"default\":0},{\"type\":\"header\",\"text\":\"This is a header\"},{\"type\":\"divider\",\"text\":\"\"},{\"type\":\"slider\",\"text\":\"This is a slider\",\"min\":0.0,\"max\":100.0,\"step\":1.0,\"default\":40.0},{\"type\":\"step_slider\",\"text\":\"This is a step slider\",\"steps\":[\"Step 1\",\"Step 2\",\"Step 3\"],\"default\":0},{\"type\":\"input\",\"text\":\"This is a text field\",\"placeholder\":\"Placeholder text\"}],\"type\":\"custom_form\"}");
        System.out.println(customFrom.serializeResponse());

//        ActionForm form = ActionForm.builder().text("text").title("tittle").label("Label").header("Header").divider().build();
//        System.out.println(FormSerializer.serialize(form));
    }

}
