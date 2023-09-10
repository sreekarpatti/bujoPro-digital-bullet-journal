package cs3500.pa05.model;

import cs3500.pa05.model.records.JsonUtils;
import cs3500.pa05.model.records.WeekJson;
import java.nio.file.Path;

/**
 * Represents a file reader.
 */
public class FileReader {
  private Path path = null;

  /**
   * Instantiates a file reader
   *
   * @param path path of the file provided
   */
  public FileReader(Path path) {
    this.path = path;
  }

  /**
   * reads the file.
   *
   * @return the string
   */
  public WeekJson readBujo() {
    OutputFile outputFile = new OutputFile(path.toFile());
    String string = outputFile.toString();
    return JsonUtils.deserializeRecord(string);
  }
}
