package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayInfo;
import cs3500.pa05.model.Week;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a week in the journal program.
 *
 * @param notes     notes
 * @param days      list of dayJsons
 * @param maxEvents maxEvents
 * @param maxTasks  maxTasks
 * @param password  password
 */
public record WeekJson(
    @JsonProperty("notes") String notes,
    @JsonProperty("days") List<DayJson> days,
    @JsonProperty("max-events") int maxEvents,
    @JsonProperty("max-tasks") int maxTasks,
    @JsonProperty("password") String password) {

  /**
   * Converts a WeekJson to a Week.
   *
   * @return the Week
   */
  public Week toWeek() {
    ArrayList<DayInfo> days = new ArrayList<DayInfo>();
    for (DayJson day : this.days) {
      days.add(day.toDayInfo());
    }
    return new Week(days, notes, maxEvents, maxTasks, password);
  }
}
