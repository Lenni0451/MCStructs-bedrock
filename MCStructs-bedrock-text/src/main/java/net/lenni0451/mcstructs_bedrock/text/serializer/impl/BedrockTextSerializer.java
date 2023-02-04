package net.lenni0451.mcstructs_bedrock.text.serializer.impl;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.text.ABedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.components.*;

import java.lang.reflect.Type;

public class BedrockTextSerializer implements JsonSerializer<ABedrockComponent> {

    @Override
    public JsonElement serialize(ABedrockComponent src, Type typeOfSrc, JsonSerializationContext context) {
        if (src instanceof RootBedrockComponent) {
            RootBedrockComponent root = (RootBedrockComponent) src;
            JsonArray rawtext = new JsonArray();
            for (ABedrockComponent component : root.getComponents()) rawtext.add(this.serialize(component, component.getClass(), context));

            JsonObject serialized = new JsonObject();
            serialized.add("rawtext", rawtext);
            return serialized;
        } else if (src instanceof StringBedrockComponent) {
            StringBedrockComponent string = (StringBedrockComponent) src;
            JsonObject serialized = new JsonObject();
            serialized.addProperty("text", string.getText());
            return serialized;
        } else if (src instanceof TranslationBedrockComponent) {
            TranslationBedrockComponent translation = (TranslationBedrockComponent) src;
            RootBedrockComponent with = new RootBedrockComponent();
            for (Object arg : translation.getArgs()) {
                if (arg instanceof ABedrockComponent) with.addComponent((ABedrockComponent) arg);
                else with.addComponent(new StringBedrockComponent(arg.toString()));
            }

            JsonObject serialized = new JsonObject();
            serialized.addProperty("translate", translation.getKey());
            serialized.add("with", this.serialize(with, with.getClass(), context));
            return serialized;
        } else if (src instanceof ScoreBedrockComponent) {
            ScoreBedrockComponent score = (ScoreBedrockComponent) src;
            JsonObject scoreJson = new JsonObject();
            scoreJson.addProperty("name", score.getName());
            scoreJson.addProperty("objective", score.getObjective());

            JsonObject serialized = new JsonObject();
            serialized.add("score", scoreJson);
            return serialized;
        } else if (src instanceof SelectorBedrockComponent) {
            SelectorBedrockComponent selector = (SelectorBedrockComponent) src;
            JsonObject serialized = new JsonObject();
            serialized.addProperty("selector", selector.getSelector());
            return serialized;
        } else {
            throw new IllegalArgumentException("Unknown component type: " + src.getClass().getName());
        }
    }

}
