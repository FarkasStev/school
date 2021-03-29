//[skeleton1
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.statik.sampled.*;

/**
 * An example that illustrates the use of sampled and described
 * content in the same visualization
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class VisualizationDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new VisualizationDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public VisualizationDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    BufferedImageOpFactory        opFactory;       
    ContentFactory                factory;
    ResourceFinder                finder;       
    Visualization                 visualization;
    VisualizationView             view;

    //[woods
    finder  = ResourceFinder.createInstance(resources.Marker.class);       
    factory   = new ContentFactory(finder);       
    opFactory = BufferedImageOpFactory.createFactory();

    Content woods  = factory.createContent("woods.png", 3);
    woods.setLocation(0,0);
    woods.setBufferedImageOp(opFactory.createBlurOp(3));
    //]woods

    //[buzzy
    FancyBuzzy buzzy  = new FancyBuzzy();
    buzzy.setLocation(200, 318);       
    //]buzzy

    //[visualization
    visualization = new Visualization();
    view          = visualization.getView();

    view.setBounds(0,0,471,418);     
    view.setSize(471,418);       

    visualization.add(woods);
    visualization.add(buzzy);
    //]visualization

    //[contentPane
    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view);
    //]contentPane
  }
}
