package cs3500.pa05.model;

/**
 * Represents a day of the week.
 */
public enum Day {
  /**
   * Represents Monday.
   */
  MONDAY,
  /**
   * Represents Tuesday.
   */
  TUESDAY,
  /**
   * Represents Wednesday.
   */
  WEDNESDAY,
  /**
   * Represents Thursday.
   */
  THURSDAY,
  /**
   * Represents Friday.
   */
  FRIDAY,
  /**
   * Represents Saturday.
   */
  SATURDAY,
  /**
   * Represents Sunday.
   */
  SUNDAY;

  /**
   * Converts a string to a Day.
   *
   * @param s the string
   * @return the Day
   */
  public static Day fromString(String s) {
    return switch (s) {
      case "MONDAY" -> MONDAY;
      case "TUESDAY" -> TUESDAY;
      case "WEDNESDAY" -> WEDNESDAY;
      case "THURSDAY" -> THURSDAY;
      case "FRIDAY" -> FRIDAY;
      case "SATURDAY" -> SATURDAY;
      case "SUNDAY" -> SUNDAY;
      default -> throw new IllegalArgumentException("Invalid day");
    };
  }
}


