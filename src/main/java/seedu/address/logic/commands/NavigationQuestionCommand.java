package seedu.address.logic.commands;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Transcript;
import seedu.address.model.hirelah.exceptions.IllegalActionException;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * NavigationQuestionCommand describes the behavior when the
 * client wants to navigate to a certain remark of the interview.
 */
public class NavigationQuestionCommand extends Command {
    public static final String COMMAND_WORD = "to";
    public static final String MESSAGE_NAVIGATION_QUESTION_SUCCESS = "Here is the remark of question %s!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Navigate to a particular answer of a question from an interviewee.\n"
            + "Parameters: questionNumber\n"
            + "Example:  " + COMMAND_WORD + " q10";

    private final String questionIndex;

    public NavigationQuestionCommand(String questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCurrentInterviewee()) {
            throw new CommandException("You need to open a transcript of a particular interviewee " +
                    "to go to the answer of a question.");
        }
        Optional<Transcript> transcriptOfCurrentInterviewee = model.getCurrentInterviewee().getTranscript();
        try {
            int index = transcriptOfCurrentInterviewee
                    .orElseThrow(() -> new CommandException(
                            String.format("Interviewee %1$s has not been interviewed.",
                                    model.getCurrentInterviewee().getFullName())))
                    .getIndexOfQuestion(questionIndex);
            return new NavigationCommandResult(String.format(MESSAGE_NAVIGATION_QUESTION_SUCCESS,
                    questionIndex), ToggleView.TRANSCRIPT, index);
        } catch (IllegalValueException | IllegalActionException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NavigationQuestionCommand);
    }
}
