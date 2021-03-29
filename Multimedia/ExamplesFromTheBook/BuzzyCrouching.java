import java.awt.*;
import java.awt.geom.*;

/**
 * Buzzy in a crouching position
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BuzzyCrouching extends Buzzy
{
  /**
   * Default Constructor
   */
  public BuzzyCrouching()
  {
    super();
  }

  /**
   * Get the Shape for Buzzy's body
   *
   * Note: This Shape should consist of 9 segments 
   *       and a closePath()
   */
  public Shape getBodyShape()
  {
    Path2D.Float       body;

    body = new Path2D.Float();
    body.moveTo( 20, 70);
    body.lineTo( 40, 80);
    body.lineTo( 20, 90);
    body.lineTo( 10, 90);
    body.lineTo( 10,100);
    body.lineTo( 80,100);
    body.lineTo( 80, 90);
    body.lineTo( 40, 90);
    body.lineTo( 60, 80);
    body.lineTo( 40, 70);
    body.closePath();

    return body;
  }

  /**
   * Get the Shape for Buzzy's hair
   */
  public Shape getHairShape()
  {
    return new QuadCurve2D.Float(10,15,40,25,30,40);
  }

  /**
   * Get the Shape for Buzzy's helmet
   */
  public Shape getHelmetShape()
  {
    return new Arc2D.Float(0,35,70,40,0,360,Arc2D.OPEN);
  }

  /**
   * Get the Shape for Buzzy's visor
   */
  public Shape getVisorShape()
  {
    return new Arc2D.Float(40,40,35,30,315,90,Arc2D.PIE);
  }
}
