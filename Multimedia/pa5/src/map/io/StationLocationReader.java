package map.io;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import io.ResourceFinder;

public class StationLocationReader
{

  private ResourceFinder finder;

  public StationLocationReader(ResourceFinder finder)
  {
    this.finder = finder;
  }

  /**
   * 
   * Must use the finder attribute to read information about a collection of weather stations from a
   * geographic location file.
   * 
   * The first field of the geographic location file will be the code/identifier for the weather
   * station. This code/identifier must be used as the key, and the horizontal and vertical
   * positions must be used as the value (i.e., the Point2D).
   * 
   * @param name
   *          of file to read
   * @return map of weather stations
   * @throws IOException
   */
  public Map<String, Point2D> read(String name) throws IOException
  {
    InputStream is = finder.findInputStream(name);
    BufferedReader in = new BufferedReader(new InputStreamReader(is));
    String line;
    HashMap<String, Point2D> stations = new HashMap<String, Point2D>();
    while ((line = in.readLine()) != null)
    {
      String[] fields = line.split(",");

      stations.put(fields[0], new Point(Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));

    }
    return stations;
  }

}
