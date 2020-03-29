package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * ListIntervieweeCommand describes the behavior when the
 * client wants to list the interviewees.
 */

public class ListIntervieweeCommand extends Command {
    public static final String COMMAND_WORD = "interviewee";
    public static final String MESSAGE_SUCCESS = "Here is the list of interviewees:";
    public static final String MESSAGE_USAGE = "list " + COMMAND_WORD
            + ": List the interviewee from the Interviewee list. "
            + "Example: list " + COMMAND_WORD;

    /**
     * Creates a ListIntervieweeCommand to list the {@code Interviewee}
     */
    public ListIntervieweeCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, ToggleView.INTERVIEWEE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListIntervieweeCommand);
    }
}
