package net.lenni0451.mcstructs_bedrock.forms.serializer.elements;

import com.google.gson.*;
import net.lenni0451.mcstructs_bedrock.forms.elements.*;

import java.lang.reflect.Type;

import static net.lenni0451.mcstructs_bedrock.forms.utils.JsonUtils.*;

/**
 * A codec for all form element types.
 */
public class FormElementCodec implements JsonSerializer<FormElement>, JsonDeserializer<FormElement> {

    @Override
    public JsonElement serialize(FormElement src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject element = new JsonObject();
        element.addProperty("type", src.getType().getName());
        element.addProperty("text", src.getText(false));
        switch (src.getType()) {
            case CHECKBOX:
                CheckboxFormElement checkbox = (CheckboxFormElement) src;
                element.addProperty("default", checkbox.getDefaultValue());
                break;
            case DROPDOWN:
                DropdownFormElement dropdown = (DropdownFormElement) src;
                element.add("options", context.serialize(dropdown.getOptions(false)));
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
                element.add("steps", context.serialize(stepSlider.getSteps(false)));
                element.addProperty("default", stepSlider.getDefaultStep());
                break;
            case TEXT_FIELD:
                TextFieldFormElement textField = (TextFieldFormElement) src;
                element.addProperty("placeholder", textField.getPlaceholder());
                element.addProperty("default", textField.getDefaultValue());
                break;
            case LABEL:
                break;
            case DIVIDER:
                break;
            case HEADER:
                break;
            case BUTTON:
                ButtonFormElement button = (ButtonFormElement) src;
                if (button.getImage() != null) element.add("image", context.serialize(button.getImage()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + src.getType());
        }
        return element;
    }

    @Override
    public FormElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject ob = ensureRootObject(json, "Form element");
        FormElementType type = FormElementType.byName(ensureContainsString(ob, "type"));
        if (type == null) throw new JsonParseException("Unknown form element type: " + ob.get("type").getAsString());
        String text = ensureContainsString(ob, "text");
        switch (type) {
            case CHECKBOX:
                return new CheckboxFormElement(text,
                        hasNonNull(ob, "default") && ensureContainsBoolean(ob, "default"));
            case DROPDOWN:
                if (hasNonNull(ob, "default")) {
                    return new DropdownFormElement(text,
                            ensureContainsInt(ob, "default"),
                            ensureContainsStringArray(ob, "options"));
                } else {
                    return new DropdownFormElement(text,
                            ensureContainsStringArray(ob, "options"));
                }
            case SLIDER:
                if (hasNonNull(ob, "default")) {
                    return new SliderFormElement(text,
                            ensureContainsFloat(ob, "min"),
                            ensureContainsFloat(ob, "max"),
                            hasNonNull(ob, "step") ? ensureContainsFloat(ob, "step") : 1,
                            ensureContainsFloat(ob, "default"));
                } else {
                    return new SliderFormElement(text,
                            ensureContainsFloat(ob, "min"),
                            ensureContainsFloat(ob, "max"),
                            hasNonNull(ob, "step") ? ensureContainsFloat(ob, "step") : 1);
                }
            case STEP_SLIDER:
                if (hasNonNull(ob, "default")) {
                    return new StepSliderFormElement(text,
                            ensureContainsInt(ob, "default"),
                            ensureContainsStringArray(ob, "steps"));
                } else {
                    return new StepSliderFormElement(text,
                            ensureContainsStringArray(ob, "steps"));
                }
            case TEXT_FIELD:
                return new TextFieldFormElement(text,
                        hasNonNull(ob, "placeholder") ? ensureContainsString(ob, "placeholder") : "",
                        hasNonNull(ob, "default") ? ensureContainsString(ob, "default") : "");
            case LABEL:
                return new LabelFormElement(text);
            case DIVIDER:
                return new DividerFormElement();
            case HEADER:
                return new HeaderFormElement(text);
            case BUTTON:
                return new ButtonFormElement(text,
                        hasNonNull(ob, "image") ? context.deserialize(ob.get("image"), FormImage.class) : null);
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}
