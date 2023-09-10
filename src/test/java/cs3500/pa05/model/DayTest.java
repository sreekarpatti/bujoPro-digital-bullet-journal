package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTest {
  private String mon;
  private String tue;
  private String wen;
  private String thu;
  private String fri;
  private String sat;
  private String sun;
  private Day[] days;

  @BeforeEach
  void setup() {
    mon = "MONDAY";
    tue = "TUESDAY";
    wen = "WEDNESDAY";
    thu = "THURSDAY";
    fri = "FRIDAY";
    sat = "SATURDAY";
    sun = "SUNDAY";
    days = new Day[7];
    days[0] = Day.MONDAY;
    days[1] = Day.TUESDAY;
    days[2] = Day.WEDNESDAY;
    days[3] = Day.THURSDAY;
    days[4] = Day.FRIDAY;
    days[5] = Day.SATURDAY;
    days[6] = Day.SUNDAY;
  }

  @Test
  void fromString() {
    assertEquals(Day.MONDAY, Day.fromString(mon));
    assertEquals(Day.TUESDAY, Day.fromString(tue));
    assertEquals(Day.WEDNESDAY, Day.fromString(wen));
    assertEquals(Day.THURSDAY, Day.fromString(thu));
    assertEquals(Day.FRIDAY, Day.fromString(fri));
    assertEquals(Day.SATURDAY, Day.fromString(sat));
    assertEquals(Day.SUNDAY, Day.fromString(sun));
  }

  @Test
  void values() {
    assertEquals(days[0], Day.values()[0]);
    assertEquals(days[1], Day.values()[1]);
    assertEquals(days[2], Day.values()[2]);
    assertEquals(days[3], Day.values()[3]);
    assertEquals(days[4], Day.values()[4]);
    assertEquals(days[5], Day.values()[5]);
    assertEquals(days[6], Day.values()[6]);
  }

  @Test
  void valueOf() {
    assertEquals(Day.MONDAY, Day.valueOf(mon));
    assertEquals(Day.TUESDAY, Day.valueOf(tue));
    assertEquals(Day.WEDNESDAY, Day.valueOf(wen));
    assertEquals(Day.THURSDAY, Day.valueOf(thu));
    assertEquals(Day.FRIDAY, Day.valueOf(fri));
    assertEquals(Day.SATURDAY, Day.valueOf(sat));
    assertEquals(Day.SUNDAY, Day.valueOf(sun));
  }
}