package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

import measurement.Temperature;
import weather.WeatherObservation;

class WeatherObservationTest
{

  @Test
  void testFromString()
  {
    WeatherObservation test = new WeatherObservation();
    StringTokenizer returnValue = null;
    assertEquals("XXX", test.getLocation());
    assertEquals("Unknown", test.getCondition());
    assertEquals("  +0.0F", test.getTemperature().toString());
    returnValue = test.fromString("PWW02,Sunny, +86.7F");
    assertNotNull(returnValue);
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals(" +86.7F", test.getTemperature().toString());
    test = new WeatherObservation();
    returnValue = test.fromString("PWW02,Sunny");
    assertNull(returnValue);
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getTemperature().toString());
    test = new WeatherObservation();
    returnValue = test.fromString("PWW02,Sunny, +1.0");
    assertNotNull(returnValue);
    assertEquals("PWW02", test.getLocation()); 
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getTemperature().toString());
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
    WeatherObservation test = new WeatherObservation("PWW02", "Sunny",
        Temperature.createTemperature(" +86.7F"));
    assertEquals("PWW02,Sunny, +86.7F", test.toString(false));
    assertEquals("Location: PWW02\tCondition: Sunny\tTemperature:  +86.7F", test.toString(true));
  }

  @Test
  void testWeatherObservation()
  {
    WeatherObservation test = new WeatherObservation();
    assertEquals("  +0.0F", test.getTemperature().toString());
    assertEquals("Unknown", test.getCondition());
    assertEquals("XXX", test.getLocation());
  }

  @Test
  void testWeatherObservationStringStringTemperature()
  {
    WeatherObservation test = new WeatherObservation("PWW02", "Sunny",
        Temperature.createTemperature(" +86.7F"));
    assertEquals(" +86.7F", test.getTemperature().toString());
    assertEquals("Sunny", test.getCondition());
    assertEquals("PWW02", test.getLocation());
  }

  @Test
  void testCreateWeatherObservation()
  {
    WeatherObservation test = WeatherObservation.createWeatherObservation("PWW02,Sunny, +86.7F");
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals(" +86.7F", test.getTemperature().toString());
    test = WeatherObservation.createWeatherObservation("PWW02,Sunny");
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getTemperature().toString());
    test = WeatherObservation.createWeatherObservation("PWW02,Sunny, 1");
    assertEquals("PWW02", test.getLocation());
    assertEquals("Sunny", test.getCondition());
    assertEquals("  +0.0F", test.getTemperature().toString());
  }

  @Test
  void testGetTemperature()
  {
    WeatherObservation test = new WeatherObservation("PWW02", "Sunny",
        Temperature.createTemperature(" +86.7F"));
    assertEquals(" +86.7F", test.getTemperature().toString());
    test = new WeatherObservation("PWW02", "Sunny", null);
    assertEquals("  +0.0F", test.getTemperature().toString());
  }

}
