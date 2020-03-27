package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/*
 * NormalParser
 *
 * CS2103 AY19/20 Semester 2
 * Team Project
 * HireLah!
 *
 * 13 Mar 2020
 *
 */

/**
 * NormalParser parses the input entered by the client
 * when HireLah! is not in interviewing mode.
 */


public class NormalParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final HashMap<String, CommandSupplier> suppliers = new HashMap<>() {
        {
            put("add", args -> new AddCommandParser().parse(args));
            put("edit", args -> new EditCommandParser().parse(args));
            put("delete", args -> new DeleteCommandParser().parse(args));
            put("list", args -> new ListCommandParser().parse(args));
            put("finalise", args -> new FinaliseCommandParser().parse(args));
            put("open", args -> new OpenReportCommandParser().parse(args));
            put("best", args -> new BestCommandParser().parse(args));
        }
    };

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        if (suppliers.containsKey(commandWord)) {
            return suppliers.get(commandWord).getCommand(arguments);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
