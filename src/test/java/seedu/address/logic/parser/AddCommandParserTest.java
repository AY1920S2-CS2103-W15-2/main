package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtility.VALID_ATTRIBUTE_PERSISTENCE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_INTERVIEWEE_JANE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_PROPERTY_ATTRIBUTE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_PROPERTY_INTERVIEWEE;
import static seedu.address.logic.commands.CommandTestUtility.VALID_PROPERTY_QUESTION;
import static seedu.address.logic.commands.CommandTestUtility.VALID_QUESTION_WHAT;
import static seedu.address.logic.commands.CommandTestUtility.WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAttributeCommand;
import seedu.address.logic.commands.AddIntervieweeCommand;
import seedu.address.logic.commands.AddQuestionCommand;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + VALID_INTERVIEWEE_JANE,
                new AddIntervieweeCommand("Jane Doe"));

        assertParseSuccess(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE
                        + WHITESPACE + VALID_INTERVIEWEE_JANE
                        + WHITESPACE + PREFIX_ALIAS
                        + WHITESPACE + "Jane",
                new AddIntervieweeCommand("Jane Doe", "Jane"));

        assertParseSuccess(parser, WHITESPACE
                        + VALID_PROPERTY_ATTRIBUTE
                        + WHITESPACE
                        + VALID_ATTRIBUTE_PERSISTENCE,
                new AddAttributeCommand("persistence"));

        assertParseSuccess(parser, WHITESPACE
                        + VALID_PROPERTY_QUESTION
                        + WHITESPACE
                        + VALID_QUESTION_WHAT,
                new AddQuestionCommand("What is this question?"));
    }

    @Test
    void parse_compulsoryFieldsMissing_success() {

        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_INTERVIEWEE + WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandParser.INPUT_FORMAT));

        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_ATTRIBUTE + WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandParser.INPUT_FORMAT));

        assertParseFailure(parser, WHITESPACE + VALID_PROPERTY_QUESTION + WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandParser.INPUT_FORMAT));
    }

    @Test
    public void parse_argumentMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommandParser.INPUT_FORMAT);
        assertParseFailure(parser, WHITESPACE, expectedMessage);
    }
}
