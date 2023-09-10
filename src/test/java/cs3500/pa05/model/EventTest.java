package cs3500.pa05.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.records.EventJson;
import cs3500.pa05.model.records.TimeJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
  private Event event1;

  @BeforeEach
  void setup() {
    event1 = new Event("event1", "something", Day.WEDNESDAY,
        new Time(5, 10), 10);
  }

  @Test
  void toEventJson() {
    assertEquals(new EventJson("event1", "something", "WEDNESDAY",
        new TimeJson(5, 10), 10), event1.toEventJson());
  }

  @Test
  void getDay() {
    assertEquals(Day.WEDNESDAY, event1.getDay());
  }

  @Test
  void getName() {
    assertEquals("event1", event1.getName());
  }

  @Test
  void getDescription() {
    assertEquals("something", event1.getDescription());
  }

  @Test
  void getStartTime() {
    assertThat(new Time(5, 10)).usingRecursiveComparison().isEqualTo(event1.getStart());
  }

  @Test
  void getDuration() {
    assertEquals(10, event1.getDuration());
  }
}