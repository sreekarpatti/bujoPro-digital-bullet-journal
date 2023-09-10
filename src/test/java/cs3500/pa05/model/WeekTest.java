package cs3500.pa05.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.records.DayJson;
import cs3500.pa05.model.records.WeekJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {
  private Week week1;

  @BeforeEach
  void setUp() {
    week1 = new Week();
  }

  @Test
  void getPassword() {
    week1.setPassword("12345");
    assertEquals("12345", week1.getPassword());
  }

  @Test
  void setPassword() {
    week1.setPassword("12345");
    assertEquals("12345", week1.getPassword());
  }

  @Test
  void getMaxEvents() {
    week1.setMaxEvents(10);
    assertEquals(10, week1.getMaxEvents());
  }

  @Test
  void setMaxEvents() {
    week1.setMaxEvents(10);
    assertEquals(10, week1.getMaxEvents());
  }

  @Test
  void setMaxTasks() {
    week1.setMaxTasks(10);
    assertEquals(10, week1.getMaxTasks());
  }

  @Test
  void getMaxTasks() {
    week1.setMaxTasks(10);
    assertEquals(10, week1.getMaxTasks());
  }

  @Test
  void getLot() {
    week1.addTask("task1", "something", Day.WEDNESDAY);
    week1.addTask("task2", "something", Day.WEDNESDAY);
    week1.addTask("task3", "something", Day.WEDNESDAY);

    ArrayList<Task> lot = new ArrayList<>();
    lot.add(new Task("task1", "something", Day.WEDNESDAY));
    lot.add(new Task("task2", "something", Day.WEDNESDAY));
    lot.add(new Task("task3", "something", Day.WEDNESDAY));

    assertEquals(lot.size(), week1.getLot().size());
  }

  @Test
  void toWeekJson() {
    ArrayList<DayInfo> days = new ArrayList<DayInfo>();
    days.add(new DayInfo(Day.SUNDAY));
    days.add(new DayInfo(Day.MONDAY));
    days.add(new DayInfo(Day.TUESDAY));
    days.add(new DayInfo(Day.WEDNESDAY));
    days.add(new DayInfo(Day.THURSDAY));
    days.add(new DayInfo(Day.FRIDAY));
    days.add(new DayInfo(Day.SATURDAY));

    ArrayList<DayJson> daysJson = new ArrayList<DayJson>();
    for (DayInfo day : days) {
      daysJson.add(day.toDayJson());
    }

    WeekJson wj = new WeekJson("", daysJson, 2, 2, null);
    assertEquals(wj, week1.toWeekJson());
  }

  @Test
  void setNotes() {
    week1.setNotes("something");
    assertEquals("something", week1.getNotes());
  }

  @Test
  void getNotes() {
    week1.setNotes("something");
    assertEquals("something", week1.getNotes());
  }

  @Test
  void getDay() {
    ArrayList<DayInfo> days = new ArrayList<DayInfo>();
    days.add(new DayInfo(Day.SUNDAY));
    days.add(new DayInfo(Day.MONDAY));
    days.add(new DayInfo(Day.TUESDAY));
    days.add(new DayInfo(Day.WEDNESDAY));
    days.add(new DayInfo(Day.THURSDAY));
    days.add(new DayInfo(Day.FRIDAY));
    days.add(new DayInfo(Day.SATURDAY));

    assertThat(days.get(0)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.SUNDAY));
    assertThat(days.get(1)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.MONDAY));
    assertThat(days.get(2)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.TUESDAY));
    assertThat(days.get(3)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.WEDNESDAY));
    assertThat(days.get(4)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.THURSDAY));
    assertThat(days.get(5)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.FRIDAY));
    assertThat(days.get(6)).usingRecursiveComparison().isEqualTo(week1.getDay(Day.SATURDAY));
  }

  @Test
  void addEvent() {
    Event e = new Event("event1", "something", Day.WEDNESDAY, new Time(11, 30), 2);
    week1.addEvent("event1", "something", Day.WEDNESDAY, new Time(11, 30), 2);

    assertThat(e).usingRecursiveComparison()
        .isEqualTo(week1.getDay(Day.WEDNESDAY).getEvents().get(0));
  }

  @Test
  void addTask() {
    Task t = new Task("task1", "something", Day.WEDNESDAY);
    week1.addTask("task1", "something", Day.WEDNESDAY);

    assertThat(t).usingRecursiveComparison()
        .isEqualTo(week1.getDay(Day.WEDNESDAY).getTasks().get(0));
  }

  @Test
  void getEvents() {
    week1.addEvent("event1", "something", Day.WEDNESDAY, new Time(11, 30), 2);
    week1.addEvent("event2", "something", Day.WEDNESDAY, new Time(11, 30), 2);
    week1.addEvent("event3", "something", Day.WEDNESDAY, new Time(11, 30), 2);

    ArrayList<Event> events = new ArrayList<>();
    events.add(new Event("event1", "something", Day.WEDNESDAY, new Time(11, 30), 2));
    events.add(new Event("event2", "something", Day.WEDNESDAY, new Time(11, 30), 2));
    events.add(new Event("event3", "something", Day.WEDNESDAY, new Time(11, 30), 2));

    assertThat(events).usingRecursiveComparison().isEqualTo(week1.getEvents());


  }

  @Test
  void getCompletedTasks() {
    week1.addTask("task1", "something", Day.WEDNESDAY);
    week1.addTask("task2", "something", Day.WEDNESDAY);
    week1.addTask("task3", "something", Day.WEDNESDAY);

    week1.getDay(Day.WEDNESDAY).getTasks().get(0).setComplete(true);
    week1.getDay(Day.WEDNESDAY).getTasks().get(1).setComplete(false);
    week1.getDay(Day.WEDNESDAY).getTasks().get(2).setComplete(true);

    assertEquals(2, week1.getCompletedTasks());
  }
}