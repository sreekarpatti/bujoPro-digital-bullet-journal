package cs3500.pa05.model;

import cs3500.pa05.model.records.DayJson;
import cs3500.pa05.model.records.WeekJson;
import java.util.ArrayList;

/**
 * Represents a week of days.
 */
public class Week {
  private ArrayList<DayInfo> days;
  private String notes;
  private int maxEvents;
  private int maxTasks;
  private String password;

  /**
   * Returns the password of the week.
   *
   * @return the password of the week
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the week.
   *
   * @param password the password to be set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Initializes a week with the given days and notes.
   *
   * @param days      the days of the week
   * @param notes     the notes of the week
   * @param maxEvents the maximum number of events allowed in a day
   * @param maxTasks  the maximum number of tasks allowed in a day
   * @param password  the password of the week
   */
  public Week(ArrayList<DayInfo> days, String notes, int maxEvents, int maxTasks, String password) {
    this.days = days;
    this.notes = notes;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.password = password;
  }

  /**
   * Initializes a week with the given days and notes.
   */
  public Week() {
    this.days = new ArrayList<DayInfo>();
    this.days.add(new DayInfo(Day.SUNDAY));
    this.days.add(new DayInfo(Day.MONDAY));
    this.days.add(new DayInfo(Day.TUESDAY));
    this.days.add(new DayInfo(Day.WEDNESDAY));
    this.days.add(new DayInfo(Day.THURSDAY));
    this.days.add(new DayInfo(Day.FRIDAY));
    this.days.add(new DayInfo(Day.SATURDAY));
    this.notes = "";
    this.maxEvents = 2;
    this.maxTasks = 2;
    this.password = null;
  }

  /**
   * Returns the number of maximum events allowed in a week.
   *
   * @return maximum number of events allowed in a week
   */
  public int getMaxEvents() {
    return maxEvents;
  }

  /**
   * Sets the maximum number of events allowed in a week.
   *
   * @param maxEvents the maximum number of events allowed in a week
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Sets the maximum number of tasks allowed in a week.
   *
   * @param maxTasks the maximum number of tasks allowed in a week
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Returns the number of maximum tasks allowed in a week.
   *
   * @return maximum number of tasks allowed in a week
   */
  public int getMaxTasks() {
    return maxTasks;
  }

  /**
   * Returns the list of tasks in a week.
   *
   * @return list of tasks in a week
   */
  public ArrayList<Task> getLot() {
    ArrayList<Task> weekLot = new ArrayList<Task>();
    for (DayInfo day : this.days) {
      weekLot.addAll(day.getTasks());
    }
    return weekLot;
  }

  /**
   * Returns the week as a WeekJson.
   *
   * @return the week as a WeekJson
   */
  public WeekJson toWeekJson() {
    ArrayList<DayJson> daysJson = new ArrayList<DayJson>();
    for (DayInfo day : this.days) {
      daysJson.add(day.toDayJson());
    }
    return new WeekJson(this.notes, daysJson, this.maxEvents, this.maxTasks, this.password);
  }

  /**
   * Sets the notes of the week.
   *
   * @param notes the notes to be set
   */
  public void setNotes(String notes) {
    this.notes = notes;
  }

  /**
   * Returns the notes of the week.
   *
   * @return the notes of the week
   */
  public String getNotes() {
    return notes;
  }

  /**
   * Returns a given day in a week.
   *
   * @return a given day in a week
   */
  public DayInfo getDay(Day day) {
    for (DayInfo d : this.days) {
      if (d.getDay().equals(day)) {
        return d;
      }
    }
    return null;
  }

  /**
   * Adds an event to a given day in a week.
   *
   * @param name        the name of the event
   * @param description the description of the event
   * @param day         the day of the event
   * @param startTime   the start time of the event
   * @param duration    the duration of the event
   */
  public void addEvent(String name, String description, Day day, Time startTime, int duration) {
    DayInfo d = this.getDay(day);
    d.addEvent(name, description, day, startTime, duration);
  }

  /**
   * Adds a task to a given day in a week.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param day         the day of the task
   */
  public void addTask(String name, String description, Day day) {
    DayInfo d = this.getDay(day);
    d.addTask(name, description, day);
  }

  /**
   * Returns the list of events in a week.
   *
   * @return list of events in a week
   */
  public ArrayList<Event> getEvents() {
    ArrayList<Event> weekLoe = new ArrayList<Event>();
    for (DayInfo day : this.days) {
      weekLoe.addAll(day.getEvents());
    }
    return weekLoe;
  }

  /**
   * Returns the number of completed tasks in a week.
   *
   * @return the number of completed tasks in a week
   */
  public int getCompletedTasks() {
    int counter = 0;
    for (Task task : this.getLot()) {
      if (task.isCompleted()) {
        counter++;
      }
    }
    return counter;
  }

}
