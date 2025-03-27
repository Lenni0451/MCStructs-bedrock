package net.lenni0451.mcstructs_bedrock.forms.serializer;

import net.lenni0451.mcstructs_bedrock.forms.TestForms;
import net.lenni0451.mcstructs_bedrock.forms.utils.JsonSorter;
import org.junit.jupiter.api.Test;

import static net.lenni0451.mcstructs_bedrock.forms.serializer.FormSerializer.deserialize;
import static net.lenni0451.mcstructs_bedrock.forms.serializer.FormSerializer.serializeJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FormSerializerTest {

    private static final String ACTION_FORM_JSON = "{\"content\":\"Action Form Test\",\"elements\":[{\"text\":\"Button without image\",\"type\":\"button\"},{\"image\":{\"data\":\"textures/items/diamond_shovel\",\"type\":\"path\"},\"text\":\"Button with image\",\"type\":\"button\"}],\"title\":\"Action Form Title\",\"type\":\"form\"}";
    private static final String CUSTOM_FORM_JSON = "{\"content\":[{\"default\":true,\"text\":\"Checkbox\",\"type\":\"toggle\"},{\"default\":1,\"options\":[\"Option 1\",\"Option 2 (default)\"],\"text\":\"Dropdown\",\"type\":\"dropdown\"},{\"text\":\"Label Text\",\"type\":\"label\"},{\"default\":12.0,\"max\":20.0,\"min\":10.0,\"step\":2.0,\"text\":\"Slider\",\"type\":\"slider\"},{\"default\":1,\"steps\":[\"Option 1\",\"Option 2 (default)\",\"Option 3\"],\"text\":\"Step Slider\",\"type\":\"step_slider\"},{\"default\":\"Default Text\",\"placeholder\":\"Placeholder\",\"text\":\"Text Field\",\"type\":\"input\"}],\"title\":\"Custom Form Title\",\"type\":\"custom_form\"}";
    private static final String MODAL_FORM_JSON = "{\"button1\":\"Button 1\",\"button2\":\"Button 2\",\"content\":\"Modal Form Test\",\"title\":\"Modal Form Title\",\"type\":\"modal\"}";

    @Test
    void serialize() {
        assertEquals(ACTION_FORM_JSON, JsonSorter.sort(serializeJson(TestForms.createActionForm())).toString());
        assertEquals(CUSTOM_FORM_JSON, JsonSorter.sort(serializeJson(TestForms.createCustomForm())).toString());
        assertEquals(MODAL_FORM_JSON, JsonSorter.sort(serializeJson(TestForms.createModalForm())).toString());
    }

    @Test
    void deserializeSerializeCheck() {
        assertEquals(ACTION_FORM_JSON, JsonSorter.sort(serializeJson(deserialize(ACTION_FORM_JSON))).toString());
        assertEquals(CUSTOM_FORM_JSON, JsonSorter.sort(serializeJson(deserialize(CUSTOM_FORM_JSON))).toString());
        assertEquals(MODAL_FORM_JSON, JsonSorter.sort(serializeJson(deserialize(MODAL_FORM_JSON))).toString());
    }

}
