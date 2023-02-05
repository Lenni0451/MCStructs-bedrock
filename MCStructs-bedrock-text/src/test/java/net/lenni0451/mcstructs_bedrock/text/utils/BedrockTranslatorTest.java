package net.lenni0451.mcstructs_bedrock.text.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BedrockTranslatorTest {

    private static final List<TestTranslation> tests = new ArrayList<>();
    private static final Object[] args = new Object[]{"A", "B", "C", "D"};

    @BeforeAll
    static void prepare() {
        tests.add(new TestTranslation(
                "Prefix, %s%2$s again %s and %1$s lastly %s and also %1$s again!",
                "Prefix, sB again s and 1 lastly s and also 1 again!",
                "Prefix, A again B and D lastly C and also D again!"
        ));
        tests.add(new TestTranslation(
                "%%s %%%s %%%%s %%%%%s",
                "A B %C %D",
                "%A %%B %%%C %%%%D"
        ));
    }

    @Test
    void translate() {
        for (TestTranslation test : tests) {
            assertEquals(test.getTranslated(), BedrockTranslator.translate("", s -> test.getKey(), args));
            assertEquals(test.getDirectTranslation(), BedrockTranslator.translate(test.getKey(), s -> s, args));
        }

        assertEquals("Now playing: Lena Raine - Pigstep", BedrockTranslator.translate("record.nowPlaying", s -> {
            if (s.equals("record.nowPlaying")) return "Now playing: %s";
            else if (s.equals("item.record_pigstep.desc")) return "Lena Raine - Pigstep";
            else return s;
        }, new Object[]{"%item.record_pigstep.desc"}));
    }

}