package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.hirelah.Question;

/**
 * Panel containing the list of questions.
 */
public class QuestionListPanel extends UiPart<Region> {
    private static final String FXML = "GeneralListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(QuestionListPanel.class);

    @FXML
    private ListView<Question> generalListView;

    public QuestionListPanel(ObservableList<Question> questionList) {
        super(FXML);
        generalListView.setItems(questionList);
        generalListView.setCellFactory(listView -> new QuestionListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the Question text with the question number.
     */
    class QuestionListViewCell extends ListCell<Question> {
        @Override
        protected void updateItem(Question question, boolean empty) {
            super.updateItem(question, empty);
            int questionNumber = getIndex() + 1;
            setText(question == null ? "" : questionNumber + ". " + question.toString());
        }
    }
}
