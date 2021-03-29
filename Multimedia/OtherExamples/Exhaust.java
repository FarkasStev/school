import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import visual.dynamic.described.*;
import visual.statik.described.*;

/**
 * A "piece" of exhaust.  Several Exhaust objects
 * together give the appearance of exhaust from
 * a tailpipe.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Exhaust extends RuleBasedSprite
{
    private double        originX, originY;    
    private int           count, length, slope;

    private static final  int      DIAMETER = 5;
    private static final  Random   rng      = new Random();

    /**
     * Default Constructor
     */
    public Exhaust()
    {
       super(new Content(new Ellipse2D.Float(0,0,DIAMETER,DIAMETER), 
                         Color.BLACK, 
                         Color.GRAY, 
                         new BasicStroke()
                         )
             );
    
       length = rng.nextInt(15);   
       count  = -1;       
    }
    
    /**
     * Handle a Metronome tick
     * (required by Sprite [through MetronomeListener])
     *
     * @param millis   The number of milliseconds since the Metronome started
     */
    public void handleTick(int millis)
    {
       count++;
       
       if (count >= length)
       {
          count = 0;          
          setLocation(originX, originY);          
       }
       else
       {
          slope  = rng.nextInt(4) - 1;   
          setLocation(originX-count, originY-(count*slope));          
       }
    }

    /**
     * Set the origin.
     *
     * All movements of this Exhaust object will be relative to
     * the origin.
     *
     * @param x    The horizontal coordinate of the origin
     * @param y    The vertical coordinate of the origin
     */
    public void setOrigin(double x, double y)
    {
       originX = x - DIAMETER/2;
       originY = y - DIAMETER/2;       
    }
}
