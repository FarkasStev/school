package weather.io;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import weather.WeatherDatum;
import weather.WeatherObserver;
import weather.WeatherSubject;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU Honor Code.
 *
 */
public abstract class WeatherDatumReader implements WeatherSubject
{

  protected BufferedReader reader;
  protected List<WeatherObserver> observers;

  /**
   * Constructor that initializes the reade to the given one.
   * 
   * @param reader
   *          to initialize with
   */
  public WeatherDatumReader(final BufferedReader reader)
  {
    this.reader = reader;
    this.observers = new ArrayList<WeatherObserver>();
  }

  @Override
  public void addObserver(final WeatherObserver observer)
  {
    observers.add(observer);

  }

  @Override
  public void notifyObservers(final WeatherDatum weather)
  {
    Iterator<WeatherObserver> i = observers.iterator();
    while (i.hasNext())
    {
      WeatherObserver observer = i.next();
      observer.handleWeatherDatum(weather);
    }
  }

  @Override
  public void removeObserver(final WeatherObserver observer)
  {
    observers.remove(observer);

  }

  /**
   * Reads a WeatherDatum Object from this object reader.
   * 
   * @return the WeatherDatum object
   * @throws IOException
   *           when issue occurs reading from file
   */
  protected abstract WeatherDatum readWeatherDatum() throws IOException;

  /**
   * Must read a single WeatherDatum object (using the abstract readWeatherDatum()method) and notify
   * all of the observers (but only if the WeatherDatum object is non-null).
   * 
   * @throws IOException
   *           when issue occurs reading from file
   */
  public void readOne() throws IOException
  {
    if (!observers.isEmpty())
    {
      WeatherDatum datum = readWeatherDatum();
      if (datum != null)
        notifyObservers(datum);
    }

  }

  /**
   * Must read all of the WeatherDatum object (using the abstract readWeatherDatum()method, until it
   * returns null) and notify all of the observers.
   * 
   * @throws IOException
   *           when issue occurs reading from file
   */
  public void readAll() throws IOException
  {
    WeatherDatum datum = readWeatherDatum();
    while ((datum = readWeatherDatum()) != null)
    {
      notifyObservers(datum);
    }

  }

}
