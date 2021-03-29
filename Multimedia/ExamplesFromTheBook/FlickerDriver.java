import java.awt.*;
import javax.swing.*;

import io.*;
import visual.statik.sampled.*;

/**
 * Illustrates the need for double-buffering
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class FlickerDriver
{
  /**
   * The entry-point of the application
   *
   * @param args  The command-line arguments
   */
  public static void main(String[] args)
  {
    FlickerCanvas                   canvas;
    ResourceFinder                  finder;       
    TransformableContent[]          frames;

    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Container c = (Container)f.getContentPane();
    f.setLayout(new BorderLayout());

    canvas = new FlickerCanvas();
    finder = ResourceFinder.createInstance(resources.Marker.class);       
    String[] names = finder.loadResourceNames("solidclock.txt");

    ContentFactory factory = new ContentFactory(finder);          
    frames = factory.createContents(names, 4);

    canvas.setFrames(frames);

    c.add(canvas, BorderLayout.CENTER);

    f.pack();
    f.setVisible(true);
  }
}
