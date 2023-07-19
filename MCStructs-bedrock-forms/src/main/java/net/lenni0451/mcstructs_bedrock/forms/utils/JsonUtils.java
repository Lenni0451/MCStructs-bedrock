package net.lenni0451.mcstructs_bedrock.forms.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class JsonUtils {

    /**
     * Check if the given object has the given element and it is not null.
     *
     * @param ob   The object to check
     * @param name The name of the element
     * @return If the element is not null
     */
    public static boolean hasNonNull(final JsonObject ob, final String name) {
        return ob.has(name) && !ob.get(name).isJsonNull();
    }

    /**
     * Ensure that the given element is a JsonObject.
     *
     * @param element The element to check
     * @param name    The name of the element
     * @return The JsonObject
     * @throws JsonParseException If the element is not a JsonObject
     */
    public static JsonObject ensureRootObject(final JsonElement element, final String name) throws JsonParseException {
        if (!element.isJsonObject()) throw new JsonParseException(name + " must be an object");
        return element.getAsJsonObject();
    }

    /**
     * Ensure the given JsonObject contains the given elements.
     *
     * @param object The JsonObject to check
     * @param names  The names of the elements
     * @throws JsonParseException If one of the elements is missing
     */
    public static void ensureContains(final JsonObject object, final String... names) throws JsonParseException {
        String msg = "Missing properties: ";
        for (String name : names) {
            if (!object.has(name)) msg += name + ", ";
        }
        if (msg.endsWith(", ")) throw new JsonParseException(msg.substring(0, msg.length() - 2));
    }

    /**
     * Ensure the given JsonObject contains the given string element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The string value of the element
     * @throws JsonParseException If the element is missing or not a string
     */
    public static String ensureContainsString(final JsonObject object, final String name) throws JsonParseException {
        ensureContains(object, name);
        if (!object.get(name).isJsonPrimitive()) throw new JsonParseException(name + " must be a string");
        return object.get(name).getAsString();
    }

    /**
     * Ensure the given JsonObject contains the given boolean element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The boolean value of the element
     * @throws JsonParseException If the element is missing or not a boolean
     */
    public static boolean ensureContainsBoolean(final JsonObject object, final String name) throws JsonParseException {
        ensureContains(object, name);
        if (!object.get(name).isJsonPrimitive()) throw new JsonParseException(name + " must be a boolean");
        return object.get(name).getAsBoolean();
    }

    /**
     * Ensure the given JsonObject contains the given int element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The int value of the element
     * @throws JsonParseException If the element is missing or not an int
     */
    public static int ensureContainsInt(final JsonObject object, final String name) throws JsonParseException {
        ensureContains(object, name);
        if (!object.get(name).isJsonPrimitive()) throw new JsonParseException(name + " must be an int");
        return object.get(name).getAsInt();
    }

    /**
     * Ensure the given JsonObject contains the given float element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The float value of the element
     * @throws JsonParseException If the element is missing or not a float
     */
    public static float ensureContainsFloat(final JsonObject object, final String name) throws JsonParseException {
        ensureContains(object, name);
        if (!object.get(name).isJsonPrimitive()) throw new JsonParseException(name + " must be a float");
        return object.get(name).getAsFloat();
    }

    /**
     * Ensure the given JsonObject contains the given JsonObject element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The JsonObject value of the element
     * @throws JsonParseException If the element is missing or not a JsonObject
     */
    public static JsonObject ensureContainsObject(final JsonObject object, final String name) throws JsonParseException {
        ensureContains(object, name);
        if (!object.get(name).isJsonObject()) throw new JsonParseException(name + " must be an object");
        return object.get(name).getAsJsonObject();
    }

    /**
     * Ensure the given JsonObject contains the given JsonArray element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The JsonArray value of the element
     * @throws JsonParseException If the element is missing or not a JsonArray
     */
    public static JsonArray ensureContainsArray(final JsonObject object, final String name) throws JsonParseException {
        ensureContains(object, name);
        if (!object.get(name).isJsonArray()) throw new JsonParseException(name + " must be an array");
        return object.get(name).getAsJsonArray();
    }

    /**
     * Ensure the given JsonObject contains the given string array element.
     *
     * @param object The JsonObject to check
     * @param name   The name of the element
     * @return The string array value of the element
     * @throws JsonParseException If the element is missing or not a string array
     */
    public static String[] ensureContainsStringArray(final JsonObject object, final String name) throws JsonParseException {
        JsonArray array = ensureContainsArray(object, name);
        String[] strings = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            if (!array.get(i).isJsonPrimitive()) throw new JsonParseException(name + " must be an array of strings");
            strings[i] = array.get(i).getAsString();
        }
        return strings;
    }

}
