import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Iterator;

import visual.dynamic.described.*;
import visual.statik.described.*;

/**
 * A "radio controlled" Buzzy.  That is, a Buzzy that responds to
 * key presses.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class      RCBuzzy 
       extends    RuleBasedSprite
       implements KeyListener
{

  private BuzzyCrouching        crouching;
  private BuzzyStanding         standing;
  private double                frac, left, top;    
  private int                   state;

  private static final int     UP          = 0;
  private static final int     MOVING_DOWN = 1;
  private static final int     MOVING_UP   = 2;
  private static final int     DOWN        = 3;

  /**
   * Explicit Value Constructor
   *
   * @param stageWidth   The width of the stage (in pixels)
   * @param stageheight  The height of the stage (in pixels)
   */
  public RCBuzzy(double stageWidth, double stageHeight)
  {
    super(null);
    Rectangle2D        bounds;       

    crouching = new BuzzyCrouching();
    standing  = new BuzzyStanding();

    state     = UP;       
    content   = standing;

    bounds    = content.getBounds2D(false);
    top       = (stageHeight - bounds.getHeight());
    left      = (stageWidth  - bounds.getWidth())/2.0;

    setLocation(left, top);
  }

  /**
   * Handle keyPressed messages
   * (required by KeyListener)
   *
   * @param ke  The KeyEvent that generated the message
   */
  public void keyPressed(KeyEvent ke)
  {
    int keyCode;
    keyCode = ke.getKeyCode();

    if (keyCode == KeyEvent.VK_DOWN)
    {
      if ((state != MOVING_DOWN) && (state != DOWN))
      {
        state = MOVING_DOWN;
      }
    }
    else if (keyCode == KeyEvent.VK_UP)
    {
      if ((state != MOVING_UP) && (state != UP))
      {
        state = MOVING_UP;
      }
    }
    else if (keyCode == KeyEvent.VK_RIGHT)
    {
      left += 5;          
      setLocation(left, top);
    }
    else if (keyCode == KeyEvent.VK_LEFT)
    {
      left -= 5;          
      setLocation(left, top);
    }
  }

  /**
   * Handle keyReleased messages
   * (required by KeyListener)
   *
   * @param ke  The KeyEvent that generated the message
   */
  public void keyReleased(KeyEvent e)		
  {
  }

  /**
   * Handle keyPressed messages
   * (required by KeyListener)
   *
   * @param ke  The KeyEvent that generated the message
   */
  public void keyTyped(KeyEvent e) 
  {
  }	


  /**
   * Handle handleTick messages
   * (required by MetronomeListener)
   */
  public void handleTick(int millis)
  {
    if (state == MOVING_DOWN)
    {
      frac += 0.02;
      if (frac > 1.0)
      {
        state   = DOWN;
        frac    = 1.0;   
      }
      content = tweenShape(standing, crouching, frac);
    }
    else if (state == MOVING_UP)
    {
      frac -= 0.02;
      if (frac < 0.0)
      {
        state   = UP;
        frac    = 0.0;   
      }
      content = tweenShape(standing, crouching, frac);
    }
    else if (state == UP)
    {
      content = standing;          
    }
    else
    {
      content = crouching;          
    }
  }

  /**
   * Tween the shape.
   * 
   * @param a    The "from" Content
   * @param b    The "to" Content
   * @param frac The interpolation parameter
   */
  protected AggregateContent tweenShape(
      AggregateContent a, 
      AggregateContent b,
      double frac)
  {
    AggregateContent tweened = new AggregateContent();

    float[] coordsA = new float[6];
    float[] coordsB = new float[6];
    float[] coords  = new float[6];

    Iterator<Content> iterA = a.iterator();
    Iterator<Content> iterB = b.iterator();

    // Loop over all of the TransformableContent objects
    // in the CompositeTransformableContent
    while (iterA.hasNext())
    {
      Content shapeA = iterA.next();
      Content shapeB;
      if (iterB.hasNext()) shapeB = iterB.next();
      else                 shapeB = shapeA;

      PathIterator piA = shapeA.getPathIterator(false);
      PathIterator piB = shapeB.getPathIterator(false);

      GeneralPath gp = new GeneralPath();
      gp.setWindingRule(piA.getWindingRule());


      // Loop over all of the segments in the TransformableContent object
      while (!piA.isDone())
      {
        int seg = piA.currentSegment(coordsA);
        if (piB.isDone()) // Use the coordinates of the first shape
        {
          for (int i=0; i < coordsA.length; i++) 
            coords[i] = coordsA[i];
        }
        else           // Interpolate the coordinates
        {
          piB.currentSegment(coordsB);

          for (int i=0; i < coordsA.length; i++)
          {
            coords[i] = coordsA[i] + 
                (float)frac*(coordsB[i] - coordsA[i]);
          }
        }

        // Add to the General Path object
        if      (seg == PathIterator.SEG_MOVETO)
        {
          gp.moveTo(coords[0], coords[1]);
        }
        else if (seg == PathIterator.SEG_LINETO)
        {
          gp.lineTo(coords[0], coords[1]);
        }
        else if (seg == PathIterator.SEG_QUADTO)
        {
          gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
        }
        else if (seg == PathIterator.SEG_CUBICTO)
        {
          gp.curveTo(coords[0], coords[1], 
              coords[2], coords[3], 
              coords[4], coords[5]);
        }
        else if (seg == PathIterator.SEG_CLOSE)
        {
          gp.closePath();
        }

        piA.next();
        piB.next();
      }

      Paint paint  = shapeA.getPaint();
      Color color  = shapeA.getColor(); // This could also be tweened
      Stroke stroke = shapeA.getStroke();

      tweened.add(new Content(gp, color, paint, stroke));
    }

    return tweened;       
  }
}
