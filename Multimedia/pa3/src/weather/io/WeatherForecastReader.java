package weather.io;

import java.io.BufferedReader;
import java.io.IOException;

import weather.WeatherDatum;
import weather.WeatherForecast;

/**
 * @author Steven Farkas
 * 
 *         This code complies with the JMU Honor Code
 *
 */
public class WeatherForecastReader extends WeatherDatumReader
{

  /**
   * Default Constructor.
   * 
   * @param reader
   *          to initialize with
   */
  public WeatherForecastReader(final BufferedReader reader)
  {
    super(reader);
  }

  /**
   * Must read a single String representation of a WeatherForecast from the inherited attribute
   * named reader, construct a WeatherForecast object from it, and return it. If the reader has
   * reached the end-of-stream or there is a problem constructing the WeatherForecastobject, then
   * this method must return null.This method must assume that there is one String representation
   * per "line" in the stream, and that the "line" contains a terse String representation of a
   * WeatherForecast object.
   * 
   * @return the next Forecast from the line
   * @throws IOException
   *           when issue occurs reading from file
   */
  public WeatherDatum readWeatherDatum() throws IOException
  {
    String rep = reader.readLine();
    WeatherForecast forecast = null;
    if (rep != null)
    {
      forecast = WeatherForecast.createWeatherForecast(rep);
    }
    return forecast;
  }

}
