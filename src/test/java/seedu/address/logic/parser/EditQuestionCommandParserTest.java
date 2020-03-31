package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.EditAttributeCommand;
import seedu.address.logic.commands.EditQuestionCommand;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtility.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class EditQuestionCommandParserTest {

    private EditQuestionCommandParser parser = new EditQuestionCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, WHITESPACE + VALID_NUMBER_1
                        + WHITESPACE + PREFIX_QUESTION
                        + WHITESPACE + VALID_QUESTION_WHAT,
                new EditQuestionCommand(1, VALID_QUESTION_WHAT));
    }

    @Test
    void parse_oldFieldMissing_failure() {
        assertParseFailure(parser, WHITESPACE + PREFIX_QUESTION
                        + WHITESPACE + VALID_INTERVIEWEE_JANICE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditQuestionCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_newFieldMissing_failure() {
        assertParseFailure(parser, WHITESPACE + VALID_NUMBER_1
                        + WHITESPACE + PREFIX_QUESTION
                        + WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditQuestionCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_prefixMissing_failure() {
        assertParseFailure(parser, WHITESPACE + VALID_NUMBER_1
                        + WHITESPACE + VALID_QUESTION_WHAT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditQuestionCommand.MESSAGE_USAGE));
    }
}
