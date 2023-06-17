package net.lenni0451.mcstructs_bedrock.forms.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * See <a href="https://github.com/Lenni0451/MCStructs/blob/1d91e09958070bc685c62c57de46ba97fa695c22/MCStructs-text/src/main/java/net/lenni0451/mcstructs/text/utils/JsonUtils.java#L188">MCStructs</a>
 */
public class JsonSorter {

    public static JsonElement sort(final JsonElement element) {
        if (element == null) {
            return null;
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) array.set(i, sort(array.get(i)));
            return array;
        } else if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            JsonObject sorted = new JsonObject();
            List<String> keys = new ArrayList<>(object.keySet());
            keys.sort(Comparator.naturalOrder());
            for (String key : keys) sorted.add(key, sort(object.get(key)));
            return sorted;
        } else {
            return element;
        }
    }

}
