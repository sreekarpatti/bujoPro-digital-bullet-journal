package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.records.TimeJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeTest {
  private Time time;

  @BeforeEach
  void setUp() {
    time = new Time(01, 30);
  }

  @Test
  void toTimeJson() {
    TimeJson tj = new TimeJson(01, 30);
    assertEquals(tj, time.toTimeJson());
  }

  @Test
  void testToString() {
    String expected = "1:30";
    assertEquals(expected, time.toString());
  }
}