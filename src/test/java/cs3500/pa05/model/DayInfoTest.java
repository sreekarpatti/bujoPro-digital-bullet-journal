package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.records.DayJson;
import cs3500.pa05.model.records.EventJson;
import cs3500.pa05.model.records.TaskJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayInfoTest {
  private Task task1;
  private Event event1;
  private ArrayList<Task> lot1;
  private ArrayList<Event> loe1;
  private DayInfo dayInfo1;

  @BeforeEach
  void setup() {
    event1 = new Event("event1", "something", Day.WEDNESDAY,
        new Time(5, 10), 10);
    task1 = new Task("task1", "something about task1", Day.WEDNESDAY);
    lot1 = new ArrayList<>();
    loe1 = new ArrayList<>();
    lot1.add(task1);
    loe1.add(event1);
    dayInfo1 = new DayInfo(lot1, loe1, Day.WEDNESDAY);
  }

  @Test
  void getDay() {
    assertEquals(Day.WEDNESDAY, event1.getDay());
  }

  @Test
  void getTasks() {
    assertEquals(lot1, dayInfo1.getTasks());
  }

  @Test
  void getEvents() {
    assertEquals(loe1, dayInfo1.getEvents());
  }

  @Test
  void addEvent() {
    dayInfo1.addEvent("event2", "something", Day.WEDNESDAY, new Time(5, 10), 10);
    loe1.add(new Event("event2", "something", Day.WEDNESDAY,
        new Time(5, 10), 10));
    assertEquals(loe1, dayInfo1.getEvents());
  }

  @Test
  void addTask() {
    dayInfo1.addTask("task2", "something about task2", Day.WEDNESDAY);
    lot1.add(new Task("task2", "something about task2", Day.WEDNESDAY));
    assertEquals(lot1, dayInfo1.getTasks());
  }

  @Test
  void toDayJson() {
    ArrayList<TaskJson> tasksJson = new ArrayList<TaskJson>();
    for (Task task : lot1) {
      tasksJson.add(task.toTaskJson());
    }
    ArrayList<EventJson> eventsJson = new ArrayList<EventJson>();
    for (Event event : loe1) {
      eventsJson.add(event.toEventJson());
    }

    DayJson dayJson1 = new DayJson(tasksJson, eventsJson, "WEDNESDAY");

    assertEquals(dayJson1, dayInfo1.toDayJson());
  }
}