package map.io;

import java.awt.Color;
import java.io.IOException;

import io.ResourceFinder;
import visual.statik.described.Content;

public class BaseMapReader
{
  private PolygonReader finder;

  public BaseMapReader(ResourceFinder finder)
  {
    this.finder = new PolygonReader(finder);
  }

  /**
   * Must use the reader attribute to read a polygon and construct a Content object with the
   * appropriate properties.
   * 
   * @param name
   *          of Content
   * @param boundary
   *          color out outline
   * @param interior
   *          color
   * @return the content
   * @throws IOException
   */
  public Content read(String name, Color boundary, Color interior) throws IOException
  {
    return new Content(finder.read(name), boundary, interior, null);

  }

}
