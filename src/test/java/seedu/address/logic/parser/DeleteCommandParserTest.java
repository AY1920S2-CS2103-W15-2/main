package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtility.VALID_ATTRIBUTE_PERSISTENCE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_INTERVIEWEE_JANE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_PROPERTY_ATTRIBUTE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_PROPERTY_INTERVIEWEE;
//import static seedu.address.logic.commands.CommandTestUtility.VALID_PROPERTY_QUESTION;
import static seedu.address.logic.commands.CommandTestUtility.WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.commands.DeleteIntervieweeCommand;


class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + VALID_INTERVIEWEE_JANE,
                new DeleteIntervieweeCommand("Jane Doe"));

        assertParseSuccess(parser, WHITESPACE
                        + VALID_PROPERTY_ATTRIBUTE
                        + WHITESPACE
                        + VALID_ATTRIBUTE_PERSISTENCE,
                new DeleteAttributeCommand("persistence"));
    }

    //    @Test
    //    void parse_compulsoryFieldsMissing_success() {
    //
    //        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE + WHITESPACE,
    //                MESSAGE_UNKNOWN_COMMAND);
    //
    //        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_ATTRIBUTE + WHITESPACE,
    //                MESSAGE_UNKNOWN_COMMAND);
    //
    //        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_QUESTION + WHITESPACE,
    //                MESSAGE_UNKNOWN_COMMAND);
    //    }

    @Test
    public void parse_argumentMissing_failure() {
        assertParseFailure(parser, WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommandParser.EXPECTED_INPUT_FORMAT));
    }
}
