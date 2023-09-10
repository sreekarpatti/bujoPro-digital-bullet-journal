package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;

/**
 * Represents an event in the journal program.
 *
 * @param name        the name
 * @param description the description
 * @param day         the day
 * @param start       the start time
 * @param duration    the duration
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") String day,
    @JsonProperty("start") TimeJson start,
    @JsonProperty("duration") int duration) {

  /**
   * Converts an EventJson to an Event.
   *
   * @return the Event
   */
  public Event toEvent() {
    return new Event(name, description, Day.valueOf(day), start.toTime(), duration);
  }
}
