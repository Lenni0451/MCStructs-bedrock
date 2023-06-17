package net.lenni0451.mcstructs_bedrock.forms.elements;

import javax.annotation.Nullable;

public class FormImage {

    private final Type type;
    private final String value;

    public FormImage(final Type type, final String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }


    public enum Type {
        PATH("path"),
        URL("url");

        @Nullable
        public static Type byName(final String name) {
            for (Type type : Type.values()) {
                if (type.name().equals(name)) return type;
            }
            return null;
        }


        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

}
