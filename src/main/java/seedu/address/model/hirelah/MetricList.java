package seedu.address.model.hirelah;

import java.util.ArrayList;

import seedu.address.commons.exceptions.IllegalValueException;

/*
 * MetricList
 *
 * CS2103 AY19/20 Semester 2
 * Team Project
 * HireLah!
 *
 * 01 Mar 2020
 *
 */

/**
 * <p>MetricList class manages the list of metrics that have been
 * added by the interviewer. MetricList can retrieve the attributes using
 * the prefix.</p>
 * @author AY1920S2-W15-2
 */

public class MetricList {
    private static final String DUPLICATE_MESSAGE = "There are multiple metrics with the same prefix.";
    private static final String NOT_FOUND_MESSAGE = "No metrics with the entered prefix.";

    private ArrayList<Metric> metrics;

    /**
     * Constructs a MetricList instance.
     */
    public MetricList() {
        this.metrics = new ArrayList<>();
    }

    /**
     * Adds the metric to the list.
     * @param metricName The metric name.
     * @return The message outcome.
     */

    public String add(String metricName) throws IllegalValueException {
        try {
            Metric metric = new Metric(metricName);
            boolean isDuplicate = isDuplicate(metric);

            if (isDuplicate) {
                throw new IllegalValueException("This attribute is already exists!");
            }

            metrics.add(metric);
            return String.format("Successfully added metric: %s", metric);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Find the metric based on its prefix.
     * @param metricPrefix The prefix of the metric.
     * @return The corresponding Attribute instance.
     * @throws IllegalValueException if the prefix can be multi-interpreted or no such Metric found.
     */

    public Metric find(String metricPrefix) throws IllegalValueException {
        checkPrefix(metricPrefix);
        return metrics.stream().filter(metric -> metric.toString().startsWith(metricPrefix))
                               .findFirst()
                               .get();
    }

    /**
     * Deletes the metric by its prefix.
     * @param metricPrefix The prefix of the attribute.
     * @return The outcome message.
     * @throws IllegalValueException if the prefix can be multi-interpreted or no such Attribute found.
     */

    public String delete(String metricPrefix) throws IllegalValueException {
        Metric metric = find(metricPrefix);
        metrics.remove(metric);
        return String.format("Successfully removed metric: %s", metric);
    }

    /**
     * Checks the number of metrics that starts with the prefix.
     * @param metricPrefix The prefix of the attribute.
     * @throws IllegalValueException if the prefix can be multi-interpreted or no such Metric found.
     */

    private void checkPrefix(String metricPrefix) throws IllegalValueException {
        long startWithPrefix = metrics.stream()
                .filter(metric -> metric.toString().startsWith(metricPrefix))
                .count();

        if (startWithPrefix > 1) {
            throw new IllegalValueException(DUPLICATE_MESSAGE);
        } else if (startWithPrefix == 0) {
            throw new IllegalValueException(NOT_FOUND_MESSAGE);
        }
    }

    private boolean isDuplicate(Metric metric) {
        return metrics.stream().anyMatch(metric::equals);
    }
}
