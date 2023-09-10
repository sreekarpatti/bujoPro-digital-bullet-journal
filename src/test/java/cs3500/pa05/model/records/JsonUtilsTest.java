package cs3500.pa05.model.records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayInfo;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.Week;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {
  private WeekJson week1;
  private ArrayList<DayJson> days1;
  private ArrayList<TaskJson> tasks1;
  private ArrayList<EventJson> events1;
  private Week week2;
  private ArrayList<DayInfo> days2;
  private ArrayList<Task> tasks2;
  private ArrayList<Event> events2;

  @BeforeEach
  void setUp() {
    tasks1 = new ArrayList<>();
    tasks1.add(new TaskJson("task1", "description1", "MONDAY", false));
    tasks1.add(new TaskJson("task2", "description2", "MONDAY", true));

    events1 = new ArrayList<>();
    events1.add(new EventJson("event1", "description1", "MONDAY", new TimeJson(1, 2), 2));
    events1.add(new EventJson("event2", "description2", "MONDAY", new TimeJson(2, 3), 3));

    days1 = new ArrayList<>();
    days1.add(new DayJson(tasks1, events1, "MONDAY"));

    week1 = new WeekJson("week1", days1, 10, 10, "password");

    tasks2 = new ArrayList<>();
    tasks2.add(new Task("task1", "description1", Day.MONDAY, false));
    tasks2.add(new Task("task2", "description2", Day.MONDAY, true));

    events2 = new ArrayList<>();
    events2.add(new Event("event1", "description1", Day.MONDAY, new Time(1, 2), 2));
    events2.add(new Event("event2", "description2", Day.MONDAY, new Time(2, 3), 3));

    days2 = new ArrayList<>();
    days2.add(new DayInfo(tasks2, events2, Day.MONDAY));

    week2 = new Week(days2, "week1", 10, 10, "password");
  }

  @Test
  void serializeRecord() {
    assertEquals(week1, JsonUtils.deserializeRecord(JsonUtils.serializeRecord(week1)));
  }

  @Test
  void deserializeRecord() {
    assertEquals(week1, JsonUtils.deserializeRecord(JsonUtils.serializeRecord(week1)));
    assertThrows(IllegalArgumentException.class, () -> JsonUtils.deserializeRecord("invalid json"));
  }
}