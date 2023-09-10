package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayInfo;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day in the journal program.
 *
 * @param tasks  list of taskJsons
 * @param events list of eventJsons
 * @param day    the day
 */
public record DayJson(
    @JsonProperty("tasks") List<TaskJson> tasks,
    @JsonProperty("events") List<EventJson> events,
    @JsonProperty("day") String day) {

  /**
   * Converts a DayJson to a DayInfo.
   *
   * @return the DayInfo
   */
  public DayInfo toDayInfo() {
    ArrayList<Task> tasks = new ArrayList<Task>();
    for (TaskJson task : this.tasks) {
      tasks.add(task.toTask());
    }
    ArrayList<Event> events = new ArrayList<Event>();
    for (EventJson event : this.events) {

      events.add(event.toEvent());
    }
    return new DayInfo(tasks, events, Day.valueOf(day));
  }
}
