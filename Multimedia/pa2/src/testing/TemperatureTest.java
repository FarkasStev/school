package testing;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import measurement.Scale;
import measurement.Temperature;

class TemperatureTest
{

  @Test
  void testTemperature()
  {
    Temperature temp = new Temperature();
    assertEquals("  +0.0F", temp.toString());
  }

  @Test
  void testTemperatureTemperature()
  {
    Temperature temp = new Temperature();
    Temperature other = new Temperature(temp);
    assertEquals("  +0.0F", other.toString());
  }

  @Test
  void testTemperatureDouble()
  {
    Temperature temp = new Temperature(20.0);
    assertEquals(" +20.0F", temp.toString());
  }

  @Test
  void testTemperatureDoubleString()
  {
    Temperature temp = new Temperature(20.0, "F");
    assertEquals(" +20.0F", temp.toString());
  }

  @Test
  void testTemperatureDoubleScale()
  {
    Temperature temp = new Temperature(20.0, Scale.F);
    assertEquals(" +20.0F", temp.toString());

  }

  @Test
  void testCreateTemperature()
  {
    Temperature temp = Temperature.createTemperature("+108.1F");
    Temperature bad = Temperature.createTemperature("ABCDEFG");
    assertEquals("+108.1F", temp.toString());
    assertEquals("  +0.0F", bad.toString());
    bad = Temperature.createTemperature("10");
    assertEquals("  +0.0F", bad.toString());
    bad = Temperature.createTemperature(null);
    assertEquals("  +0.0F", bad.toString());
    temp = Temperature.createTemperature("-108.1F");
    assertEquals("-108.1F", temp.toString());

  }

  @Test
  void testCompareTo()
  {
    Temperature far = Temperature.createTemperature("+108.1F");
    Temperature far1 = Temperature.createTemperature("  +1.1F");
    Temperature cel = Temperature.createTemperature("  +1.0C");

    assertEquals(1, far.compareTo(cel));
    assertEquals(0, far.compareTo(far));
    assertEquals(-1, far1.compareTo(cel));
    assertEquals(1, cel.compareTo(far1));
    assertEquals(0, cel.compareTo(cel));
  }

  @Test
  void testDecreaseBy()
  {
    Temperature far0 = Temperature.createTemperature(" +32.0F");
    Temperature far1 = Temperature.createTemperature("  +1.0F");
    far0.decreaseBy(far1);
    assertEquals(" +31.0F", far0.toString());
  }

  @Test
  void testFromString()
  {
    Temperature temp = new Temperature();
    temp.fromString("+108.1F");
    assertEquals("+108.1F", temp.toString());
    temp.fromString("1");
    assertEquals("+108.1F", temp.toString());
    temp.fromString("");
    assertEquals("+108.1F", temp.toString());
    temp.fromString("f");
    assertEquals("+108.1F", temp.toString());
    temp.fromString("ABCDEFG");
    assertEquals("+108.1F", temp.toString());
    temp.fromString(null);
    assertEquals("+108.1F", temp.toString());
    temp.fromString(" 108.1f");
    assertEquals("+108.1F", temp.toString());
    temp.fromString("-108.1f");
    assertEquals("-108.1F", temp.toString());
    temp.fromString("-10..1f");
    assertEquals("-108.1F", temp.toString());
    temp.fromString("-100.1 ");
    assertEquals("-108.1F", temp.toString());
  }

  @Test
  void testIncreaseBy()
  {
    Temperature far0 = Temperature.createTemperature(" +32.0F");
    Temperature far1 = Temperature.createTemperature("  +1.0F");

    far0.increaseBy(far1);
    assertEquals(" +33.0F", far0.toString());

  }

  @Test
  void testToString()
  {
    Temperature temp = new Temperature();
    assertEquals("  +0.0F", temp.toString());
  }

  @Test
  void testToStringScale()
  {
    Temperature temp = new Temperature(32);
    assertEquals(" +32.0F", temp.toString(Scale.F));
    assertEquals("  +0.0C", temp.toString(Scale.C));
  }

}
