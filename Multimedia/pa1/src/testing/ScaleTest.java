package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import measurement.Scale;

class ScaleTest
{

  @Test
  void testCreateScale()
  {
    assertEquals(Scale.F, Scale.createScale("f"));
    assertEquals(Scale.F, Scale.createScale("F"));
    assertEquals(Scale.F, Scale.createScale("fahrenheit"));
    assertEquals(Scale.F, Scale.createScale("Fahrenheit"));
    assertEquals(Scale.C, Scale.createScale("c"));
    assertEquals(Scale.C, Scale.createScale("C"));
    assertEquals(Scale.C, Scale.createScale("celsius"));
    assertEquals(Scale.C, Scale.createScale("Celsius"));
    assertNull(Scale.createScale("p"));
  } 

  @Test
  void testToStringBoolean()
  {
    assertEquals("Fahrenheit", Scale.F.toString(true));
    assertEquals("F", Scale.F.toString(false));

  }

  @Test
  void testToString()
  {
    assertEquals("F", Scale.F.toString());
  }

}
