package weather.io;

import java.io.*;

import weather.*;

/**
 * A class for reading WeatherObservation information from a BufferedReader.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class WeatherObservationReader extends WeatherDatumReader
{
  /**
   * Explicit Value Constructor.
   *
   * @param reader The BufferedReader to use
   */
  public WeatherObservationReader(final BufferedReader reader)
  {
    super(reader);
  }

  /**
   * Read a WeatherObservation Calls the createWeatherObservation method in the WeatherObservation
   * class.
   *
   * @return The next WeatherObservation object
   */
  @Override
  public WeatherDatum readWeatherDatum() throws IOException
  {
    String line = reader.readLine();
    if (line == null) return null;

    return WeatherObservation.createWeatherObservation(line);
  }
}
