package net.lenni0451.mcstructs_bedrock.forms.serializer.modal;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.AForm;
import net.lenni0451.mcstructs_bedrock.forms.FormType;
import net.lenni0451.mcstructs_bedrock.forms.types.ActionForm;
import net.lenni0451.mcstructs_bedrock.forms.types.CustomForm;
import net.lenni0451.mcstructs_bedrock.forms.types.ModalForm;

import java.lang.reflect.Type;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.ensureContainsString;
import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.ensureRootObject;

/**
 * A codec for all form types.<br>
 * Form specifics are delegated to the specific codecs.
 */
public class TypedFormCodec implements JsonSerializer<AForm>, JsonDeserializer<AForm> {

    private final ActionFormCodec actionFormCodec = new ActionFormCodec();
    private final ModalFormCodec modalFormCodec = new ModalFormCodec();
    private final CustomFormCodec customFormCodec = new CustomFormCodec();

    @Override
    public JsonElement serialize(AForm src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject form;
        switch (src.getType()) {
            case ACTION:
                form = this.actionFormCodec.serialize((ActionForm) src, typeOfSrc, context).getAsJsonObject();
                break;
            case MODAL:
                form = this.modalFormCodec.serialize((ModalForm) src, typeOfSrc, context).getAsJsonObject();
                break;
            case CUSTOM:
                form = this.customFormCodec.serialize((CustomForm) src, typeOfSrc, context).getAsJsonObject();
                break;
            default:
                throw new JsonParseException("Unknown form type: " + src.getType().getName());
        }
        form.addProperty("type", src.getType().getName());
        return form;
    }

    @Override
    public AForm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "Form");
        FormType type = FormType.byName(ensureContainsString(ob, "type"));
        if (type == null) throw new JsonParseException("Unknown form type: " + ob.get("type").getAsString());
        switch (type) {
            case ACTION:
                return this.actionFormCodec.deserialize(ob, typeOfT, context);
            case MODAL:
                return this.modalFormCodec.deserialize(ob, typeOfT, context);
            case CUSTOM:
                return this.customFormCodec.deserialize(ob, typeOfT, context);
            default:
                throw new JsonParseException("Unknown form type: " + type.getName());
        }
    }

}
