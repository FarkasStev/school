package measurement;

/**
 * An enumeration of the different types of temperature scales supported by WeatherBits.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public enum Scale
{
  C("C", "Celsius"), F("F", "Fahrenheit");

  private final String abbreviation, description;

  /**
   * Explicit Value Constructor.
   *
   * @param abbreviation The standard abbreviation
   * @param description The description
   */
  private Scale(final String abbreviation, final String description)
  {
    this.abbreviation = abbreviation;
    this.description = description;
  }

  /**
   * Parse a String representation of a Scale and return the corresponding Scale.
   *
   * This method returns null if the String does not correspond to a valid Scale.
   *
   * @param s The String representation
   * @return The Scale (or null)
   */
  public static Scale createScale(final String s)
  {
    if (s == null) return null;
    
    for (Scale m : Scale.values())
    {
      if (s.equalsIgnoreCase(m.abbreviation)) return m;
      if (s.equalsIgnoreCase(m.description))  return m;
    }

    return null;
  }

  /**
   * Return a terse or verbose String representation of this Scale.
   *
   * @param verbose  true for a verbose description; false otherwise
   * @return The String
   */
  public String toString(final boolean verbose)
  {
    if (verbose) return description;
    else return abbreviation;
  }

  /**
   * Return a terse String representation of this Scale.
   *
   * @return The String
   */
  @Override
  public String toString()
  {
    return toString(false);
  }

}
