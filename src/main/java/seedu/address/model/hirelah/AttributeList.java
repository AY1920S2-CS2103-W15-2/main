package seedu.address.model.hirelah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;

/*
 * AttributeList
 *
 * CS2103 AY19/20 Semester 2
 * Team Project
 * HireLah!
 *
 * 01 Mar 2020
 *
 */

/**
 * <p>AttributeList class manages the list of attributes that have been
 * added by the interviewer. AttributeList can retrieve the attributes using
 * the prefix.</p>
 * @author AY1920S2-W15-2
 */

public class AttributeList {
    private static final String DUPLICATE_MESSAGE = "There are multiple attributes with the same prefix.";
    private static final String NOT_FOUND_MESSAGE = "No attributes with the entered prefix.";

    private ObservableList<Attribute> attributes;

    /**
     * Constructs an AttributeList instance.
     */
    public AttributeList() {
        this.attributes = FXCollections.observableArrayList();
    }

    public ObservableList<Attribute> getObservableList() {
        return attributes;
    }

    /**
     * Adds the attribute to the list.
     *
     * @param attributeName The attribute name.
     * @throws IllegalValueException if the attribute already exists, or the name is invalid
     */
    public void add(String attributeName) throws IllegalValueException {
        Attribute attribute = Attribute.of(attributeName);
        boolean isDuplicate = isDuplicate(attribute);

        if (isDuplicate) {
            throw new IllegalValueException("This attribute is already exists!");
        }

        attributes.add(attribute);
    }

    /**
     * Find the attribute based on its prefix.
     * @param attributePrefix The prefix of the attribute.
     * @return The corresponding Attribute instance.
     * @throws IllegalValueException if the prefix can be multi-interpreted or no such Attribute found.
     */
    public Attribute find(String attributePrefix) throws IllegalValueException {
        checkPrefix(attributePrefix);
        return attributes.stream().filter(attribute -> attribute.toString().startsWith(attributePrefix))
                                  .findFirst()
                                  .get();
    }

    /**
     * Deletes the attribute by its prefix.
     *
     * @param attributePrefix The prefix of the attribute.
     * @throws IllegalValueException if the prefix can be multi-interpreted or no such Attribute found.
     */
    public void delete(String attributePrefix) throws IllegalValueException {
        Attribute attribute = find(attributePrefix);
        attributes.remove(attribute);
    }

    /**
     * Checks the number of attributes that starts with the prefix.
     * @param attributePrefix The prefix of the attribute.
     * @throws IllegalValueException if the prefix can be multi-interpreted or no such Attribute found.
     */
    private void checkPrefix(String attributePrefix) throws IllegalValueException {
        long startWithPrefix = attributes.stream()
                .filter(attribute -> attribute.toString().startsWith(attributePrefix))
                .count();

        if (startWithPrefix > 1) {
            throw new IllegalValueException(DUPLICATE_MESSAGE);
        } else if (startWithPrefix == 0) {
            throw new IllegalValueException(NOT_FOUND_MESSAGE);
        }
    }

    private boolean isDuplicate(Attribute attribute) {
        return attributes.stream().anyMatch(attribute::equals);
    }
}
