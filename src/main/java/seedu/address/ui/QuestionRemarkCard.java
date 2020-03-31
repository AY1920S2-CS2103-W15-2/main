package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.hirelah.Question;
import seedu.address.model.hirelah.QuestionRemark;
import seedu.address.model.hirelah.Remark;

/**
 * An UI component that displays information of an {@code Remark}.
 */
public class QuestionRemarkCard extends UiPart<Region> {

    private static final String FXML = "QuestionRemarkCard.fxml";

    public final QuestionRemark questionRemark;

    private Question question;

    @FXML
    private VBox remarkCardPane;
    @FXML
    private Label questionHeader;
    @FXML
    private Label underline;

    /**
     * Constructs a question remark header in a RemarkList.
     *
     * @param remark remark object to take information from.
     */
    public QuestionRemarkCard(Remark remark, ObservableList<Question> questions) {
        super(FXML);
        this.questionRemark = (QuestionRemark) remark;
        question = questions.get(questionRemark.getQuestionNumber() - 1);
        String questionText;
        if (question.toString().length() < 40) {
            questionText = question.toString();
        } else {
            questionText = question.toString().substring(0, 38) + "...";
        }
        questionHeader.setText("Question " + questionRemark.getQuestionNumber() + ": " + questionText);
    }
}
