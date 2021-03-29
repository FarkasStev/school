import java.awt.*;
import javax.swing.*;

import app.*;
import io.ResourceFinder;
import visual.VisualizationView;
import visual.dynamic.sampled.Fade;
import visual.dynamic.sampled.Dissolve;
import visual.dynamic.sampled.Screen;
import visual.dynamic.sampled.TransformableContentSuperimposition;
import visual.statik.TransformableContent;

/**
 * An example that illustrates the use of the
 * TransformableContentSuperimposition class.
 *
 * It includes transitions as well to show that the two
 * can be used together.
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */

public class SuperimpositionDemo extends JApplication
{
  private String logoName;

  public static void main(String[] args)
  {
    JApplication demo = new SuperimpositionDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public SuperimpositionDemo(String[] args, int width, int height)
  {
    super(args, width, height);
    if (args.length > 0) logoName = args[0];
    else                 logoName = "multimedia.png";
  }


  public void init()
  {
    ResourceFinder                        finder;       
    TransformableContent                  logo;       
    TransformableContent[]                frames;       
    TransformableContentSuperimposition   si;
    VisualizationView                     component;       

    visual.statik.sampled.ContentFactory  factory;

    Screen screen = new Screen(4);
    screen.setRepeating(true);
    component = screen.getView();       
    component.setBackground(Color.WHITE);
    component.setBounds(0,0,200,200);       

    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(component);

    // Setup the animation
    frames = null;       
    finder = ResourceFinder.createInstance(resources.Marker.class);
    String[] names = finder.loadResourceNames("solidclock.txt");
    factory = new visual.statik.sampled.ContentFactory(finder);
    frames = factory.createContents(names, 4);

    for (int i=0; i<frames.length; i++)
    {
      screen.add(frames[i]);
    }

    Fade fadeIn  = new Fade(Fade.FADE_IN,   0, 6);
    Fade fadeOut = new Fade(Fade.FADE_OUT, 30, 6);

    Dissolve dissolve = new Dissolve(20, 4);

    screen.addTransition(fadeIn);

    screen.addTransition(dissolve);

    screen.addTransition(fadeOut);

    // Setup the Superimposition
    logo = null;       
    if (logoName.equalsIgnoreCase("buzzy"))
    {
      logo = new BuzzyStanding();
    }
    else 
    {
      logo = factory.createContent(logoName, 4);
    }

    si = new TransformableContentSuperimposition(
        logo, 
        5, 10, SwingConstants.SOUTH_EAST);

    screen.addSuperimposition(si);
    screen.start();       
  }
}
