package net.lenni0451.mcstructs_bedrock.text.utils;

public class TestTranslation {

    private final String key;
    private final String directTranslation;
    private final String translated;

    public TestTranslation(final String key, final String directTranslation, final String translated) {
        this.key = key;
        this.directTranslation = directTranslation;
        this.translated = translated;
    }

    public String getKey() {
        return this.key;
    }

    public String getDirectTranslation() {
        return this.directTranslation;
    }

    public String getTranslated() {
        return this.translated;
    }

}
