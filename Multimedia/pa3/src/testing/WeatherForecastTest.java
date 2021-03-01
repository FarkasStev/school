package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

import measurement.Temperature;
import weather.WeatherForecast;

class WeatherForecastTest
{

  @Test
  void testFromString()
  {
    WeatherForecast test = new WeatherForecast();
    StringTokenizer returnValue;
    returnValue = test.fromString("PWW02,Sunny, +86.7F, +91.3F");
    assertNotNull(returnValue);
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals(" +91.3F", test.getHigh().toString());
    test = new WeatherForecast();
    returnValue = test.fromString("PWW02,Sunny, +86.7F");
    assertNull(returnValue);
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getHigh().toString());
    test = new WeatherForecast();
    returnValue = test.fromString("PWW02,Sunny, +86.7F, 1");
    assertNotNull(returnValue);
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getHigh().toString());
    returnValue = test.fromString("PWW02");
    assertNull(returnValue);
    assertEquals("PWW02", test.getLocation());
    returnValue = test.fromString("");
    assertNull(returnValue);
    returnValue = test.fromString(null);
    assertNull(returnValue);

  }

  @Test
  void testToStringBoolean()
  {
    WeatherForecast test = new WeatherForecast("PWW02", "Sunny",
        Temperature.createTemperature(" +86.7F"), Temperature.createTemperature(" +91.3F"));
    assertEquals("PWW02,Sunny, +86.7F, +91.3F", test.toString(false));
    assertEquals("Location: PWW02\tCondition: Sunny\tLow:  +86.7F\tHigh:  +91.3F",
        test.toString(true));
  }

  @Test
  void testWeatherForecast()
  {
    WeatherForecast test = new WeatherForecast();
    assertEquals("  +0.0F", test.getLow().toString());
    assertEquals("  +0.0F", test.getHigh().toString());
    assertEquals("Unknown", test.getCondition());
    assertEquals("XXX", test.getLocation());
  }

  @Test
  void testWeatherForecastStringStringTemperatureTemperature()
  {
    WeatherForecast test = new WeatherForecast("PWW02", "Sunny",
        Temperature.createTemperature(" +86.7F"), Temperature.createTemperature(" +91.3F"));
    assertEquals(" +86.7F", test.getLow().toString());
    assertEquals(" +91.3F", test.getHigh().toString());
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());

    test = new WeatherForecast("PWW02", "Sunny", null, null);
    assertEquals("  +0.0F", test.getLow().toString());
    assertEquals("  +0.0F", test.getHigh().toString());
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
  }

  @Test
  void testCreateWeatherForecast()
  {
    WeatherForecast test = WeatherForecast.createWeatherForecast("PWW02,Sunny, +86.7F, +91.3F");
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals(" +91.3F", test.getHigh().toString());
    test = WeatherForecast.createWeatherForecast("PWW02,Sunny");
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getHigh().toString());
    test = WeatherForecast.createWeatherForecast("PWW02,Sunny, 1, 1");
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getHigh().toString());
  }

  @Test
  void testGetHigh()
  {
    WeatherForecast test = new WeatherForecast();
    assertEquals("  +0.0F", test.getHigh().toString());
  }

  @Test
  void testGetLow()
  {
    WeatherForecast test = new WeatherForecast();
    assertEquals("  +0.0F", test.getLow().toString());
  }

}
