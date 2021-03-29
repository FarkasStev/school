import java.awt.*;
import java.awt.geom.*;

/**
 * Buzzy in a "leaning forward" position
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BuzzyLeaningForward extends Buzzy
{
  /**
   * Default Constructor
   */
  public BuzzyLeaningForward()
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
    body.moveTo( 40, 50);
    body.lineTo( 30, 70);
    body.lineTo( 20, 90);
    body.lineTo( 10, 90);
    body.lineTo( 10,100);
    body.lineTo( 80,100);
    body.lineTo( 80, 90);
    body.lineTo( 40, 90);
    body.lineTo( 50, 70);
    body.lineTo( 60, 50);
    body.closePath();

    return body;
  }

  /**
   * Get the Shape for Buzzy's hair
   */
  public Shape getHairShape()
  {
    return new QuadCurve2D.Float(30,0,60,10,50,25);
  }

  /**
   * Get the Shape for Buzzy's helmet
   */
  public Shape getHelmetShape()
  {
    return new Arc2D.Float(20,20,70,40,0,360,Arc2D.OPEN);
  }

  /**
   * Get the Shape for Buzzy's visor
   */
  public Shape getVisorShape()
  {
    return new Arc2D.Float(60,25,35,30,315,90,Arc2D.PIE);
  }
}
