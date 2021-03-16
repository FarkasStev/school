package weather.io;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import weather.*;


/**
 * An abstract class for reading WeatherDatum information from a BufferedReader.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public abstract class WeatherDatumReader implements WeatherSubject
{
  protected BufferedReader reader;
  protected List<WeatherObserver> observers;

  /**
   * Explicit Value Constructor (called by the constructor of a a derived class).
   * 
   * @param reader  The BufferedReader to read from
   */
  public WeatherDatumReader(final BufferedReader reader)
  {
    this.reader = reader;

    // A CopyOnWriteArrayList is used so that
    // observers can be notified while the collection is being modified
    observers = new CopyOnWriteArrayList<WeatherObserver>();
  }

  /**
   * Add a WeatherObserver to the list of observers (required by WeatherSubject).
   *
   * @param observer The WeatherObserver to add
   */
  @Override
  public void addObserver(final WeatherObserver observer)
  {
    observers.add(observer);
  }

  /**
   * Notify all registered WeatherObserver objects of a new/changed Weather object (required by
   * WeatherSubject).
   *
   * @param weather The new/changed WeatherDatum
   */
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

  /**
   * Remove a WeatherObserver from the list of observers (required by WeatherSubject).
   *
   * @param observer The WeatherObserver to remove
   */
  @Override
  public void removeObserver(final WeatherObserver observer)
  {
    observers.remove(observer);
  }
  
  /**
   * Read a WeatherDatum.
   *
   * @return The WeatherDatum object
   * @throws IOException if something goes wrong
   */
  protected abstract WeatherDatum readWeatherDatum() throws IOException;

  /**
   * Read one WeatherDatum object (and inform the observers).
   * 
   * Note: This method uses the readWeatherDatum() method in the
   * derived class. 
   * 
   * @throws IOException if something goes wrong
   */
  public void readOne() throws IOException
  {
    WeatherDatum datum = readWeatherDatum();
    if (datum != null) notifyObservers(datum);
  }

  /**
   * Read all (until the BufferedReader returns null) WeatherDatum objects 
   * (informing the observers).
   *
   * Note: This method uses the readWeatherDatum() method in the
   * derived class.
   *
   * @throws IOException if something goes wrong
   */
  public void readAll() throws IOException
  {
    WeatherDatum datum;

    while ((datum = readWeatherDatum()) != null)
    {
      notifyObservers(datum);
    }
  }
}
