package net.lenni0451.mcstructs_bedrock.forms.responses;

import net.lenni0451.mcstructs_bedrock.forms.TestForms;
import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModalFormTest {

    private ModalForm form;

    @BeforeEach
    void setUp() {
        this.form = TestForms.createModalForm();
    }

    @ParameterizedTest
    @CsvSource({
            "0, true",
            "1, false"
    })
    void serialize(final int id, final String state) {
        this.form.setClickedButton(id);
        assertEquals(state, this.form.serializeResponse());
    }

    @ParameterizedTest
    @CsvSource({
            "0, true",
            "1, false"
    })
    void deserialize(final int id, final String state) {
        this.form.deserializeResponse(state);
        assertEquals(id, this.form.getClickedButton());
    }

    @Test
    void invalidDeserialize() {
        assertDoesNotThrow(() -> this.form.deserializeResponse("test"));
        assertEquals(1, this.form.getClickedButton());
    }

}
