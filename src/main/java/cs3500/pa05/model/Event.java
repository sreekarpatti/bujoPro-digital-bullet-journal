package cs3500.pa05.model;

import cs3500.pa05.model.records.EventJson;

/**
 * Represents an event in the journal program.
 */
public class Event {
  String name;
  String description;
  Day day;
  Time start;
  int duration;

  /**
   * Constructs an Event.
   *
   * @param name        the name
   * @param description the description
   * @param day         the day
   * @param start       the start time
   * @param duration    the duration
   */
  public Event(String name, String description, Day day, Time start, int duration) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.start = start;
    this.duration = duration;
  }

  /**
   * Converts an Event to an EventJson.
   *
   * @return the EventJson
   */
  public EventJson toEventJson() {
    return new EventJson(this.name, this.description, this.day.toString(), this.start.toTimeJson(),
        this.duration);
  }

  /**
   * Gets the name of the event.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the description of the event.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the start time of the event.
   *
   * @return the start time
   */
  public Time getStart() {
    return start;
  }

  /**
   * gets the duration of the event.
   *
   * @return the duration
   */
  public int getDuration() {
    return duration;
  }

  /**
   * gets the day of the event.
   *
   * @return the day
   */
  public Day getDay() {
    return day;
  }

}
