package cs3500.pa05.model.records;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayInfo;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Time;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayJsonTest {
  private List<TaskJson> lot;
  private List<EventJson> loe;
  private DayJson day;

  private DayInfo di;

  private ArrayList<Task> lot2;
  private ArrayList<Event> loe2;

  @BeforeEach
  void setUp() {
    lot = new ArrayList<>();
    lot.add(new TaskJson("task1", "description1", "MONDAY", false));
    lot.add(new TaskJson("task2", "description2", "MONDAY", true));

    lot2 = new ArrayList<>();
    lot2.add(new Task("task1", "description1", Day.MONDAY, false));
    lot2.add(new Task("task2", "description2", Day.MONDAY, true));


    loe = new ArrayList<>();
    loe.add(new EventJson("event1", "description1", "MONDAY", new TimeJson(1, 2), 2));
    loe.add(new EventJson("event2", "description2", "MONDAY", new TimeJson(2, 3), 3));

    loe2 = new ArrayList<>();
    loe2.add(new Event("event1", "description1", Day.MONDAY, new Time(1, 2), 2));
    loe2.add(new Event("event2", "description2", Day.MONDAY, new Time(2, 3), 3));

    day = new DayJson(lot, loe, "MONDAY");
    di = new DayInfo(lot2, loe2, Day.MONDAY);
  }


  @Test
  void toDayInfo() {
    DayInfo di2 = day.toDayInfo();
    assertThat(di2).usingRecursiveComparison().isEqualTo(this.di);
  }

  @Test
  void tasks() {
    assertEquals(lot, day.tasks());
  }

  @Test
  void events() {
    assertEquals(loe, day.events());
  }

  @Test
  void day() {
    assertEquals("MONDAY", day.day());
  }
}