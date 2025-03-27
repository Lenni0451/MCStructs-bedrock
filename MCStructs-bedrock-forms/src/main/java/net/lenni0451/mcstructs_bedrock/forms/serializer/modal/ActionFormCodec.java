package net.lenni0451.mcstructs_bedrock.forms.serializer.modal;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.ButtonFormElement;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormElement;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormElementType;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;
import net.lenni0451.mcstructs_bedrock.forms.utils.CollectionUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.*;

/**
 * A codec for the {@link ActionForm}.
 */
public class ActionFormCodec implements JsonSerializer<ActionForm>, JsonDeserializer<ActionForm> {

    private static final Set<FormElementType> SUPPORTED_TYPES = CollectionUtils.asSet(
            FormElementType.BUTTON,
            FormElementType.HEADER,
            FormElementType.LABEL,
            FormElementType.DIVIDER
    );

    @Override
    public JsonElement serialize(ActionForm src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject form = new JsonObject();
        form.addProperty("title", src.getTitle(false));
        form.addProperty("content", src.getText(false));
        JsonArray elements = new JsonArray();
        for (FormElement element : src.getElements()) {
            if (!SUPPORTED_TYPES.contains(element.getType())) {
                throw new IllegalArgumentException("The element type " + element.getType().getName() + " is not supported in an ActionForm");
            }
            elements.add(context.serialize(element));
        }
        form.add("elements", elements);
        return form;
    }

    @Override
    public ActionForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<FormElement> formElements = new ArrayList<>();
        JsonObject ob = ensureRootObject(json, "ActionForm");
        String title = ensureContainsString(ob, "title");
        String content = ensureContainsString(ob, "content");
        if (hasNonNull(ob, "buttons")) { //Legacy code
            JsonArray buttons = ensureContainsArray(ob, "buttons");
            for (JsonElement button : buttons) {
                JsonObject buttonJson = ensureRootObject(button, "Button");
                String text = ensureContainsString(buttonJson, "text");
                FormImage image = null;
                if (buttonJson.has("image")) image = context.deserialize(ensureContainsObject(buttonJson, "image"), FormImage.class);
                formElements.add(new ButtonFormElement(text, image));
            }
        } else if (hasNonNull(ob, "elements")) {
            JsonArray elements = ensureContainsArray(ob, "elements");
            for (JsonElement element : elements) {
                FormElement formElement = context.deserialize(element, FormElement.class);
                if (!SUPPORTED_TYPES.contains(formElement.getType())) {
                    throw new JsonParseException("The element type " + formElement.getType().getName() + " is not supported in an ActionForm");
                }
                formElements.add(formElement);
            }
        }
        return new ActionForm(title, content, formElements.toArray(new FormElement[0]));
    }

}
