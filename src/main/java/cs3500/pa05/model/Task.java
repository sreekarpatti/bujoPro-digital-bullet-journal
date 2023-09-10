package cs3500.pa05.model;

import cs3500.pa05.model.records.TaskJson;

/**
 * Represents a task.
 */
public class Task {
  private String name;
  private String description;
  private Day day;
  private boolean isComplete;

  /**
   * Initializes a task with the given name, description, and day.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param day         the day of the task
   */
  public Task(String name, String description, Day day) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.isComplete = false;
  }

  /**
   * Initializes a task with the given name, description, day, and completion status.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param day         the day of the task
   * @param isComplete  the completion status of the task
   */
  public Task(String name, String description, Day day, boolean isComplete) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.isComplete = isComplete;
  }

  /**
   * Returns the name of the task.
   *
   * @return the name of the task
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the description of the task.
   *
   * @return the description of the task
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the completion status of the task.
   *
   * @param complete the completion status of the task
   */
  public void setComplete(boolean complete) {
    isComplete = complete;
  }

  /**
   * Converts the task to a TaskJson.
   *
   * @return the TaskJson
   */
  public TaskJson toTaskJson() {
    return new TaskJson(this.name, this.description, this.day.toString(), this.isComplete);
  }

  /**
   * Returns the day of the task.
   *
   * @return the day of the task
   */
  public Day getDay() {
    return day;
  }

  /**
   * Returns the completion status of the task.
   *
   * @return the completion status of the task
   */
  public boolean isCompleted() {
    return this.isComplete;
  }
}
