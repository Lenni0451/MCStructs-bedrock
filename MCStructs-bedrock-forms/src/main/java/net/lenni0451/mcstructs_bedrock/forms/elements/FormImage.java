package net.lenni0451.mcstructs_bedrock.forms.elements;

import javax.annotation.Nullable;

public class FormImage {

    private final Type type;
    private final String value;

    public FormImage(final Type type, final String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * @return The type of the image
     */
    public Type getType() {
        return this.type;
    }

    /**
     * @return The value of the image
     */
    public String getValue() {
        return this.value;
    }


    /**
     * The type of an image.
     */
    public enum Type {
        PATH("path"),
        URL("url");

        @Nullable
        public static Type byName(final String name) {
            for (Type type : Type.values()) {
                if (type.getName().equals(name)) return type;
            }
            return null;
        }


        private final String name;

        Type(final String name) {
            this.name = name;
        }

        /**
         * @return The name of the type
         */
        public String getName() {
            return this.name;
        }
    }

}
