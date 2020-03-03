package seedu.address.model.hirelah;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.hirelah.exceptions.IllegalActionException;

class IntervieweeListTest {

    @Test
    void addInterviewee_validNames_intervieweeAdded() throws IllegalValueException, IllegalActionException {
        IntervieweeList interviewees = new IntervieweeList();
        interviewees.addInterviewee("Sarah O'Conner");
        interviewees.addInterviewee("King Henry the 3rd");
        interviewees.addInterviewee("-155"); // Allow anything as long as it is not parsed by Integer#parseUnsignedInt
        assertEquals("Sarah O'Conner", interviewees.getInterviewee("1").getFullName());
        assertEquals("King Henry the 3rd", interviewees.getInterviewee("2").getFullName());
    }

    @Test
    void addInterviewee_invalidNames_exceptionThrown() {
        IntervieweeList interviewees = new IntervieweeList();
        try {
            interviewees.addInterviewee("15"); // numeric names are disallowed
        } catch (IllegalValueException e) {
            assertEquals(Interviewee.MESSAGE_CONSTRAINTS, e.getMessage());
        }
        try {
            interviewees.addInterviewee(""); // empty strings are disallowed
        } catch (IllegalValueException e) {
            assertEquals(Interviewee.MESSAGE_CONSTRAINTS, e.getMessage());
        }
        assertFalse(interviewees.iterator().hasNext()); // Should still be empty
    }

    @Test
    void addIntervieweeWithAlias_validNameInvalidAlias_exceptionThrownButIntervieweeAdded()
            throws IllegalActionException {
        IntervieweeList interviewees = new IntervieweeList();
        try {
            interviewees.addInterviewee("Bob");
            interviewees.addIntervieweeWithAlias("Tom", "Bob");
            fail();
        } catch (IllegalValueException e) {
            assertEquals("An Interviewee with this name or alias already exists!", e.getMessage());
        }
        assertDoesNotThrow(() -> interviewees.getInterviewee("Tom"));
    }

    @Test
    void addAlias_duplicateName_exceptionThrown() throws IllegalValueException, IllegalActionException {
        IntervieweeList interviewees = new IntervieweeList();
        interviewees.addInterviewee("Bob");
        interviewees.addInterviewee("Tom");
        try {
            interviewees.addAlias("Tom", "Bob");
            fail();
        } catch (IllegalValueException e) {
            assertEquals("An Interviewee with this name or alias already exists!", e.getMessage());
        }
    }

    @Test
    void deleteInterviewee_validIdentifier_intervieweeDeleted() throws IllegalActionException, IllegalValueException {
        IntervieweeList interviewees = new IntervieweeList();
        interviewees.addInterviewee("Bob");
        interviewees.addIntervieweeWithAlias("Tom", "T");
        interviewees.deleteInterviewee("1"); // delete Bob by id
        assertThrows(IllegalActionException.class, () -> interviewees.getInterviewee("Bob"));
        interviewees.deleteInterviewee("T"); // delete Tom by alias
        assertThrows(IllegalActionException.class, () -> interviewees.getInterviewee("Tom"));
    }

    @Test
    void getInterviewee_idAndFullName_sameIntervieweeReturned() throws IllegalValueException, IllegalActionException {
        IntervieweeList interviewees = new IntervieweeList();
        interviewees.addInterviewee("Hello World!");
        Interviewee a = interviewees.getInterviewee("1");
        Interviewee b = interviewees.getInterviewee("Hello World!");
        assertEquals(a, b);
    }

    @Test
    void iterator_increasingIdOrder() throws IllegalValueException, IllegalActionException {
        IntervieweeList interviewees = new IntervieweeList();
        interviewees.addInterviewee("A");
        interviewees.addInterviewee("B");
        interviewees.addInterviewee("C");
        interviewees.addInterviewee("D");
        interviewees.addInterviewee("E");
        interviewees.deleteInterviewee("D");
        int previous = 0;
        for (Interviewee i : interviewees) {
            assertTrue(i.getId() > previous);
            previous = i.getId();
        }
    }
}