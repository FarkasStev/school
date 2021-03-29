import java.util.*;

import visual.dynamic.described.*;
import visual.statik.sampled.*;


/**
 * A Snowflake in the game Flakey
 *
 * A Snowflake is a simple rule-based sprite that uses one piece
 * of visual content.  Unlike a Pinecone, a Snowflake is 
 * always falling.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Snowflake extends RuleBasedSprite
{
    private double             x, y, speed;
    
    private static Random      rng = new Random();

    
    /**
     * Explicit Value Constructor
     *
     * @param content   The visual content to use
     */
    public Snowflake(TransformableContent content)
    {
        super(content);

       x     = rng.nextInt(640);
       y     = rng.nextInt(480);;
       speed = 2;
       setLocation(x, y);
    }

    
    /**
     * Handle a tick event
     *
     * @param time  The current time (in milliseconds)
     */
    public void handleTick(int time)
    {
       y += speed;
            
       if (y > 550)
       {
          x = rng.nextInt(640);
          y = 0;
          speed = 2;
       }
       
       // Set the location
       setLocation(x, y);
    }
}
