package weather;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU Honor Code.
 *
 */
public class WeatherPattern implements WeatherObserver, Iterable<WeatherDatum>
{

  protected String description;
  protected List<WeatherDatum> data;

  /**
   * Constructor that initializes with the given description.
   * 
   * @param description
   *          to initialize with
   */
  public WeatherPattern(final String description)
  {
    this.description = description;
    this.data = new ArrayList<WeatherDatum>();
  }

  /**
   * Getter for description.
   * 
   * @return description
   */
  public String getDescription()
  {
    return this.description;
  }

  /**
   * Getter for given Element.
   * 
   * @param index
   *          of element to get
   * @return element
   */
  public WeatherDatum getElement(final int index)
  {
    return this.data.get(index);
  }

  /**
   * Must add the given WeatherDatum to the collection if it is non-null, otherwise it must do
   * nothing.
   */
  @Override
  public void handleWeatherDatum(final WeatherDatum datum)
  {
    if (datum != null)
    {
      data.add(datum);
    }

  }

  @Override
  public Iterator<WeatherDatum> iterator()
  {
    return data.iterator();
  }

  /**
   * Must reset (i.e., remove all elements from) the collection.
   */
  @Override
  public void reset()
  {
    data.clear();

  }

  /**
   * Getter for size of collection.
   * 
   * @return size
   */
  public int size()
  {
    return data.size();
  }

}
