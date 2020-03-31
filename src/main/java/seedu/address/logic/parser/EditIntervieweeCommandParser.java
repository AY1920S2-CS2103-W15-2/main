package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.EditIntervieweeCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditIntervieweeCommand object
 */
public class EditIntervieweeCommandParser implements Parser<EditIntervieweeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditIntervieweeCommand
     * and returns an EditIntervieweeCommand object for execution.
     *
     * @param arguments the arguments to be parsed
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditIntervieweeCommand parse(String arguments) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_ALIAS, PREFIX_NAME);

        checkArgument(argMultimap);
        return new EditIntervieweeCommand(
                argMultimap.getPreamble(),
                argMultimap.getValue(PREFIX_NAME).orElse(""),
                argMultimap.getValue(PREFIX_ALIAS).orElse("")
        );
    }

    /**
     * Checks whether the argument is satisfied or not.
     * @param argMultimap The argument multimap.
     * @throws ParseException If the argument is incomplete.
     */
    private void checkArgument(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getPreamble().equals("")
            || !argMultimap.arePrefixesPresent(PREFIX_NAME) && !argMultimap.arePrefixesPresent(PREFIX_ALIAS)
            || (argMultimap.getValue(PREFIX_ALIAS).isPresent() && argMultimap.getValue(PREFIX_ALIAS).get().equals(""))
            || (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_NAME).get().equals(""))
        ) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIntervieweeCommand.MESSAGE_USAGE));
        }
    }
}
