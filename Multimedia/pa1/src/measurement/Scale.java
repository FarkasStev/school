package measurement;

/**
 * @author Steven Farkas
 *
 *         I have neither given nor received any unauthorized aid on this assignment. This
 *         assignment complies with the JMU Honor Code.
 */
public enum Scale
{
  C("C", "Celsius"), F("F", "Fahrenheit");

  private String abbreviation;
  private String description;

  /**
   * @param abbreviation
   *          abbreviation of Scale
   * @param description
   *          description of Scale
   */
  private Scale(final String abbreviation, final String description)
  {
    this.abbreviation = abbreviation;
    this.description = description;
  }

  /**
   * Parses a terse or verbose String representation (ignoring case) of a Scale and returns the
   * appropriate instance. If the String is neither a terse nor verbose representation (ignoring
   * case), then returns null.
   * 
   * @param s
   *          String version of scale to create
   * @return Scale instance
   */
  public static Scale createScale(final String s)
  {
    if (s == null)
    {
      return null;
    }

    if (s.compareToIgnoreCase("f") == 0 || s.compareToIgnoreCase("fahrenheit") == 0)
    {
      return Scale.F;
    }
    else if (s.compareToIgnoreCase("c") == 0 || s.compareToIgnoreCase("celsius") == 0)
    {
      return Scale.C;
    }
    return null;
  }

  /**
   * Returns the description if verbose is true and must return the abbreviation otherwise.
   * 
   * @param verbose
   *          true if verbose
   * @return string representation
   */
  public String toString(final Boolean verbose)
  {
    if (verbose)
    {
      return description;
    }
    return abbreviation;

  }

  /**
   * Returns a terse String representation of the owning instance.
   * 
   * @return terse String
   */
  public String toString()
  {
    return abbreviation;
  }
}
