package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hirelah.Attribute;

/**
 * Panel containing the list of attributes.
 */
public class AttributeListPanel extends UiPart<Region> {
    private static final String FXML = "GeneralListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AttributeListPanel.class);

    @FXML
    private ListView<Attribute> attributeListView;

    public AttributeListPanel(ObservableList<Attribute> attributeList) {
        super(FXML);
        attributeListView.setItems(attributeList);
    }

}
