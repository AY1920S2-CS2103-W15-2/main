package seedu.address.logic.commands;

import java.io.File;

import javafx.stage.FileChooser;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Interviewee;
import seedu.address.model.hirelah.exceptions.IllegalActionException;

/**
 * Adds a pdf resume file to the given Interviewee.
 */
public class UploadResumeCommand extends Command {
    public static final String COMMAND_WORD = "upload";
    public static final String MESSAGE_SUCCESS = "Successfully added the resume!";
    public static final String MESSAGE_FILE_NOT_FOUND = "Could not find the file!";

    private String path;
    private String identifier;

    public UploadResumeCommand(String identifier, String path) {
        this.path = path;
        this.identifier = identifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Interviewee interviewee;
        try {
            interviewee = model.getIntervieweeList().getInterviewee(identifier);
        } catch (IllegalActionException e) {
            throw new CommandException(e.getMessage());
        }
        File resume;
        if (path == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a pdf file");
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
            resume = fileChooser.showOpenDialog(null);
        } else {
            resume = new File(path);
        }
        if (resume == null || !resume.exists()) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        }
        interviewee.setResume(resume);

        return new ToggleCommandResult(MESSAGE_SUCCESS, ToggleView.INTERVIEWEE);
    }
}
