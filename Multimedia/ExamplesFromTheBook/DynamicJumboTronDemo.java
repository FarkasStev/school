import java.awt.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example that illustrates the use of multiple views
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 *
 */
public class DynamicJumboTronDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new DynamicJumboTronDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public DynamicJumboTronDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder                 finder;       
    ScaledVisualizationRenderer    renderer2;       
    VisualizationView              view1, view2;       

    finder  = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory factory = new ContentFactory(finder);       

    // The Stage for Buzzy
    Stage stage = new Stage(10);
    stage.setBackground(Color.white);
    stage.setRestartTime(7000);
    view1 = stage.getView();
    view1.setBounds(0,0,640,480);       

    renderer2 = new ScaledVisualizationRenderer(
        new PlainVisualizationRenderer(), 640.0, 480.0);
    view2 = new VisualizationView(stage, renderer2);       
    view2.setBounds(50,50,160,120);       
    stage.addView(view2);

    Content mars = factory.createContent("mars.png");
    stage.add(mars);

    // Buzzy
    BuzzyOnMars buzzy = new BuzzyOnMars();
    stage.add(buzzy);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view2);
    contentPane.add(view1);

    stage.start();
  }
}
