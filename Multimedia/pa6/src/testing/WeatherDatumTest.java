package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import weather.WeatherDatum;

class WeatherDatumTest
{

  @Test
  void testWeatherDatum()
  {
    WeatherDatum test = new WeatherDatum("H4RI550N8URG", "Cloudy");
    assertEquals("H4RI550N8URG", test.getLocation());
    assertEquals("Cloudy", test.getCondition());
  }

  @Test
  void testGetCondition()
  {
    WeatherDatum test = new WeatherDatum("H4RI550N8URG", "Cloudy");
    assertEquals("Cloudy", test.getCondition());
  }

  @Test
  void testGetLocation()
  {
    WeatherDatum test = new WeatherDatum("H4RI550N8URG", "Cloudy");
    assertEquals("H4RI550N8URG", test.getLocation());
  }

  @Test
  void testToStringBoolean()
  {
    WeatherDatum test = new WeatherDatum("H4RI550N8URG", "Cloudy");
    assertEquals("H4RI550N8URG,Cloudy", test.toString(false));
    assertEquals("Location: H4RI550N8URG\tCondition: Cloudy", test.toString(true));
  }

  @Test
  void testToString()
  {
    WeatherDatum test = new WeatherDatum("H4RI550N8URG", "Cloudy");
    assertEquals("H4RI550N8URG,Cloudy", test.toString());
  }

}
