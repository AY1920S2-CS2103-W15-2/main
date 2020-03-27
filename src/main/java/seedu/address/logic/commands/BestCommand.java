package seedu.address.logic.commands;

import java.util.Comparator;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.hirelah.Attribute;
import seedu.address.model.hirelah.AttributeList;
import seedu.address.model.hirelah.BestParameter;
import seedu.address.model.hirelah.Interviewee;
import seedu.address.model.hirelah.MetricList;

/**
 * BestCommand describes the behavior of the command
 * that sorts the interviewees based on a certain parameter.
 */

public class BestCommand extends Command {
    public static final String COMMAND_WORD = "best";
    public static final String MESSAGE_SIZE_NOT_A_NUMBER = "The size of the interviewees provided is not a number.";
    public static final String MESSAGE_NON_POSITIVE_SIZE = "The size of the interviewees provided must be positive.";
    public static final String MESSAGE_PARAM_NOT_FOUND = "The param prefix provided: %s is not found.";
    public static final String MESSAGE_SUCCESS = "Here are the best %s interviewees based on %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds best N candidates from the list.\n"
            + "Parameters: "
            + " NUMBER_OF_INTERVIEWEES [-a ATTRIBUTE_PREFIX] [-m METRIC_PREFIX]\n"
            + "e.g. best 3 -a lea";

    private final String numberOfInterviewees;
    private final String paramPrefix;
    private final BestParameter paramType;

    public BestCommand(String numberOfInterviewees, BestParameter paramType) {
        this.numberOfInterviewees = numberOfInterviewees;
        this.paramPrefix = "";
        this.paramType = paramType;
    }

    public BestCommand(String numberOfInterviewees, String paramPrefix, BestParameter paramType) {
        this.numberOfInterviewees = numberOfInterviewees;
        this.paramPrefix = paramPrefix;
        this.paramType = paramType;
    }

    public CommandResult execute(Model model) throws CommandException {
        int size = parseNumberOfInterviewees(numberOfInterviewees);
        Comparator<Interviewee> comparator;
        try {
            switch (paramType) {
            case METRIC:
                comparator = getComparatorByMetric(model.getMetricList(), paramPrefix);
                break;
            case ATTRIBUTE:
                comparator = getComparatorByAttribute(model.getAttributeList(), paramPrefix);
                break;
            default:
                comparator = getOverallComparator(model.getAttributeList());
            }

            ObservableList<Interviewee> observableInterviewees = model.getIntervieweeList().getObservableList();
            ObservableList<Interviewee> sortedObservableInterviewees = getBestN(observableInterviewees, comparator,
                    size);
            return new CommandResult(MESSAGE_SUCCESS);

        } catch (IllegalValueException e) {
            throw new CommandException(String.format(MESSAGE_PARAM_NOT_FOUND, paramPrefix));
        }
    }

    private ObservableList<Interviewee> getBestN(ObservableList<Interviewee> observableInterviewees,
                                                 Comparator<Interviewee> comparator, int size) {
        SortedList<Interviewee> sorted = new SortedList<>(observableInterviewees, comparator);
        ObservableList<Interviewee> interviewees = FXCollections.observableArrayList(
                interviewee -> new Observable[] {
                        interviewee.fullNameProperty(),
                        interviewee.aliasProperty(),
                        interviewee.resumeProperty(),
                        interviewee.transcriptProperty()
                });

        for (int i = 0; i < size; i++) {
            interviewees.add(sorted.get(i));
        }
        return interviewees;
    }

    /**
     * Gets the Interviewee Comparator based on the Metric provided.
     * @param metrics The metric list.
     * @param paramPrefix The prefix of the metric name.
     * @return The corresponding comparator.
     * @throws IllegalValueException If the prefix of the metric is not found or there are multiple metrics with the
     * same prefix.
     */
    private Comparator<Interviewee> getComparatorByMetric(MetricList metrics, String paramPrefix)
            throws IllegalValueException {
        return metrics.find(paramPrefix).getComparator();
    }

    /**
     * Gets the Interviewee Comparator based on the Attribute provided.
     * @param attributes The attribute list.
     * @param paramPrefix The prefix of the attribute name.
     * @return The corresponding comparator.
     * @throws IllegalValueException If the prefix of the attribute is not found or there are multiple attributes with
     * the same prefix.
     */
    private Comparator<Interviewee> getComparatorByAttribute(AttributeList attributes, String paramPrefix)
            throws IllegalValueException {
        Attribute attribute = attributes.find(paramPrefix);
        return (interviewee, anotherInterviewee) -> {
            double totalScoreFirst = interviewee.getScore(attribute);
            double totalScoreSecond = anotherInterviewee.getScore(attribute);
            return totalScoreSecond - totalScoreFirst < 0 ? -1 : totalScoreFirst == totalScoreSecond ? 0 : 1;
        };
    }

    /**
     * Gets the Interviewee Comparator based on their overall performances.
     * @param attributes The attribute list.
     * @return The corresponding comparator.
     */
    private Comparator<Interviewee> getOverallComparator(AttributeList attributes) {
        return (interviewee, anotherInterviewee) -> {
            double totalScoreFirst = getOverallScore(attributes, interviewee);
            double totalScoreSecond = getOverallScore(attributes, anotherInterviewee);
            return totalScoreSecond - totalScoreFirst < 0 ? -1 : totalScoreFirst == totalScoreSecond ? 0 : 1;
        };
    }

    /**
     * Computes the total overall score of an interviewee.
     * @param attributes The attribute list.
     * @param interviewee The interviewee.
     * @return The overall score of the interviewee.
     */
    private double getOverallScore(AttributeList attributes, Interviewee interviewee) {
        ObservableList<Attribute> observableList = attributes.getObservableList();

        double totalScore = 0;
        for (Attribute attribute: observableList) {
            totalScore += interviewee.getScore(attribute);
        }
        return totalScore;
    }

    /**
     * Parses the number of interviewees entered by the client to the corresponding integer.
     * @param numberOfInterviewees The String representing the size of the interviewees entered by the client.
     * @return The corresponding integer value.
     * @throws CommandException If the value entered is not a number or the number is less than or equal to zero.
     */
    private int parseNumberOfInterviewees(String numberOfInterviewees) throws CommandException {
        try {
            int result = Integer.parseInt(numberOfInterviewees);
            if (result <= 0) {
                throw new CommandException(MESSAGE_NON_POSITIVE_SIZE);
            }

            return result;
        } catch (NumberFormatException e) {
            throw new CommandException(MESSAGE_SIZE_NOT_A_NUMBER);
        }
    }
}
