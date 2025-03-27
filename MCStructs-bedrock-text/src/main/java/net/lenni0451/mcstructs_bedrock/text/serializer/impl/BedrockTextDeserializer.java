package net.lenni0451.mcstructs_bedrock.text.serializer.impl;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.text.BedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.components.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BedrockTextDeserializer implements JsonDeserializer<RootBedrockComponent> {

    @Override
    public RootBedrockComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) throw new JsonParseException("Json element is not an object");
        JsonObject ob = json.getAsJsonObject();
        RootBedrockComponent root = new RootBedrockComponent();
        if (!ob.has("rawtext")) throw new JsonParseException("Json object does not contain a rawtext array");
        if (!ob.get("rawtext").isJsonArray()) throw new JsonParseException("Json object rawtext is not an array");

        JsonArray rawtext = ob.get("rawtext").getAsJsonArray();
        for (JsonElement element : rawtext) {
            if (!element.isJsonObject()) throw new JsonParseException("Json element in rawtext array is not an object");
            JsonObject sibling = element.getAsJsonObject();
            root.addComponent(this.deserialize(sibling, context));
        }
        return root;
    }

    private BedrockComponent deserialize(final JsonObject json, final JsonDeserializationContext context) {
        if (json.has("rawtext")) {
            return this.deserialize(json, RootBedrockComponent.class, context);
        } else if (json.has("translate")) {
            String key = json.get("translate").getAsString();
            List<Object> args = new ArrayList<>();
            if (json.has("with")) {
                if (json.get("with").isJsonObject()) {
                    RootBedrockComponent root = (RootBedrockComponent) this.deserialize(json.get("with").getAsJsonObject(), RootBedrockComponent.class, context);
                    args.addAll(root.getComponents());
                } else if (json.get("with").isJsonArray()) {
                    JsonArray with = json.getAsJsonArray("with");
                    for (JsonElement element : with) {
                        if (!element.isJsonPrimitive() && !element.getAsJsonPrimitive().isString()) continue;
                        args.add(element.getAsString());
                    }
                } else {
                    throw new JsonParseException("Json element with is not an object or an array");
                }
            }
            return new TranslationBedrockComponent(key, args);
        } else if (json.has("text")) {
            return new StringBedrockComponent(json.get("text").getAsString());
        } else if (json.has("score")) {
            if (!json.get("score").isJsonObject()) throw new JsonParseException("Json element score is not an object");
            JsonObject score = json.getAsJsonObject("score");
            if (!score.has("name")) throw new JsonParseException("Json object score does not contain a name");
            if (!score.has("objective")) throw new JsonParseException("Json object score does not contain a objective");
            if (!score.get("name").isJsonPrimitive()) throw new JsonParseException("Json object score name is not a primitive");
            if (!score.get("objective").isJsonPrimitive()) throw new JsonParseException("Json object score objective is not a primitive");
            return new ScoreBedrockComponent(score.get("name").getAsString(), score.get("objective").getAsString());
        } else if (json.has("selector")) {
            if (!json.get("selector").isJsonPrimitive()) throw new JsonParseException("Json element selector is not a primitive");
            return new SelectorBedrockComponent(json.get("selector").getAsString());
        } else {
            throw new JsonParseException("Json object does not contain a translate, text, score or selector");
        }
    }

}
