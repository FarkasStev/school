import javax.swing.*;

import app.*;
import visual.*;
import io.ResourceFinder;
import visual.dynamic.sampled.Screen;
import visual.statik.SimpleContent;
import visual.statik.sampled.ContentFactory;

/**
 * An example that illustrates the use of multiple views
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class ScreenPIPDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new ScreenPIPDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public ScreenPIPDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder   finder;       

    finder = ResourceFinder.createInstance(resources.Marker.class);

    Screen screen1 = new Screen(20);
    screen1.setRepeating(true);

    VisualizationView view1 = screen1.getView();       
    view1.setBounds(0,0,320,240);       

    String[] names = finder.loadResourceNames("scribble.txt");
    ContentFactory factory = new ContentFactory(finder);          

    SimpleContent[] frames1 = factory.createContents(names, 4);
    for (int i=0; i<frames1.length; i++)
    {
      screen1.add(frames1[i]);          
    }

    Screen screen2 = new Screen();
    screen2.setRepeating(true);

    VisualizationView view2 = screen2.getView();       
    view2.setRenderer(new ScaledVisualizationRenderer(view2.getRenderer(),
        200.0, 200.0));       
    view2.setBounds(200,10,75,75);     
    view2.setSize(75,75);       

    MovingRectangle mr = new MovingRectangle();          
    SimpleContent[] frames2 = mr.getFrames();          

    for (int i=0; i<frames2.length; i++)
    {
      screen2.add(frames2[i]);          
    }

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view2);
    contentPane.add(view1);

    screen1.start();
    screen2.start();
  }
}
