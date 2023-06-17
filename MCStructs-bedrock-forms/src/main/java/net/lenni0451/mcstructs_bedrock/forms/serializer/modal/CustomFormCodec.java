package net.lenni0451.mcstructs_bedrock.forms.serializer.modal;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.AFormElement;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;

import java.lang.reflect.Type;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.*;

/**
 * A codec for the {@link CustomForm}.
 */
public class CustomFormCodec implements JsonSerializer<CustomForm>, JsonDeserializer<CustomForm> {

    @Override
    public JsonElement serialize(CustomForm src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject form = new JsonObject();
        form.addProperty("title", src.getTitle());
        JsonArray elements = new JsonArray();
        for (AFormElement element : src.getElements()) elements.add(context.serialize(element));
        form.add("content", elements);
        return form;
    }

    @Override
    public CustomForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "CustomForm");
        String title = ensureContainsString(ob, "title");
        JsonArray content = ensureContainsArray(ob, "content");
        AFormElement[] elements = new AFormElement[content.size()];
        for (int i = 0; i < content.size(); i++) elements[i] = context.deserialize(content.get(i), AFormElement.class);
        return new CustomForm(title, elements);
    }

}
