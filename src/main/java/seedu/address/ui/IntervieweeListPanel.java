package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hirelah.Interviewee;

/**
 * Panel containing the list of interviewees.
 */
public class IntervieweeListPanel extends UiPart<Region> {
    private static final String FXML = "GeneralListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(IntervieweeListPanel.class);

    @FXML
    private ListView<Interviewee> generalListView;

    public IntervieweeListPanel(ObservableList<Interviewee> intervieweeList) {
        super(FXML);
        generalListView.setItems(intervieweeList);
        generalListView.setCellFactory(listView -> new IntervieweeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Interviewee} using a {@code IntervieweeCard}.
     */
    class IntervieweeListViewCell extends ListCell<Interviewee> {
        @Override
        protected void updateItem(Interviewee interviewee, boolean empty) {
            super.updateItem(interviewee, empty);

            if (empty || interviewee == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IntervieweeCard(interviewee).getRoot());
            }
        }
    }

}
