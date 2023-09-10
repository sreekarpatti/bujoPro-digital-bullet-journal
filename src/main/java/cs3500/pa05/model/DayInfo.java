package cs3500.pa05.model;

import cs3500.pa05.model.records.DayJson;
import cs3500.pa05.model.records.EventJson;
import cs3500.pa05.model.records.TaskJson;
import java.util.ArrayList;

/**
 * Represents a day in the journal program.
 */
public class DayInfo {
  private ArrayList<Task> lot;
  private ArrayList<Event> loe;

  private Day day;

  /**
   * Constructs a DayInfo.
   *
   * @param lot list of tasks
   * @param loe list of events
   * @param day the day
   */
  public DayInfo(ArrayList<Task> lot, ArrayList<Event> loe, Day day) {
    this.lot = lot;
    this.loe = loe;
    this.day = day;
  }

  /**
   * Constructs a DayInfo.
   *
   * @param day the day
   */
  public DayInfo(Day day) {
    this.lot = new ArrayList<Task>();
    this.loe = new ArrayList<Event>();
    this.day = day;
  }

  /**
   * gets the day.
   *
   * @return the day
   */
  public Day getDay() {
    return day;
  }

  /**
   * gets the list of tasks.
   *
   * @return the list of tasks
   */
  public ArrayList<Task> getTasks() {
    return this.lot;
  }

  /**
   * gets the list of events.
   *
   * @return the list of events
   */
  public ArrayList<Event> getEvents() {
    return this.loe;
  }

  /**
   * adds an event to the list of events.
   *
   * @param name        the name
   * @param description the description
   * @param day         the day
   * @param startTime   the start time
   * @param duration    the duration
   */
  public void addEvent(String name, String description, Day day, Time startTime, int duration) {
    this.loe.add(new Event(name, description, day, startTime, duration));
  }

  /**
   * adds a task to the list of tasks.
   *
   * @param name        the name
   * @param description the description
   * @param day         the day
   */
  public void addTask(String name, String description, Day day) {
    this.lot.add(new Task(name, description, day));
  }

  /**
   * converts a DayInfo to a DayJson.
   *
   * @return the DayJson
   */
  public DayJson toDayJson() {
    ArrayList<TaskJson> tasksJson = new ArrayList<TaskJson>();
    for (Task task : this.lot) {
      tasksJson.add(task.toTaskJson());
    }
    ArrayList<EventJson> eventsJson = new ArrayList<EventJson>();
    for (Event event : this.loe) {
      eventsJson.add(event.toEventJson());
    }
    return new DayJson(tasksJson, eventsJson, this.day.toString());
  }
}
