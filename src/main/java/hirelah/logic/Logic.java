package hirelah.logic;

import java.nio.file.Path;

import hirelah.commons.core.GuiSettings;
import hirelah.logic.commands.CommandResult;
import hirelah.logic.commands.exceptions.CommandException;
import hirelah.logic.parser.exceptions.ParseException;
import hirelah.model.hirelah.Attribute;
import hirelah.model.hirelah.Interviewee;
import hirelah.model.hirelah.IntervieweeToScore;
import hirelah.model.hirelah.Metric;
import hirelah.model.hirelah.Question;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the list of attributes */
    ObservableList<Attribute> getAttributeListView();

    /** Returns an unmodifiable view of the list of interviewees */
    ObservableList<Interviewee> getIntervieweeListView();

    /** Returns an unmodifiable view of the list of questions */
    ObservableList<Question> getQuestionListView();

    /** Returns an unmodifiable view of the list of metrics */
    ObservableList<Metric> getMetricListView();

    /** Returns the Interviewee currently being looked at*/
    Interviewee getCurrentInterviewee();

    /**
     * Sets the currentInterviewee.
     */
    void setCurrentInterviewee(Interviewee interviewee);

    ObservableList<IntervieweeToScore> getBestNIntervieweesView();

    /**
     * Returns the user prefs' sessions directory.
     */
    Path getSessionsDirectory();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
