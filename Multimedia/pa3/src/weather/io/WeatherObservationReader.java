package weather.io;

import java.io.BufferedReader;
import java.io.IOException;

import weather.WeatherDatum;
import weather.WeatherObservation;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU Honor Code.
 *
 */
public class WeatherObservationReader extends WeatherDatumReader
{

  /**
   * Constructor that initializes with the given reader.
   * 
   * @param reader
   *          to initialize with
   */
  public WeatherObservationReader(final BufferedReader reader)
  {
    super(reader);
  }

  /**
   * Must read a single String representation of a WeatherObservation from the inherited attribute
   * named reader, construct a WeatherObservation object from it, and return it. If the reader has
   * reached the end-of-stream or there is a problem constructing the WeatherObservation object,
   * then this method must return null.This method must assume that there is one String
   * representation per "line" in the stream, and that the "line" contains a terse String
   * representation of a WeatherObservation object.
   * 
   * @return the observation from the next line of the file
   * @throws IOException
   *           when issue occurs reading from file
   */
  public WeatherDatum readWeatherDatum() throws IOException
  {
    String rep = reader.readLine();
    WeatherObservation observation = null;
    if (rep != null)
    {
      observation = WeatherObservation.createWeatherObservation(rep);
    }
    return observation;
  }

}
