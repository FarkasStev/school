package weather;

import java.util.*;

/**
 * A type safe ordered collection of WeatherDatum objects.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class WeatherPattern implements Iterable<WeatherDatum>, WeatherObserver
{
  protected String description;
  protected List<WeatherDatum> data;

  /**
   * Explicit Value Constructor.
   *
   * Note: The description must follow a specific format.
   *
   * @param description  A description of the WeatherPattern
   */
  public WeatherPattern(final String description)
  {
    this.description = description;
    data = new ArrayList<WeatherDatum>();
  }

  /**
   * Get the description of this WeatherPattern.
   *
   * @return The description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Get a WeatherDatum in this WeatherPattern.
   *
   * @param index The index of the WeatherDatum
   * @return The WeatherDatum
   */
  public WeatherDatum getElement(final int index)
  {
    return data.get(index);
  }
  
  /**
   * Handle a new/changed WeatherDatum object.
   *
   * @param datum  The new/changed WeatherDatum object
   */
  @Override
  public void handleWeatherDatum(final WeatherDatum datum)
  {
    if (datum != null) data.add(datum);
  }
  
  /**
   * Returns an Iterator of the elements of this WeatherPattern.
   *
   * @return The Iterator of the elements
   */
  @Override
  public Iterator<WeatherDatum> iterator()
  {
    return data.iterator();
  }
  
  /**
   * Reset this WeatherPattern.
   */
  @Override
  public void reset()
  {
    data.clear();
  }

  /**
   * Get the size of this WeatherPattern.
   *
   * @return The size
   */
  public int size()
  {
    return data.size();
  }
}
