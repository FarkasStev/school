package weather.visual;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import io.ResourceFinder;

public class WeatherIconReader
{
  private static String[] IMAGE_NAMES = {"PartlyCloudy", "Rainy", "Snowy", "Sunny"};
  private ResourceFinder finder;

  public WeatherIconReader(final ResourceFinder finder)
  {
    this.finder = finder;
  }

  public Map<String, Image> read() throws IOException
  {
    InputStream is;
    HashMap<String, Image> icons = new HashMap<String, Image>();
    for (int i = 0; i < IMAGE_NAMES.length; i++)
    {
      is = finder.findInputStream(IMAGE_NAMES[i] + ".png");

      icons.put(IMAGE_NAMES[i], ImageIO.read(is));

    }
    
    return icons;

  }

}
