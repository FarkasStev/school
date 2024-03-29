package weather;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU Honor Code.
 *
 */
public interface WeatherObserver
{
  
  /**
   * Resets all the elements of this WeatherObserver.
   */
  public void reset();

  /**
   * Adds the given WeatherDatum to the collection if it is non-null, otherwise it must do
   * nothing.
   * 
   * @param datum
   *          to add to the collection.
   */
  public void handleWeatherDatum(WeatherDatum datum);
}
