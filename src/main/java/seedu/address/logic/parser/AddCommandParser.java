package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAttributeCommand;
import seedu.address.logic.commands.AddIntervieweeCommand;
import seedu.address.logic.commands.AddMetricCommand;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new object of type AddCommand
 */
public class AddCommandParser implements Parser<Command> {

    public static final String EXPECTED_INPUT_FORMAT =
            AddAttributeCommand.MESSAGE_FORMAT + AddAttributeCommand.MESSAGE_FUNCTION
            + AddIntervieweeCommand.MESSAGE_FORMAT + AddIntervieweeCommand.MESSAGE_FUNCTION
            + AddMetricCommand.MESSAGE_FORMAT + AddMetricCommand.MESSAGE_FUNCTION
            + AddQuestionCommand.MESSAGE_FORMAT + AddQuestionCommand.MESSAGE_FUNCTION;

    private static final Pattern BASIC_ADD_COMMAND_FORMAT =
            Pattern.compile("(?<addCommandWord>\\S+)(?<addArguments>.*)");

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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EXPECTED_INPUT_FORMAT));
        }

        final String addCommandWord = matcher.group("addCommandWord");
        final String addArguments = matcher.group("addArguments");

        switch (addCommandWord) {
        case AddAttributeCommand.COMMAND_WORD:
            ParserUtil.checkEmptyArgument(AddAttributeCommand.MESSAGE_USAGE);
            return new AddAttributeCommand(addArguments.trim());

        case AddIntervieweeCommand.COMMAND_WORD:
            return new AddIntervieweeCommandParser().parse(addArguments.trim());

        case AddQuestionCommand.COMMAND_WORD:
            ParserUtil.checkEmptyArgument(AddQuestionCommand.MESSAGE_USAGE);
            return new AddQuestionCommand(addArguments.trim());

        case AddMetricCommand.COMMAND_WORD:
            return new AddMetricCommandParser().parse(addArguments);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EXPECTED_INPUT_FORMAT));
        }
    }

}
