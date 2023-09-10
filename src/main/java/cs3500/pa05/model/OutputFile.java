package cs3500.pa05.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Represents an Output File
 */
public class OutputFile {
  private File create;

  /**
   * Instantiates an Output File
   *
   * @param create create represents the new file to be made
   */
  public OutputFile(File create) {
    this.create = create;
  }

  /**
   * writes to the file all the important information needed for the study guide
   *
   * @param string string is the string to be written to the file
   */
  public void writeFile(String string) {
    try {
      FileWriter myWriter = new FileWriter(create);
      myWriter.write(string);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  /**
   * returns the string of the file
   *
   * @return the string of the file
   */
  public String toString() {
    byte[] bytes = new byte[0];
    try {
      bytes = Files.readAllBytes(Path.of(create.getPath()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return new String(bytes);
  }
}
