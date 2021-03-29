import javax.swing.*;

import app.*;
import visual.*;
import io.ResourceFinder;
import visual.dynamic.*;
import visual.dynamic.sampled.*;
import visual.statik.*;
import visual.statik.sampled.*;

/**
 * An example that illustrates the use of multiple views
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class   SpecialEffectsPIPDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new SpecialEffectsPIPDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public SpecialEffectsPIPDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }


  public void init()
  {
    ResourceFinder                        finder;       
    VisualizationView                     view1, view2;

    finder = ResourceFinder.createInstance(resources.Marker.class);

    Screen screen1 = new Screen(4);
    screen1.setRepeating(true);

    view1 = screen1.getView();       
    view1.setRenderer(new ScaledVisualizationRenderer(view1.getRenderer(),
        200.0, 200.0));       
    view1.setBounds(200,10,100,100);       
    view1.setSize(100,100);       

    String[] names = finder.loadResourceNames("solidclock.txt");
    ContentFactory factory = new ContentFactory(finder);          

    SimpleContent[] frames1 = factory.createContents(names, 4);
    for (int i=0; i<frames1.length; i++)
    {
      screen1.add(frames1[i]);          
    }

    SpecialEffectsScreen screen2 = new SpecialEffectsScreen(20);
    screen2.setRepeating(true);

    view2 = screen2.getView();       
    view2.setBounds(0,0,320,240);     

    names = finder.loadResourceNames("scribble.txt");
    SimpleContent[] frames2 = factory.createContents(names, 4);

    for (int i=0; i<frames2.length; i++)
    {
      screen2.add(frames2[i]);          
    }

    Bee bee = new Bee();
    screen2.add(bee);       

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view1);
    contentPane.add(view2);

    screen2.start();
    screen1.start();
  }
}
