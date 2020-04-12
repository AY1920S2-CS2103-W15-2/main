package hirelah.logic.parser;

import static hirelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import hirelah.logic.commands.GenerateReportCommand;
import hirelah.logic.parser.exceptions.ParseException;

/**
 * Parses the GenerateReportCommand with the identifier.
 */
public class GenerateReportCommandParser implements Parser<GenerateReportCommand> {

    /**
     * Parses the given {@code String} of identifier in the context of the GenerateReportCommand
     * and returns an GenerateReportCommand object for execution.
     *
     * @param arguments the identifier of the Interviewee
     * @throws ParseException if the user input is not a valid command word
     */
    public GenerateReportCommand parse(String arguments) throws ParseException {

        if (arguments.trim().equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateReportCommand.MESSAGE_USAGE));
        }
        return new GenerateReportCommand(arguments.trim());
    }
}