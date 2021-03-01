package weather;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU Honor Code.
 *
 */
public interface WeatherSubject
{
  /**
   * Adds the observer to this subjects Collection.
   * 
   * @param observer
   *          to add
   */
  public void addObserver(final WeatherObserver observer);

  /**
   * Notify's all observers in this Collection.
   * 
   * @param weather
   *          WeatherDatum object
   */
  public void notifyObservers(final WeatherDatum weather);

  /**
   * Removes given observer.
   * 
   * @param observer
   *          to remove
   */
  public void removeObserver(final WeatherObserver observer);
}
