package net.lenni0451.mcstructs_bedrock.text.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import net.lenni0451.mcstructs_bedrock.text.BedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.components.RootBedrockComponent;
import net.lenni0451.mcstructs_bedrock.text.serializer.impl.BedrockTextDeserializer;
import net.lenni0451.mcstructs_bedrock.text.serializer.impl.BedrockTextSerializer;

import java.io.IOException;
import java.io.StringReader;

/**
 * The bedrock component serializer and deserializer wrapper class.
 */
public class BedrockComponentSerializer {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(BedrockComponent.class, new BedrockTextSerializer())
            .registerTypeAdapter(RootBedrockComponent.class, new BedrockTextDeserializer())
            .create();

    /**
     * Serialize a bedrock component to a json string.
     *
     * @param component The component to serialize
     * @return The json string
     */
    public static String serialize(final BedrockComponent component) {
        return GSON.toJson(component);
    }

    /**
     * Serialize a bedrock component to a {@link JsonElement}.
     *
     * @param component The component to serialize
     * @return The json element
     */
    public static JsonElement serializeJson(final BedrockComponent component) {
        return GSON.toJsonTree(component);
    }

    /**
     * Deserialize a bedrock component from a json string.
     *
     * @param json The json string
     * @return The deserialized bedrock component
     */
    public static RootBedrockComponent deserialize(final String json) {
        return GSON.fromJson(json, RootBedrockComponent.class);
    }

    /**
     * Deserialize a bedrock component from a {@link JsonElement}.
     *
     * @param element The json element
     * @return The deserialized bedrock component
     */
    public static RootBedrockComponent deserialize(final JsonElement element) {
        return GSON.fromJson(element, RootBedrockComponent.class);
    }

    /**
     * Deserialize a bedrock component from a json string.<br>
     * This method uses a {@link JsonReader} with {@code lenient} set to {@code false}.
     *
     * @param json The json string
     * @return The deserialized bedrock component
     */
    public static RootBedrockComponent deserializeReader(final String json) {
        return deserializeReader(json, false);
    }

    /**
     * Deserialize a bedrock component from a json string.<br>
     * This method uses a {@link JsonReader} with {@code lenient} set to {@code true}.
     *
     * @param json The json string
     * @return The deserialized bedrock component
     */
    public static RootBedrockComponent deserializeLenientReader(final String json) {
        return deserializeReader(json, true);
    }

    /**
     * Deserialize a bedrock component from a json string.<br>
     * This method uses a {@link JsonReader} with {@code lenient} set to the given value.
     *
     * @param json    The json string
     * @param lenient The lenient value
     * @return The deserialized bedrock component
     */
    public static RootBedrockComponent deserializeReader(final String json, final boolean lenient) {
        try {
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(lenient);
            return GSON.getAdapter(RootBedrockComponent.class).read(reader);
        } catch (IOException e) {
            throw new JsonParseException("Failed to parse json", e);
        }
    }

}
