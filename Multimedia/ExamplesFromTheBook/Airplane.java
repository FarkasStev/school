import java.awt.geom.*;

import io.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An Airplane
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class Airplane extends SampledSprite
{
    /**
     * Default Constructor
     *
     */
    public Airplane()
    {
	super();
        Content            content;        
        ContentFactory     factory;
        
        factory = new ContentFactory(ResourceFinder.createInstance(resources.Marker.class));
        content = factory.createContent("airplane.png", 4);
        addKeyTime(  500,   0.0, 350.0, -0.75, 1.0, content);
        addKeyTime( 2000, 100.0, 200.0, -0.30, 1.0, null);        
        addKeyTime( 4000, 200.0,  50.0,  0.00, 1.0, null);        
        addKeyTime( 6000, 300.0,  50.0,  0.20, 1.0, null);        
        addKeyTime( 8000, 400.0, 200.0,  0.00, 1.0, null);        
        addKeyTime( 8500, 500.0, 200.0,  0.00, 1.0, null);        
        setEndState(REMOVE);
    }

    /**
     * Add a key frame
     */
    private void addKeyTime(int time, double x, double y,
                             double r, double s, Content c)
    {
       addKeyTime(time, new Point2D.Double(x, y), new Double(r),
                   new Double(s), c);
    }
}
