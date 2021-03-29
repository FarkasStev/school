import javax.swing.*;

import app.*;
import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.sampled.Fade;
import visual.dynamic.sampled.RectangleWipe;
import visual.dynamic.sampled.Screen;
import visual.statik.SimpleContent;
import visual.statik.sampled.ContentFactory;

/**
 * An application that illustrates the use of transitions
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class TransitionDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new TransitionDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public TransitionDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder                        finder;       
    VisualizationView                     component;

    Screen screen = new Screen(24);
    screen.setRepeating(true);
    component = screen.getView();       
    component.setBounds(0,0,320,240);       

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(component);

    finder = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory factory = new ContentFactory(finder);

    SimpleContent[] frames = null;       
    String[] names = finder.loadResourceNames("scribble.txt");
    frames = factory.createContents(names, 4);

    Fade fadeIn  = new Fade(Fade.FADE_IN,  0, 24);
    screen.addTransition(fadeIn);

    RectangleWipe wipe = new RectangleWipe(72, 24);
    screen.addTransition(wipe);             

    Fade fadeOut = new Fade(Fade.FADE_OUT, 126, 24);
    screen.addTransition(fadeOut);

    for (int i=0; i<frames.length; i++)
    {
      screen.add(frames[i]);
    }

    screen.start();       
  }
}
