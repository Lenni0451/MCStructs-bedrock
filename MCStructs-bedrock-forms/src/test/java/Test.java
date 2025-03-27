import net.lenni0451.mcstructs_bedrock.forms.elements.StepSliderFormElement;
import net.lenni0451.mcstructs_bedrock.forms.serializer.FormSerializer;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

public class Test {

    public static void main(String[] args) {
//        ActionForm form = ActionForm.builder().title("Test").text("Texting your mom").button("Button 1").button("Button 2").button("Button 3").build();
//        String s = FormSerializer.serialize(form);
//        System.out.println(s);

        CustomForm customForm = CustomForm.builder().title("Testing").header("Header").checkbox("CheckBox").dropdown("drop", "it", "like", "it's", "hot").label("Label").slider("slide me ", 0, 10, 1).textField("Field", "lol").stepSlider("stepping", "around", "in", "darkness").build();
        System.out.println(FormSerializer.serialize(customForm));
        System.out.println(customForm.serializeResponse());
        customForm.deserializeResponse("[false,0,0.0,\"\",1]");
        System.out.println(((StepSliderFormElement) customForm.getElements()[customForm.getElements().length - 1]).getSelected());

//        ActionForm form = ActionForm.builder().text("text").title("tittle").label("Label").header("Header").divider().build();
//        System.out.println(FormSerializer.serialize(form));
    }

}
