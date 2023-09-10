package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.FileReader;
import cs3500.pa05.model.OutputFile;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.records.JsonUtils;
import cs3500.pa05.model.records.WeekJson;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a controller for the journal program.
 */
public class PlannerController implements JournalController {

  private Week week = new Week();

  @FXML
  private MenuItem addEventMenuItem;
  @FXML
  private Button submitEvent;

  @FXML
  private MenuItem templateMenuItem;

  @FXML
  private MenuItem newWeekMenuItem;

  @FXML
  private MenuItem addTaskMenuItem;
  @FXML
  private MenuItem commitMenuItem;

  @FXML
  private Button submitTask;

  @FXML
  private MenuItem openMenuItem;

  @FXML
  private MenuItem saveMenuItem;
  private Popup eventMaker;
  private Popup taskMaker;
  private Popup commitPopup;
  private Popup passwordSetterPopup;
  private Popup passwordEnter;
  private Stage stage;
  private File file;

  //fxmls for event maker
  @FXML
  private TextField eventNameTextField;
  @FXML
  private TextArea eventDescriptionTextArea;
  @FXML
  private ChoiceBox eventDayChoiceBox;
  @FXML
  private ChoiceBox eventStartTimeChoiceBox;
  @FXML
  private TextField eventDurationTextField;

  //fxmls for task maker
  @FXML
  private TextField taskNameTextField;

  @FXML
  private TextArea taskDescriptionTextArea;

  @FXML
  private ChoiceBox taskDayChoiceBox;

  @FXML
  private GridPane taskOverviewGrid;

  //gridpanes for events in days
  @FXML
  private GridPane gridEvent1;
  @FXML
  private GridPane gridEvent2;
  @FXML
  private GridPane gridEvent3;
  @FXML
  private GridPane gridEvent4;
  @FXML
  private GridPane gridEvent5;
  @FXML
  private GridPane gridEvent6;
  @FXML
  private GridPane gridEvent7;

  //gridpanes for tasks in days
  @FXML
  private GridPane gridTask1;
  @FXML
  private GridPane gridTask2;
  @FXML
  private GridPane gridTask3;
  @FXML
  private GridPane gridTask4;
  @FXML
  private GridPane gridTask5;
  @FXML
  private GridPane gridTask6;
  @FXML
  private GridPane gridTask7;

  @FXML
  private TabPane tabPane;

  @FXML
  private ProgressBar progressBar;
  @FXML
  private Label remainingTaskLabel;

  @FXML
  private TextArea notesArea;
  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label completedTasks;
  @FXML
  private Button settingsSave;
  @FXML
  private TextField maxEvents;
  @FXML
  private TextField maxTasks;
  @FXML
  private Button settingsClose;

  @FXML
  private HostServices hostServices;
  @FXML
  private TextField searchBar;
  @FXML
  private Button searchButton;
  @FXML
  private PasswordField password1;
  @FXML
  private PasswordField password2;
  @FXML
  private Button buttonCreatePassword;
  @FXML
  private MenuItem passwordMenuItem;

  @FXML
  private Button closeEvent;

  @FXML
  private Button closeTask;
  @FXML
  private Button buttonCreatePasswordClose;
  @FXML
  private Button buttonCheckPassword;
  @FXML
  private Button buttonClosePassword;
  @FXML
  private PasswordField enterPasswordField;
  @FXML
  private Button saveNotesButton;

  /**
   * Constructs a controller for the journal program.
   *
   * @param stage        the stage
   * @param hostServices the host services
   */
  public PlannerController(Stage stage, HostServices hostServices) {
    this.stage = stage;
    this.hostServices = hostServices;
  }

  /**
   * runs the controller.
   */
  @Override
  @FXML
  public void run() throws IllegalStateException, IOException {
    setShortcuts();
    eventMakerShow();
    taskMakerShow();
    commitmentMakerShow();
    openFile();
    saveFile();
    newWeek();
    progressBar();
    searchBar();
    chooseTemplate();
    passwordMakerShow();
    noteSaver();
  }

