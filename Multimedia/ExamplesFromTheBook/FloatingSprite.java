//[skeleton0
import java.util.*;

import visual.dynamic.described.*;
import visual.statik.TransformableContent;

/**
 * A simple Sprite
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FloatingSprite extends AbstractSprite
{
  private double                    maxX, maxY, x, y;    
  private Random                    rng;    
  private TransformableContent      content;

  /**
   * Explicit Value Constructor
   *
   * @param content   The static visual content
   * @param width     The width of the Stage
   * @param height    The height of the Stage
   */
  public FloatingSprite(TransformableContent content,
      double width, double height)
  {
    super();
    this.content = content;
    maxX         = width;
    maxY         = height;       

    rng = new Random();       

    x = rng.nextDouble()*maxX;       
    y = 0.0;       
    setLocation(x, y);       

    setVisible(true);
  }
  //]skeleton0

  //[getContent
  /**
   * Get the visual content associated with this Sprite
   * (required by Sprite)
   */
  public TransformableContent getContent()
  {
    return content;
  }
  //]getContent

  //[handleTick
  /**
   * Handle a tick event (required by MetronomeListener)
   *
   * @param time  The current time (in milliseconds)
   */
  public void handleTick(int time)
  {
    double        n;

    n = rng.nextDouble();
    if      (n < 0.80)   y += 2.0;
    else if (n > 0.90)   y -= 1.0;       

    n = rng.nextDouble();
    if      (n < 0.20) x -= 1.0;
    else if (n > 0.80) x += 1.0;

    // Check if at the bottom
    if (y > maxY)
    {
      y  = 0.0;
      x = rng.nextDouble()*maxX;       
    }

    setLocation(x, y);       
  }
  //]handleTick
  //[skeleton1
}
//]skeleton1
