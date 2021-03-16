package measurement;

/**
 * An encapsulation of a temperature that includes degrees and units/scale (e.g., Celsius or
 * Fahrenheit).
 *
 * @version 1.0
 * @author Prof. David Bernstein, James Madison University
 */
public class Temperature implements Comparable<Temperature>
{
  private static final int FIELD_WIDTH = 6;
  private static final int DECIMALS = 1;
  private static final String FORMAT = "%+" + FIELD_WIDTH + "." + DECIMALS + "f%s"; // "%+6.1f%s"
  
  private Scale scale;
  private double degrees;

  /**
   * Default Constructor.
   */
  public Temperature()
  {
    this(0.0, Scale.F);
  }

  /**
   * Copy Constructor.
   * 
   * @param other  The Temperature to copy
   */
  public Temperature(final Temperature other)
  {
    this(other.degrees, other.scale);
  }

  /**
   * Explicit Value Constructor.
   *
   * @param degrees The number of degrees (in Fahrenheit)
   */
  public Temperature(final double degrees)
  {
    this(degrees, Scale.F);
  }

  /**
   * Explicit Value Constructor.
   *
   * @param degrees The number of degrees
   * @param scale The scale (e.g., "C" or "F")
   */
  public Temperature(final double degrees, final String scale)
  {
    this(degrees, Scale.createScale(scale));
  }

  /**
   * Explicit Value Constructor.
   *
   * @param degrees The number of degrees
   * @param scale The scale (e.g., Scale.C or Scale.F)
   */
  public Temperature(final double degrees, final Scale scale)
  {
    this.degrees = degrees;
    this.scale = scale;

    if (this.scale == null) this.scale = Scale.F;
  }

  /**
   * Create a Temperature object from a String representation.
   *
   * @param s The String representation (e.g., 32.0F or 100.0C)
   * @return A new Temperature object
   */
  public static Temperature createTemperature(final String s)
  {
    Temperature temp;

    temp = new Temperature();
    temp.fromString(s);

    return temp;
  }

  /**
   * Compares this Temperature to another Temperature.
   *
   * @param other The other Temperature
   * @return 1 if this > other, -1 if this < other, 0 if ==
   */
  @Override
  public int compareTo(final Temperature other)
  {
    double thisF, otherF;
    int comp;

    thisF = getDegrees(Scale.F);
    otherF = other.getDegrees(Scale.F);

    comp = 0;
    if (thisF > otherF)
      comp = 1;
    else if (thisF < otherF)
      comp = -1;

    return comp;
  }

  /**
   * Decrease this temperature by the given temperature (accounting for scale differences).
   *
   * @param amount The amount of the decrease
   */
  public void decreaseBy(final Temperature amount)
  {
    degrees -= amount.getDegrees(scale);
  }

  /**
   * Parse a String representation of a Temperature (and stores the values in this Temperature). If
   * there is a problem with the String representation, leave this String unchanged.
   *
   * @param s The String representation
   */
  public void fromString(final String s)
  {
    if (s.length() == (FIELD_WIDTH + 1))
    {
      int signIndex = s.indexOf('-');
      if (signIndex < 0) signIndex = s.indexOf('+');
      if (signIndex < 0) return;

      int endIndex = s.length() - 1;

      String sScale = s.substring(endIndex);
      String sTemperature = s.substring(0, endIndex);
      Scale givenScale = Scale.createScale(sScale);

      if (givenScale != null)
      {
        try
        {
          degrees = Double.parseDouble(sTemperature);
          scale = givenScale;
        }
        catch (NumberFormatException nfe)
        {
          // Don't change the degrees
        }
      }
    }
  }

  /**
   * Get the degrees in the requested scale.
   *
   * @param scaleToUse The scale to use
   * @return The temperature in the requested scale
   */
  private double getDegrees(final Scale scaleToUse)
  {
    double t;

    t = 0.0;
    if (scaleToUse == this.scale)
      t = degrees;
    else if (scaleToUse == Scale.C)
      t = 5.0 / 9.0 * (degrees - 32.0); // F to C
    else // scaleToUse == Scale.F
      t = 9.0 / 5.0 * degrees + 32.0; // C to F

    return t;
  }

  /**
   * Increase this temperature by the given temperature (accounting for scale differences).
   *
   * @param amount The amount of the increase
   */
  public void increaseBy(final Temperature amount)
  {
    degrees += amount.getDegrees(scale);
  }

  /**
   * Return a String representation of this Temperature (using its scale).
   *
   * @return The String representation
   */
  @Override
  public String toString()
  {
    return toString(this.scale);
  }

  /**
   * Return a String representation of this Temperature using the given scale.
   *
   * @param scaleToUse The scale to use
   * @return The String representation
   */
  public String toString(final Scale scaleToUse)
  {
    return String.format(FORMAT, getDegrees(scaleToUse), scaleToUse.toString());
  }
}
