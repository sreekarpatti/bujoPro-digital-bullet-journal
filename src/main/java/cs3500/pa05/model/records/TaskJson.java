package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;

/**
 * Represents a task in the journal program.
 *
 * @param name        the name
 * @param description the description
 * @param day         the day
 * @param completed   whether the task is completed
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") String day,
    @JsonProperty("completed") boolean completed) {

  /**
   * Converts a TaskJson to a Task.
   *
   * @return the Task
   */
  public Task toTask() {
    return new Task(name, description, Day.valueOf(day), completed);
  }
}
