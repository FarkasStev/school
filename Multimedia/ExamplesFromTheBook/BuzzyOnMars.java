import java.awt.geom.*;

import visual.dynamic.described.DescribedSprite;
import visual.statik.described.*;

/**
 * A Buzzy moving on mars
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class BuzzyOnMars extends DescribedSprite
{
  /**
   * Default Constructor
   */
  public BuzzyOnMars()
  {
    super();
    BuzzyStanding       buzzy;

    buzzy = new BuzzyStanding();
    addKeyTime(  500,   0.0, 380.0,  0.00, 1.0, buzzy);
    addKeyTime( 2000, 180.0, 380.0,  0.00, 1.0, null);        
    addKeyTime( 4000, 180.0,  75.0,  0.20, 1.0, null);        
    addKeyTime( 6000, 640.0,  20.0,  6.48, 1.0, null);        
    setEndState(REMOVE);
  }

  /**
   * Add a key time
   *
   * @param time    The key time
   * @param x       The x position
   * @param y       The y position
   * @param r       The rotation angle
   * @param r       The scaling
   * @param c       The Content 
   */
  private void addKeyTime(int time, double x, double y,
      double r, double s, AggregateContent c)
  {
    addKeyTime(time, new Point2D.Double(x, y), new Double(r),
        new Double(s), c);
  }
}
