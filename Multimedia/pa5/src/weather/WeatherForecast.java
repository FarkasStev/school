package weather;

import java.util.*;
import measurement.*;

/**
 * A weather forecast.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class WeatherForecast extends WeatherDatum
{
  protected Temperature high, low;

  /**
   * Default Constructor.
   */
  public WeatherForecast()
  {
    this("XXX", "Unknown", new Temperature(), new Temperature());
  }

  /**
   * Explicit Value Constructor.
   *
   * @param location The location
   * @param condition The weather condition (e.g., "Sunny")
   * @param low The forecasted low Temperature
   * @param high The forecasted high Temperature
   */
  public WeatherForecast(final String location, final String condition, 
      final Temperature low, final Temperature high)
  {
    super(location, condition);

    this.low = low;
    this.high = high;
  }

  /**
   * Create a WeatherForecast object from a String representation.
   *
   * @param s The String representation
   * @return A new WeatherForecast object
   */
  public static WeatherForecast createWeatherForecast(final String s)
  {
    WeatherForecast wf = new WeatherForecast();
    wf.fromString(s);

    return wf;
  }

  /**
   * Parse a String representation of a WeatherForecast into its components and
   * set the attributes of this WeatherForecast appropriately.
   *
   * @param s The String representation
   * @return The StringTokenizer used (in case more parsing is needed)
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
      this.low = Temperature.createTemperature(temp);

      temp = tokenizer.nextToken();
      this.high = Temperature.createTemperature(temp);
      
      return tokenizer;

    }
    catch (NoSuchElementException nsee)
    {
      return null;
    }
  }

  /**
   * Return the forecasted high temperature.
   *
   * @return The high temperature
   */
  public Temperature getHigh()
  {
    return high;
  }

  /**
   * Return the forecasted low temperature.
   *
   * @return The low temperature
   */
  public Temperature getLow()
  {
    return low;
  }

  /**
   * Get either a verbose or terse String representation of this WeatherDatum.
   *
   * The terse representation is in a format that can be processed by the fromString() method. The
   * verbose representation is tab-delimited and includes descriptions of the various attributes
   * (e.g., "Low: 75 High: 89")
   *
   * @param verbose true for a verbose String; false for a terse String
   * @return The String representation
   */
  @Override
  public String toString(final boolean verbose)
  {
    String result = super.toString(verbose);

    if (verbose) result += String.format("\tLow: %s\tHigh: %s", low.toString(), high.toString());
    else result += String.format(",%s,%s", low.toString(), high.toString());

    return result;
  }
}
