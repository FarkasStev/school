package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import weather.WeatherDatum;
import weather.WeatherPattern;
import weather.io.WeatherObservationReader;

class WeatherForecastReaderTest
{
  // use test.cast
  private BufferedReader createBufferedReader(String name)
  {
    InputStream is = getClass().getResourceAsStream(name);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));

    return br;
  }

  @Test
  void testReadWeatherDatum() throws IOException
  {
    WeatherObservationReader observation = new WeatherObservationReader(
        createBufferedReader("test.cast"));
    WeatherDatum datum = observation.readWeatherDatum();
    assertEquals("PWW01", datum.getLocation());
    datum = observation.readWeatherDatum();
    assertEquals("PWW02", datum.getLocation());
    datum = observation.readWeatherDatum();
    assertEquals("PWW03", datum.getLocation());
    datum = observation.readWeatherDatum();
    assertEquals("PWW04", datum.getLocation());
    datum = observation.readWeatherDatum();
    assertEquals("PWW05", datum.getLocation());
    datum = observation.readWeatherDatum();
    assertEquals("PWW06", datum.getLocation());
    datum = observation.readWeatherDatum();
    assertNull(datum);
    datum = observation.readWeatherDatum();
    assertNull(datum);
  }

  @Test
  void weatherForecastReaderTest() throws IOException, InterruptedException
  {
    WeatherObservationReader observation = new WeatherObservationReader(
        createBufferedReader("test.cast"));
    WeatherPattern pattern1 = new WeatherPattern("Description1");
    pattern1.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern1.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern1.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern1.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));
    WeatherPattern pattern2 = new WeatherPattern("Description2");
    pattern2.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern2.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern2.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern2.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));
    WeatherPattern pattern3 = new WeatherPattern("Description3");
    pattern3.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern3.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern3.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern3.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));

    observation.removeObserver(pattern1);

    observation.addObserver(pattern1);

  }

  @Test
  void readAllTest() throws IOException, InterruptedException
  {

    WeatherObservationReader observation = new WeatherObservationReader(
        createBufferedReader("test.cast"));
    WeatherPattern pattern1 = new WeatherPattern("Description1");
    pattern1.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern1.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern1.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern1.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));
    WeatherPattern pattern2 = new WeatherPattern("Description2");
    pattern2.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern2.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern2.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern2.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));
    WeatherPattern pattern3 = new WeatherPattern("Description3");
    pattern3.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern3.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern3.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern3.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));

    
    try {
      observation.readAll();
    }
    catch(IllegalMonitorStateException e) 
    {
      assertTrue(true);
    }
  }

}
