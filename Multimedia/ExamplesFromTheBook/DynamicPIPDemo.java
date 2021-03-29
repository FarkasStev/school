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
 */
public class DynamicPIPDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new  DynamicPIPDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public  DynamicPIPDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  public void init()
  {
    ResourceFinder                finder;       
    Stage                         stage1, stage2;
    VisualizationView             view1, view2;

    finder  = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory factory = new ContentFactory(finder);       

    // The Stage for Buzzy
    stage1 = new Stage(10);
    stage1.setBackground(Color.white);
    stage1.setRestartTime(7000);
    view1 = stage1.getView();
    view1.setRenderer(new ScaledVisualizationRenderer(
        view1.getRenderer(),
        640.0, 480.0));       
    view1.setBounds(0,0,640,480);       

    Content mars = factory.createContent("mars.png");
    stage1.add(mars);

    // Buzzy
    BuzzyOnMars buzzy = new BuzzyOnMars();
    stage1.add(buzzy);

    // The stage for the airplane
    stage2 = new Stage(10);
    view2 = stage2.getView();
    view2.setRenderer(new ScaledVisualizationRenderer(
        view2.getRenderer(),
        640.0, 480.0));
    view2.setBounds(50,50,160,120);     
    view2.setSize(160,120);       
    view2.setBackground(Color.white);
    stage2.setRestartTime(12000);

    // The Airplane
    Airplane plane = new Airplane();
    stage2.add(plane);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(view2);
    contentPane.add(view1);

    stage1.start();
    stage2.start();
  }
}
