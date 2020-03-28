package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ToggleView;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.hirelah.Interviewee;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private IntervieweeListPanel intervieweeListPanel;
    private IntervieweeListPanel bestNIntervieweesPanel;
    private RemarkListPanel remarkListPanel;
    private AttributeListPanel attributeListPanel;
    private DetailedIntervieweeCard detailedIntervieweeCard;
    private HelpWindow helpWindow;
    private MetricListPanel metricListPanel;
    private QuestionListPanel questionListPanel;
    private ResultDisplay resultDisplay;

    // On startup, HireLah shows the list of interviewees
    private ToggleView toggleView = ToggleView.INTERVIEWEE;

    private Interviewee currentInterviewee;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelStackPane;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        attributeListPanel = new AttributeListPanel(logic.getAttributeListView());
        helpWindow = new HelpWindow();
        intervieweeListPanel = new IntervieweeListPanel(logic.getFilteredIntervieweeListView());
        bestNIntervieweesPanel = new IntervieweeListPanel(logic.getBestNIntervieweesView());
        attributeListPanel = new AttributeListPanel(logic.getAttributeListView());
        metricListPanel = new MetricListPanel(logic.getMetricListView());
        questionListPanel = new QuestionListPanel(logic.getQuestionListView());

        this.currentInterviewee = null;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        listPanelStackPane.getChildren().add(intervieweeListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getSessionsDirectory());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Sets what is displayed in the listPanelStackPane based on the toggle.
     *
     * @param toggleView enum representing what should be displayed
     */
    @FXML
    public void handleToggle(ToggleView toggleView) {
        if (this.toggleView == toggleView && this.toggleView != ToggleView.TRANSCRIPT) {
            return;
        }
        this.toggleView = toggleView;

        listPanelStackPane.getChildren().clear();
        switch (toggleView) {
        case ATTRIBUTE: // attribute
            listPanelStackPane.getChildren().add(attributeListPanel.getRoot());
            break;
        case INTERVIEWEE: // interviewee
            listPanelStackPane.getChildren().add(intervieweeListPanel.getRoot());
            break;

        case METRIC: // metrics
            listPanelStackPane.getChildren().add(metricListPanel.getRoot());
            break;
        case QUESTION: // questions
            listPanelStackPane.getChildren().add(questionListPanel.getRoot());
            break;
        case TRANSCRIPT: // transcript
            Interviewee currentInterviewee = logic.getCurrentInterviewee();
            if (currentInterviewee.equals(this.currentInterviewee)) {
                break;
            }
            // remarkListPanel = new RemarkListPanel(FXCollections
            //         .unmodifiableObservableList(logic.getCurrentInterviewee()
            //         .getTranscript()
            //         .get()
            //         .getRemarkList()));
            remarkListPanel = new RemarkListPanel(null);
            detailedIntervieweeCard = new DetailedIntervieweeCard(currentInterviewee);
            listPanelStackPane.getChildren().addAll(remarkListPanel.getRoot(), detailedIntervieweeCard.getRoot());
            StackPane.setAlignment(detailedIntervieweeCard.getRoot(), Pos.TOP_CENTER);
            break;
        case BEST_INTERVIEWEE:
            bestNIntervieweesPanel = new IntervieweeListPanel(logic.getBestNIntervieweesView());
            listPanelStackPane.getChildren().add(bestNIntervieweesPanel.getRoot());
            break;
        default:
            break;
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }


    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }


    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, IllegalValueException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            handleToggle(commandResult.getToggleView());

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
