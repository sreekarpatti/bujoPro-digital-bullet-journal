package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputFileTest {
  private File file;
  private OutputFile outputFile;

  @BeforeEach
  void setUp() {
    file = new File("sampleData/outputFile.txt");
    outputFile = new OutputFile(file);
  }

  @Test
  void writeFile() {
    String expected = "";
    outputFile.writeFile("hello");

    try {
      expected = Files.readString(file.toPath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals("hello", expected);

  }

  @Test
  void testToString() {
    String expected = "hello";

    assertEquals(expected, outputFile.toString());

  }
}