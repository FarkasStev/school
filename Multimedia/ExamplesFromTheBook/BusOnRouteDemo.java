import java.awt.*;
import javax.swing.*;

import app.*;
import io.*;
import visual.VisualizationView;
import visual.dynamic.described.*;
import visual.statik.sampled.*;

/**
 * An example of "sprite animation" that uses: a SampledSprite, location
 * tweening, and aligned rotation tweening
 *
 * @author  Prof. David Bernstein, James Madison University
 * @version 1.0
 */
public class BusOnRouteDemo extends JApplication
{
  public static void main(String[] args)
  {
    JApplication demo = new BusOnRouteDemo(args, 640, 480);
    invokeInEventDispatchThread(demo);
  }

  public BusOnRouteDemo(String[] args, int width, int height)
  {
    super(args, width, height);
  }

  /**
   * The entry point
   */
  public void init()
  {
    ResourceFinder            finder;       
    finder  = ResourceFinder.createInstance(resources.Marker.class);       
    ContentFactory factory = new ContentFactory(finder);       

    // The Stage
    Stage stage = new Stage(50);
    stage.getView().setBackground(Color.WHITE);
    Content map = factory.createContent("map.png");
    stage.setRestartTime(30000);
    VisualizationView stageView = stage.getView();
    stageView.setBounds(0,0,640,480);

    // The static background
    stage.add(map);

    // The Sprite 
    BusOnRoute bus = new BusOnRoute();
    stage.add(bus);

    // The content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.add(stageView);

    stage.start();
  }
}
