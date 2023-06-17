package net.lenni0451.mcstructs_bedrock.forms.serializer.elements;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.*;

import java.lang.reflect.Type;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.*;

/**
 * A codec for all form element types.
 */
public class FormElementCodec implements JsonSerializer<AFormElement>, JsonDeserializer<AFormElement> {

    @Override
    public JsonElement serialize(AFormElement src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject element = new JsonObject();
        element.addProperty("type", src.getType().getName());
        element.addProperty("text", src.getText());
        switch (src.getType()) {
            case CHECKBOX:
                CheckboxFormElement checkbox = (CheckboxFormElement) src;
                element.addProperty("default", checkbox.getDefaultValue());
                break;
            case DROPDOWN:
                DropdownFormElement dropdown = (DropdownFormElement) src;
                element.add("options", context.serialize(dropdown.getOptions()));
                element.addProperty("default", dropdown.getDefaultOption());
                break;
            case SLIDER:
                SliderFormElement slider = (SliderFormElement) src;
                element.addProperty("min", slider.getMin());
                element.addProperty("max", slider.getMax());
                element.addProperty("step", slider.getStep());
                element.addProperty("default", slider.getDefaultValue());
                break;
            case STEP_SLIDER:
                StepSliderFormElement stepSlider = (StepSliderFormElement) src;
                element.add("steps", context.serialize(stepSlider.getSteps()));
                element.addProperty("default", stepSlider.getDefaultStep());
                break;
            case TEXT_FIELD:
                TextFieldFormElement textField = (TextFieldFormElement) src;
                element.addProperty("placeholder", textField.getPlaceholder());
                element.addProperty("default", textField.getDefaultValue());
                break;
            case LABEL:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + src.getType());
        }
        return element;
    }

    @Override
    public AFormElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "Form element");
        FormElementType type = FormElementType.byName(ensureContainsString(ob, "type"));
        if (type == null) throw new JsonParseException("Unknown form element type: " + ob.get("type").getAsString());
        String text = ensureContainsString(ob, "text");
        switch (type) {
            case CHECKBOX:
                if (ob.has("default")) {
                    return new CheckboxFormElement(text,
                            ensureContainsBoolean(ob, "default"));
                } else {
                    return new CheckboxFormElement(text);
                }
            case DROPDOWN:
                if (ob.has("default")) {
                    return new DropdownFormElement(text,
                            ensureContainsInt(ob, "default"),
                            ensureContainsStringArray(ob, "options"));
                } else {
                    return new DropdownFormElement(text,
                            ensureContainsStringArray(ob, "options"));
                }
            case SLIDER:
                if (ob.has("default")) {
                    return new SliderFormElement(text,
                            ensureContainsFloat(ob, "min"),
                            ensureContainsFloat(ob, "max"),
                            ensureContainsInt(ob, "step"),
                            ensureContainsFloat(ob, "default"));
                } else {
                    return new SliderFormElement(text,
                            ensureContainsFloat(ob, "min"),
                            ensureContainsFloat(ob, "max"),
                            ensureContainsInt(ob, "step"));
                }
            case STEP_SLIDER:
                if (ob.has("default")) {
                    return new StepSliderFormElement(text,
                            ensureContainsInt(ob, "default"),
                            ensureContainsStringArray(ob, "steps"));
                } else {
                    return new StepSliderFormElement(text,
                            ensureContainsStringArray(ob, "steps"));
                }
            case TEXT_FIELD:
                if (ob.has("default")) {
                    return new TextFieldFormElement(text,
                            ensureContainsString(ob, "placeholder"),
                            ensureContainsString(ob, "default"));
                } else {
                    return new TextFieldFormElement(text,
                            ensureContainsString(ob, "placeholder"));
                }
            case LABEL:
                return new LabelFormElement(text);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}
