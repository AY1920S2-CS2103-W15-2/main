package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * FinaliseCommand finalises the properties of an interview session
 * which includes the attributes, the questions and interviewees.
 */

public class FinaliseCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_SUCCESS = "Attributes, questions, and interviewees of this interview session"
            + " has been finalised. You cannot change them anymore.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finalises the attributes, questions, "
            + "and interviewees of an interview session.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.finaliseInterviewProperties();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


}
