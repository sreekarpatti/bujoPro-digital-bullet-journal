package cs3500.pa05.model;

import cs3500.pa05.model.records.TimeJson;

/**
 * Represents a time.
 */
public class Time {
  private int hour;
  private int minute;

  /**
   * Initializes a time with the given hour and minute.
   *
   * @param hour   the hour of the time
   * @param minute the minute of the time
   */
  public Time(int hour, int minute) {
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * Converts the time to a TimeJson.
   *
   * @return the TimeJson
   */
  public TimeJson toTimeJson() {
    return new TimeJson(this.hour, this.minute);
  }

  /**
   * Returns the time as a string.
   *
   * @return the time as a string
   */
  @Override
  public String toString() {
    return this.hour + ":" + this.minute;
  }
}
