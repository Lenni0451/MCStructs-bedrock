package net.lenni0451.mcstructs_bedrock.forms.serializer.modal;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormElement;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormElementType;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;
import net.lenni0451.mcstructs_bedrock.forms.utils.CollectionUtils;

import java.lang.reflect.Type;
import java.util.Set;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.*;

/**
 * A codec for the {@link CustomForm}.
 */
public class CustomFormCodec implements JsonSerializer<CustomForm>, JsonDeserializer<CustomForm> {

    private static final Set<FormElementType> SUPPORTED_TYPES = CollectionUtils.asSet(
            FormElementType.CHECKBOX,
            FormElementType.DROPDOWN,
            FormElementType.LABEL,
            FormElementType.SLIDER,
            FormElementType.STEP_SLIDER,
            FormElementType.TEXT_FIELD,
            FormElementType.DIVIDER,
            FormElementType.HEADER
    );

    @Override
    public JsonElement serialize(CustomForm src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject form = new JsonObject();
        form.addProperty("title", src.getTitle(false));
        JsonArray elements = new JsonArray();
        for (FormElement element : src.getElements()) {
            if (!SUPPORTED_TYPES.contains(element.getType())) {
                throw new IllegalArgumentException("The element type " + element.getType().getName() + " is not supported in a CustomForm");
            }
            elements.add(context.serialize(element));
        }
        form.add("content", elements);
        return form;
    }

    @Override
    public CustomForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "CustomForm");
        String title = ensureContainsString(ob, "title");
        JsonArray content = ensureContainsArray(ob, "content");
        FormElement[] elements = new FormElement[content.size()];
        for (int i = 0; i < content.size(); i++) {
            FormElement element = context.deserialize(content.get(i), FormElement.class);
            if (!SUPPORTED_TYPES.contains(element.getType())) {
                throw new JsonParseException("The element type " + element.getType().getName() + " is not supported in a CustomForm");
            }
            elements[i] = element;
        }
        return new CustomForm(title, elements);
    }

}
