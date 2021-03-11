package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import weather.WeatherDatum;
import weather.io.WeatherForecastReader;

class WeatherObservationReaderTest
{

  // use test.obs
  private BufferedReader createBufferedReader(String name)
  {
    InputStream is = getClass().getResourceAsStream(name);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));

    return br;
  }

  @Test
  void readWeatherDatumTest() throws IOException
  {
    WeatherForecastReader forecast = new WeatherForecastReader(createBufferedReader("test.obs"));
    WeatherDatum datum = forecast.readWeatherDatum();
    assertEquals("PWW01", datum.getLocation());
    datum = forecast.readWeatherDatum();
    assertEquals("PWW02", datum.getLocation());
    datum = forecast.readWeatherDatum();
    assertEquals("PWW03", datum.getLocation());
    datum = forecast.readWeatherDatum();
    assertEquals("PWW04", datum.getLocation());
    datum = forecast.readWeatherDatum();
    assertEquals("PWW05", datum.getLocation());
    datum = forecast.readWeatherDatum();
    assertEquals("PWW06", datum.getLocation());
    datum = forecast.readWeatherDatum();
    assertNull(datum);
    datum = forecast.readWeatherDatum();
    assertNull(datum);
  }


}
