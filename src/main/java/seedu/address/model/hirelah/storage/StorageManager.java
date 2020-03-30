package seedu.address.model.hirelah.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.hirelah.AttributeList;
import seedu.address.model.hirelah.IntervieweeList;
import seedu.address.model.hirelah.MetricList;
import seedu.address.model.hirelah.QuestionList;
import seedu.address.storage.UserPrefsStorage;

/**
 * Manages storage of different Sessions data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(seedu.address.storage.StorageManager.class);
    private UserPrefsStorage userPrefsStorage;
    private IntervieweeStorage intervieweeStorage;
    private AttributeStorage attributeStorage;
    private QuestionStorage questionStorage;
    private MetricStorage metricStorage;

    public StorageManager(UserPrefsStorage userPrefsStorage, IntervieweeStorage intervieweeStorage,
                          AttributeStorage attributeStorage, QuestionStorage questionStorage,
                          MetricStorage metricStorage) {
        super();
        this.userPrefsStorage = userPrefsStorage;
        this.intervieweeStorage = intervieweeStorage;
        this.attributeStorage = attributeStorage;
        this.questionStorage = questionStorage;
        this.metricStorage = metricStorage;
    }

    // ================ InterviewStorage methods ==============================

    public void saveInterviewee(IntervieweeList source) throws IOException {
        logger.fine("Attempting to write to Metric data file: " + getIntervieweeDirectory());
        intervieweeStorage.saveInterview(source);
    }
    public Optional<IntervieweeList> readInterviewee() throws DataConversionException {
        return readInterviewee(intervieweeStorage.getPath());
    }

    public Optional<IntervieweeList> readInterviewee(Path filepath) throws DataConversionException {
        logger.fine("Attempting to read data from Interviewee file: " + filepath);
        return intervieweeStorage.readInterviewee(filepath);
    }

    public Path getIntervieweeDirectory() {
        return intervieweeStorage.getPath();
    }

    // ================ AttributeStorage methods ==============================

    public void saveAttribute(AttributeList source) throws IOException, IllegalValueException {
        logger.fine("Attempting to write to Metric data file: " + getAttributeDirectory());
        attributeStorage.saveAttributes(source);
    }

    public Optional<AttributeList> readAttribute() throws DataConversionException {
        return readAttribute(attributeStorage.getPath());
    }

    public Optional<AttributeList> readAttribute(Path filepath) throws DataConversionException {
        logger.fine("Attempting to read data from Attribute file: " + filepath);
        return attributeStorage.readAttribute(filepath);
    }
    public Path getAttributeDirectory() {
        return attributeStorage.getPath();
    }

    // ================ QuestionStorage methods ==============================

    public void saveQuestion(QuestionList source) throws IOException, IllegalValueException {
        logger.fine("Attempting to write to Metric data file: " + getQuestionDirectory());
        questionStorage.saveQuestions(source);
    }

    public Optional<QuestionList> readQuestion() throws DataConversionException {
        return readQuestion(questionStorage.getPath());
    }

    public Optional<QuestionList> readQuestion(Path filepath) throws DataConversionException {
        logger.fine("Attempting to read data from Question file: " + filepath);
        return questionStorage.readQuestion(filepath);
    }

    public Path getQuestionDirectory() {
        return questionStorage.getPath();
    }

    // ================ MetricStorage methods ================================

    public void saveMetric(MetricList source) throws IOException, IllegalValueException {
        logger.fine("Attempting to write to Metric data file: " + getMetricDirectory());
        metricStorage.saveMetrics(source);
    }

    public Optional<MetricList> readMetric() throws DataConversionException {
        return readMetric(metricStorage.getPath());
    }

    public Optional<MetricList> readMetric(Path filepath) throws DataConversionException {
        logger.fine("Attempting to read data from Metric file: " + filepath);
        return metricStorage.readMetric(filepath);
    }

    public Path getMetricDirectory() {
        return metricStorage.getPath();
    }

    // ================ UserPrefsStorage methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }
}
