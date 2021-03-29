import java.awt.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.*;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of described dynamics (a.k.a., "sprite animation")
 * that uses a DescribedSprite, rotation tweening, and location tweening
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BuzzyOnMarsDemo extends JApplication
{
  private Stage                     stage;


  public static void main(String[] args)
  {
    JApplication demo = new BuzzyOnMarsDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }


  public BuzzyOnMarsDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  /**
   * The entry point
   */
  public void init()
  {
    ResourceFinder finder  = ResourceFinder.createInstance(new resources.Marker());       
    ContentFactory factory = new ContentFactory(finder);       


    // The Stage
    stage = new Stage(10);
    stage.setBackground(Color.WHITE);
    stage.setRestartTime(7000);
    VisualizationView stageView = stage.getView();       
    stageView.setBounds(0,0, width, height);       
    Content mars = factory.createContent("mars.png");
    stage.add(mars);

    // The Sprite 
    BuzzyOnMars buzzy = new BuzzyOnMars();
    stage.add(buzzy);


    JPanel contentPane = (JPanel)getContentPane();
    contentPane.setLayout(null);
    contentPane.add(stageView);
  }

  public void start()
  {
    stage.start();
  }

  public void stop()
  {
    stage.stop();
  }
}
