package seedu.address.model.hirelah;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.hirelah.exceptions.IllegalActionException;

/**
 * Encapsulates all the details that are put by the interviewer during the interview session
 * for a particular {@code Interviewee}. It stores the {@code RemarkList} which contains all {@code Remark}s
 * of this interview, the {@code File} of the audio recorded for this interview
 * and the attribute scores inside a HashMap.
 */
public class Transcript {
    private final RemarkList remarkList;
    private final Map<Attribute, Double> attributeToScoreMap;

    /**
     * Constructs a {@code Transcript} object
     * that are associated with a particular interviewee.
     */
    Transcript() {
        this.remarkList = new RemarkList();
        this.attributeToScoreMap = new HashMap<>();
    }

    /**
     * Retrieves the {@code RemarkList} that are associated with this {@code Transcript}.
     *
     * @return The {@code RemarkList} that are associated with this {@code Transcript}.
     */
    public RemarkList getRemarkList() {
        return remarkList;
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
    public double getAttributeScore (Attribute attribute) {
        return this.attributeToScoreMap.get(attribute);
    }

    /**
     * Retrieves {@code Remark} that are created nearest to a tim
     * if the time is within the duration of the interview.
     *
     * @param timeMs The time that are used to query the {@code Remark}.
     * @return The {@code Remark} that are created nearest to the time.
     */
    public Remark getTranscriptAtTime(long timeMs) throws IllegalActionException {
        System.out.println("APAAH SIH");
        System.out.println(timeMs <= remarkList.getInterviewDurationInMs());
        if (!remarkList.isTimeInValidRange(timeMs)) {
            throw new IllegalActionException(
                    "The duration of the interview is only " + remarkList.getInterviewDurationInMs()
            );
        }
        Remark currentRemark = this.remarkList.getRemarkAtTime(timeMs);
        return currentRemark;
    }

    /**
     * Retrieves {@code Remark} that are first associated with this {@code Question}.
     *
     * @param question {@code Question} that are to be queried.
     * @return Instant of the {@code Remark} that are first associated with this {@code Question}.
     */

    public Remark getTranscriptAtQuestion(Question question) throws IllegalActionException {
        if (!remarkList.isQuestionAnswered(question)) {
            throw new IllegalActionException("There is no answer for this question");
        }
        return remarkList.getRemarkOfQuestion(question);
    }

}
