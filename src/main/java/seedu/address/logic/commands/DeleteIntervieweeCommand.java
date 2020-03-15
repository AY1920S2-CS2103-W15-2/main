package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.IntervieweeList;
import seedu.address.model.hirelah.exceptions.IllegalActionException;

/**
 * DeleteIntervieweeCommand describes the behavior when the
 * client wants to delete an interviewee from the list.
 */

public class DeleteIntervieweeCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "interviewee";
    public static final String MESSAGE_SUCCESS = "Deleted interviewee with identifier: %1$s";
    public static final String MESSAGE_USAGE = "delete " + COMMAND_WORD
            + ": Deletes an interviewee to the Interviewee list. "
            + "Parameters: "
            + "IDENTIFIER\n"
            + "Example: delete " + COMMAND_WORD + " Doe";

    public static final String EMPTY_STRING = "";
    private final String identifier;

    /**
     * Creates an DeleteIntervieweeCommand to delete the specified {@code Interviewee}
     */
    public DeleteIntervieweeCommand(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        IntervieweeList interviewees = model.getIntervieweeList();

        try {
            interviewees.deleteInterviewee(identifier);
        } catch (IllegalActionException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, identifier), ToggleView.INT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteIntervieweeCommand // instanceof handles nulls
                && identifier.equals(((DeleteIntervieweeCommand) other).identifier));
    }
}
