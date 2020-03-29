package seedu.address.logic.commands.interview;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ToggleView;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.InterviewSession;

/**
 * Remark command is an Interview phase command that adds a Remark at the current interview time
 * to the Interviewee's Transcript.
 */
public class RemarkCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Added the remark";

    private final String remark;

    public RemarkCommand(String remark) {
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        InterviewSession session = model.getInterviewSession();
        try {
            model.getCurrentTranscript().addRemark(session.createRemark(this.remark));
        } catch (IllegalValueException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(MESSAGE_SUCCESS, ToggleView.TRANSCRIPT);
    }
}
