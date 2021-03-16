package weather;

/**
 * Requirements of an object that is informed of new/changed WeatherDatum objects.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public interface WeatherObserver
{
  /**
   * Reset this WeatherObserver.
   */
  public abstract void reset();
  
  /**
   * Handle a new/changed WeatherDatum object.
   *
   * @param datum  The new/changed WeatherDatum object
   */
  public abstract void handleWeatherDatum(final WeatherDatum datum);
}
