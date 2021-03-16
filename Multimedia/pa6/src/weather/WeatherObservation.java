package weather;

import java.util.*;
import measurement.*;

/**
 * An observation of weather conditions for a particular location.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class WeatherObservation extends WeatherDatum
{
  protected Temperature temperature;

  /**
   * Default Constructor.
   */
  public WeatherObservation()
  {
    this("XXX", "Unknown", new Temperature());
  }

  /**
   * Explicit Value Constructor.
   *
   * @param location The location
   * @param condition The weather condition (e.g., "Sunny")
   * @param temperature The temperature
   */
  public WeatherObservation(final String location, final String condition, 
      final Temperature temperature)
  {
    super(location, condition);

    this.temperature = temperature;
  }

  /**
   * Create a WeatherObservation object from a String representation.
   *
   * @param s The String representation
   * @return A new WeatherObservation object
   */
  public static WeatherObservation createWeatherObservation(final String s)
  {
    WeatherObservation wo = new WeatherObservation();
    wo.fromString(s);

    return wo;
  }

  /**
   * Parse a String representation of a WeatherForecast into its components and
   * set the attributes of this WeatherForecast appropriately.
   *
   * @param s The String representation
   * @return The StringTokenizer used (in case more parsing is needed) or null
   */
  @Override
  public StringTokenizer fromString(final String s)
  {
    // Call the parent's fromString method to parse the
    // common elements
    StringTokenizer tokenizer = super.fromString(s);
    if (tokenizer == null) return null;

    try
    {
      String temp = tokenizer.nextToken();
      this.temperature = Temperature.createTemperature(temp);

      return tokenizer;
    }
    catch (NoSuchElementException nsee)
    {
      return null;
    }
  }

  /**
   * Return the temperature.
   *
   * @return The temperature
   */
  public Temperature getTemperature()
  {
    return temperature;
  }

  /**
   * Get either a verbose or terse String representation of this WeatherDatum.
   *
   * The terse representation is in a format that can be processed by the fromString() method. The
   * verbose representation is tab-delimited and includes descriptions of the various attributes.
   *
   * @param verbose true for a verbose String; false for a terse String
   * @return The String representation
   */
  @Override
  public String toString(final boolean verbose)
  {
    String result = super.toString(verbose);

    if (verbose) result += String.format("\t" + "Temperature: %s", temperature);
    else result += String.format(",%s", temperature);

    return result;
  }
}
