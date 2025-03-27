package net.lenni0451.mcstructs_bedrock.forms.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionUtils {

    public static <T> Set<T> asSet(T... elements) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }

}
