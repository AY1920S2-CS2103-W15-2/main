package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtility.*;
import static seedu.address.logic.parser.CliSyntax.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hirelah.BestParameter;

class NormalParserTest {

    private NormalParser parser = new NormalParser();

    @Test
    void parse_validAddCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_ADD + WHITESPACE
                + VALID_PROPERTY_INTERVIEWEE + WHITESPACE + VALID_INTERVIEWEE_JANE);
        assertEquals(result, new AddIntervieweeCommand("Jane Doe"));
    }

    @Test
    void parse_validEditCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_EDIT + WHITESPACE
                + VALID_PROPERTY_INTERVIEWEE + WHITESPACE
                + VALID_INTERVIEWEE_JANE + WHITESPACE
                + PREFIX_NAME + VALID_INTERVIEWEE_JANICE);
        assertEquals(result, new EditIntervieweeCommand("Jane Doe", "Janice Doe", ""));
    }

    @Test
    void parse_validDeleteCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_DELETE + WHITESPACE
                + VALID_PROPERTY_INTERVIEWEE + WHITESPACE + VALID_INTERVIEWEE_JANE);
        assertEquals(result, new DeleteIntervieweeCommand("Jane Doe"));
    }

    @Test
    void parse_validOpenCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_OPEN + WHITESPACE
                + VALID_INTERVIEWEE_JANE);
        assertEquals(result, new OpenReportCommand("Jane Doe"));
    }

    @Test
    void parse_validFinaliseCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_FINALISE);
        assertEquals(result, new FinaliseCommand());
    }

    @Test
    void parse_validListCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_LIST
                + WHITESPACE + VALID_PROPERTY_INTERVIEWEE);
        assertEquals(result, new ListIntervieweeCommand());
    }

    @Test
    void parse_validNavigationCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_GOTO + WHITESPACE + VALID_QUESTION_NUMBER_14);
        assertEquals(result, new NavigationQuestionCommand(14));
    }

    @Test
    void parse_validStartInterviewCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_INTERVIEW + WHITESPACE + VALID_INTERVIEWEE_JANE);
        assertEquals(result, new StartInterviewCommand("Jane Doe"));
    }

    @Test
    void parse_validExitCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_EXIT);
        assertEquals(result, new ExitCommand());
    }

    @Test
    void parse_validHelpCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_HELP);
        assertEquals(result, new HelpCommand());
    }

    @Test
    void parse_validOpenResumeCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_RESUME + WHITESPACE + VALID_INTERVIEWEE_JANE);
        assertEquals(result, new OpenResumeCommand("Jane Doe"));
    }

    @Test
    void parse_validBestCommand_success() throws ParseException {
        Command result = parser.parseCommand(VALID_COMMAND_BEST + WHITESPACE + VALID_NUMBER_1);
        assertEquals(result, new BestCommand("1", BestParameter.OVERALL));
    }

    @Test
    void parse_invalidCommand_failure() {
        assertThrows(ParseException.class, () -> parser.parseCommand(VALID_ATTRIBUTE_INTEGRITY + WHITESPACE
                + VALID_PROPERTY_INTERVIEWEE + WHITESPACE
                + PREFIX_OLD + WHITESPACE + VALID_INTERVIEWEE_JANE
                + WHITESPACE + PREFIX_NEW
                + WHITESPACE + VALID_INTERVIEWEE_JANICE));
    }
}
