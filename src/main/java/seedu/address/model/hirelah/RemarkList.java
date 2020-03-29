package seedu.address.model.hirelah;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hirelah.exceptions.IllegalActionException;

/**
 * A list of remarks that are associated  with a particular interview session of an {@code Interviewee}.
 * Some remarks are associated with a question, as it is part of the answer to a question, and some remarks do not.
 * Both are able to be inserted to this remark list. Once a remark has been added, it cannot be removed from the list.
 * An interview session always have a start remark and an end remark, thus it minimally has 2 {@code Remark} objects.
 * It is assumed that there are no 2 Remarks at any particular time.
 *
 * Supports a minimal set of list operations.
 *
 * @see Remark
 */
public class RemarkList {
    private final ObservableList<Remark> remarks = FXCollections.observableArrayList();
    private final int[] questionIndices;

    public RemarkList(int questionsCount) {
        questionIndices = new int[questionsCount + 1];
    }

    /**
     * Retrieves the remark list encapsulated by {@code RemarkList}.
     */
    public ObservableList<Remark> getRemarks() {
        return remarks;
    }

    /**
     * Adds a remark to the list.
     *
     * @param toAdd The remark to be added to the list.
     */
    public void add(Remark toAdd) throws IllegalValueException {
        requireNonNull(toAdd);
        if (toAdd.getQuestionNumber() != null) {
            try {
                if (!isQuestionAnswered(toAdd.getQuestionNumber())) {
                    questionIndices[toAdd.getQuestionNumber()] = remarks.size();
                }
            } catch (IllegalValueException e) {
                throw new IllegalValueException(e.getMessage());
            }
        }
        remarks.add(toAdd);
    }

    /**
     * Retrieves the number of {@code Remark}s in {@code RemarkList}.
     *
     * @return The number of {@code Remark}s in this {@code RemarkList}.
     */
    private int getRemarkListSize() {
        return this.remarks.size();
    }


    /**
     * Retrieves index of {@code Remark} created around the given time.
     *
     * @param time Time queried.
     * @return The index of the {@code Remark} at or just after time, or the last index
     *         if time exceeds the interview duration.
     */
    public int getIndexAtTime(Duration time) {
        return IntStream.range(0, getRemarkListSize())
                .filter(i -> remarks.get(i).getTime().compareTo(time) >= 0)
                .findFirst()
                .orElse(getRemarkListSize() - 1);
    }

    /**
     * Checks if a question is answered during the interview
     * by checking whether there is a {@code Remark} that is associated with it.
     *
     * @param questionNumber Question that is checked against.
     * @return Whether the question has {@code Remark} associated with it.
     */
    public boolean isQuestionAnswered(int questionNumber) throws IllegalValueException {
        try {
            return questionIndices[questionNumber] != 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalValueException(
                    String.format("There are only %d questions in this interview session.", questionIndices.length - 1));
        }
    }

    /**
     * Retrieves the index of the Remark when this {@code Question}
     * was first asked.
     *
     * @param questionIndex Question number that is queried.
     * @return The index of the {@code Remark} in the RemarkList
     *         that was first associated with this {@code Question}.
     * @throws IllegalActionException If the question queried has not been answered.
     * @throws IllegalValueException If the question index queried is out of bound or is not a number.
     */
    public int getIndexOfQuestion(int questionIndex) throws IllegalActionException, IllegalValueException {
        if (!isQuestionAnswered(questionIndex)) {
            throw new IllegalActionException("This question was not answered!");
        }
        return questionIndices[questionIndex];
    }
}
