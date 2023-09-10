package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Time;

/**
 * Represents a time in the journal program.
 *
 * @param hour   the hour
 * @param minute the minute
 */
public record TimeJson(
    @JsonProperty("hour") int hour,
    @JsonProperty("minute") int minute) {

  /**
   * Converts a TimeJson to a Time.
   *
   * @return the Time
   */
  public Time toTime() {
    return new Time(hour, minute);
  }
}