  /**
   * Saves the notes
   */
  private void noteSaver() {
    saveNotesButton.setOnAction(e -> {
      this.week.setNotes(notesArea.getText());
    });
  }


  /**
   * sets the event handler for the new week button
   */
  private void newWeek() {
    newWeekMenuItem.setOnAction(e -> {

      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Choose File Location");

      File file = fileChooser.showSaveDialog(stage);
      if (file != null) {

        file = extensionHelper(file);
        // Proceed with saving the file
        OutputFile outputFile = new OutputFile(file);
        getDefault();
        outputFile.writeFile(JsonUtils.serializeRecord(this.week.toWeekJson()));
        FileReader fileReader = new FileReader(file.toPath());
        this.file = file;
        getDefault();
        updateGui();
        passwordMenuItem.fire();
      } else {
        // No file selected
        System.out.println("No file selected.");
      }

    });
  }

  /**
   * helper to remove extension and put the right bujo extension
   *
   * @param file the file
   * @return the file with the right extension
   */
  private File extensionHelper(File file) {
    String fileName = file.getName();

    // Check if the file already has an extension
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex >= 0) {
      // Remove the existing extension
      fileName = fileName.substring(0, dotIndex);
    }

    // Append the ".bujo" extension
    fileName += ".bujo";
    file = new File(file.getParent(), fileName);
    return file;
  }

  /**
   * sets the shortcut keys
   */
  private void setShortcuts() {
    KeyCodeCombination shortcutSave;
    KeyCodeCombination shortcutOpen;
    KeyCodeCombination shortcutAddEvent;
    KeyCodeCombination shortcutAddTask;
    KeyCodeCombination shortcutCommitments;
    KeyCodeCombination shortcutWeek;
    KeyCodeCombination shortcutTemplate;
    KeyCodeCombination shortcutPassword;

    if (System.getProperty("os.name").toLowerCase().contains("win")) {
      shortcutSave = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
      shortcutOpen = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
      shortcutAddEvent = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
      shortcutAddTask = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
      shortcutCommitments = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
      shortcutWeek = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
      shortcutTemplate = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN,
          KeyCombination.SHIFT_DOWN);
      shortcutPassword = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN);
    } else {
      shortcutSave = new KeyCodeCombination(KeyCode.S, KeyCombination.META_DOWN);
      shortcutOpen = new KeyCodeCombination(KeyCode.O, KeyCombination.META_DOWN);
      shortcutAddEvent = new KeyCodeCombination(KeyCode.E, KeyCombination.META_DOWN);
      shortcutAddTask = new KeyCodeCombination(KeyCode.T, KeyCombination.META_DOWN);
      shortcutCommitments = new KeyCodeCombination(KeyCode.M, KeyCombination.META_DOWN);
      shortcutWeek = new KeyCodeCombination(KeyCode.N, KeyCombination.META_DOWN);
      shortcutTemplate = new KeyCodeCombination(KeyCode.T, KeyCombination.META_DOWN,
          KeyCombination.SHIFT_DOWN);
      shortcutPassword = new KeyCodeCombination(KeyCode.P, KeyCombination.META_DOWN);
    }

    saveMenuItem.setAccelerator(shortcutSave);
    openMenuItem.setAccelerator(shortcutOpen);
    addEventMenuItem.setAccelerator(shortcutAddEvent);
    addTaskMenuItem.setAccelerator(shortcutAddTask);
    newWeekMenuItem.setAccelerator(shortcutWeek);
    commitMenuItem.setAccelerator(shortcutCommitments);
    templateMenuItem.setAccelerator(shortcutTemplate);
    passwordMenuItem.setAccelerator(shortcutPassword);
  }

  /**
   * Commitment maker show.
   *
   * @throws IOException the io exception
   */
  void commitmentMakerShow() throws IOException {
    commitMenuItem.setOnAction(event -> showCommitMaker());
    this.commitPopup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Settings.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    this.commitPopup.getContent().add(s.getRoot());
    settingsSave.setOnAction(event -> changeCommit());
    settingsClose.setOnAction(event -> commitPopup.hide());
  }

  /**
   * modifies the week to have the new max events and tasks
   */
  private void changeCommit() {
    int maxEvent = -1;
    int maxTask = -1;

    if (validateCommit(maxTask, maxEvent)) {
      maxEvent = Integer.parseInt(maxEvents.getText());
      maxTask = Integer.parseInt(maxTasks.getText());
      this.week.setMaxEvents(maxEvent);
      this.week.setMaxTasks(maxTask);
      this.commitPopup.hide();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid Input");
      alert.setContentText("Please enter a valid number");
      alert.showAndWait();
    }
  }

  /**
   * validates the input for the max events and tasks
   *
   * @param maxTask  the max tasks
   * @param maxEvent the max events
   * @return true if valid, false otherwise
   */
  private boolean validateCommit(int maxTask, int maxEvent) {
    boolean valid = true;
    try {
      maxEvent = Integer.parseInt(maxEvents.getText());
      maxTask = Integer.parseInt(maxTasks.getText());
    } catch (NumberFormatException e) {
      valid = false;
    }

    if (maxEvent < 0 || maxTask < 0) {
      valid = false;
    }
    return valid;
  }

  /**
   * shows the commitment maker
   */
  private void showCommitMaker() {
    this.commitPopup.show(stage);
  }

  /**
   * Event maker show.
   *
   * @throws IOException the io exception
   */
  void eventMakerShow() throws IOException {
    addEventMenuItem.setOnAction(event -> showEventMaker());
    this.eventMaker = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Event.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    this.eventMaker.getContent().add(s.getRoot());
    submitEvent.setOnAction(event -> createEvent());
    closeEvent.setOnAction(event -> this.eventMaker.hide());
  }


  /**
   * creates an event
   */
  private void createEvent() {
    String name = eventNameTextField.getText();
    String description = eventDescriptionTextArea.getText();
    Day day = Day.fromString(eventDayChoiceBox.getValue().toString());
    String time = eventStartTimeChoiceBox.getValue().toString();

    Time startTime =
        new Time(Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(3, 5)));
    int duration = 0;
    try {
      duration = Integer.parseInt(eventDurationTextField.getText());
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    if (validateEvent(name, description, day, startTime, duration)
        && isInteger(eventDurationTextField.getText())) {
      if (eventsMet()) {
        this.week.addEvent(name, description, day, startTime, duration);
        updateGui();
        this.eventMaker.hide();
      } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Exceeding Commitments");
        alert.showAndWait();
        this.week.addEvent(name, description, day, startTime, duration);
        updateGui();
        this.eventMaker.hide();
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid event\nPlease fill in all fields and try again");
      alert.showAndWait();
    }
  }

  /**
   * Compares the number of events in the week to the max events
   *
   * @return true if the number of events is less than the max events, false otherwise
   */
  private boolean eventsMet() {
    Day day = Day.fromString(eventDayChoiceBox.getValue().toString());
    int counter = 0;
    for (Event e : this.week.getEvents()) {
      if (e.getDay().equals(day)) {
        counter++;
      }
    }
    return counter < this.week.getMaxEvents();
  }

  /**
   * validates the event
   *
   * @param name        the name
   * @param description the description
   * @param day         the day
   * @param startTime   the start time
   * @param duration    the duration
   * @return true if valid, false otherwise
   */
  private boolean validateEvent(String name, String description, Day day, Time startTime,
                                int duration) {
    return !name.isEmpty() || !description.isEmpty() || day != null || startTime != null
        || duration > 0;
  }

  /**
   * checks if the number is an integer
   *
   * @param input the input
   * @return true if integer, false otherwise
   */
  private static boolean isInteger(String input) {
    return input.matches("-?\\d+");
  }

  /**
   * shows the task maker
   */
  private void createTask() {
    String name = taskNameTextField.getText();
    String description = taskDescriptionTextArea.getText();
    Day day = null;
    if (taskDayChoiceBox.getValue() == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Please select a day");
      alert.showAndWait();
    } else {
      day = Day.fromString(taskDayChoiceBox.getValue().toString());
    }
    if (validateTask(name, description, day)) {
      if (tasksMet()) {
        this.week.addTask(name, description, day);
        updateGui();
        this.taskMaker.hide();
      } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Exceeding Commitments");
        alert.showAndWait();
        this.week.addTask(name, description, day);
        updateGui();
        this.taskMaker.hide();
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid task\nPlease fill in all fields and try again");
      alert.showAndWait();
    }
  }

  /**
   * checks if the number of tasks is less than the max tasks
   *
   * @return true if less than max tasks, false otherwise
   */
  private boolean tasksMet() {
    Day day = Day.fromString(taskDayChoiceBox.getValue().toString());
    int counter = 0;
    for (Task t : this.week.getLot()) {
      if (t.getDay().equals(day)) {
        counter++;
      }
    }
    return counter < this.week.getMaxTasks();
  }

  /**
   * validates the task
   *
   * @param name        the name
   * @param description the description
   * @param day         the day
   * @return true if valid, false otherwise
   */
  private boolean validateTask(String name, String description, Day day) {
    return !name.isEmpty() || !description.isEmpty() || day != null;
  }

  /**
   * updates the gui
   */
  private void updateGui() {

    // populate tasks grid for every day of the week
    populateTaskGrids();
    // populate events grid for every day of the week
    populateEventGrids();
    // populate notes
    populateNotes();
    // populate taskOverview
    populateTaskOverview();
    // populate the weekly overview
    populateWeeklyOverview();
    //populate progress bar
    progressBar();
  }

  /**
   * populates the weekly overview
   */
  private void populateWeeklyOverview() {
    totalEvents.setText("" + this.week.getEvents().size());
    totalTasks.setText("" + this.week.getLot().size());
    DecimalFormat df = new DecimalFormat("00.00");
    completedTasks.setText(String.valueOf(
        df.format((double) this.week.getCompletedTasks()
            / this.week.getLot().size() * 100)));
  }

  /**
   * populates the notes
   */
  private void populateNotes() {
    notesArea.setText(this.week.getNotes());
  }

  /**
   * populates progress bar
   */
  private void progressBar() {
    for (Tab tab : tabPane.getTabs()) {
      // creat event handler for each tab if tab pressed then update progress bar
      tab.selectedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
          // Tab is selected
          updateProgressBar(tab.getText());
        }
      });

      Tab initialTab = tabPane.getSelectionModel().getSelectedItem();
      if (initialTab != null) {
        updateProgressBar(initialTab.getText());
      }
    }
  }

  /**
   * updates the progress bar
   *
   * @param tabName the weekday
   */
  private void updateProgressBar(String tabName) {
    // get total tasks from lot in week for that particular day
    ArrayList<Task> currTasks = new ArrayList<>();
    for (Task t : this.week.getLot()) {
      if (t.getDay().toString().equalsIgnoreCase(tabName)) {
        currTasks.add(t);
      }
    }
    int totalTasks = currTasks.size();
    int completedTasks = 0;
    for (Task t : currTasks) {
      if (t.isCompleted()) {
        completedTasks++;
      }
    }
    // get the n  of tasks completed
    // update progress bar
    progressBar.setProgress((double) completedTasks / totalTasks);
    remainingTaskLabel.setText("" + (totalTasks - completedTasks));
  }

  /**
   * populates the event grids
   */
  private void populateEventGrids() {
    List<GridPane> eventGrids = List.of(gridEvent1, gridEvent2, gridEvent3, gridEvent4, gridEvent5,
        gridEvent6, gridEvent7);
    int counter = 1; // Start counter at 1 for the header row
    int currDay = -1; // Initialize with an invalid value

    for (GridPane g : eventGrids) {
      g.getChildren().clear();
      setupEventGridPane(g);

      // Add headers
      addEventHeaderRow(g);

    }

    for (Event e : this.week.getEvents()) {
      int indexOfGrid = e.getDay().ordinal();

      GridPane currGrid = getCurrentGrid(eventGrids, indexOfGrid, currDay, counter);

      String eventName = e.getName();
      String eventDescription = e.getDescription();
      String eventStartTime = e.getStart().toString();
      String eventDuration = String.valueOf(e.getDuration());

      Text eventNameText = new Text(eventName);
      TextFlow eventDescriptionTextFlow = hyperlinkHelper(eventDescription.split("\\s+"));
      Text eventStartTimeText = new Text(eventStartTime);
      Text eventDurationText = new Text(eventDuration);
      eventDescriptionTextFlow.setTextAlignment(TextAlignment.CENTER);

      addEventRow(currGrid, eventNameText, eventDescriptionTextFlow, eventStartTimeText,
          eventDurationText, counter);
      counter++;
    }
  }

  /**
   * sets up the event grid pane
   *
   * @param gridPane the grid pane
   */
  private void setupEventGridPane(GridPane gridPane) {
    // Clear existing column and row constraints
    gridPane.getColumnConstraints().clear();
    gridPane.getRowConstraints().clear();

    int numColumns = 4;
    int numRows = gridPane.getRowCount();
    double availableWidth = gridPane.getPrefWidth();
    double columnWidth = availableWidth / numColumns;
    double rowHeight = gridPane.getPrefHeight() / numRows;

    for (int i = 0; i < numColumns; i++) {
      ColumnConstraints columnConstraints = new ColumnConstraints();
      columnConstraints.setPrefWidth(columnWidth);
      columnConstraints.setHgrow(Priority.ALWAYS);
      gridPane.getColumnConstraints().add(columnConstraints);
    }

    for (int i = 0; i < numRows; i++) {
      RowConstraints rowConstraints = new RowConstraints();
      rowConstraints.setPrefHeight(rowHeight);
      rowConstraints.setVgrow(Priority.ALWAYS);
      gridPane.getRowConstraints().add(rowConstraints);
    }

    // Set grid alignment
    gridPane.setAlignment(Pos.TOP_CENTER);
  }

  /**
   * adds an event row
   *
   * @param gridPane the grid pane
   */
  private void addEventHeaderRow(GridPane gridPane) {
    Text eventNameHeader = new Text("Event Name");
    GridPane.setConstraints(eventNameHeader, 0, 0);
    GridPane.setHalignment(eventNameHeader, HPos.CENTER);

    Text eventDescriptionHeader = new Text("Description");
    GridPane.setConstraints(eventDescriptionHeader, 1, 0);
    GridPane.setHalignment(eventDescriptionHeader, HPos.CENTER);

    Text eventStartTimeHeader = new Text("Start Time");
    GridPane.setConstraints(eventStartTimeHeader, 2, 0);
    GridPane.setHalignment(eventStartTimeHeader, HPos.CENTER);

    Text eventDurationHeader = new Text("Duration");
    GridPane.setConstraints(eventDurationHeader, 3, 0);
    GridPane.setHalignment(eventDurationHeader, HPos.CENTER);

    gridPane.getChildren()
        .addAll(eventNameHeader, eventDescriptionHeader, eventStartTimeHeader, eventDurationHeader);
  }

  /**
   * gets the current grid
   *
   * @param gridPane         the grid pane
   * @param eventName        the event name
   * @param eventDescription the event description
   * @param eventStartTime   the event start time
   * @param eventDuration    the event duration
   * @param row              the row
   */
  private void addEventRow(GridPane gridPane, Text eventName, TextFlow eventDescription,
                           Text eventStartTime, Text eventDuration, int row) {
    gridPane.addRow(row, eventName, eventDescription, eventStartTime, eventDuration);

    for (Node node : gridPane.getChildren()) {
      GridPane.setHalignment(node, HPos.CENTER);
      GridPane.setValignment(node, VPos.TOP);
    }
  }

  /**
   * hyper link helper
   *
   * @param splitStrings the split strings an array of strings
   * @return the text flow with the hyper link
   */
  private TextFlow hyperlinkHelper(String[] splitStrings) {
    TextFlow textFlow = new TextFlow();
    for (String s : splitStrings) {
      if (s.startsWith("http://") || s.startsWith("https://")) {
        Hyperlink link = new Hyperlink(s);
        link.setOnAction(ev -> {
          hostServices.showDocument(link.getText());
        });
        textFlow.getChildren().add(link);
      } else {
        Text text = new Text(s);
        Text space = new Text(" ");
        textFlow.getChildren().addAll(text, space);
      }
    }
    return textFlow;
  }

  /**
   * populates the task grids
   */
  private void populateTaskGrids() {
    List<GridPane> taskGrids =
        List.of(gridTask1, gridTask2, gridTask3, gridTask4, gridTask5, gridTask6, gridTask7);

    for (GridPane g : taskGrids) {
      setupGridPane(g);
      addHeaders(g);
    }

    int counter = 1; // Start counter at 1 for the first task row
    int currDay = -1; // Initialize with an invalid value

    for (Task t : this.week.getLot()) {
      int indexOfGrid = t.getDay().ordinal();
      GridPane currGrid = getCurrentGrid(taskGrids, indexOfGrid, currDay, counter);

      String taskName = t.getName();
      String description = t.getDescription();

      String[] splitStrings = description.split("\\s+");
      TextFlow textFlow = hyperlinkHelper(splitStrings);

      CheckBox checkBox = new CheckBox();
      checkBox.setSelected(t.isCompleted()); // Set the default state based on isCompleted
      Text taskNameText = new Text(taskName);
      HBox descriptionBox = new HBox(textFlow);
      descriptionBox.setAlignment(Pos.CENTER);
      currGrid.setVgap(10);

      addTaskRow(currGrid, checkBox, taskNameText, descriptionBox, counter);
      counter++;

      // Add a handler to the checkbox
      checkBox.setOnAction(event -> {
        t.setComplete(checkBox.isSelected());
        updateGui();
        progressBar();
      });
    }
  }

  /**
   * get current grid
   *
   * @param gridPanes the grid panes
   * @param index     the index
   * @param currDay   the current day
   * @param counter   the counter
   * @return the current grid
   */
  private GridPane getCurrentGrid(List<GridPane> gridPanes, int index, int currDay, int counter) {
    GridPane currGrid = gridPanes.get(index);

    if (currDay != index) {
      counter = 1; // Reset counter for each grid
      currDay = index;
    }

    return currGrid;
  }

  /**
   * sets us the task grid
   *
   * @param gridPane the grid pane
   */
  private void setupGridPane(GridPane gridPane) {
    gridPane.getChildren().clear();
    gridPane.getColumnConstraints().clear();
    gridPane.getRowConstraints().clear();

    int numColumns = 3;
    int numRows = gridPane.getRowCount();
    double availableWidth = gridPane.getPrefWidth();
    double checkboxColumnWidth = availableWidth * 0.15; // 15% of available width
    double nameColumnWidth = availableWidth * 0.25; // 25% of available width
    double descriptionColumnWidth = availableWidth * 0.6; // 60% of available width

    for (int i = 0; i < numColumns; i++) {
      ColumnConstraints columnConstraints = new ColumnConstraints();
      if (i == 0) {
        columnConstraints.setPercentWidth(15);
      } else if (i == 1) {
        columnConstraints.setPercentWidth(25);
      } else {
        columnConstraints.setPercentWidth(60);
      }
      columnConstraints.setHgrow(Priority.ALWAYS);
      gridPane.getColumnConstraints().add(columnConstraints);
    }

    for (int i = 0; i < numRows; i++) {
      RowConstraints rowConstraints = new RowConstraints();
      rowConstraints.setVgrow(Priority.ALWAYS);
      gridPane.getRowConstraints().add(rowConstraints);
    }

    gridPane.setAlignment(Pos.TOP_CENTER);
  }

  /**
   * sets the node alignment
   *
   * @param node the node
   * @param horPos the horizontal position
   * @param verPos the vertical position
   */
  private void setNodeAlignment(Node node, HPos horPos, VPos verPos) {
    GridPane.setHalignment(node, horPos);
    GridPane.setValignment(node, verPos);
  }

  /**
   * adds the headers
   *
   * @param gridPane the grid pane
   */
  private void addHeaders(GridPane gridPane) {
    Text checkboxHeader = new Text("Completed");
    Text nameHeader = new Text("Task Name");
    Text descriptionHeader = new Text("Description");

    gridPane.add(checkboxHeader, 0, 0);
    gridPane.add(nameHeader, 1, 0);
    gridPane.add(descriptionHeader, 2, 0);

    setNodeAlignment(checkboxHeader, HPos.CENTER, VPos.TOP);
    setNodeAlignment(nameHeader, HPos.CENTER, VPos.TOP);
    setNodeAlignment(descriptionHeader, HPos.CENTER, VPos.TOP);
  }

  /**
   * adds the task row
   *
   * @param gridPane       the grid pane
   * @param checkBox       the check box
   * @param taskNameText   the task name text
   * @param descriptionBox the description box
   * @param row            the row
   */
  private void addTaskRow(GridPane gridPane, CheckBox checkBox, Text taskNameText,
                          HBox descriptionBox, int row) {
    gridPane.add(checkBox, 0, row);
    gridPane.add(taskNameText, 1, row);
    gridPane.add(descriptionBox, 2, row);

    setNodeAlignment(checkBox, HPos.CENTER, VPos.TOP);
    setNodeAlignment(taskNameText, HPos.CENTER, VPos.TOP);
    setNodeAlignment(descriptionBox, HPos.CENTER, VPos.TOP);

  }

  /**
   * populate task overview
   */
  private void populateTaskOverview() {
    taskOverviewGrid.getChildren().clear();
    searchResults("");
  }

  /**
   * search bar
   */
  private void searchBar() {
    searchButton.setOnAction(e -> searchResults(searchBar.getText()));
  }

  /**
   * search results
   *
   * @param text the text
   */
  private void searchResults(String text) {
    taskOverviewGrid.getChildren().clear();
    int counter = 0;
    ArrayList<Task> results = new ArrayList<Task>();
    for (Task t : this.week.getLot()) {
      if (t.getName().contains(text)) {
        results.add(t);
      }
    }

    for (Task t : results) {
      Label label = new Label(t.getName());
      GridPane.setConstraints(label, 0, counter);
      GridPane.setHalignment(label, HPos.CENTER);
      GridPane.setHgrow(label, Priority.ALWAYS);
      GridPane.setVgrow(label, Priority.ALWAYS);
      taskOverviewGrid.getChildren().add(label);
      counter++;
    }
  }

  /**
   * show task maker
   *
   * @throws IOException the io exception
   */
  void taskMakerShow() throws IOException {
    addTaskMenuItem.setOnAction(event -> showTaskMaker());
    this.taskMaker = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Task.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    this.taskMaker.getContent().add(s.getRoot());
    submitTask.setOnAction(event -> createTask());
    closeTask.setOnAction(event -> this.taskMaker.hide());
  }

  /**
   * show popup
   */
  private void showTaskMaker() {
    this.taskMaker.show(this.stage);
  }

  /**
   * show popup
   */
  @FXML
  private void showEventMaker() {
    this.eventMaker.show(this.stage);
  }

  /**
   * open file function
   */
  @FXML
  private void openFile() {
    openMenuItem.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extensionFilter =
          new FileChooser.ExtensionFilter("Journal Files (*.bujo)", "*.bujo");
      fileChooser.getExtensionFilters().add(extensionFilter);
      File file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        try {
          file = file.getAbsoluteFile();
          FileReader fileReader = new FileReader(file.toPath());
          this.file = file;
          WeekJson wj = fileReader.readBujo();
          Week w = wj.toWeek();
          passwordEnterShow(w);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  /**
   * password enter show
   *
   * @param w the week
   * @throws IOException the io exception
   */
  private void passwordEnterShow(Week w) throws IOException {
    this.passwordEnter = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("PasswordChecker.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    this.passwordEnter.getContent().add(s.getRoot());
    buttonCheckPassword.setOnAction(event -> enterPassword(w));
    buttonClosePassword.setOnAction(event -> passwordEnter.hide());
    this.passwordEnter.show(this.stage);
  }

  /**
   * enter password
   *
   * @param w the week
   */
  private void enterPassword(Week w) {
    if (enterPasswordField.getText().equals(w.getPassword())) {
      getDefault();
      updateGui();
      this.week = w;
      updateGui();
      this.passwordEnter.hide();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Password incorrect");
      alert.setContentText("Please try again");
      alert.showAndWait();
      enterPasswordField.clear();
    }
  }

  /**
   * choose a template
   */
  @FXML
  private void chooseTemplate() {
    templateMenuItem.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extensionFilter =
          new FileChooser.ExtensionFilter("Journal Files (*.bujo)", "*.bujo");
      fileChooser.getExtensionFilters().add(extensionFilter);
      File file = fileChooser.showOpenDialog(stage);

      if (file != null) {
        // Handle the selected file
        file = file.getAbsoluteFile();
        FileReader fileReader = new FileReader(file.toPath());
        WeekJson wj = fileReader.readBujo();
        Week week = wj.toWeek();
        int maxEvents = week.getMaxEvents();
        int maxTasks = week.getMaxTasks();
        this.week.setMaxEvents(maxEvents);
        this.week.setMaxTasks(maxTasks);
      }
    });
  }

  /**
   * sets the field of week to default
   */
  private void getDefault() {
    this.week = new Week();
  }

  /**
   * password maker show
   *
   * @throws IOException the io exception
   */
  private void passwordMakerShow() throws IOException {
    passwordMenuItem.setOnAction(event -> showPasswordMaker());
    this.passwordSetterPopup = new Popup();
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("PasswordSetter.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    this.passwordSetterPopup.getContent().add(s.getRoot());
    buttonCreatePassword.setOnAction(event -> setPassword());
    buttonCreatePasswordClose.setOnAction(event -> passwordSetterPopup.hide());
  }

  /**
   * show popup
   */
  private void showPasswordMaker() {
    this.passwordSetterPopup.show(this.stage);
  }

  /**
   * set password
   */
  private void setPassword() {
    String password1 = this.password1.getText();
    String password2 = this.password2.getText();
    if (password1.equals(password2)) {
      this.week.setPassword(password1);
      this.passwordSetterPopup.hide();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Passwords do not match");
      alert.setContentText("Please try again");
      alert.showAndWait();
    }
  }

  /**
   * save file function
   */
  private void saveFile() {
    saveMenuItem.setOnAction(e -> {
      if (this.week.getPassword() != null) {
        if (this.file == null) {
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Choose File Location");

          File file = fileChooser.showSaveDialog(stage);
          if (file != null) {
            file = extensionHelper(file);
            this.file = file;
            this.week.setNotes(notesArea.getText());
            OutputFile outputFile = new OutputFile(this.file);
            outputFile.writeFile(JsonUtils.serializeRecord(this.week.toWeekJson()));
            saveMenuItem.fire();
          }

        } else {
          this.week.setNotes(notesArea.getText());
          OutputFile outputFile = new OutputFile(this.file);
          outputFile.writeFile(JsonUtils.serializeRecord(this.week.toWeekJson()));
          System.out.println("File Saved");
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("File Saved");
          alert.setHeaderText("File Saved Successfully");
          alert.showAndWait();

        }
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Must Set A Password!!!");
        alert.setContentText("Please try again");
        alert.showAndWait();
      }
    });
  }
}

