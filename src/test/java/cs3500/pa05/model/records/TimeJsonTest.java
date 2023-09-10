package cs3500.pa05.model.records;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeJsonTest {
  private TimeJson time1;

  @BeforeEach
  void setUp() {
    time1 = new TimeJson(1, 2);
  }

  @Test
  void toTime() {
    Time time2 = time1.toTime();
    assertThat(new Time(1, 2)).usingRecursiveComparison().isEqualTo(time2);
  }

  @Test
  void hour() {
    assertEquals(1, time1.hour());
  }

  @Test
  void minute() {
    assertEquals(2, time1.minute());
  }
}