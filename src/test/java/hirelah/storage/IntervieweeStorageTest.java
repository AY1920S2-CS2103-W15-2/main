package hirelah.storage;

import static hirelah.testutil.Assert.assertThrows;
import static hirelah.testutil.TypicalAttributes.getTypicalAttributes;
import static hirelah.testutil.TypicalInterviewee.intervieweeBeforeInterview;
import static hirelah.testutil.TypicalQuestionList.getTypicalQns;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import hirelah.model.hirelah.AttributeList;
import hirelah.model.hirelah.QuestionList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import hirelah.commons.exceptions.DataConversionException;
import hirelah.model.hirelah.IntervieweeList;

public class IntervieweeStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "IntervieweeStorageTest");
    private static final TranscriptStorage transcriptStorage = new TranscriptStorage(Paths.get("src", "test", "data",
            "IntervieweeStorageTest", "transcript"));
    private static final boolean model = false;
    @TempDir
    public Path testFolder;

    @Test
    public void readInterviewee_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInterviewee(null, getTypicalQns(),
                getTypicalAttributes(), model, transcriptStorage));
    }
    /**Create a readInterviewee method to support the tests*/
    private java.util.Optional<IntervieweeList> readInterviewee(String filePath, QuestionList questionList,
                                                                AttributeList attributeList, boolean model,
                                                                TranscriptStorage transcriptStorage) throws Exception {
        IntervieweeStorage intervieweeStorage = new IntervieweeStorage(Paths.get(filePath));
        return intervieweeStorage.readInterviewee(addToTestDataPathIfNotNull(filePath),
                questionList, attributeList, model, transcriptStorage);

    }
    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }
    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInterviewee("NonExistentFile.json", getTypicalQns(),
                getTypicalAttributes(), model, transcriptStorage).isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readInterviewee("notJsonFormat.json", getTypicalQns(),
                getTypicalAttributes(), model, transcriptStorage));
    }

    @Test
    public void readInterviewee_invalidIntervieweeList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInterviewee("invalidInterviewee.json", getTypicalQns(),
                getTypicalAttributes(), model, transcriptStorage));
    }

    @Test
    public void readInterviewee_invalidAndValidInterviewee_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInterviewee("invalidAndValidInterviewee.json" , getTypicalQns(),
                getTypicalAttributes(), model, transcriptStorage));
    }
    @Test
    public void readAndSaveInterviewees_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempIntervieweeList.json");
        IntervieweeList original = intervieweeBeforeInterview();
        IntervieweeStorage intervieweeStorage = new IntervieweeStorage(filePath);

        // Save in new file and read back
        intervieweeStorage.saveInterview(original);
        IntervieweeList readBack = intervieweeStorage.readInterviewee(filePath,
                getTypicalQns(), getTypicalAttributes(), model, transcriptStorage).get();
        assertEquals(original, readBack);

        // Modify data, overwrite exiting file, and read back, without specifying file path
        original.addInterviewee("HOON");
        original.deleteInterviewee("JANE");
        intervieweeStorage.saveInterview(original);
        readBack = intervieweeStorage.readInterviewee(filePath,
                getTypicalQns(), getTypicalAttributes(), model, transcriptStorage).get();
        assertEquals(original, readBack);
    }
    @Test
    public void saveInterview_nullIntervieweeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInterviewee(null, "SomeFile.json"));
    }

    /**
     * Saves {@code IntervieweeList} at the specified {@code filePath}.
     */
    private void saveInterviewee(IntervieweeList intervieweeList, String filePath) {
        try {
            new IntervieweeStorage(Paths.get(filePath))
                    .saveInterview(intervieweeList);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInterview_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInterviewee(new IntervieweeList(), null));
    }
}
