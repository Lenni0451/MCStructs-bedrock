package net.lenni0451.mcstructs_bedrock.forms.serializer.modal;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;

import java.lang.reflect.Type;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.ensureContainsString;
import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.ensureRootObject;

/**
 * A codec for the {@link ModalForm}.
 */
public class ModalFormCodec implements JsonSerializer<ModalForm>, JsonDeserializer<ModalForm> {

    @Override
    public JsonElement serialize(ModalForm src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject form = new JsonObject();
        form.addProperty("title", src.getTitle(false));
        form.addProperty("content", src.getText(false));
        form.addProperty("button1", src.getButton1(false));
        form.addProperty("button2", src.getButton2(false));
        return form;
    }

    @Override
    public ModalForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "ModalForm");
        return new ModalForm(ensureContainsString(ob, "title"),
                ensureContainsString(ob, "content"),
                ensureContainsString(ob, "button1"),
                ensureContainsString(ob, "button2"));
    }

}
