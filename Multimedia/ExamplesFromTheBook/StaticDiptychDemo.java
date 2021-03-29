import java.awt.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example that uses multiple views
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class   StaticDiptychDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new StaticDiptychDemo(args, 320, 480);
    invokeInEventDispatchThread(demo);
  }

  public StaticDiptychDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder                 finder;       
    VisualizationRenderer          renderer2;       
    VisualizationView              view1, view2;       

    finder  = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory factory = new ContentFactory(finder);       

    Visualization visualization = new Stage(10);
    visualization.setBackground(Color.WHITE);
    view1 = visualization.getView();
    view1.setRenderer(new PartialVisualizationRenderer(
        view1.getRenderer(),
        0.0, 0.0));
    view1.setBounds(0,0,320,480);       

    renderer2 = new PartialVisualizationRenderer(
        new PlainVisualizationRenderer(), 320.0, 0.0);
    view2 = new VisualizationView(visualization, renderer2);
    view2.setBounds(0,0,320,480);       
    visualization.addView(view2);

    Content mars = factory.createContent("mars.png");
    visualization.add(mars);

    
    // The content pane for the main window
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view1);

    // The content pane for the other window
    JFrame window2 = new JFrame();
    window2.setSize(320,480);       
    contentPane = (JPanel)window2.getContentPane();
    contentPane.add(view2);
    window2.setVisible(true);
  }
}
