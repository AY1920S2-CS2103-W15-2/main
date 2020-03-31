package seedu.address.model.hirelah.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.hirelah.Question;
import seedu.address.model.hirelah.QuestionRemark;
import seedu.address.model.hirelah.Remark;

import java.time.Duration;

public class JsonAdaptedRemark {
    private final Duration time;
    private final String message;
    private final Integer questionNumber;

    @JsonCreator
    public JsonAdaptedRemark(@JsonProperty("time") Duration time, @JsonProperty("message") String message,
                             @JsonProperty("questionNumber") Integer questionNumber) {
        this.time = time;
        this.message = message;
        this.questionNumber = questionNumber;
    }

    public JsonAdaptedRemark(Remark source) {
        time = source.getTime();
        message = source.getMessage();
        if (source instanceof QuestionRemark) {
            questionNumber = ((QuestionRemark) source).getQuestionNumber();
        }
        else {
            questionNumber = null;
        }
    }

    public Duration getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

}
