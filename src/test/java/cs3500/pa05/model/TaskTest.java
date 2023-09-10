package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.records.TaskJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
  private Task task1;

  @BeforeEach
  void setup() {
    task1 = new Task("task1", "something about task1", Day.TUESDAY);
  }

  @Test
  void getName() {
    assertEquals("task1", task1.getName());
  }

  @Test
  void setComplete() {
    assertEquals(false, task1.isCompleted());
    task1.setComplete(true);
    assertEquals(true, task1.isCompleted());
    task1.setComplete(false);
    assertEquals(false, task1.isCompleted());
  }

  @Test
  void toTaskJson() {
    assertEquals(new TaskJson("task1", "something about task1",
        "TUESDAY", false), task1.toTaskJson());
  }

  @Test
  void getDay() {
    assertEquals(Day.TUESDAY, task1.getDay());
  }

  @Test
  void getDescription() {
    assertEquals("something about task1", task1.getDescription());
  }

  @Test
  void isCompleted() {
    assertEquals(false, task1.isCompleted());
  }
}