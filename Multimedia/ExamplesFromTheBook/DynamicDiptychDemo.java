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
 *
 */
public class DynamicDiptychDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new DynamicDiptychDemo(args, 320, 480);
    invokeInEventDispatchThread(demo);
  }
  
  public DynamicDiptychDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }
 
  public void init()
    {
       ContentFactory                 factory;
       ResourceFinder                 finder;       
       VisualizationRenderer          renderer2;       

       // The Stage for Buzzy
       Stage stage = new Stage(10);
       stage.setBackground(Color.WHITE);
       stage.setRestartTime(7000);
       VisualizationView view1 = stage.getView();
       view1.setRenderer(new PartialVisualizationRenderer(
                                view1.getRenderer(),
                                0.0, 0.0));
       view1.setBounds(0,0,320,480);       

       renderer2 = new PartialVisualizationRenderer(
                       new PlainVisualizationRenderer(), 320.0, 0.0);
       VisualizationView view2 = new VisualizationView(stage, renderer2);
       view2.setBounds(0,0,320,480);       
       stage.addView(view2);

       finder = ResourceFinder.createInstance(resources.Marker.class);       
       factory = new ContentFactory(finder);       
       
       Content mars = factory.createContent("mars.png");
       stage.add(mars);

       // Buzzy
       BuzzyOnMars buzzy = new BuzzyOnMars();
       stage.add(buzzy);

       // The content pane for the main window
       JPanel contentPane = (JPanel)getContentPane();
       contentPane.add(view1);

       // The content pane for the other window
       JFrame window2 = new JFrame();
       window2.setSize(320,480);   
       window2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       contentPane = (JPanel)window2.getContentPane();
       contentPane.add(view2);
       window2.setVisible(true);

       stage.start();
    }
}
