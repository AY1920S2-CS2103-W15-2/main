package seedu.address.logic;

import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InterviewParser;
import seedu.address.logic.parser.NormalParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Attribute;
import seedu.address.model.hirelah.Interviewee;
import seedu.address.model.hirelah.Question;
import seedu.address.model.hirelah.Transcript;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InterviewParser interviewParser;
    private final NormalParser normalParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        interviewParser = new InterviewParser();
        normalParser = new NormalParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command;
        switch (model.getAppPhase()) {
        case PreSession:
            // TODO: PreSession parser
        case Normal:
            command = normalParser.parseCommand(commandText);
            break;
        case Interview:
            command = interviewParser.parseCommand(commandText);
            break;
        default:
            throw new IllegalArgumentException("Impossible enum case");
        }
        commandResult = command.execute(model);

        //try {
        //    storage.saveAddressBook(model.getAddressBook());
        //} catch (IOException ioe) {
        //    throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        //}

        return commandResult;
    }

    @Override
    public ObservableList<Attribute> getAttributeListView() {
        return model.getAttributeListView();
    }

    @Override
    public ObservableList<Question> getQuestionListView() {
        return model.getQuestionListView();
    }

    @Override
    public ObservableList<Transcript> getTranscriptListView(Interviewee interviewee) {
        return model.getTranscriptListView(interviewee);
    }

    @Override
    public ObservableList<Interviewee> getFilteredIntervieweeListView() {
        return model.getFilteredIntervieweeListView();
    }

    @Override
    public Path getSessionsDirectory() {
        return model.getSessionsDirectory();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
