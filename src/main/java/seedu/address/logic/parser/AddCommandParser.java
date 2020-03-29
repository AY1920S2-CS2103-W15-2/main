package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new object of type AddCommand
 */
public class AddCommandParser implements Parser<Command> {

    public static final String INPUT_WORD = "add";

    public static final String INPUT_FORMAT = INPUT_WORD + ": Adds a property to the current interview session.\n"
            + "Includes:\n"
            + "adding an Interviewee object\n"
            + "adding an Attribute object\n"
            + "adding a Question object\n";

    private static final Pattern BASIC_ADD_COMMAND_FORMAT =
            Pattern.compile("(?<addCommandWord>\\S+) (?<addArguments>.+)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param arguments the arguments to be parsed
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String arguments) throws ParseException {
        Matcher matcher = BASIC_ADD_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, INPUT_FORMAT));
        }

        final String addCommandWord = matcher.group("addCommandWord");
        final String addArguments = matcher.group("addArguments");

        switch (addCommandWord) {
        case AddAttributeCommand.COMMAND_WORD:
            if (ParserUtil.isEmptyArgument(arguments)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttributeCommand.MESSAGE_USAGE));
            }
            return new AddAttributeCommand(addArguments.trim());

        case AddIntervieweeCommand.COMMAND_WORD:
            return new AddIntervieweeCommandParser().parse(addArguments.trim());

        case AddQuestionCommand.COMMAND_WORD:
            if (ParserUtil.isEmptyArgument(arguments)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddQuestionCommand.MESSAGE_USAGE));
            }
            return new AddQuestionCommand(addArguments.trim());

        case AddMetricCommand.COMMAND_WORD:
            return new AddMetricCommandParser().parse(addArguments.trim());

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
