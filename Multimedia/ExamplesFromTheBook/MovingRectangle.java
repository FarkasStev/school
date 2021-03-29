import java.awt.*;
import java.awt.geom.*;
import visual.statik.SimpleContent;
import visual.statik.described.CompositeContent;
import visual.statik.described.Content;

/**
 * A sequence of frames of described visual content.  Each frame
 * contains a rectangle in a slightly different position.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class MovingRectangle
{
    private SimpleContent[]         frames;

    /**
     * Default Constructor
     */
    public MovingRectangle()
    {
       Shape             shape;       
       Stroke            stroke;
       CompositeContent  ctc;

       frames = new SimpleContent[100];

       stroke = new BasicStroke();

       for (int i=0; i < frames.length; i++)
       {
          ctc   = new CompositeContent();

          shape = new Rectangle2D.Double(0.0, 0.0, 300.0, 200.0);
          ctc.add(new Content(shape, Color.WHITE, Color.WHITE, 
                                     stroke));

          shape = new Rectangle2D.Double(10.0, i, 100.0, 100.0);
          ctc.add(new Content(shape, Color.BLACK, Color.RED, 
                                     stroke));
          frames[i] = ctc;
       }
    }
    
    /**
     * Get the frames
     */
    public SimpleContent[] getFrames()
    {
       return frames;
    }
}
