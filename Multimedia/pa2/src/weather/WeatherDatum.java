package weather;

import java.util.StringTokenizer;

/**
 * @author Steven Farkas
 *
 *         I have neither given nor received any unauthorized aid on this assignment. This
 *         assignment complies with the JMU Honor Code.
 */
public class WeatherDatum
{
  protected String condition;
  protected String location;

  /**
   * @param location
   *          to initialize with
   * @param condition
   *          to initialize with
   */
  public WeatherDatum(final String location, final String condition)
  {
    this.location = location;
    this.condition = condition;
  }

  /**
   * A setter method that parses the terse String representation of a WeatherDatum and sets the
   * attributes of the owning object accordingly. If the String representation contains too few
   * fields then this method must leave the remaining attributes of the owning object unchanged and
   * return null. Otherwise, it must return the StringTokenizer object that was used to tokenize the
   * String.
   * 
   * @param s
   *          String to update from
   * @return the String tokenizer used to update this
   */
  protected StringTokenizer fromString(final String s)
  {
    StringTokenizer tokenizer = null;

    if (s != null)
    {
      tokenizer = new StringTokenizer(s, ",");

      if (tokenizer.hasMoreElements())
      {
        location = tokenizer.nextToken();
      }
      else
      {
        tokenizer = null;
      }
      if (tokenizer != null && tokenizer.hasMoreElements())
      {
        condition = tokenizer.nextToken();
      }
      else
      {
        tokenizer = null;
      }
    }
    return tokenizer;
  }

  /**
   * @return condition
   */
  public String getCondition()
  {
    return condition;
  }

  /**
   * @return location
   */
  public String getLocation()
  {
    return location;
  }

  /**
   * Returns either a terse or verbose String representation of the owning object. The terse
   * representation must consist of the location, followed by a comma and the condition. For
   * example:
   * 
   * "PWW02,Sunny"
   * 
   * The verbose representation must consist of the String"Location: " (note there must be exactly
   * one space after the colon), followed by the location, followed by a tab, followed by the String
   * "Condition: " (note there must be exactly one space after the colon), followed by the
   * condition. For example:
   * 
   * "Location: PWW02\tCondition: Sunny"
   * 
   * @param verbose
   *          boolean used to specify if String representation should be verbose or terse
   * @return String representation
   */
  public String toString(final boolean verbose)
  {
    String output = "";
    if (verbose)
    {
      output = "Location: " + location + "\tCondition: " + condition;
    }
    else
    {
      output = location + ',' + condition;
    }
    return output;
  }

  /**
   * Must return a terse String representation of the owning object.
   * 
   * @return String representation
   */
  public String toString()
  {
    return toString(false);
  }

}
