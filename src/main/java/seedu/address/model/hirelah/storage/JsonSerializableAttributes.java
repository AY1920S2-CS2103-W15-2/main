package seedu.address.model.hirelah.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.hirelah.Attribute;
import seedu.address.model.hirelah.AttributeList;
import seedu.address.model.hirelah.Interviewee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable JsonAdaptedAttributes that is serializable to JSON format {@link JsonAdaptedAttributes}.
 */
@JsonRootName(value = "attributes")
public class JsonSerializableAttributes {
    private final List<JsonAdaptedAttributes> attributes = new ArrayList<>();
    /**
     * Converts a given {@code AttributeList} into this class for Jackson use.
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    @JsonCreator
    public JsonSerializableAttributes(@JsonProperty("attributes") List<JsonAdaptedAttributes> source) {
        this.attributes.addAll(source);
    }

    public JsonSerializableAttributes(AttributeList source) throws IllegalValueException {
        List<Attribute> convertedList = source.getObservableList();
        attributes.addAll(convertedList.stream().map(JsonAdaptedAttributes::new).collect(Collectors.toList()));
    }
    /**
     * Converts into the model's {@code AttributeList} object.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AttributeList toModelType() throws IllegalValueException {
        AttributeList newData = new AttributeList();
        for (JsonAdaptedAttributes jsonAdaptedattributes : attributes) {
            Attribute attribute = jsonAdaptedattributes.toModelType();
            if (newData.isDuplicate(attribute)){
                throw new IllegalValueException("This attribute is already exists!");
            }
            newData.add(attribute.toString());
        }
        return newData;
    }
}
