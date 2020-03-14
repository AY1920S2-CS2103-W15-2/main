package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hirelah.Transcript;

/**
 * Panel containing the list of transcripts for an interviewee.
 */
public class TranscriptListPanel extends UiPart<Region> {
    private static final String FXML = "TranscriptListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TranscriptListPanel.class);

    @FXML
    private ListView<Transcript> generalListView;

    public TranscriptListPanel(ObservableList<Transcript> transcriptList) {
        super(FXML);
        generalListView.setItems(transcriptList);
        generalListView.setCellFactory(listView -> new TranscriptListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Transcript} using a {@code TranscriptCard}.
     */
    class TranscriptListViewCell extends ListCell<Transcript> {
        @Override
        protected void updateItem(Transcript transcript, boolean empty) {
            super.updateItem(transcript, empty);

            if (empty || transcript == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TranscriptCard(transcript).getRoot());
            }
        }
    }
}
