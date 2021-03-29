import java.awt.geom.Point2D;

import visual.dynamic.described.*;
import visual.statik.described.*;

/**
 * A Buzzy jumping
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class BuzzyJumping extends DescribedSprite
{
  /**
   * Default Constructor
   *
   */
  public BuzzyJumping()
  {
    super();

    addKeyTime( 1000,   0.0, 355.0,  0.00, new BuzzyStanding());
    addKeyTime( 1500,   0.0, 355.0,  0.00, new BuzzyCrouching());
    addKeyTime( 2000,   0.0, 355.0,  0.00, new BuzzyLeaningForward());
    addKeyTime( 4000, 180.0,  75.0,  0.00, new BuzzyLeaningForward());
    addKeyTime( 6000, 540.0, 355.0,  0.00, new BuzzyCrouching());        
    addKeyTime( 6500, 540.0, 355.0,  0.00, new BuzzyStanding());        

    setEndState(REMAIN);
  }

  /**
   * Add a key time
   *
   * @param time    The key time
   * @param x       The x position
   * @param y       The y position
   * @param r       The rotation angle
   * @param content The static visual content
   */
  private void addKeyTime(int time, double x, double y,
      double r, AggregateContent content)
  {
    addKeyTime(time, new Point2D.Double(x, y), new Double(r), 
        new Double(1.0), content);
  }
}
