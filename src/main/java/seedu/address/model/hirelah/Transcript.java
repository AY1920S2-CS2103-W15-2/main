package seedu.address.model.hirelah;

import java.time.Duration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hirelah.exceptions.IllegalActionException;

/**
 * Encapsulates all the details that are put by the interviewer during the interview session
 * for a particular {@code Interviewee}. It stores the {@code RemarkList} which contains all {@code Remark}s
 * of this interview, the {@code File} of the audio recorded for this interview
 * and the attribute scores inside a HashMap.
 */
public class Transcript {
    private final RemarkList remarkList;
    private final ObservableMap<Attribute, Double> attributeToScoreMap;

    /**
     * Constructs a {@code Transcript} object
     * that are associated with a particular interviewee.
     */
    public Transcript(QuestionList questions, AttributeList attributes) {
        this.remarkList = new RemarkList(questions.size());
        this.attributeToScoreMap = FXCollections.observableHashMap();
        for (Attribute attribute : attributes) {
            attributeToScoreMap.put(attribute, Double.NaN);
        }
    }

    /**
     * Returns an unmodifiable view of the {@code RemarkList} associated with this {@code Transcript}.
     * @return An {@code ObservableList} tracking changes to the RemarkList.
     */
    public ObservableList<Remark> getRemarkListView() {
        return FXCollections.unmodifiableObservableList(remarkList.getRemarks());
    }

    /**
     * Returns an unmodifiable view of the attributes and scores associated with this {@code Transcript}.
     *
     * @return An {@code ObservableMap} tracking changes to the scores.
     */
    public ObservableMap<Attribute, Double> getAttributeToScoreMapView() {
        return FXCollections.unmodifiableObservableMap(attributeToScoreMap);
    }

    /**
     * Sets an {@code Attribute} of this {@code Interviewee} to have a certain score.
     *
     * @param attribute The attribute that is to be updated.
     * @param score The score of this {@code Attribute}.
     */
    public void setAttributeScore (Attribute attribute, Double score) {
        this.attributeToScoreMap.put(attribute, score);
    }

    /**
     * Retrieves the score of this {@code Attribute} of this {@code Interviewee}.
     *
     * @param attribute The attribute whose score is to be retrieved.
     * @return The score of the given attribute as recorded in the interview.
     */
    public double getAttributeScore(Attribute attribute) {
        return this.attributeToScoreMap.get(attribute);
    }

    /**
     * Indicates if an attribute has been scored yet.
     *
     * @param attribute the attribute to check.
     * @return true if attribute has a score, false otherwise.
     */
    public boolean isAttributeScored(Attribute attribute) {
        return this.attributeToScoreMap.containsKey(attribute);
    }

    /**
     * Adds a remark to the {@code RemarkList} in this {@code Transcript}.
     *
     * @param message the message of the remark to add.
     */
    public void addRemark(String message) {
        remarkList.addRemark(message);
    }

    /**
     * Marks the beginning of a {@code Question} in this {@code Transcript}.
     */
    public void startQuestion(int questionNumber, Question question)
            throws IllegalActionException, IllegalValueException {
        remarkList.startQuestion(questionNumber, question);
    }

    /**
     * Retrieves index of {@code Remark} created around the given time.
     *
     * @param time Time queried.
     * @return The index of the {@code Remark} at or just after time, or the last index
     *         if time exceeds the interview duration.
     */
    public int getIndexAtTime(Duration time) {
        return remarkList.getIndexAtTime(time);
    }

    /**
     * Retrieves the index of the Remark when this {@code Question}
     * was first asked.
     *
     * @param questionIndex Index of the question that is queried.
     * @return The index of the {@code QuestionRemark} in the RemarkList associated with this question.
     */
    public int getIndexOfQuestion(int questionIndex) throws IllegalActionException, IllegalValueException {
        return remarkList.getIndexOfQuestion(questionIndex);
    }

}
