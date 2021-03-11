package weather;

import java.util.*;


/**
 * An abstract piece of weather information.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public abstract class WeatherDatum
{
  protected static final String COMMA = ",";
  protected String condition, location;

  /**
   * Common explicit value constructor for all derived classes.
   *
   * @param location     The location
   * @param condition    The weather condition (e.g., "Sunny")
   */
  public WeatherDatum(final String location, final String condition)
  {
    this.location = location;
    this.condition = condition;
  }

  /**
   * Return the weather condition.
   *
   * @return The condition
   */
  public String getCondition()
  {
    return condition;
  }

  /**
   * Return the location.
   *
   * @return The location
   */
  public String getLocation()
  {
    return location;
  }

  /**
   * Parse a String representation of a WeatherDatum into its components and
   * set the attributes of this WeatherForecast appropriately.
   * 
   * This method is only useful for derived classes.
   *
   * @param s The String representation
   * @return The StringTokenizer used (in case more parsing is needed) or null 
   */
  protected StringTokenizer fromString(final String s)
  {
    StringTokenizer tokenizer;

    tokenizer = new StringTokenizer(s, COMMA);

    try
    {
      location = tokenizer.nextToken();
      condition = tokenizer.nextToken();
      
      return tokenizer;
    }
    catch (NoSuchElementException nsee)
    {
      return null;
    }
  }

  /**
   * Get either a verbose or terse String representation of this WeatherDatum.
   *
   * The terse representation is in a format that can be processed by the fromString() method. The
   * verbose representation is tab-delimited and includes descriptions of the various attributes
   * (e.g., "Location: CVA01 Condition: Sunny")
   *
   * @param verbose true for a verbose String; false for a terse String
   * @return The String representation
   */
  public String toString(final boolean verbose)
  {
    String result;

    if (verbose) 
      result = String.format("Location: %s\tCondition: %s", 
          location.toString(), condition.toString());
    else
      result = String.format("%s,%s", 
          location.toString(), condition.toString());

    return result;
  }

  /**
   * Return a terse String representation of this WeatherDatum.
   *
   * @return The String representation
   */
  @Override
  public String toString()
  {
    return toString(false);
  }
}
