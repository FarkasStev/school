package weather.io;

import java.io.*;

import weather.*;

/**
 * A class for reading WeatherForecast information from a BufferedReader.
 *
 * @author Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class WeatherForecastReader extends WeatherDatumReader
{
  /**
   * Explicit Value Constructor.
   *
   * @param reader The BufferedReader to use
   */
  public WeatherForecastReader(final BufferedReader reader)
  {
    super(reader);
  }

  /**
   * Read a WeatherForecast (Uses the createWeatherForecast method in the WeatherForecast class).
   *
   * @return The next WeatherForecast object
   */
  @Override
  public WeatherDatum readWeatherDatum() throws IOException
  {
    String line = reader.readLine();
    if (line == null) return null;

    return WeatherForecast.createWeatherForecast(line);
  }

}
