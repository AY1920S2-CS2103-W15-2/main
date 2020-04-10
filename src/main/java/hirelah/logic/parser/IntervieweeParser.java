package hirelah.logic.parser;

import static hirelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hirelah.logic.parser.CliSyntax.PREFIX_BEST;

import java.util.Optional;

import hirelah.logic.commands.BestCommand;
import hirelah.logic.commands.Command;
import hirelah.logic.commands.ListIntervieweeCommand;
import hirelah.logic.parser.exceptions.ParseException;

public class IntervieweeParser {
    private static final String EMPTY_STRING = "";
    private static final String TEMPLATE = "%s\n%s";

    public Command parse(String arguments) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_BEST);

        if (arguments.equals("")) {
            return new ListIntervieweeCommand();
        } else if (argMultimap.arePrefixesPresent(PREFIX_BEST)) {
            Optional<String> args = argMultimap.getValue(PREFIX_BEST);
            return new BestCommandParser().parse(args.isEmpty() ? EMPTY_STRING : args.get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(TEMPLATE, ListIntervieweeCommand.MESSAGE_USAGE, BestCommand.MESSAGE_USAGE)));
        }
    }
}
