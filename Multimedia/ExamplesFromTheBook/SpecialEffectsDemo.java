import javax.swing.*;

import app.*;
import visual.*;
import io.ResourceFinder;
import visual.dynamic.*;
import visual.statik.*;
import visual.statik.sampled.*;

/**
 * An example that illustrates combined sampled and described
 * dynamic visual content
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1
 */
public class SpecialEffectsDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new SpecialEffectsDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public SpecialEffectsDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }



  public void init()
  {
    ResourceFinder                        finder;       

    SpecialEffectsScreen screen = new SpecialEffectsScreen(20);
    screen.setRepeating(true);

    VisualizationView view = screen.getView();       
    view.setBounds(0,0,320,240);       

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view);

    finder = ResourceFinder.createInstance(resources.Marker.class);

    String[] names = finder.loadResourceNames("scribble.txt");
    ContentFactory factory = new ContentFactory(finder);          
    SimpleContent[] frames = factory.createContents(names, 4);

    for (int i=0; i<frames.length; i++)
    {
      screen.add(frames[i]);          
    }

    Bee bee = new Bee();
    screen.add(bee);       

    screen.start();       
  }
}
