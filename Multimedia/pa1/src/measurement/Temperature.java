package measurement;

/**
 * @author Steven Farkas
 *
 *         I have neither given nor received any unauthorized aid on this assignment. This
 *         assignment complies with the JMU Honor Code.
 */
public class Temperature implements Comparable<Temperature>
{

  private double degrees;
  private Scale scale;

  /**
   * Constructs a Temperature of 0.0 degrees F.
   */
  public Temperature()
  {
    this(0.0, Scale.F);
  }

  /**
   * Constructs a copy of the given Temperature object.
   * 
   * @param other
   *          Tempurature to copy
   */
  public Temperature(final Temperature other)
  {
    this(other.degrees, other.scale);
  }

  /**
   * Constructs a Tempurature with the given degrees in Fahrenheit.
   * 
   * @param degrees
   *          degrees in fahrenheit to initialize with
   */
  public Temperature(final double degrees)
  {
    this(degrees, Scale.F);
  }

  /**
   * Constructs a Temperature object with the given degrees and Scale.
   * 
   * @param degrees
   *          to be used
   * @param scale
   *          to be used as a String
   */
  public Temperature(final double degrees, final String scale)
  {
    this(degrees, Scale.createScale(scale));
  }

  /**
   * Constructs a Temperature object with the given degrees and Scale.
   * 
   * @param degrees
   *          to be used
   * @param scale
   *          to be used as a String
   */
  public Temperature(final double degrees, final Scale scale)
  {
    this.degrees = degrees;
    this.scale = scale;

    if (this.scale == null)
    {
      this.scale = Scale.F;
    }
  }

  /**
   * Creates a Temperature object from the given terse String representation. Returns the default
   * Temperature if the String is not a valid terse representation.
   * 
   * @param s
   *          String to create Temperature from
   * @return Temperature object created
   */
  public static Temperature createTemperature(final String s)
  {
    Temperature temp;

    temp = new Temperature();
    temp.fromString(s);

    return temp;
  }

  @Override
  public int compareTo(final Temperature other)
  {
    if (degrees > other.convert(scale))
    {
      return 1;
    }
    else if (degrees == other.convert(scale))
    {
      return 0;
    }
    return -1;
  }

  /**
   * Private helper method that converts into the given Scale. Could easily be refactored to include
   * other scales.
   * 
   * @param other
   *          Scale to convert from
   * @return converted double value
   */
  private double convert(final Scale other)
  {

    if (scale.equals(Scale.F) && other.equals(Scale.C))
    {
      return (degrees - 32.0) * (5.0 / 9.0);
    }
    else if (scale.equals(Scale.C) && other.equals(Scale.F))
    {
      return (degrees * (9.0 / 5.0)) + 32.0;
    }
    return degrees;

  }

  /**
   * Decreases the owning Temperature by the given Temperature, accounting for Scale differences.
   * This method does not change the scale attribute of either Temperature.
   * 
   * @param amount
   *          converted
   */
  public void decreaseBy(final Temperature amount)
  {

    degrees -= amount.convert(scale);

  }

  /**
   * Parses a terse String representation of a Temperature and change the attributes of the owning
   * Temperature appropriately. If there is a problem with the String representation then it leaves
   * the attributes of the owning Temperature unchanged
   * 
   * @param s
   *          String representation to be changed to
   */
  public void fromString(final String s)
  {
    if (s != null && s.length() == (7))
    {
      int signIndex = s.indexOf('-');
      if (signIndex < 0)
        signIndex = s.indexOf('+');
      if (signIndex < 0)
        return;

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
   * Increases the owning Temperature by the given Temperature, accounting for Scale differences.
   * This method does not change the scale attribute of either Temperature.
   * 
   * @param amount
   *          increased by
   */
  public void increaseBy(final Temperature amount)
  {
    degrees += amount.convert(scale);
  }

  /**
   * Returns a terseString representation of the owning Temperature. The numeric part of the String
   * representation begins with a sign indicator, and is in a field of width 6 with one place to the
   * right of the decimal. The numeric part is followed immediately by the terse representation of
   * the scale attribute.
   * 
   * @return String representation
   */
  public String toString()
  {
    return toString(scale);
  }

  /**
   * Returns a terse String representation of the owning Temperature using the given Scale (which
   * may or may not be different from the Scale of the owning Temperature).
   * 
   * @param scaleToUse
   *          representation to use
   * @return terse String representation
   */
  public String toString(final Scale scaleToUse)
  {
    return String.format("%+6.1f" + scaleToUse.toString(), convert(scaleToUse));
  }

}
