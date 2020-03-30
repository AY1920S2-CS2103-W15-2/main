package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ModelUtil.validateFinalisation;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.QuestionList;

/**
 * DeleteQuestionCommand describes the behavior when the
 * client wants to delete a question from the list.
 */

public class DeleteQuestionCommand extends Command {
    public static final String COMMAND_WORD = "question";
    public static final boolean DESIRED_MODEL_FINALIZED_STATE = false;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the question identified by its index number in the question list.\n"
            + "Parameters: INDEX\n"
            + "Example: delete " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_QUESTION_SUCCESS = "Deleted Question: %1$s";

    private final int questionIndex;

    public DeleteQuestionCommand(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        validateFinalisation(model, DESIRED_MODEL_FINALIZED_STATE);
        QuestionList questions = model.getQuestionList();
        try {
            return new CommandResult(String.format(MESSAGE_DELETE_QUESTION_SUCCESS, questions.delete(questionIndex)),
                    ToggleView.QUESTION);
        } catch (IllegalValueException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteQuestionCommand // instanceof handles nulls
                && questionIndex == (((DeleteQuestionCommand) other).questionIndex)); // state check
    }
}
