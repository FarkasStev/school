import java.awt.geom.Point2D;

import io.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An animated crystal ball
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class CrystalBall extends SampledSprite
{
  /**
   * Default Constructor
   *
   */
  public CrystalBall()
  {
    super();

    ResourceFinder finder  = ResourceFinder.createInstance(resources.Marker.class);        
    ContentFactory factory = new ContentFactory(finder);

    Content content = factory.createContent("crystalball01.png");
    addKeyTime(  500,   0.0, 350.0, -0.75, content);
    addKeyTime( 4000, 100.0, 200.0, -0.30, null);        

    content = factory.createContent("crystalball02.png");
    addKeyTime( 7500, 200.0,  50.0,  0.00, content);        

    setEndState(REMAIN);
  }

  /**
   * Add a key time
   *
   * @param time     The key time
   * @param x        The x position
   * @param y        The y position
   * @param r        The rotation angle
   * @param content  The static visual content
   */
  private void addKeyTime(int time, double x, double y,
      double r, Content content)
  {
    addKeyTime(time, new Point2D.Double(x, y), new Double(r),
        new Double(1.0), content);
  }
}
