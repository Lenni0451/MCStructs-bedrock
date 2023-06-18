package net.lenni0451.mcstructs_bedrock.forms.responses;

import net.lenni0451.mcstructs_bedrock.forms.TestForms;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionFormTest {

    private ActionForm form;

    @BeforeEach
    void setUp() {
        this.form = TestForms.createActionForm();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void serialize(final int id) {
        this.form.setClickedButton(id);
        assertEquals(String.valueOf(id), this.form.serializeResponse());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void deserialize(final int id) {
        this.form.deserializeResponse(String.valueOf(id));
        assertEquals(id, this.form.getClickedButton());
    }

    @Test
    void invalidDeserialize() {
        assertThrows(NumberFormatException.class, () -> this.form.deserializeResponse("test"));
    }

}
