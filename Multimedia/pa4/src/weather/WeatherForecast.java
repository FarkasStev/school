package weather;

import java.util.StringTokenizer;

import measurement.Temperature;

/**
 * @author Steven Farkas
 *
 *         I have neither given nor received any unauthorized aid on this assignment. This
 *         assignment complies with the JMU Honor Code.
 */
public class WeatherForecast extends WeatherDatum
{
  Temperature high;
  Temperature low;

  /**
   * 
   */
  public WeatherForecast()
  {
    super("XXX", "Unknown");
    high = new Temperature();
    low = new Temperature();
  }

  /**
   * @param location
   *          to initialize with
   * @param condition
   *          to initialize with
   * @param low
   *          Temperature
   * @param high
   *          Temperature
   */
  public WeatherForecast(final String location, final String condition, final Temperature low,
      final Temperature high)
  {
    super(location, condition);
    if (low != null)
    {
      this.low = low;
    }
    else
    {
      this.low = new Temperature();

    }
    if (high != null)
    {
      this.high = high;
    }
    else
    {
      this.high = new Temperature();
    }
  }

  /**
   * Creates a WeatherForecast object from a terse String representation.
   * 
   * @param s
   *          terse string representation
   * @return the WeatherForecast object
   */
  public static WeatherForecast createWeatherForecast(final String s)
  {
    WeatherForecast temp = new WeatherForecast();
    temp.fromString(s);
    return temp;
  }

  /**
   * A setter method that parses the terse String representation of a WeatherForecast object and
   * sets the attributes of the owning object accordingly. If the String representation contains too
   * few fields then this method leaves the remaining attributes of the owning object unchanged and
   * return null. Otherwise, it returns the StringTokenizer object that was used to tokenize the
   * String. If the String representation of the low attribute is invalid, it changes the low
   * attribute to the default Temperature. Similarly, if the String representation of the high
   * attribute is invalid, it changes the high attribute to the default Temperature.
   * 
   * @param s
   *          terse String representation of object ot create
   * @return the StringTokenizer used to update this object
   */
  public StringTokenizer fromString(final String s)
  {
    StringTokenizer tokenizer = super.fromString(s);

    if (tokenizer != null && tokenizer.hasMoreElements())
    {
      low = Temperature.createTemperature(tokenizer.nextToken());
    }
    else
    {
      tokenizer = null;
    }
    if (tokenizer != null && tokenizer.hasMoreElements())
    {
      high = Temperature.createTemperature(tokenizer.nextToken());
    }
    else
    {
      tokenizer = null;
    }
    return tokenizer;
  }

  /**
   * @return the high Temperature
   */
  public Temperature getHigh()
  {
    return high;
  }

  /**
   * @return the low Temperature
   */
  public Temperature getLow()
  {
    return low;
  }

  /**
   * Returns either a terse or verbose String representation of the owning object. The terse
   * representation consists of the terse representation of the parent class, followed by a comma,
   * followed by the String representation of the low, followed by the String representation of the
   * high. For example:
   * 
   * "PWW01,Sunny, +86.7F, +91.3F"
   * 
   * The verbose representation consists of the verbose representation of the parent class, followed
   * by a tab, followed by the String literal "Low: " (note there must be exactly one space after
   * the colon), followed by the String representation of the low, followed by a tab, followed by
   * the String literal "High: " (note there must be exactly one space after the colon), followed by
   * the String representation of the high. For example:
   * 
   * "Location: PWW02\tCondition: Sunny\tLow: +86.7F\tHigh: +91.3F".
   * 
   * @param verbose
   *          boolean describing if the String representation should be verbose or terse
   * @return the String representation
   */
  public String toString(final boolean verbose)
  {
    if (verbose)
    {
      return super.toString(verbose) + '\t' + "Low: " + low.toString() + '\t' + "High: "
          + high.toString();
    }
    return super.toString(verbose) + ',' + low.toString() + ',' + high.toString();

  }

}
