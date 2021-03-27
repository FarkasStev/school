package weather;

/**
 * Requirements of an object that notifies WeatherObserver objects of new/changed WeatherDatum
 * objects.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public interface WeatherSubject
{
  /**
   * Add a WeatherObserver to the list of observers.
   *
   * @param observer The WeatherObserver to add
   */
  public abstract void addObserver(final WeatherObserver observer);

  /**
   * Notify all registered WeatherObserver objects of a new/changed WeatherDatum object.
   *
   * @param weather   The new/changed WeatherDatum
   */
  public abstract void notifyObservers(final WeatherDatum weather);

  /**
   * Remove a WeatherObserver to the list of observers.
   *
   * @param observer The WeatherObserver to remove
   */
  public abstract void removeObserver(final WeatherObserver observer);
}
