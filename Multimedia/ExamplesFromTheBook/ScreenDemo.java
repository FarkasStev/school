import javax.swing.*;

import app.*;
import visual.VisualizationView;
import io.ResourceFinder;
import visual.dynamic.sampled.Screen;
import visual.statik.SimpleContent;
import visual.statik.sampled.ContentFactory;

/**
 * An example that illustrates sampled dynamic visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   ScreenDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new ScreenDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }


  public ScreenDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  /**
   * The entry-point of the application
   */
  public void init()
  {
    ContentFactory                        factory;       
    MovingRectangle                       mr;       
    SimpleContent[]                       frames;

    Screen screen = new Screen(24);
    screen.setRepeating(true);
    VisualizationView view = screen.getView();       

    String[] names = null;       
    String                                arg;       
    if (args == null || args.length == 0 || args[0].isEmpty()) arg = "solidclock";
    else  arg = args[0];
    
    ResourceFinder finder = ResourceFinder.createInstance(resources.Marker.class);

    if (arg.equalsIgnoreCase("solidclock"))
    {
      view.setBounds(0,0,200,200);       
      names = finder.loadResourceNames("solidclock.txt");
      factory = new ContentFactory(finder);          
      frames = factory.createContents(names, 4);
    }
    else if (arg.equalsIgnoreCase("scribble"))
    {
      view.setBounds(0,0,320,240);       
      names = finder.loadResourceNames("scribble.txt");
      factory = new ContentFactory(finder);          
      frames = factory.createContents(names, 4);
    }
    else
    {
      view.setBounds(0,0,200,200);       
      mr     = new MovingRectangle();          
      frames = mr.getFrames();          
    }

    for (int i=0; i<frames.length; i++)
    {
      screen.add(frames[i]);          
    }

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view);
    screen.start();       
  }
}
