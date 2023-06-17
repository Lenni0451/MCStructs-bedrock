package net.lenni0451.mcstructs_bedrock.forms.serializer.elements;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;
import net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils;

import java.lang.reflect.Type;

/**
 * A codec for the {@link FormImage}.
 */
public class FormImageCodec implements JsonSerializer<FormImage>, JsonDeserializer<FormImage> {

    @Override
    public JsonElement serialize(FormImage src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject image = new JsonObject();
        image.addProperty("type", src.getType().getName());
        image.addProperty("data", src.getValue());
        return image;
    }

    @Override
    public FormImage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = JsonUtils.ensureRootObject(json, "Image");
        FormImage.Type type = FormImage.Type.byName(JsonUtils.ensureContainsString(ob, "type"));
        if (type == null) throw new JsonParseException("Unknown image type: " + ob.get("type").getAsString());
        String data = JsonUtils.ensureContainsString(ob, "data");
        return new FormImage(type, data);
    }

}
