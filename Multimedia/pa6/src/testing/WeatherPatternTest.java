package testing;

import static org.junit.jupiter.api.Assertions.*;



import java.util.Iterator;

import org.junit.jupiter.api.Test;

import weather.WeatherDatum;
import weather.WeatherPattern;

class WeatherPatternTest
{

  @Test
  void getterSetterTest()
  {
    WeatherPattern pattern = new WeatherPattern("Description");
    assertEquals("Description", pattern.getDescription());
    pattern.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));
    pattern.handleWeatherDatum(null);
    assertEquals(4, pattern.size());
    assertEquals("PWW01",pattern.getElement(0).getLocation());
    assertEquals("PWW02",pattern.getElement(1).getLocation());
    assertEquals("PWW03",pattern.getElement(2).getLocation());
    assertEquals("PWW04",pattern.getElement(3).getLocation());

    pattern.reset();
    assertEquals(0, pattern.size());
    
  }
  
  @Test
  void iteratorTest()
  {
    WeatherPattern pattern = new WeatherPattern("Description");
    pattern.handleWeatherDatum(new WeatherDatum("PWW01", "sunny"));
    pattern.handleWeatherDatum(new WeatherDatum("PWW02", "sunny"));
    pattern.handleWeatherDatum(new WeatherDatum("PWW03", "sunny"));
    pattern.handleWeatherDatum(new WeatherDatum("PWW04", "sunny"));
    
    Iterator<WeatherDatum> itr = pattern.iterator();
    assertNotNull(itr);
    
    assertEquals("PWW01", itr.next().getLocation());
    assertEquals("PWW02", itr.next().getLocation());
    assertEquals("PWW03", itr.next().getLocation());
    assertEquals("PWW04", itr.next().getLocation());
    assertFalse(itr.hasNext());
  }

}
