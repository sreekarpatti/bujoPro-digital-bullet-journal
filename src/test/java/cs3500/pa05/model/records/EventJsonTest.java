package cs3500.pa05.model.records;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventJsonTest {
  private EventJson event1;
  private EventJson event2;
  private Event event3;
  private Event event4;

  @BeforeEach
  void setUp() {
    event1 = new EventJson("event1", "description1", "MONDAY", new TimeJson(1, 2), 2);
    event2 = new EventJson("event2", "description2", "MONDAY", new TimeJson(2, 3), 3);
    event3 = new Event("event1", "description1", Day.MONDAY, new Time(1, 2), 2);
    event4 = new Event("event2", "description2", Day.MONDAY, new Time(2, 3), 3);
  }

  @Test
  void toEvent() {
    Event event5 = event1.toEvent();
    assertThat(event5).usingRecursiveComparison().isEqualTo(event3);
    Event event6 = event2.toEvent();
    assertThat(event6).usingRecursiveComparison().isEqualTo(event4);
  }

  @Test
  void name() {
    assertEquals("event1", event1.name());
    assertEquals("event2", event2.name());
  }

  @Test
  void description() {
    assertEquals("description1", event1.description());
    assertEquals("description2", event2.description());
  }

  @Test
  void day() {
    assertEquals("MONDAY", event1.day());
    assertEquals("MONDAY", event2.day());
  }

  @Test
  void start() {
    assertEquals(new TimeJson(1, 2), event1.start());
    assertEquals(new TimeJson(2, 3), event2.start());
  }

  @Test
  void duration() {
    assertEquals(2, event1.duration());
    assertEquals(3, event2.duration());
  }
}