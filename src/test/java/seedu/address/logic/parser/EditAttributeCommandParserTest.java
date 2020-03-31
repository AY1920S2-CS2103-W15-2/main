package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtility.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAttributeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class EditAttributeCommandParserTest {

    private EditAttributeCommandParser parser = new EditAttributeCommandParser();

    @Test
    void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, WHITESPACE + VALID_ATTRIBUTE_TEAM_WORK
                        + WHITESPACE + PREFIX_ATTRIBUTE
                        + WHITESPACE + VALID_ATTRIBUTE_PERSISTENCE,
                new EditAttributeCommand("team work", "persistence"));
    }

    @Test
    void parse_oldFieldMissing_failure() {
        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + PREFIX_OLD
                        + WHITESPACE + PREFIX_NEW
                        + WHITESPACE + VALID_INTERVIEWEE_JANICE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAttributeCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_newFieldMissing_failure() {
        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + PREFIX_OLD
                        + WHITESPACE + VALID_INTERVIEWEE_JANE
                        + WHITESPACE + PREFIX_NEW,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAttributeCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_oldPrefixMissing_failure() {
        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + VALID_INTERVIEWEE_JANE
                        + WHITESPACE + PREFIX_NEW,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAttributeCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_newPrefixMissing_failure() {
        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + PREFIX_OLD
                        + WHITESPACE + VALID_INTERVIEWEE_JANICE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAttributeCommand.MESSAGE_USAGE));
    }
}
