package cs3500.pa05.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import cs3500.pa05.model.records.DayJson;
import cs3500.pa05.model.records.WeekJson;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderTest {
  private FileReader fileReader;

  @BeforeEach
  void setup() {
    fileReader = new FileReader(Path.of("sampleData/empty.bujo"));
  }

  @Test
  void readBujo() {
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

    WeekJson wj = new WeekJson("", daysJson, 2, 2, "123");


    assertThat(wj).usingRecursiveComparison().isEqualTo(fileReader.readBujo());
  }
}