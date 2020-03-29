package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<Command> {

    private static final String INPUT_WORD = "delete";

    public static final String INPUT_FORMAT = INPUT_WORD + ": Deletes the properties of an interview session "
            + "Includes: "
            + "deleting an Interviewee object"
            + "deleting an Attribute object"
            + "deleting a Question object";

    private static final Pattern BASIC_DELETE_COMMAND_FORMAT =
            Pattern.compile("(?<deleteCommandWord>\\S+) (?<deleteArguments>.+)");

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, INPUT_FORMAT));
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
            return new DeleteQuestionCommand(deleteArguments.trim());

        case DeleteMetricCommand.COMMAND_WORD:
            return new DeleteMetricCommand(deleteArguments.trim());

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND.trim());
        }
    }

}
