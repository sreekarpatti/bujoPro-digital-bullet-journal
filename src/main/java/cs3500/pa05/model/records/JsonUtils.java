package cs3500.pa05.model.records;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a utility class for converting records to and from JSON.
 */
public class JsonUtils {
  /**
   * Converts a given record object to a String.
   *
   * @param record the record to convert
   * @return the String representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static String serializeRecord(Record record) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      String string = mapper.convertValue(record, JsonNode.class).toString();
      return string;
    } catch (Exception e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   * Converts a given String to a record object.
   *
   * @param jsonString the String to convert
   * @return the record
   */
  public static WeekJson deserializeRecord(String jsonString) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree(jsonString);
      return mapper.treeToValue(node, WeekJson.class);
    } catch (Exception e) {
      throw new IllegalArgumentException("Given record cannot be deserialized");
    }
  }
}
