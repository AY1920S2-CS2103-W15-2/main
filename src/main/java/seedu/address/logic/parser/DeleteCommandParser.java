package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.commands.DeleteIntervieweeCommand;
import seedu.address.logic.commands.DeleteMetricCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<Command> {
    private static final Pattern BASIC_DELETE_COMMAND_FORMAT =
            Pattern.compile("(?<deleteCommandWord>\\S+) (?<deleteArguments>.+)");
    private static final String INDEX_NOT_A_NUMBER = "The index is not a number.";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @param arguments the arguments to be parsed
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String arguments) throws ParseException {
        Matcher matcher = BASIC_DELETE_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
        final String deleteCommandWord = matcher.group("deleteCommandWord");
        final String deleteArguments = matcher.group("deleteArguments");

        switch (deleteCommandWord) {
        case DeleteAttributeCommand.COMMAND_WORD:
            ParserUtil.checkEmptyArgument(DeleteAttributeCommand.MESSAGE_USAGE);
            return new DeleteAttributeCommand(deleteArguments.trim());

        case DeleteIntervieweeCommand.COMMAND_WORD:
            return new DeleteIntervieweeCommand(deleteArguments.trim());

        case DeleteQuestionCommand.COMMAND_WORD:
            ParserUtil.checkEmptyArgument(DeleteQuestionCommand.MESSAGE_USAGE);

            try {
                int index = Integer.parseInt(deleteArguments.trim());
                return new DeleteQuestionCommand(index);
            } catch (NumberFormatException e) {
                throw new ParseException(INDEX_NOT_A_NUMBER);
            }

        case DeleteMetricCommand.COMMAND_WORD:
            return new DeleteMetricCommand(deleteArguments.trim());

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
