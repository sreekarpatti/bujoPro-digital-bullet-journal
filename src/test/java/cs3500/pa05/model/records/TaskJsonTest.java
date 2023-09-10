package cs3500.pa05.model.records;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskJsonTest {
  private TaskJson task1;

  @BeforeEach
  void setUp() {
    task1 = new TaskJson("name", "description", "MONDAY", false);
  }

  @Test
  void toTask() {
    Task task2 = task1.toTask();
    assertThat(new Task("name", "description", Day.MONDAY, false)).usingRecursiveComparison()
        .isEqualTo(task2);
  }

  @Test
  void name() {
    assertEquals("name", task1.name());
  }


  @Test
  void description() {
    assertEquals("description", task1.description());
  }

  @Test
  void day() {
    assertEquals("MONDAY", task1.day());
  }

  @Test
  void completed() {
    assertEquals(false, task1.completed());
  }
}