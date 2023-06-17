package net.lenni0451.mcstructs_bedrock.forms.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.elements.AFormElement;
import net.lenni0451.mcstructs_bedrock.forms.elements.FormImage;
import net.lenni0451.mcstructs_bedrock.forms.serializer.elements.FormElementCodec;
import net.lenni0451.mcstructs_bedrock.forms.serializer.elements.FormImageCodec;
import net.lenni0451.mcstructs_bedrock.forms.serializer.modal.TypedFormCodec;

import java.io.IOException;
import java.io.StringReader;

public class FormSerializer {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(FormImage.class, new FormImageCodec())
            .registerTypeHierarchyAdapter(AForm.class, new TypedFormCodec())
            .registerTypeHierarchyAdapter(AFormElement.class, new FormElementCodec())
            .create();

    /**
     * Serialize a form to a json string.
     *
     * @param form The form to serialize
     * @return The json string
     */
    public static String serialize(final AForm form) {
        return GSON.toJson(form);
    }

    /**
     * Serialize a form to a {@link JsonElement}.
     *
     * @param form The form to serialize
     * @return The json element
     */
    public static JsonElement serializeJson(final AForm form) {
        return GSON.toJsonTree(form);
    }

    /**
     * Deserialize a form from a json string.
     *
     * @param element The json string
     * @return The deserialized form
     */
    public static AForm deserialize(final String element) {
        return GSON.fromJson(element, AForm.class);
    }

    /**
     * Deserialize a form from a json {@link JsonElement}.
     *
     * @param element The json string
     * @return The deserialized form
     */
    public static AForm deserialize(final JsonElement element) {
        return GSON.fromJson(element, AForm.class);
    }

    /**
     * Deserialize a form from a json string using a json reader.
     *
     * @param json The json string
     * @return The deserialized form
     */
    public static AForm deserializeReader(final String json) {
        return deserializeReader(json, false);
    }

    /**
     * Deserialize a form from a json string using a lenient json reader.
     *
     * @param json The json string
     * @return The deserialized form
     */
    public static AForm deserializeLenientReader(final String json) {
        return deserializeReader(json, true);
    }

    /**
     * Deserialize a form from a json string using a json reader.
     *
     * @param json    The json string
     * @param lenient Whether to use a lenient json reader
     * @return The deserialized form
     */
    public static AForm deserializeReader(final String json, final boolean lenient) {
        try {
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(lenient);
            return GSON.getAdapter(AForm.class).read(reader);
        } catch (IOException e) {
            throw new JsonParseException("Failed to parse json", e);
        }
    }

}
