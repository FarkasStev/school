import java.awt.geom.Point2D;

import io.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * The Bus in the transit information system example
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BusOnRoute extends SampledSprite
{
  /**
   * Default Constructor
   *
   * @param fn   The file containing the bitmap
   */
  public BusOnRoute()
  {
    super();
    Content              content;
    ContentFactory       factory;
    ResourceFinder       finder;        

    finder  = ResourceFinder.createInstance(resources.Marker.class);        
    factory = new ContentFactory(finder);        
    content = factory.createContent("bus.png");
    addKeyTime( 0, 164, 210, content);
    addKeyTime( 1, 310, 255, null);
    addKeyTime( 2, 314, 234, null);
    addKeyTime( 3, 401, 231, null);
    addKeyTime( 4, 419, 269, null);
    addKeyTime( 5, 353, 340, null);
    addKeyTime( 6, 430, 367, null);
    addKeyTime( 7, 420, 418, null);
    addKeyTime( 8, 450, 421, null);
    addKeyTime( 9, 454, 386, null);
    addKeyTime(10, 512, 393, null);
    addKeyTime(11, 487, 338, null);
    addKeyTime(12, 554, 323, null);
    addKeyTime(13, 500, 238, null);
    addKeyTime(14, 577, 206, null);
    addKeyTime(15, 632, 155, null);
    addKeyTime(16, 480, 151, null);
    addKeyTime(19, 301,  88, null);
    addKeyTime(21, 233, 149, null);
    addKeyTime(22, 147, 181, null);
    addKeyTime(30, 164, 210, null);
    setEndState(REMAIN);
  }

  /**
   * Add a key time
   *
   * @param time     The key time
   * @param x        The x position
   * @param y        The y position
   * @param content  The Content to use
   */
  private void addKeyTime(int time, int x, int y, 
      Content content)
  {
    addKeyTime(time*1000, new Point2D.Double((double)x, (double)y), 
        null, new Double(1.0), content);
  }
}
