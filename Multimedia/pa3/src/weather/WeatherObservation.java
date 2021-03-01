package weather;

import java.util.StringTokenizer;

import measurement.Temperature;

/**
 * @author Steven Farkas
 * 
 *         I have neither given nor received any unauthorized aid on this assignment. This
 *         assignment complies with the JMU Honor Code.
 *
 */
public class WeatherObservation extends WeatherDatum
{

  Temperature temperature;

  /**
   * 
   */
  public WeatherObservation()
  {
    super("XXX", "Unknown");
    this.temperature = new Temperature();
  }

  /**
   * @param location
   *          to initialize with
   * @param condition
   *          to initialize with
   * @param temperature
   *          to initialize with
   */
  public WeatherObservation(final String location, final String condition,
      final Temperature temperature)
  {
    super(location, condition);
    if (temperature != null)
    {
      this.temperature = temperature;
    }
    else
    {
      this.temperature = new Temperature();
    }
  }

  /**
   * Creates a WeatherObservation object from a terse String representation.
   * 
   * @param s
   *          String to generate from
   * @return WeatherObservation object that was generated
   */
  public static WeatherObservation createWeatherObservation(final String s)
  {
    WeatherObservation temp = new WeatherObservation();
    temp.fromString(s);
    return temp;
  }

  /**
   * A setter method that parses the terse String representation of a WeatherObservation and sets
   * the attributes of the owning object accordingly. If the String representation contains too few
   * fields then this method leaves the remaining attributes of the owning object unchanged and
   * return null. Otherwise, it returns the StringTokenizer object that was used to tokenize the
   * String. If the String representation of the temperature attribute is invalid, it changes the
   * temperature attribute to the default Temperature.
   * 
   * @param s
   *          String to generate WeatherObservation object from
   * @return StringTokenizer used to update this WeatherObservation object
   */
  public StringTokenizer fromString(final String s)
  {
    StringTokenizer tokenizer = super.fromString(s);
    if (tokenizer == null)
    {
      tokenizer = null;
    }
    if (tokenizer != null && tokenizer.hasMoreElements())
    {
      temperature = Temperature.createTemperature(tokenizer.nextToken());
    }
    else
    {
      tokenizer = null;
    }
    return tokenizer;
  }

  /**
   * @return tempurature
   */
  public Temperature getTemperature()
  {
    return temperature;
  }

  /**
   * Returns either a terse or verbose String representation of the owning object. The terse
   * representation consists of the terse representation of the parent class followed by a comma and
   * the String representation of the temperature. For example:
   * 
   * "PWW02,Sunny, +86.7F"
   * 
   * The verbose representation consists of the verbose representation of the parent class followed
   * by a tab, followed by the String literal "Temperature: " (note there must be exactly one space
   * after the colon), followed by the String representation of the temperature. For example:
   * 
   * "Location: PWW02\tCondition: Sunny\tTemperature: +86.7F"
   * 
   * @param verbose
   *          boolean used to specify if String representation should be verbose or terse
   * @return String representation
   */
  public String toString(final boolean verbose)
  {
    if (verbose)
    {
      return super.toString(verbose) + "\t" + "Temperature: " + temperature.toString();
    }
    return super.toString(verbose) + "," + temperature.toString();
  }

}
