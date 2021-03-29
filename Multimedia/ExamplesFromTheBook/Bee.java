import java.awt.geom.*;

import io.*;
import visual.dynamic.described.*;
import visual.dynamic.sampled.*;
import visual.statik.sampled.*;

/**
 * A bee that moves around
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Bee extends SampledSprite
{
  /**
   * Default Constructor
   */
  public Bee()
  {
    super();
    Content            content;        
    ContentFactory     factory;
    ResourceFinder     finder;

    finder  = ResourceFinder.createInstance(resources.Marker.class);        
    factory = new ContentFactory(finder);
    content = factory.createContent("bee.png", 4);
    addKeyFrame(    1, 173.0, 118.0,  0.00, 0.20, content);
    addKeyFrame(   45, 166.0, 120.0,  0.00, 0.35, null);        
    addKeyFrame(  100, 148.0, 105.0,  0.00, 0.50, null);        
    addKeyFrame(  115, 230.0,  90.0,  0.00, 0.75, null);        
    addKeyFrame(  200, 245.0, 143.0,  0.00, 1.00, null);        

    setEndState(REMOVE);
  }

  /**
   * Add a key frame
   */
  private void addKeyFrame(int frame, double x, double y,
      double r, double s, Content c)
  {
    int     time;

    time = frame * Screen.DEFAULT_FRAME_DELAY;

    addKeyTime(time, new Point2D.Double(x, y), new Double(r),
        new Double(s), c);
  }
}
