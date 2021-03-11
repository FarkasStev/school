package map.io;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.ResourceFinder;
import resources.Marker;

public class PolygonReader
{
  private ResourceFinder finder;

  public PolygonReader()
  {
    this(null);
  }

  public PolygonReader(final ResourceFinder finder)
  {
    this.finder = finder;
  }

  /**
   * Must read a polygon from either the resource (if the finder attribute is non-null) or the file
   * (if the finder attribute is null) with the given name.
   * 
   * @param name
   *          of file to read
   * @return Shape
   * @throws IOException
   */
  public Shape read(final String name) throws IOException
  {
    InputStream is;
    if (finder == null)
    {
      finder = ResourceFinder.createInstance(new Marker());

    }
    is = finder.findInputStream(name);
    BufferedReader in = new BufferedReader(new InputStreamReader(is));
    String line;
    Path2D bodyShape = new Path2D.Float();

    while ((line = in.readLine()) != null)
    {
      String[] fields = line.split(",");
      if (Integer.parseInt(fields[0]) == 4)
      {
        bodyShape.moveTo(Double.parseDouble(fields[1]), Double.parseDouble(fields[2]));
      }
      else
      {
        bodyShape.lineTo(Double.parseDouble(fields[1]), Double.parseDouble(fields[2]));
      }

    }
    bodyShape.closePath();
    return bodyShape;

  }

}
