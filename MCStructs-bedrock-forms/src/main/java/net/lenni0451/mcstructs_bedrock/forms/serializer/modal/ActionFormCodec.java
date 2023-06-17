package net.lenni0451.mcstructs_bedrock.forms.serializer.modal;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;

import java.lang.reflect.Type;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.*;

/**
 * A codec for the {@link ActionForm}.
 */
public class ActionFormCodec implements JsonSerializer<ActionForm>, JsonDeserializer<ActionForm> {

    @Override
    public JsonElement serialize(ActionForm src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject form = new JsonObject();
        form.addProperty("title", src.getTitle());
        form.addProperty("content", src.getText());
        JsonArray buttons = new JsonArray();
        for (ActionForm.Button button : src.getButtons()) {
            JsonObject buttonJson = new JsonObject();
            buttonJson.addProperty("text", button.getText());
            if (button.getImage() != null) buttonJson.add("image", context.serialize(button.getImage()));
            buttons.add(buttonJson);
        }
        form.add("buttons", buttons);
        return form;
    }

    @Override
    public ActionForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "ActionForm");
        String title = ensureContainsString(ob, "title");
        String content = ensureContainsString(ob, "content");
        JsonArray buttons = ensureContainsArray(ob, "buttons");
        ActionForm.Button[] buttonArray = new ActionForm.Button[buttons.size()];
        for (int i = 0; i < buttons.size(); i++) {
            JsonObject buttonJson = ensureContainsObject(ensureRootObject(buttons.get(i), "Button"), "Button");
            String text = ensureContainsString(buttonJson, "text");
            FormImage image = null;
            if (buttonJson.has("image")) image = context.deserialize(ensureContainsObject(buttonJson, "image"), FormImage.class);
            buttonArray[i] = new ActionForm.Button(text, image);
        }
        return new ActionForm(title, content, buttonArray);
    }

}
